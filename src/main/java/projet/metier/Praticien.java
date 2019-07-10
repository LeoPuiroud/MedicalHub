package projet.metier;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonView;

import projet.metier.view.JsonViews;

@Entity
public class Praticien extends User {
	


	@Column(name = "nom_praticien", length = 50)
	@JsonView(JsonViews.Common.class)
	private String nom_praticien;

	@Column(name = "prenom_praticien", length = 30)
	@JsonView(JsonViews.Common.class)
	private String prenom;

	@OneToMany(mappedBy = "praticien")
	@JsonView(JsonViews.PraticienAvecRdv.class)
	private List<Rdv> drdv;
	
	
	@OneToMany(mappedBy = "key.praticien")
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	private List<PraticienSpecialite> praticienSpecialite;
	
	@OneToMany(mappedBy="praticien")
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	private List<Adresse> adresses;

	@OneToMany(mappedBy = "praticien")
	@JsonView(JsonViews.PraticienAvecMotif.class)
	private List<Motif> motifs;

	public Praticien(String nom, String prenom, List<Rdv> drdv,
			List<Adresse> adresses, List<Motif> motifs) {
		super();
		this.nom_praticien = nom;
		this.prenom = prenom;
		this.drdv = drdv;
		this.adresses = adresses;
		this.motifs = motifs;
	}

	public Praticien() {
		super();
	}

	public String getNom() {
		return nom_praticien;
	}

	public void setNom(String nom) {
		this.nom_praticien = nom;
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

	public List<PraticienSpecialite> getPraticienSpecialite() {
		return praticienSpecialite;
	}

	public void setPraticienSpecialite(List<PraticienSpecialite> praticienSpecialite) {
		this.praticienSpecialite = praticienSpecialite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adresses == null) ? 0 : adresses.hashCode());
		result = prime * result + ((drdv == null) ? 0 : drdv.hashCode());
		result = prime * result + ((motifs == null) ? 0 : motifs.hashCode());
		result = prime * result + ((nom_praticien == null) ? 0 : nom_praticien.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		if (nom_praticien == null) {
			if (other.nom_praticien != null)
				return false;
		} else if (!nom_praticien.equals(other.nom_praticien))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	
}
