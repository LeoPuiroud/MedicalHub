package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projet.metier.Rdv;
import projet.metier.User;

public interface RdvRepository extends JpaRepository<Rdv, Integer> {

	@Query("select r from Rdv r left join fetch r.praticien where r.id=:id")
	Optional<User> FindByIdWithPraticien(@Param("id")Integer id);
	
}
