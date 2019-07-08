package projet.metier;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import projet.metier.view.JsonViews;

@Entity
public class Praticien extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPraticien")
	@SequenceGenerator(name = "seqPraticien", sequenceName = "seq_praticien", initialValue = 100, allocationSize = 1)
	@JsonView(JsonViews.Common.class)
	private Integer id;

	@Column(name = "nom_praticien", length = 50)
	@JsonView(JsonViews.Common.class)
	private String nom;

	@Column(name = "prenom_praticien", length = 30)
	@JsonView(JsonViews.Common.class)
	private String prenom;

	@OneToMany(mappedBy = "rdv")
	@JsonView(JsonViews.PraticienAvecRdv.class)
	private List<Rdv> drdv;

	@OneToMany(mappedBy = "specialite")
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	private List<Specialite> specs;

	@Embedded
	private List<Adresse> adresses;

	@OneToMany(mappedBy = "motif")
	private List<Motif> motifs;

	public Praticien(Integer id, String nom, String prenom, List<Rdv> drdv, List<Specialite> specs,
			List<Adresse> adresses, List<Motif> motifs) {
		super();
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Praticien other = (Praticien) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
