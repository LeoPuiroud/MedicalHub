package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.metier.Patient;
import projet.metier.User;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	@Query("select pat from User pat where pat.username=:username")
	Optional<Patient> findByUsername(@Param("username")String username);
	
	@Query("select pat from User pat left join fetch pat.prdv prdv where pat.username=:username")
	Optional<Patient> findByUsernameWithRdv(@Param("username")String username);
	
}
