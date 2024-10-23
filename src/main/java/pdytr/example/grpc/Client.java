package pdytr.example.grpc;
import io.grpc.*;
import java.util.concurrent.TimeUnit;

public class Client
{
    public static void main( String[] args ) throws Exception
    {
        // Configuración del canal
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext(true)
                .build();

        // Crear el stub bloqueante
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        // Construir la solicitud
        GreetingServiceOuterClass.HelloRequest request =
                GreetingServiceOuterClass.HelloRequest.newBuilder()
                        .setName("Ray")
                        .build();

        try {
            // Establecer un DEADLINE de 1 segundo
            GreetingServiceOuterClass.HelloResponse response =
                    stub.withDeadlineAfter(1, TimeUnit.SECONDS)  // DEADLINE configurado
                            .greeting(request);
            System.out.println(response);
        } catch (StatusRuntimeException e) {
            System.out.println("Error: " + e.getStatus());
        }

        // Cerrar el canal
        channel.shutdownNow();
    }
}
