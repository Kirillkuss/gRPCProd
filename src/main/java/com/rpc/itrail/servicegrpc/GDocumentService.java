package com.rpc.itrail.servicegrpc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import com.rpc.itrail.DocumentRequest;
import com.rpc.itrail.DocumentResponse;
import com.rpc.itrail.GDocumentServiceGrpc;
import com.rpc.itrail.ListDocument;
import com.rpc.itrail.model.Document;
import com.rpc.itrail.repositories.DocumentRepository;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class GDocumentService extends GDocumentServiceGrpc.GDocumentServiceImplBase {
    
    @Autowired
    private DocumentRepository documentRepository;

    public void findById( DocumentRequest request , StreamObserver<DocumentResponse> response ){
        Document document = documentRepository.findById( request.getIdDocument() )
                                              .orElseThrow( () -> new NoSuchElementException( "Документа с таким ИД не сущесутвет")) ;
        response.onNext( DocumentResponse.newBuilder()
                                         .setIdDocument( document.getIdDocument() )
                                         .setTypeDocument( document.getTypeDocument() )
                                         .setSeria( document.getSeria() )
                                         .setNumar( document.getNumar() )
                                         .setSnils( document.getSnils() )
                                         .setPolis( document.getPolis() )
                                         .build() );
        response.onCompleted();
        log.info( " GDocumentService - Find document by id ");
    }

    public void findAll( DocumentRequest request, StreamObserver<ListDocument> response ){
        Iterator iterator =  documentRepository.findAll().iterator();
        List<DocumentResponse> listDocumentResponse = new ArrayList<>();
        while( iterator.hasNext() ){
            Document document = ( Document ) iterator.next();
            listDocumentResponse.add( DocumentResponse.newBuilder()
                            .setIdDocument( document.getIdDocument() )
                            .setTypeDocument( document.getTypeDocument() )
                            .setSeria( document.getSeria() )
                            .setNumar( document.getNumar() )
                            .setSnils( document.getSnils() )
                            .setPolis( document.getPolis() )
                            .build());
        }
        response.onNext( ListDocument.newBuilder()
                                     .addAllDocumentResponse( listDocumentResponse )
                                     .build() );
        response.onCompleted();
        log.info( " GDocumentService - Find all documents ");
    }

    public void addDocument( DocumentResponse request , StreamObserver<DocumentResponse> response ){
        if  ( documentRepository.findById( request.getIdDocument() ).isPresent() ) throw new IllegalArgumentException( "Документ с таким ИД уже существует");
        if ( documentRepository.findByNumar( request.getNumar()).isPresent() ) throw new IllegalArgumentException( "Документ с таким номером документа уже существует");
        if ( documentRepository.findByPolis( request.getPolis()).isPresent() ) throw new IllegalArgumentException( "Документ с таким полисом уже существует");
        if ( documentRepository.findBySnils( request.getSnils()).isPresent() ) throw new IllegalArgumentException( "Документ с таким СНИЛСом уже существует");
        Document document = documentRepository.save( new Document( request.getIdDocument(),
                                                                   request.getTypeDocument(),
                                                                   request.getSeria(),
                                                                   request.getNumar(),
                                                                   request.getSnils(),
                                                                   request.getPolis() ));
        response.onNext( DocumentResponse.newBuilder()
                                         .setIdDocument( document.getIdDocument() )
                                         .setTypeDocument( document.getTypeDocument() )
                                         .setSeria( document.getSeria() )
                                         .setNumar( document.getNumar() )
                                         .setSnils( document.getSnils() )
                                         .setPolis( document.getPolis() )
                                         .build() );                                                       
        response.onCompleted();
        log.info( "GDocumentService - Added document");
    }

}
