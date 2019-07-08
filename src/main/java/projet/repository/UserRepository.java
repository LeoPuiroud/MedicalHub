package projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.metier.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	

}
