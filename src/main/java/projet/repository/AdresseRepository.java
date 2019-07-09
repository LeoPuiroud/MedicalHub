package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.metier.Adresse;
import projet.metier.Patient;

public interface AdresseRepository extends JpaRepository<Adresse,Integer>{
	
	@Query("select a from Adresse a left join fetch a.praticien where a.id=:id")
	Optional<Adresse> findByIdWithPraticien(@Param("id")Integer id);
	

}
