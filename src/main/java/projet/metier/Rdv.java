package projet.metier;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

import projet.metier.view.JsonViews;

@Entity
public class Rdv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRdv")
	@SequenceGenerator(name = "seqRdv", sequenceName = "seq_rdv", initialValue = 100, allocationSize = 1)
	@JsonView(JsonViews.Common.class)
	private Integer id;
	@ManyToOne 
	private Praticien praticien;
	@ManyToOne 
	private Patient patient;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date; 
	@ManyToOne
	private Motif motif;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Praticien getPraticien() {
		return praticien;
	}
	public void setPraticien(Praticien praticien) {
		this.praticien = praticien;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Motif getMotif() {
		return motif;
	}
	public void setMotif(Motif motif) {
		this.motif = motif;
	}
	public Rdv() {
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motif == null) ? 0 : motif.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + ((praticien == null) ? 0 : praticien.hashCode());
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
		Rdv other = (Rdv) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motif == null) {
			if (other.motif != null)
				return false;
		} else if (!motif.equals(other.motif))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (praticien == null) {
			if (other.praticien != null)
				return false;
		} else if (!praticien.equals(other.praticien))
			return false;
		return true;
	}
	
	
	
}
