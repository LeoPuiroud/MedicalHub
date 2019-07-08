package projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projet.metier.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select distinct u from User u left join fetch u.roles where u.username=:username")
	Optional<User> findByIdWithRoles(String username);

}
