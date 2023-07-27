package com.rpc.itrail.exception;

import java.util.NoSuchElementException;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

public class ExceptionHandler implements ServerInterceptor {
    
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        ServerCall.Listener<ReqT> listener = serverCallHandler.startCall(serverCall, metadata);
        return new ExceptionHandlingServerCallListener<>(listener, serverCall, metadata);
    }

    private class ExceptionHandlingServerCallListener<ReqT, RespT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {
        private ServerCall<ReqT, RespT> serverCall;
        private Metadata metadata;
        ExceptionHandlingServerCallListener(ServerCall.Listener<ReqT> listener, ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            super(listener);
            this.serverCall = serverCall;
            this.metadata = metadata;
        }

        @Override
        public void onHalfClose() {
            try {
                super.onHalfClose();
            } catch (Exception ex) {
                handleException(ex, serverCall, metadata);
                throw ex;
            }
        }

        @Override
        public void onReady() {
            try {
                super.onReady();
            } catch (Exception ex) {
                handleException(ex, serverCall, metadata);
                throw ex;
            }
        }

        private void handleException( Exception exception, ServerCall<ReqT, RespT> serverCall, Metadata metadata) {
            if (exception instanceof IllegalArgumentException) {
                serverCall.close( Status.INVALID_ARGUMENT.withDescription( "TEST 1 >>> " + exception.getMessage()), metadata);
            }if( exception instanceof NoSuchElementException){
                serverCall.close(Status.NOT_FOUND.withDescription( "TEST 2 >>> " + exception.getMessage()), metadata);
            } else {
                serverCall.close( Status.UNKNOWN.withDescription( "TEST 3 >>> " +  exception.getMessage()), metadata);
            }
        }
    }
}
