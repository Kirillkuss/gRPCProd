package com.rpc.itrail.exception;

import io.grpc.protobuf.StatusProto;
import lombok.extern.slf4j.Slf4j;

import com.google.rpc.Code;
import com.google.rpc.Status;
import java.util.NoSuchElementException;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
/**
 * обработчик ошибок
 */
@Slf4j
@GrpcAdvice
public class GlobalExceptionHandler  {

    @GrpcExceptionHandler( NoSuchElementException.class )
    public StatusRuntimeException  handleValidationError( NoSuchElementException ex ){
    Status status = Status.newBuilder()
        .setCode( Code.NOT_FOUND.getNumber())
        .setMessage( ex.getMessage())
        .build();
    log.error( Code.NOT_FOUND.getNumber() + " " + ex.getMessage() );    
    return StatusProto.toStatusRuntimeException( status );
    }

    
    @GrpcExceptionHandler( IllegalArgumentException.class )
    public StatusRuntimeException  handleValidationError( IllegalArgumentException ex ){
    Status status = Status.newBuilder()
        .setCode( Code.INVALID_ARGUMENT.getNumber())
        .setMessage( ex.getMessage())
        .build();
    return StatusProto.toStatusRuntimeException( status );
    }

    @GrpcExceptionHandler( Exception.class )
    public StatusRuntimeException  handleValidationError( Exception ex ){
    Status status = Status.newBuilder()
        .setCode( Code.UNKNOWN.getNumber())
        .setMessage( ex.getMessage())
        .build();
    return StatusProto.toStatusRuntimeException( status );
    }
    
}
