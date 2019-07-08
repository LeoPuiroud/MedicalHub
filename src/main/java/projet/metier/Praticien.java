package projet.metier;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import projet.metier.view.JsonViews;

@Entity
public class Praticien extends User {
	


	@Column(name = "nom_praticien", length = 50)
	@JsonView(JsonViews.Common.class)
	private String nom;

	@Column(name = "prenom_praticien", length = 30)
	@JsonView(JsonViews.Common.class)
	private String prenom;

	@OneToMany(mappedBy = "rdv")
	@JsonView(JsonViews.PraticienAvecRdv.class)
	private List<Rdv> drdv;

	@Enumerated
	@OneToMany(mappedBy = "specialite")
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	private List<Specialite> specs;

	@Embedded
	private List<Adresse> adresses;

	@OneToMany(mappedBy = "motif")
	private List<Motif> motifs;

	public Praticien(String nom, String prenom, List<Rdv> drdv, List<Specialite> specs,
			List<Adresse> adresses, List<Motif> motifs) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.drdv = drdv;
		this.specs = specs;
		this.adresses = adresses;
		this.motifs = motifs;
	}

	public Praticien() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Rdv> getDrdv() {
		return drdv;
	}

	public void setDrdv(List<Rdv> drdv) {
		this.drdv = drdv;
	}

	public List<Specialite> getSpecs() {
		return specs;
	}

	public void setSpecs(List<Specialite> specs) {
		this.specs = specs;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	public List<Motif> getMotifs() {
		return motifs;
	}

	public void setMotifs(List<Motif> motifs) {
		this.motifs = motifs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adresses == null) ? 0 : adresses.hashCode());
		result = prime * result + ((drdv == null) ? 0 : drdv.hashCode());
		result = prime * result + ((motifs == null) ? 0 : motifs.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((specs == null) ? 0 : specs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Praticien other = (Praticien) obj;
		if (adresses == null) {
			if (other.adresses != null)
				return false;
		} else if (!adresses.equals(other.adresses))
			return false;
		if (drdv == null) {
			if (other.drdv != null)
				return false;
		} else if (!drdv.equals(other.drdv))
			return false;
		if (motifs == null) {
			if (other.motifs != null)
				return false;
		} else if (!motifs.equals(other.motifs))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (specs == null) {
			if (other.specs != null)
				return false;
		} else if (!specs.equals(other.specs))
			return false;
		return true;
	}

	
}
