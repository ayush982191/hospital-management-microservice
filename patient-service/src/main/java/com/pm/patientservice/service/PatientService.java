package com.pm.patientservice.service;


import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.PatientResponseDTO;
import com.pm.patientservice.entity.Patient;
import com.pm.patientservice.exceptions.EmailAlreadyExistException;
import com.pm.patientservice.exceptions.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toPatientResponseDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("Patient email " + patientRequestDTO.getEmail() + " already exists");
        }
        Patient newPatient = PatientMapper.toEntity(patientRequestDTO);
        return PatientMapper.toPatientResponseDTO( patientRepository.save(newPatient));
    }

    public PatientResponseDTO updatePatient(UUID patientId, PatientRequestDTO patientRequestDTO){
        Patient existingPatient = patientRepository.findById(patientId)
                .orElseThrow(()-> new PatientNotFoundException("Patient not found"));
        boolean isEmailExist = patientRepository.existsByEmailAndUuidNot(patientRequestDTO.getEmail(),patientId);
        if(isEmailExist){
            throw new EmailAlreadyExistException("Patient email " + patientRequestDTO.getEmail() + " already exists");
        }
        Patient updatedPatient = PatientMapper.updateEntity(existingPatient, patientRequestDTO);
        return PatientMapper.toPatientResponseDTO(patientRepository.save(updatedPatient));
    }
    public void deletePatient(UUID patientId){
        if(!patientRepository.existsById(patientId)){
            throw new PatientNotFoundException("Patient not found");
        }
        patientRepository.deleteById(patientId);
    }



}
