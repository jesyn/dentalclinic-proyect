package com.example.clinicadental.clinica.repository;

import com.example.clinicadental.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository <Paciente, Integer> {
}
