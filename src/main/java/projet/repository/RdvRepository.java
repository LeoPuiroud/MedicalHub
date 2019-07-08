package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projet.metier.Rdv;
import projet.metier.User;

public interface RdvRepository extends JpaRepository<Rdv, Integer> {

	@Query("select distinct r from Rdv r left join fetch r.praticien left join fetch r.patient left join fetch r.motif where r.id=:id")
	Optional<User> FindByIdWithUsernames(Integer id);
	
}
