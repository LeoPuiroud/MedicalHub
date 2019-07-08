package projet.metier;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import projet.metier.view.JsonViews;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Adresse {

	@JsonView(JsonViews.Common.class)
	private Integer numero;
	@JsonView(JsonViews.Common.class)
	private String rue;
	@JsonView(JsonViews.Common.class)
	@Column(name="code_postal")
	private String cp;
	@JsonView(JsonViews.Common.class)
	private String ville;
	
	
	public Adresse() {}

	public Adresse(Integer numero, String rue, String cp, String ville) {
		this.numero = numero;
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	
	
}
