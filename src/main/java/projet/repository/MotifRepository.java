package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.metier.Motif;

public interface MotifRepository extends JpaRepository<Motif,Integer>{
	@Query("select a from Motif a left join fetch a.praticien where a.id=:id")
	Optional<Motif> findByIdWithPraticien(@Param("id") Integer id);
	
}
