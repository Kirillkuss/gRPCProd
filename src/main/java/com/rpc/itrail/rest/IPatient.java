package com.rpc.itrail.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rpc.itrail.model.Patient;
import com.rpc.itrail.response.BaseResponseError;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag( name = "2. PATIENT", description = "CRUD PATIENTS" )
@RequestMapping( "patients" )
@ApiResponses( value = {
    @ApiResponse( responseCode = "200", description = "Success",            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = Patient.class ))) }),
    @ApiResponse( responseCode = "400", description = "Bad request",        content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponseError.class ))) }),
    @ApiResponse( responseCode = "500", description = "System malfunction", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema( implementation = BaseResponseError.class ))) })
    })
public interface IPatient {

    @GetMapping( "/find")
    @Operation( description = "Поиск пациента по ИД", summary = "Поиск пациента по ИД")
    public Patient findById( Long id );

    @GetMapping("/all")
    @Operation( description = "Список пациентов", summary = "Список пациентов")
    public List<Patient> findAll();

    @PostMapping("/add")
    @Operation( description = "Добавить пациента", summary = "Добавить пациента")
    public Patient add( Patient patient, Long idDocument );
    
}
