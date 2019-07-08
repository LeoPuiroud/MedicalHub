package projet.metier;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import projet.metier.view.JsonViews;

@Entity
public class Patient extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "seqPatient")
	@SequenceGenerator(name= "seqPatient", sequenceName="seq_patient", initialValue = 100, allocationSize = 1 )
	@JsonView(JsonViews.Common.class)
	private Integer id;
	@Column(name= "nom_patient", length = 50)
	@JsonView(JsonViews.Common.class)
	private String nom;
	@Column(name= "prenom_patient", length = 50)
	@JsonView(JsonViews.Common.class)
	private String prenom;
	@OneToMany(mappedBy = "rdv")
	@JsonView(JsonViews.PatientAvecRdv.class)
	private List<Rdv> prdv;
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
	public List<Rdv> getPrdv() {
		return prdv;
	}
	public void setPrdv(List<Rdv> prdv) {
		this.prdv = prdv;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return true;
	}
	
	public Patient() {}
	
}
