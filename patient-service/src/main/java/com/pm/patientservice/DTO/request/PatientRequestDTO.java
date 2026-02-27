package com.pm.patientservice.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDTO {
    @NotBlank
    @Size(max=100,message="Message cannot exceed 100 char")
    private String name;
    @NotBlank
    @Email(message="email should be valid")
    private String email;
    @NotBlank(message="Address is required")
    private String address;
    private String dateOfBirth;
    private String registeredDate;
}
