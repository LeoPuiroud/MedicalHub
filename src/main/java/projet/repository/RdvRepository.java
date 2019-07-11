package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.metier.Rdv;
import projet.metier.User;

public interface RdvRepository extends JpaRepository<Rdv, Integer> {

	@Query("select r from Rdv r left join fetch r.praticien where r.id=:id")
	Optional<Rdv> findByIdWithPraticien(@Param("id")Integer id);
	@Query("select r from Rdv r left join fetch r.patient where r.id=:id")
	Optional<Rdv> findByIdWithPatient(@Param("id")Integer id);
	
}
