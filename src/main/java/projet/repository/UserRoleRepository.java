package projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.metier.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
