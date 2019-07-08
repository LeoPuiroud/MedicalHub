package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projet.metier.Praticien;
import projet.metier.User;

public interface PraticienRepository extends JpaRepository<Praticien, Integer>{

	//@Query("select distinct prat from User prat left join fetch prat.patient pat left join fetch prat.specs specs left join fetch prat.drdv drdv where prat.username=:username")
	//Optional<Praticien> findByUsernameWithRdv(String username);
	
	@Query("select prat from User prat where prat.username=:username")
	Optional<Praticien> findByUsername(String username);
	
}
