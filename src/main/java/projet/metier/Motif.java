package projet.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import projet.metier.view.JsonViews;

@Entity
public class Motif {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPraticien")
	@SequenceGenerator(name = "seqPraticien", sequenceName = "seq_praticien", initialValue = 400, allocationSize = 1)
	@JsonView(JsonViews.Common.class)
	private Integer id;
	
	@JsonView(JsonViews.Common.class)
	private String libelle;
	
	@JsonView(JsonViews.Common.class)
	private Integer duree;
	
	@ManyToOne
	@JoinColumn(name="id_praticien")
	@JsonView(JsonViews.MotifAvecPraticien.class)
	private Praticien praticien;

	public Motif(Integer id, String libelle, Integer duree, Praticien praticien) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.duree = duree;
		this.praticien = praticien;
	}

	public Motif() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public Praticien getPraticien() {
		return praticien;
	}

	public void setPraticien(Praticien praticien) {
		this.praticien = praticien;
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
		Motif other = (Motif) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
