package projet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.metier.Motif;
import projet.metier.Praticien;

public interface MotifRepository extends JpaRepository<Motif,Integer>{
	@Query("select a from Motif a left join fetch a.praticien where a.id=:id")
	Optional<Motif> findByIdWithPraticien(@Param("id") Integer id);
	
	@Query("select distinct m from Motif m where m.praticien.nom_praticien = :rech")
	List<Motif> findAllCustom(@Param("rech")String rech);
}
