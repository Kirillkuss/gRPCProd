package com.rpc.itrail.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import com.rpc.itrail.Document;
import com.rpc.itrail.ListPatient;
import com.rpc.itrail.PatientCreate;
import com.rpc.itrail.PatientRequest;
import com.rpc.itrail.PatientResponse;
import com.rpc.itrail.PatientServiceGrpc;
import com.rpc.itrail.PatientWithoutParams;
import com.rpc.itrail.model.Patient;
import com.rpc.itrail.repositories.DocumentRepository;
import com.rpc.itrail.repositories.PatientRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
/**
 * Сервис для работы с пациентами
 */
@Slf4j
@GrpcService
public class PatientService extends PatientServiceGrpc.PatientServiceImplBase {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DocumentRepository documentRepository;

    /**
     * Поиск пациента по ИД
     */
    public void findById( PatientRequest request, StreamObserver<PatientResponse> response ){
        try{
            Patient patient = patientRepository.findById( request.getIdPatient() )
                                               .orElseThrow( () ->new  NoSuchElementException("Пациента с таким ИД не существует" ));                              
            response.onNext( PatientResponse.newBuilder()
                                            .setIdPatient( patient.getIdPatient() )
                                            .setSurname( patient.getSurname() )
                                            .setName( patient.getName() )
                                            .setFullname( patient.getFullname() )
                                            .setAdderss( patient.getAddress() )
                                            .setGender( patient.getGender() )
                                            .setPhone( patient.getPhone() )
                                            .setDocument( Document.newBuilder()
                                                                  .setIdDocument( patient.getDocument().getIdDocument() )
                                                                  .setTypeDocument( patient.getDocument().getTypeDocument() )
                                                                  .setNumar( patient.getDocument().getNumar() )
                                                                  .setSeria( patient.getDocument().getSeria() )
                                                                  .setSnils( patient.getDocument().getSnils() )
                                                                  .setPolis( patient.getDocument().getPolis() )
                                                                  .build())
                                            .build() );
            response.onCompleted();
            log.info( "Find patient by ID patient");                                
        }catch( Exception ex ){
            log.error( ex.getMessage() );
            response.onError( Status.NOT_FOUND.withDescription( ex.getMessage() ).asException());
        }
    }
    /**
     * Получение списка всех пациентов
     */
    public void findAll( PatientWithoutParams request, StreamObserver<ListPatient> response){
        try{
            Iterator iterator = patientRepository.findAll().iterator();
            List<PatientResponse> listPatientResponses = new ArrayList<>();
            while( iterator.hasNext() ){
                Patient patient = (Patient ) iterator.next();
                listPatientResponses.add(  PatientResponse.newBuilder()
                                            .setIdPatient( patient.getIdPatient() )
                                            .setSurname( patient.getSurname() )
                                            .setName( patient.getName() )
                                            .setFullname( patient.getFullname() )
                                            .setAdderss( patient.getAddress() )
                                            .setGender( patient.getGender() )
                                            .setPhone( patient.getPhone() )
                                            .setDocument( Document.newBuilder()
                                                                  .setIdDocument( patient.getDocument().getIdDocument() )
                                                                  .setTypeDocument( patient.getDocument().getTypeDocument() )
                                                                  .setNumar( patient.getDocument().getNumar() )
                                                                  .setSeria( patient.getDocument().getSeria() )
                                                                  .setSnils( patient.getDocument().getSnils() )
                                                                  .setPolis( patient.getDocument().getPolis() )
                                                                  .build())
                                            .build() );

            }
            response.onNext( ListPatient.newBuilder()
                                        .addAllPatientResponse( listPatientResponses )
                                        .build() );
            response.onCompleted();
            log.info( "Find All patients" ); 
        }catch( Exception ex ) {
            log.error( ex.getMessage() );
            response.onError( Status.NOT_FOUND.withDescription( ex.getMessage() ).asException());
        }
    }

    /**
     * Добавить пациента
     */
    public void addPatient( PatientCreate request, StreamObserver<PatientResponse> response ){
        try{
            com.rpc.itrail.model.Document document = documentRepository.findById( request.getIdDocument() )
                                                                       .orElseThrow( () -> new NoSuchElementException( "Документа с таким ИД не существует, укажите другой") );
            if( patientRepository.findPatientByIdDocument( request.getIdDocument() ).isPresent() ) throw new IllegalArgumentException( "Значение 'idDocument' не уникально ");                                                    
            if( patientRepository.findById( request.getIdPatient() ).isPresent() ) throw new IllegalArgumentException( "Значение 'idPatient' не уникально ");
            if( patientRepository.findByPhone( request.getPhone() ).isPresent() ) throw new IllegalArgumentException( "Значение 'phone' пациента не уникально ");
            Patient patient =  patientRepository.save( new Patient( request.getIdPatient(),
                                                                    request.getSurname(),
                                                                    request.getName(),
                                                                    request.getFullname(),
                                                                    request.getGender(),
                                                                    request.getPhone(),
                                                                    request.getAdderss(),
                                                                    document ) );
            response.onNext( PatientResponse.newBuilder()
                                            .setIdPatient( patient.getIdPatient() )
                                            .setSurname( patient.getSurname() )
                                            .setName( patient.getName() )
                                            .setFullname( patient.getFullname() )
                                            .setAdderss( patient.getAddress() )
                                            .setGender( patient.getGender() )
                                            .setPhone( patient.getPhone() )
                                            .setDocument( Document.newBuilder()
                                                                  .setIdDocument( patient.getDocument().getIdDocument() )
                                                                  .setTypeDocument( patient.getDocument().getTypeDocument() )
                                                                  .setNumar( patient.getDocument().getNumar() )
                                                                  .setSeria( patient.getDocument().getSeria() )
                                                                  .setSnils( patient.getDocument().getSnils() )
                                                                  .setPolis( patient.getDocument().getPolis() )
                                                                  .build())
                                            .build() );  
            response.onCompleted();                                                                                      
            log.info( "Added patient" ); 
        }catch( Exception ex ){
            ex.printStackTrace( System.err );
            log.error( ex.getMessage() );
            response.onError( Status.INVALID_ARGUMENT.withDescription( ex.getMessage() ).asException());
        }                             

    }

    
}
