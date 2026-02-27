package com.pm.patientservice.mapper;

import com.pm.patientservice.DTO.request.PatientRequestDTO;
import com.pm.patientservice.DTO.response.PatientResponseDTO;
import com.pm.patientservice.entity.PatientEntity;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientEntity toEntity(PatientRequestDTO patientRequestDTO)
    {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setName(patientRequestDTO.getName());
        patientEntity.setEmail(patientRequestDTO.getEmail());
        patientEntity.setAddress(patientRequestDTO.getAddress());
        patientEntity.setDateOfBirth(
                LocalDate.parse(patientRequestDTO.getDateOfBirth())
        );
        patientEntity.setRegisterDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patientEntity;
    }

    public static PatientResponseDTO toDTO(PatientEntity patientEntity)
    {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patientEntity.getId().toString());
        patientResponseDTO.setName(patientEntity.getName());
        patientResponseDTO.setEmail(patientEntity.getEmail());
        patientResponseDTO.setAddress(patientEntity.getAddress());
        patientResponseDTO.setDateOfBirth(patientEntity.getDateOfBirth().toString());
        return patientResponseDTO;
    }
}
