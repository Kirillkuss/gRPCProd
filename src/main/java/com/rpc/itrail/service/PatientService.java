package com.rpc.itrail.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.rpc.itrail.GPatientServiceGrpc;
import com.rpc.itrail.ListPatient;
import com.rpc.itrail.PatientCreate;
import com.rpc.itrail.PatientRequest;
import com.rpc.itrail.PatientResponse;
import com.rpc.itrail.PatientWithoutParams;
import com.rpc.itrail.model.Document;
import com.rpc.itrail.model.Patient;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Slf4j
@Service
public class PatientService {

    @GrpcClient("gRPCRrod")
    private GPatientServiceGrpc.GPatientServiceBlockingStub patientServiceBlockingStub;

    public Patient findById( Long id ){
        PatientResponse patientResponse = patientServiceBlockingStub.findById( PatientRequest.newBuilder()
                                                                                             .setIdPatient( id )
                                                                                             .build() );
        log.info( " PatientService - findById");
        return new Patient( patientResponse.getIdPatient(),
                            patientResponse.getSurname(),
                            patientResponse.getName(),
                            patientResponse.getFullname(),
                            patientResponse.getGender(),
                            patientResponse.getPhone(),
                            patientResponse.getAdderss(),
                            new Document( patientResponse.getDocument().getIdDocument(),
                                          patientResponse.getDocument().getTypeDocument(), 
                                          patientResponse.getDocument().getSeria(),
                                          patientResponse.getDocument().getNumar(),
                                          patientResponse.getDocument().getSnils(),
                                          patientResponse.getDocument().getPolis() ));
    }

    public List<Patient> findAll(){
        ListPatient listPatient = patientServiceBlockingStub.findAll( PatientWithoutParams.newBuilder()
                                                                                          .build() );
        Iterator iterator = listPatient.getPatientResponseList().iterator();
        List<Patient> listPatients = new ArrayList<>();
        while( iterator.hasNext() ){
            PatientResponse patientResponse = ( PatientResponse ) iterator.next();
            listPatients.add( new Patient( patientResponse.getIdPatient(),
                            patientResponse.getSurname(),
                            patientResponse.getName(),
                            patientResponse.getFullname(),
                            patientResponse.getGender(),
                            patientResponse.getPhone(),
                            patientResponse.getAdderss(),
                            new Document( patientResponse.getDocument().getIdDocument(),
                                          patientResponse.getDocument().getTypeDocument(), 
                                          patientResponse.getDocument().getSeria(),
                                          patientResponse.getDocument().getNumar(),
                                          patientResponse.getDocument().getSnils(),
                                          patientResponse.getDocument().getPolis() )));

        }
        log.info( "PatientService - find All" );
        return listPatients;
    }

    public Patient addPatient( Patient patient, Long idDocument ){
        PatientResponse patientResponse = patientServiceBlockingStub.addPatient( PatientCreate.newBuilder()
                                                                                              .setIdPatient( patient.getIdPatient())
                                                                                              .setSurname( patient.getSurname() )
                                                                                              .setName( patient.getName())
                                                                                              .setFullname( patient.getFullname() )
                                                                                              .setGender( patient.getGender() )
                                                                                              .setPhone( patient.getPhone() )
                                                                                              .setAdderss( patient.getAddress() )
                                                                                              .setIdDocument( idDocument )
                                                                                              .build());
        log.info( "PatientService - add Patient");
        return new Patient( patientResponse.getIdPatient(),
                            patientResponse.getSurname(),
                            patientResponse.getName(),
                            patientResponse.getFullname(),
                            patientResponse.getGender(),
                            patientResponse.getPhone(),
                            patientResponse.getAdderss(),
                            new Document( patientResponse.getDocument().getIdDocument(),
                                          patientResponse.getDocument().getTypeDocument(), 
                                          patientResponse.getDocument().getSeria(),
                                          patientResponse.getDocument().getNumar(),
                                          patientResponse.getDocument().getSnils(),
                                          patientResponse.getDocument().getPolis() ));
    }

    
    
}
