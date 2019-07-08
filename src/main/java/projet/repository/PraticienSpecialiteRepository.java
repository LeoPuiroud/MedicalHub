package projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projet.metier.PraticienSpecialite;
import projet.metier.PraticienSpecialiteKey;

public interface PraticienSpecialiteRepository extends JpaRepository<PraticienSpecialite,PraticienSpecialiteKey>{

}
