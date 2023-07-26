package com.rpc.itrail.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import com.rpc.itrail.ListPerson;
import com.rpc.itrail.PersonRequest;
import com.rpc.itrail.PersonResponse;
import com.rpc.itrail.PersonServiceGrpc;
import com.rpc.itrail.model.Person;
import com.rpc.itrail.repositories.PersonRepository;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
/**
 * Этот сервис предназначен для работы с 
 * 
 */
@Slf4j
@GrpcService
public class PersonService extends PersonServiceGrpc.PersonServiceImplBase {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Find Person by ID
     */
    public void findById( PersonRequest request, StreamObserver<PersonResponse> response ){
        log.info( "Find by ID person ", PersonService.class);
        Person person = personRepository.findById( request.getId() ).orElseThrow(() ->new  NoSuchElementException("Not found Person by Id"));
        response.onNext( PersonResponse.newBuilder()
                                       .setId( person.getId() )
                                       .setLogin( person.getLogin() )
                                       .setName( person.getName() )
                                       .setPhone( person.getPhone() )
                                       .setWallet( person.getWallet().longValue() )
                                       .build() );    
        response.onCompleted();
    }

    /**
     * Find all Persons
     */
    public void findAll( PersonRequest request, StreamObserver<ListPerson> response ){
        log.info( "Find All persons ", PersonService.class);
        Iterator iterator = personRepository.findAll().iterator();
        List<PersonResponse> listPersonResponse = new ArrayList<>();
        while ( iterator.hasNext() ){
            Person person = ( Person ) iterator.next();
            listPersonResponse.add( PersonResponse.newBuilder()
                                                  .setId( person.getId() )
                                                  .setLogin( person.getLogin() )
                                                  .setName( person.getName() )
                                                  .setPhone( person.getPhone() )
                                                  .setWallet( person.getWallet().longValue() )
                                                  .build() );   
        } 
        response.onNext( ListPerson.newBuilder()
                                   .addAllPersonResponse( listPersonResponse )
                                   .build() );                  
        response.onCompleted();
    }
    
}
