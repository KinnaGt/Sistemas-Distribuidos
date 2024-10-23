package pdytr.example.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: chat.proto")
public final class ChatServiceGrpc {

  private ChatServiceGrpc() {}

  public static final String SERVICE_NAME = "chat.ChatService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<pdytr.example.grpc.Chat.ClientRequest,
      pdytr.example.grpc.Chat.ServerResponse> METHOD_CONNECT =
      io.grpc.MethodDescriptor.<pdytr.example.grpc.Chat.ClientRequest, pdytr.example.grpc.Chat.ServerResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "chat.ChatService", "Connect"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.ClientRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.ServerResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("Connect"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<pdytr.example.grpc.Chat.ClientRequest,
      pdytr.example.grpc.Chat.ServerResponse> METHOD_DISCONNECT =
      io.grpc.MethodDescriptor.<pdytr.example.grpc.Chat.ClientRequest, pdytr.example.grpc.Chat.ServerResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "chat.ChatService", "Disconnect"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.ClientRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.ServerResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("Disconnect"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<pdytr.example.grpc.Chat.MessageRequest,
      pdytr.example.grpc.Chat.MessageResponse> METHOD_SEND_MESSAGE =
      io.grpc.MethodDescriptor.<pdytr.example.grpc.Chat.MessageRequest, pdytr.example.grpc.Chat.MessageResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "chat.ChatService", "SendMessage"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.MessageRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.MessageResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("SendMessage"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<pdytr.example.grpc.Chat.HistoryRequest,
      pdytr.example.grpc.Chat.HistoryResponse> METHOD_GET_HISTORY =
      io.grpc.MethodDescriptor.<pdytr.example.grpc.Chat.HistoryRequest, pdytr.example.grpc.Chat.HistoryResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "chat.ChatService", "GetHistory"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.HistoryRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.HistoryResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("GetHistory"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<pdytr.example.grpc.Chat.PoolRequest,
      pdytr.example.grpc.Chat.MessageResponse> METHOD_STREAM_MESSAGES =
      io.grpc.MethodDescriptor.<pdytr.example.grpc.Chat.PoolRequest, pdytr.example.grpc.Chat.MessageResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "chat.ChatService", "StreamMessages"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.PoolRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              pdytr.example.grpc.Chat.MessageResponse.getDefaultInstance()))
          .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("StreamMessages"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatServiceStub newStub(io.grpc.Channel channel) {
    return new ChatServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ChatServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void connect(pdytr.example.grpc.Chat.ClientRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.ServerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CONNECT, responseObserver);
    }

    /**
     */
    public void disconnect(pdytr.example.grpc.Chat.ClientRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.ServerResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DISCONNECT, responseObserver);
    }

    /**
     */
    public void sendMessage(pdytr.example.grpc.Chat.MessageRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.MessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SEND_MESSAGE, responseObserver);
    }

    /**
     */
    public void getHistory(pdytr.example.grpc.Chat.HistoryRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.HistoryResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_HISTORY, responseObserver);
    }

    /**
     */
    public void streamMessages(pdytr.example.grpc.Chat.PoolRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.MessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_STREAM_MESSAGES, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_CONNECT,
            asyncUnaryCall(
              new MethodHandlers<
                pdytr.example.grpc.Chat.ClientRequest,
                pdytr.example.grpc.Chat.ServerResponse>(
                  this, METHODID_CONNECT)))
          .addMethod(
            METHOD_DISCONNECT,
            asyncUnaryCall(
              new MethodHandlers<
                pdytr.example.grpc.Chat.ClientRequest,
                pdytr.example.grpc.Chat.ServerResponse>(
                  this, METHODID_DISCONNECT)))
          .addMethod(
            METHOD_SEND_MESSAGE,
            asyncUnaryCall(
              new MethodHandlers<
                pdytr.example.grpc.Chat.MessageRequest,
                pdytr.example.grpc.Chat.MessageResponse>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            METHOD_GET_HISTORY,
            asyncUnaryCall(
              new MethodHandlers<
                pdytr.example.grpc.Chat.HistoryRequest,
                pdytr.example.grpc.Chat.HistoryResponse>(
                  this, METHODID_GET_HISTORY)))
          .addMethod(
            METHOD_STREAM_MESSAGES,
            asyncUnaryCall(
              new MethodHandlers<
                pdytr.example.grpc.Chat.PoolRequest,
                pdytr.example.grpc.Chat.MessageResponse>(
                  this, METHODID_STREAM_MESSAGES)))
          .build();
    }
  }

  /**
   */
  public static final class ChatServiceStub extends io.grpc.stub.AbstractStub<ChatServiceStub> {
    private ChatServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceStub(channel, callOptions);
    }

    /**
     */
    public void connect(pdytr.example.grpc.Chat.ClientRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.ServerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CONNECT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void disconnect(pdytr.example.grpc.Chat.ClientRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.ServerResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DISCONNECT, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMessage(pdytr.example.grpc.Chat.MessageRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.MessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SEND_MESSAGE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getHistory(pdytr.example.grpc.Chat.HistoryRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.HistoryResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_HISTORY, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void streamMessages(pdytr.example.grpc.Chat.PoolRequest request,
        io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.MessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_STREAM_MESSAGES, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChatServiceBlockingStub extends io.grpc.stub.AbstractStub<ChatServiceBlockingStub> {
    private ChatServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pdytr.example.grpc.Chat.ServerResponse connect(pdytr.example.grpc.Chat.ClientRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CONNECT, getCallOptions(), request);
    }

    /**
     */
    public pdytr.example.grpc.Chat.ServerResponse disconnect(pdytr.example.grpc.Chat.ClientRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DISCONNECT, getCallOptions(), request);
    }

    /**
     */
    public pdytr.example.grpc.Chat.MessageResponse sendMessage(pdytr.example.grpc.Chat.MessageRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SEND_MESSAGE, getCallOptions(), request);
    }

    /**
     */
    public pdytr.example.grpc.Chat.HistoryResponse getHistory(pdytr.example.grpc.Chat.HistoryRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_HISTORY, getCallOptions(), request);
    }

    /**
     */
    public pdytr.example.grpc.Chat.MessageResponse streamMessages(pdytr.example.grpc.Chat.PoolRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_STREAM_MESSAGES, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChatServiceFutureStub extends io.grpc.stub.AbstractStub<ChatServiceFutureStub> {
    private ChatServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pdytr.example.grpc.Chat.ServerResponse> connect(
        pdytr.example.grpc.Chat.ClientRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CONNECT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pdytr.example.grpc.Chat.ServerResponse> disconnect(
        pdytr.example.grpc.Chat.ClientRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DISCONNECT, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pdytr.example.grpc.Chat.MessageResponse> sendMessage(
        pdytr.example.grpc.Chat.MessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SEND_MESSAGE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pdytr.example.grpc.Chat.HistoryResponse> getHistory(
        pdytr.example.grpc.Chat.HistoryRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_HISTORY, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pdytr.example.grpc.Chat.MessageResponse> streamMessages(
        pdytr.example.grpc.Chat.PoolRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_STREAM_MESSAGES, getCallOptions()), request);
    }
  }

  private static final int METHODID_CONNECT = 0;
  private static final int METHODID_DISCONNECT = 1;
  private static final int METHODID_SEND_MESSAGE = 2;
  private static final int METHODID_GET_HISTORY = 3;
  private static final int METHODID_STREAM_MESSAGES = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CONNECT:
          serviceImpl.connect((pdytr.example.grpc.Chat.ClientRequest) request,
              (io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.ServerResponse>) responseObserver);
          break;
        case METHODID_DISCONNECT:
          serviceImpl.disconnect((pdytr.example.grpc.Chat.ClientRequest) request,
              (io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.ServerResponse>) responseObserver);
          break;
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((pdytr.example.grpc.Chat.MessageRequest) request,
              (io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.MessageResponse>) responseObserver);
          break;
        case METHODID_GET_HISTORY:
          serviceImpl.getHistory((pdytr.example.grpc.Chat.HistoryRequest) request,
              (io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.HistoryResponse>) responseObserver);
          break;
        case METHODID_STREAM_MESSAGES:
          serviceImpl.streamMessages((pdytr.example.grpc.Chat.PoolRequest) request,
              (io.grpc.stub.StreamObserver<pdytr.example.grpc.Chat.MessageResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pdytr.example.grpc.Chat.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatService");
    }
  }

  private static final class ChatServiceFileDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier {
    ChatServiceFileDescriptorSupplier() {}
  }

  private static final class ChatServiceMethodDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChatServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ChatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatServiceFileDescriptorSupplier())
              .addMethod(METHOD_CONNECT)
              .addMethod(METHOD_DISCONNECT)
              .addMethod(METHOD_SEND_MESSAGE)
              .addMethod(METHOD_GET_HISTORY)
              .addMethod(METHOD_STREAM_MESSAGES)
              .build();
        }
      }
    }
    return result;
  }
}
