package com.pm.patientservice.controller;

import com.pm.patientservice.DTO.request.PatientRequestDTO;
import com.pm.patientservice.DTO.response.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/patients")
@Tag(name="Patient", description="Api for managing Patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // CREATE
    @PostMapping
    @Operation(summary = "Create new patient")
    public ResponseEntity<PatientResponseDTO> savePatient(
            @Valid @RequestBody PatientRequestDTO patientRequestDTO) {

        PatientResponseDTO response = patientService.savePatient(patientRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // READ BY ID
    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) {

        PatientResponseDTO response = patientService.getPatientById(id);
        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    @Operation(summary = "Update patient by ID")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable UUID id,
            @Validated(Default.class) @RequestBody PatientRequestDTO patientRequestDTO) {

        PatientResponseDTO response = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete patient by ID")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    // GET ALL
    @GetMapping
    @Operation(summary = "Get all patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {

        List<PatientResponseDTO> response = patientService.getAllPatients();
        return ResponseEntity.ok(response);
    }


}