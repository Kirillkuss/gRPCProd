package com.rpc.itrail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.rpc.itrail.response.BaseResponseError;

import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestGlobalExceptionHandler {

    @ExceptionHandler( Throwable.class )
    public ResponseEntity<BaseResponseError> errorBaseResponse( Throwable ex ){
        log.error( "ERROR >>>>> ", ex );
        return ResponseEntity.internalServerError()
                             .body( new BaseResponseError( HttpStatus.INTERNAL_SERVER_ERROR.value() , ex.getMessage() ));
    }

    @ExceptionHandler( StatusRuntimeException.class )
    public ResponseEntity<BaseResponseError> errorBaseResponse( StatusRuntimeException ex ){
        log.error( "ERROR >>>>> ", ex );
        return ResponseEntity.badRequest()
                             .body( new BaseResponseError( HttpStatus.BAD_REQUEST.value() , ex.getMessage() ));
    }
    
}
