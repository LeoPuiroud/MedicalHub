package projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.metier.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
