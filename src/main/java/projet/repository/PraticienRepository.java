package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.metier.Praticien;

public interface PraticienRepository extends JpaRepository<Praticien, Integer>{

	@Query("select  prat from User prat left join fetch prat.drdv where prat.username=:username")
	Optional<Praticien> findByUsernameWithRdv(@Param("username")String username);
	
	@Query("select prat from User prat where prat.username=:username")
	Optional<Praticien> findByUsername(@Param("username")String username);
	
	@Query("select  prat from User prat left join fetch prat.adresse where prat.username=:username")
	Optional<Praticien> findByUsernameWithAdresse(@Param("username")String username);
	
	@Query("select  prat from User prat left join fetch prat.motif where prat.username=:username")
	Optional<Praticien> findByUsernameWithMotif(@Param("username")String username);
	
	@Query("select  prat from User prat left join fetch prat.praticienSpecialite where prat.username=:username")
	Optional<Praticien> findByUsernameWithSpecialite(@Param("username")String username);

}
