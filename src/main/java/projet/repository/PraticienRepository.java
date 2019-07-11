package projet.repository;

import java.util.List;
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

	@Query("select distinct p from User p left join fetch p.praticienSpecialite ps left join fetch ps.key k left join fetch k.specialite s where lower(s.specialite) like %:rech%")
	List<Praticien> findAllCustom1(@Param("rech")String rech);
	
	@Query("select distinct p from User p left join fetch p.adresses a where lower(a.ville) like %:rech%")
	List<Praticien> findAllCustom2(@Param("rech")String rech);
	
	@Query("select distinct p from User p where lower(p.nom_praticien) like %:rech%")
	List<Praticien> findAllCustom3(@Param("rech")String rech);
	
	//@Query("select  prat from User prat left join fetch prat.adresse where prat.username=:username")
	//Optional<Praticien> findByUsernameWithAdresse(@Param("username")String username);
	
	//@Query("select  prat from User prat left join fetch prat.motif where prat.username=:username")
	//Optional<Praticien> findByUsernameWithMotif(@Param("username")String username);
	
	//@Query("select  prat from User prat left join fetch prat.praticienSpecialite where prat.username=:username")
	//Optional<Praticien> findByUsernameWithSpecialite(@Param("username")String username);

}
