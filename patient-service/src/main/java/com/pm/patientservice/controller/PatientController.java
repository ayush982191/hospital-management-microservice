package com.pm.patientservice.controller;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.ApiResponse;
import com.pm.patientservice.dto.response.PatientResponseDTO;
import com.pm.patientservice.dto.response.ResponseBuilder;
import com.pm.patientservice.service.MessageService;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final ResponseBuilder responseBuilder;
    private final PatientService patientService;
    public PatientController(PatientService patientService,ResponseBuilder responseBuilder ){
        this.patientService = patientService;
        this.responseBuilder = responseBuilder;
    }
    @GetMapping("/all")
    public ApiResponse<List<PatientResponseDTO>> getPatients(){
        return responseBuilder.success(200, patientService.getPatients() );
    }
    @PostMapping("/create")
    public ApiResponse<PatientResponseDTO> create(@Valid @RequestBody PatientRequestDTO dto) {
        return responseBuilder.success(201, patientService.createPatient(dto));
    }


}
