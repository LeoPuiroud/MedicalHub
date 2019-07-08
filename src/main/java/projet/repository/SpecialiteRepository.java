package projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.metier.Adresse;
import projet.metier.Specialite;

public interface SpecialiteRepository extends JpaRepository<Specialite,Integer>{

}
