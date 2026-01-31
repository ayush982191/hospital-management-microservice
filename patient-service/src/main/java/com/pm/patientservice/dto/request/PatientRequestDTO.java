package com.pm.patientservice.dto.request;


import com.pm.patientservice.dto.request.validator.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@NoArgsConstructor
public class PatientRequestDTO {
    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Name can't be empty")
    @Size(groups = CreatePatientValidationGroup.class,min = 2, max = 100,message = "Name should be b/w 2 to 100 character")
    private String name;

    @NotBlank(groups = CreatePatientValidationGroup.class,message = "Email is required")
    @Email(groups = CreatePatientValidationGroup.class,message = "Email should be valid")
    private String email;

    @NotBlank(groups = CreatePatientValidationGroup.class,message = "Address is required")
    private String address;

    @NotBlank(groups = CreatePatientValidationGroup.class,message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class,message = "Registered date is required")
    private String registeredDate;

}
