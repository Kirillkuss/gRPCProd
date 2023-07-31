package com.rpc.itrail.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.rpc.itrail.DocumentRequest;
import com.rpc.itrail.DocumentResponse;
import com.rpc.itrail.GDocumentServiceGrpc;
import com.rpc.itrail.ListDocument;
import com.rpc.itrail.model.Document;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Slf4j
@Service
public class DocumentService {
    
    @GrpcClient("gRPCRrod")
    private GDocumentServiceGrpc.GDocumentServiceBlockingStub documentServiceBlockingStub;
    /**
     * Поиск по ИД
     * 
     * @param id - ИД документа
     * @return Document
     */
    public Document findById(  Long id ){
        DocumentResponse documentResponse = documentServiceBlockingStub.findById( DocumentRequest.newBuilder()
                                                                                                 .setIdDocument( id )
                                                                                                 .build() );
        log.info( " DocumentService  - findById ");                                                                                         
        return new Document( documentResponse.getIdDocument(),
                             documentResponse.getTypeDocument(),
                             documentResponse.getSeria(),
                             documentResponse.getNumar(),
                             documentResponse.getSnils(),
                             documentResponse.getPolis() );
    }
    /**
     * Список всех док
     * 
     * @return  List<Document>
     */
    public List<Document> findAll(){
        ListDocument listDocument = documentServiceBlockingStub.findAll( DocumentRequest.newBuilder().build() );
        Iterator iterator = listDocument.getDocumentResponseList().stream().iterator();
        List<Document> list = new ArrayList<>();
        while( iterator.hasNext() ){
            DocumentResponse documentResponse = ( DocumentResponse ) iterator.next();
            list.add( new Document( documentResponse.getIdDocument(),
                                    documentResponse.getTypeDocument(),
                                    documentResponse.getSeria(),
                                    documentResponse.getNumar(),
                                    documentResponse.getSnils(),
                                    documentResponse.getPolis() ));
        }
        log.info( " DocumentService  - findAll "); 
        return list;
    }
    /**
     * Добавить док
     * 
     * @param document - входной параметр
     * @return Document
     */
    public Document addDocument( Document document ){
        DocumentResponse documentResponse = documentServiceBlockingStub.addDocument( DocumentResponse.newBuilder()
                                                                                                     .setIdDocument( document.getIdDocument() )
                                                                                                     .setTypeDocument( document.getTypeDocument() )
                                                                                                     .setSeria( document.getSeria() )
                                                                                                     .setNumar( document.getNumar())
                                                                                                     .setSnils( document.getSnils() )
                                                                                                     .setPolis( document.getPolis() )
                                                                                                     .build() );
        log.info( " DocumentService  - addDocument "); 
        return new Document( documentResponse.getIdDocument(),
                             documentResponse.getTypeDocument(),
                             documentResponse.getSeria(),
                             documentResponse.getNumar(),
                             documentResponse.getSnils(),
                             documentResponse.getPolis() );
    }


}
