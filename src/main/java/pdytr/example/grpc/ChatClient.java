package pdytr.example.grpc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.protobuf.ProtocolStringList;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import pdytr.example.grpc.Chat.ClientRequest;
import pdytr.example.grpc.Chat.HistoryRequest;
import pdytr.example.grpc.Chat.HistoryResponse;
import pdytr.example.grpc.Chat.MessageRequest;
import pdytr.example.grpc.Chat.MessageResponse;
import pdytr.example.grpc.Chat.PoolRequest;
import pdytr.example.grpc.Chat.ServerResponse;

public class ChatClient {

    private static volatile boolean running = true;
    private static boolean voluntaryDisconnect = false;
    private static String clientName;
    private static int msgSize;
    private static List<String> messages;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Falta el nombre del cliente");
            return;
        }
        messages = new ArrayList<>();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext(true)
                .build();

        ChatServiceGrpc.ChatServiceBlockingStub stub = ChatServiceGrpc.newBlockingStub(channel);
        clientName = args[0];
        msgSize = Integer.parseInt(args[1]);
        // Scanner scanner = new Scanner(System.in);

        // // System.out.print("Ingresa tu nombre: ");
        // clientName = scanner.nextLine();
        Thread messageThread = new Thread(() -> checkIncomingMessages(stub));
        connectToServer(stub, clientName);
        messageThread.start(); // hilo que escucha los mensajes de otros usuarios

        // Hook para capturar la desconexión involuntaria y limpiar recursos
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!voluntaryDisconnect) { // Solo desconecta si fue involuntario
                running = false; // Detener el ciclo while
                messageThread.interrupt(); // Interrumpir el hilo si está durmiendo
                disconnectFromServer(stub, clientName); // Desconexión limpia al cerrar
                channel.shutdown();
                // System.out.println("Aplicación terminada, limpiando recursos...");
            }
        }));
        // Interacción del cliente
        // System.out.println("Ingresa un mensaje (o 'exit' para salir o /historial para el historial): ");
        for (int i = 0; i < 100; i++) {
            {
                String messageContent = new String(new char[msgSize]).replace("\0", "a");
                sendMessage(stub, clientName, messageContent);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        //esperar 3 segundos para asegurar que llega escribir el csv
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.print("Cerrando");
        
        voluntaryDisconnect = true;
        running = false; // Detener el ciclo while
        messageThread.interrupt(); // Interrumpir el hilo si está durmiendo
        disconnectFromServer(stub, clientName); // Desconexión limpia al cerrar
        channel.shutdown();
    }

    private static void connectToServer(ChatServiceGrpc.ChatServiceBlockingStub stub, String clientName) {
        ClientRequest request = ClientRequest.newBuilder()
                .setClientName(clientName)
                .build();

        ServerResponse response = stub.connect(request);
        // System.out.println("Respuesta del servidor: " + response.getMessage());
        if (response.getMessage().contains("ya está conectado")) {
            // System.out.print("Ingresa tu nombre: ");
            Scanner scanner = new Scanner(System.in);
            clientName = scanner.nextLine();
            connectToServer(stub, clientName); // Reintentar la conexión
        }
    }

    public static void printLatestMessages(String messages) {
        // System.out.println(messages);
    }

    private static void checkIncomingMessages(ChatServiceGrpc.ChatServiceBlockingStub stub) {
        // System.out.println("Cree un hilo para escuchar mensajes");
        //Hear the server messages
        while (running) {
            PoolRequest request = PoolRequest.newBuilder()
                    .setClientName(clientName)
                    .setPoolSize(messages.size())
                    .build();
            try {
                MessageResponse response = stub.streamMessages(request);
                if (response.getMessage() != "") {
                    String[] cutted = response.getMessage().split("\n");
                    messages.addAll(Arrays.asList(cutted));

                    printLatestMessages(response.getMessage());
                }
            } catch (StatusRuntimeException e) {
                System.err.println("Cerrando hilo de escucha");
                break;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

    }

    private static void disconnectFromServer(ChatServiceGrpc.ChatServiceBlockingStub stub, String clientName) {
        ClientRequest request = ClientRequest.newBuilder()
                .setClientName(clientName)
                .build();

        ServerResponse response = stub.disconnect(request);
        // System.out.println("Respuesta del servidor: " + response.getMessage());
    }

    private static void sendMessage(ChatServiceGrpc.ChatServiceBlockingStub stub, String clientName, String messageContent) {
        MessageRequest request = MessageRequest.newBuilder()
                .setClientName(clientName)
                .setMessageContent(messageContent)
                .build();

        try {
            Instant start = Instant.now();
            MessageResponse response = stub.sendMessage(request);
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);

            // System.out.println(duration.toMillis() + "ms");
            writeCsv(duration);
            // System.out.println("Respuesta del servidor: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            System.err.println("Error al enviar el mensaje: " + e.getStatus());
        }
    }

    private static void writeCsv(Duration duration) {
        // Añadir linea al csv
        String filename = clientName + "_" + "size" + msgSize + "_tiempos.csv";

        // Abrir el archivo en modo de append (añadir al final)
        try (PrintWriter out = new PrintWriter(new FileOutputStream(filename, true))) {
            out.println(duration.toMillis() + "ms");
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se pudo crear el archivo " + filename);
        }
    }

    private static void requestHistory(ChatServiceGrpc.ChatServiceBlockingStub stub) {
        HistoryRequest request = HistoryRequest.newBuilder().build(); // Crear la solicitud

        try {
            HistoryResponse response = stub.getHistory(request); // Llamar al método getHistory
            // System.out.println("Historial de mensajes:");
            for (String message : response.getMessagesList()) {
                // System.out.println(message); // Imprimir cada mensaje del historial
            }
            makeTxt(response.getMessagesList());

        } catch (StatusRuntimeException e) {
            System.err.println("Error al solicitar el historial: " + e.getStatus());
        }
    }

    private static void makeTxt(ProtocolStringList messages) {
        String filename = clientName + "_historial.txt";
        try (PrintWriter out = new PrintWriter(filename)) {
            for (String message : messages) {
                out.println(message);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se pudo crear el archivo " + filename);
        }
    }
}
