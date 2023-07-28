package com.rpc.itrail.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.rpc.itrail.DocumentRequest;
import com.rpc.itrail.DocumentResponse;
import com.rpc.itrail.GDocumentServiceGrpc;
import com.rpc.itrail.ListDocument;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class DocumentService {
    
    @GrpcClient("gRPCRrod")
    private GDocumentServiceGrpc.GDocumentServiceBlockingStub documentServiceBlockingStub;

    public BigInteger findById( ){
        DocumentResponse documentResponse = documentServiceBlockingStub.findById( DocumentRequest.newBuilder().setIdDocument( 1L ).build());
        return new BigInteger(  documentResponse.toString() );
    }

    public ListDocument findAll( DocumentRequest documentRequest ){
        return documentServiceBlockingStub.findAll( documentRequest );
    }

    public DocumentResponse addDocument( DocumentResponse documentRequest ){
        return documentServiceBlockingStub.addDocument( documentRequest );
    }


}
