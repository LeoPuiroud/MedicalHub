package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projet.metier.Rdv;
import projet.metier.User;

public interface RdvRepository extends JpaRepository<Rdv, Integer> {

	@Query("select distinct r from Rdv r left join fetch r.praticien prat left join fetch r.patient pat left join fetch r.motif m where pat.username=:username")
	Optional<User> FindByIdWithUsernames(String username);
	
}
