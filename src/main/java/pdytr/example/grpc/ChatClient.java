package pdytr.example.grpc;

import java.util.Scanner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import pdytr.example.grpc.Chat.ClientRequest;
import pdytr.example.grpc.Chat.HistoryRequest;
import pdytr.example.grpc.Chat.HistoryResponse;
import pdytr.example.grpc.Chat.MessageRequest;
import pdytr.example.grpc.Chat.MessageResponse;
import pdytr.example.grpc.Chat.ServerResponse;

public class ChatClient {

    private static boolean voluntaryDisconnect = false;

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext(true)
                .build();

        ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(channel);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String clientName = scanner.nextLine();
        connectToServer(stub, clientName);

        // Hook para capturar la desconexión involuntaria
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!voluntaryDisconnect) { // Solo desconecta si fue involuntario
                disconnectFromServer(stub, clientName); // Desconexión limpia al cerrar
                channel.shutdown();
            }
        }));

        // Interacción del cliente
        while (true) {
            System.out.print("Ingresa un mensaje (o 'exit' para salir o /historial para el historial): ");
            String messageContent = scanner.nextLine();
            if (messageContent.equalsIgnoreCase("exit")) {
                voluntaryDisconnect = true;
                disconnectFromServer(stub, clientName);
                break;
            } else if (messageContent.equalsIgnoreCase("/historial")) {
                requestHistory(stub);
            } else {
                sendMessage(stub, clientName, messageContent);
            }
        }

        channel.shutdown();
        scanner.close();
    }

    private static void connectToServer(ChatServiceGrpc.ChatServiceBlockingStub stub, String clientName) {
        ClientRequest request = ClientRequest.newBuilder()
                .setClientName(clientName)
                .build();

        ServerResponse response = stub.connect(request);
        System.out.println("Respuesta del servidor: " + response.getMessage());
        if (response.getMessage().contains("ya está conectado")) {
            System.out.print("Ingresa tu nombre: ");
            Scanner scanner = new Scanner(System.in);
            clientName = scanner.nextLine();
            connectToServer(stub, clientName); // Reintentar la conexión
        }
    }

    private static void disconnectFromServer(ChatServiceGrpc.ChatServiceBlockingStub stub, String clientName) {
        ClientRequest request = ClientRequest.newBuilder()
                .setClientName(clientName)
                .build();

        ServerResponse response = stub.disconnect(request);
        System.out.println("Respuesta del servidor: " + response.getMessage());
    }

    private static void sendMessage(ChatServiceGrpc.ChatServiceBlockingStub stub, String clientName, String messageContent) {
        MessageRequest request = MessageRequest.newBuilder()
                .setClientName(clientName)
                .setMessageContent(messageContent)
                .build();

        try {
            MessageResponse response = stub.sendMessage(request);
            System.out.println("Respuesta del servidor: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            System.err.println("Error al enviar el mensaje: " + e.getStatus());
        }
    }

    private static void requestHistory(ChatServiceGrpc.ChatServiceBlockingStub stub) {
        HistoryRequest request = HistoryRequest.newBuilder().build(); // Crear la solicitud

        try {
            HistoryResponse response = stub.getHistory(request); // Llamar al método getHistory
            System.out.println("Historial de mensajes:");
            for (String message : response.getMessagesList()) {
                System.out.println(message); // Imprimir cada mensaje del historial
                //TODO PASS TO PDF OR TXT
            }
        } catch (StatusRuntimeException e) {
            System.err.println("Error al solicitar el historial: " + e.getStatus());
        }
    }
}
