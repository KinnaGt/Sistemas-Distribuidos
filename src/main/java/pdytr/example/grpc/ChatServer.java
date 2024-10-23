package pdytr.example.grpc;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import pdytr.example.grpc.Chat.ClientRequest;
import pdytr.example.grpc.Chat.HistoryRequest;
import pdytr.example.grpc.Chat.HistoryResponse;
import pdytr.example.grpc.Chat.MessageRequest;
import pdytr.example.grpc.Chat.MessageResponse;
import pdytr.example.grpc.Chat.ServerResponse;

public class ChatServer {

    // Lista para almacenar el historial de mensajes
    private static List<String> messageHistory = new ArrayList<>();
    // Hash of the connected clients
    private static HashMap<String, StreamObserver<ServerResponse>> clients = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051) // Puerto del servidor
                .addService(new ChatServiceImpl()) // Agregar implementación del servicio
                .build();

        server.start();
        System.out.println("Servidor iniciado en el puerto: " + 50051);
        server.awaitTermination();
    }

    // Implementación del servicio ChatService
    static class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {

        @Override
        public void connect(ClientRequest request, StreamObserver<ServerResponse> responseObserver) {
            String clientName = request.getClientName();
            String responseMessage = "Cliente " + clientName + " conectado.";
            System.out.println(responseMessage);

            if (clients.containsKey(clientName)) {
                responseMessage = "Cliente " + clientName + " ya está conectado utilice otro nombre.";
            } else {
                clients.put(clientName, responseObserver);
            }

            ServerResponse response = ServerResponse.newBuilder()
                    .setMessage(responseMessage)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void disconnect(ClientRequest request, StreamObserver<ServerResponse> responseObserver) {
            String clientName = request.getClientName();
            String responseMessage = "Cliente " + clientName + " desconectado.";
            System.out.println(responseMessage);

            ServerResponse response = ServerResponse.newBuilder()
                    .setMessage(responseMessage)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
            String clientName = request.getClientName();
            String messageContent = request.getMessageContent();

            // Crear el timestamp en el formato [yyyyMMdd - HH:mm:ss]
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd - HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter);
            String messageWithTimestamp = String.format("[%s] %s: %s", timestamp, clientName, messageContent);

            // Guardar el mensaje en el historial
            messageHistory.add(messageWithTimestamp);

            // Devolver la respuesta al cliente
            MessageResponse response = MessageResponse.newBuilder()
                    .setMessage("Mensaje recibido.")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void getHistory(HistoryRequest request, StreamObserver<HistoryResponse> responseObserver) {
            HistoryResponse.Builder responseBuilder = HistoryResponse.newBuilder();
            for (String message : messageHistory) {
                responseBuilder.addMessages(message);
            }

            responseObserver.onNext(responseBuilder.build());
            responseObserver.onCompleted();
        }
    }
}
