package projet.metier;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

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
	@JsonView(JsonViews.RdvAvecInfos.class)
	private Praticien praticien;
	@ManyToOne 
	@JsonView(JsonViews.RdvAvecInfos.class)
	private Patient patient;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(JsonViews.Common.class)
	private Date start; 
	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(JsonViews.Common.class)
	private Date dend; 
	@ManyToOne
	@JsonView(JsonViews.Common.class)
	private Motif motif;
	
	
	@Version
	private Integer version;
	
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
	
	
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}

	
	public Date getDend() {
		return dend;
	}
	public void setDend(Date dend) {
		this.dend = dend;
	}
	public Motif getMotif() {
		return motif;
	}
	public void setMotif(Motif motif) {
		this.motif = motif;
	}
	
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Rdv() {
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
		Rdv other = (Rdv) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
