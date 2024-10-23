package pdytr.example.grpc;

import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        // Simular retraso para provocar timeout
        try {
            Thread.sleep(5000);  // Pausa de 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Construir la respuesta
        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
                .setGreeting("Hello there, " + request.getName())
                .build();

        // Enviar la respuesta
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
