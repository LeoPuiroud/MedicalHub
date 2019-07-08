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
public class Praticien extends User{
	@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "seqPraticien")
@SequenceGenerator(name= "seqPraticien", sequenceName="seq_praticien", initialValue = 100, allocationSize = 1 )
	@JsonView(JsonViews.Common.class)
	private Integer id;
	
	@Column(name= "nom_praticien", length = 50)
	@JsonView(JsonViews.Common.class)
	private String nom;
	
	@Column(name= "prenom_praticien", length = 30)
	@JsonView(JsonViews.Common.class)
	private String prenom;
	
	@OneToMany(mappedBy = "rdv")
	@JsonView(JsonViews.PraticienAvecRdv.class)
	private List<Rdv> drdv;
	
	@OneToMany(mappedBy="specialite")
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	private List<Specialite> specs;
	
	@Embedded
	private List<Adresse> adresses;
	
	
	@OneToMany(mappedBy="motif")
	private List<Motif> motifs;
	
}
