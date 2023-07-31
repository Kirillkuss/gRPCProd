package com.rpc.itrail.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.rpc.itrail.model.Patient;
import com.rpc.itrail.rest.IPatient;
import com.rpc.itrail.service.PatientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PatientController implements IPatient {

    private final PatientService patientService;
    
    @Override
    public Patient findById(Long id) {
        return patientService.findById( id );
    }

    @Override
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @Override
    public Patient add(Patient patient, Long idDocument) {
        return patientService.addPatient( patient, idDocument );
    }
    
}
