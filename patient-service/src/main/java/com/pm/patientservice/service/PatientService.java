package com.pm.patientservice.service;

import com.pm.patientservice.DTO.request.PatientRequestDTO;
import com.pm.patientservice.DTO.response.PatientResponseDTO;
import com.pm.patientservice.entity.PatientEntity;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.repository.PatientRepository;
import jakarta.validation.groups.Default;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    PatientService(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

//    public
    public PatientResponseDTO getPatientById(UUID id) {
        return PatientMapper.toDTO(patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient not found")));
    }

    public PatientResponseDTO savePatient(PatientRequestDTO patientRequestDTO) {
//
        PatientEntity entity = PatientMapper.toEntity(patientRequestDTO);
        if(patientRepository.existsByEmail(entity.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists with "+patientRequestDTO.getEmail());
        }

        return PatientMapper.toDTO(patientRepository.save(entity));
    }

    public PatientResponseDTO updatePatient(UUID id,PatientRequestDTO patientRequestDTO) {
        PatientEntity patient =patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient not found"));
        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("Email already exists with "+patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(
                LocalDate.parse(patientRequestDTO.getDateOfBirth())
        );
        return PatientMapper.toDTO(patientRepository.save(patient));
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

    public List<PatientResponseDTO> getAllPatients() {
        return  patientRepository.findAll().stream()
                .map(PatientMapper::toDTO)
                .toList()
                ;
    }
}
