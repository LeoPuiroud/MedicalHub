package projet.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import projet.metier.view.JsonViews;

@Entity
@Table(name="specialite")
public class Specialite {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqSpe")
	@SequenceGenerator(name = "seqSpe", sequenceName="seq_spe", initialValue = 200, allocationSize = 1)
	@JsonView(JsonViews.Common.class)
	private Integer id;
	
	@JsonView(JsonViews.Common.class)
	private String specialite;
	
	@Version
	private Integer version;
	
	public Specialite() {
		super();
	}


	public String getSpecialite() {
		return specialite;
	}


	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
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
		Specialite other = (Specialite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	

}
