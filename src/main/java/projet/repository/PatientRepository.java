package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projet.metier.Patient;
import projet.metier.User;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	@Query("select pat from User pat where pat.username=:username")
	Optional<Patient> findByUsername(String username);
	
	//@Query("select pat from User pat left join fetch pat.prdv prdv left join fetch where pat.username=:username")
	//Optional<Patient> findByUsernameWithRdv(String username);
	
}
