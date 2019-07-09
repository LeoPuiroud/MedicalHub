package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.metier.Specialite;

public interface SpecialiteRepository extends JpaRepository<Specialite,Integer>{
	//@Query("select s from Specialite s left join fetch s.praticienSpecialite where s.id=:id")
	//Optional<Specialite> findByIdWithPraticien(@Param("id")Integer id);
}
