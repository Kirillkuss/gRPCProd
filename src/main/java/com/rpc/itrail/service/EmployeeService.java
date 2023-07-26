package com.rpc.itrail.service;

import com.rpc.itrail.EmployeeRequest;
import com.rpc.itrail.EmployeeResponse;
import com.rpc.itrail.EmployeeServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    public void getEmployee( EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
        //call repository to load the data from database
        //we have added static data for example
        log.info( "Method getEmployee success");
        EmployeeResponse empResp = EmployeeResponse.newBuilder()
                                                   .setEmpId( request.getEmpId() )
                                                   .setName( request.getName() )
                                                   .build();
        //set the response object
        responseObserver.onNext(empResp);
        //mark process is completed
        responseObserver.onCompleted();
        
    }
}
