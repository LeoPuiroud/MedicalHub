package projet.metier;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import projet.metier.view.JsonViews;

@Entity
@Table(name = "join_praticien_specialite")
public class PraticienSpecialite {
	@EmbeddedId
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	private PraticienSpecialiteKey key;
	
	public PraticienSpecialiteKey getKey() {
		return key;
	}

	public void setKey(PraticienSpecialiteKey key) {
		this.key = key;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		PraticienSpecialite other = (PraticienSpecialite) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
