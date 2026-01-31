package com.pm.patientservice.controller;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.request.validator.CreatePatientValidationGroup;
import com.pm.patientservice.dto.response.ApiResponse;
import com.pm.patientservice.dto.response.PatientResponseDTO;
import com.pm.patientservice.dto.response.ApiResponseBuilder;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient",description = "API for managing patient")
public class PatientController {
    private final ApiResponseBuilder apiResponseBuilder;
    private final PatientService patientService;
    public PatientController(PatientService patientService, ApiResponseBuilder apiResponseBuilder){
        this.patientService = patientService;
        this.apiResponseBuilder = apiResponseBuilder;
    }
    @GetMapping("/all")
    @Operation(summary = "Get Patient")
    public ApiResponse<List<PatientResponseDTO>> getPatients(){
        return apiResponseBuilder.success(200, patientService.getPatients() );
    }

    @PostMapping("/create")
    @Operation(summary = "create patient")
    public ApiResponse<PatientResponseDTO> create(@Validated({CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO dto) {
        return apiResponseBuilder.success(201, patientService.createPatient(dto));
    }

    @PutMapping("/update/{patientId}")
    @Operation(summary = "update patient")
    public ApiResponse<PatientResponseDTO> update(@PathVariable UUID patientId, @Validated({ Default.class}) @RequestBody PatientRequestDTO dto) {
        return apiResponseBuilder.success(200, patientService.updatePatient(patientId,dto));
    }

    @DeleteMapping("/delete/{patientId}")
    @Operation(summary = "delete patient")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.ok(apiResponseBuilder.success(200, null));
    }

}
