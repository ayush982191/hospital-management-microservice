package com.pm.patientservice.repository;


import com.pm.patientservice.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, UUID> {
    Optional<PatientEntity> findById(UUID id);

    PatientEntity save(PatientEntity patientEntity);

    void deleteById(UUID id);
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email,UUID id);

}
