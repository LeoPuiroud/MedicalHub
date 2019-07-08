package projet.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import projet.metier.Adresse;
import projet.metier.Praticien;
import projet.metier.view.JsonViews;
import projet.repository.AdresseRepository;
import projet.repository.PraticienRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/rest/adresse")
@CrossOrigin(origins="*")
public class AdresseRestController {
	@Autowired
	private AdresseRepository adresseRepository;
	
	@Autowired
	private PraticienRepository praticienRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Adresse>> findAll(){
		return list();
	}
	
	@JsonView(JsonViews.AdresseAvecPraticien.class)
	@GetMapping(value= {"/"})
	public ResponseEntity<List<Adresse>> findAllWithPraticien(){
		return list();
	}
	
	public ResponseEntity<List<Adresse>> list(){
		return new ResponseEntity<List<Adresse>>(adresseRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value= {"","/"})
	public ResponseEntity<Void> create(@RequestBody Adresse adresse,BindingResult br, UriComponentsBuilder ucb){
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		adresseRepository.save(adresse);
		HttpHeaders headers=new HttpHeaders();
		URI uri=ucb.path("/rest/adresse/{id}").buildAndExpand(adresse.getId()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	//trouver un praticien par son id
	@GetMapping(value= {"/{id}"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Adresse> findById(@PathVariable(name="id")Integer id){
		return findAdresseById(id);
	}
	
	@GetMapping(value= {"/{id}/rdv"})
	@JsonView(JsonViews.AdresseAvecPraticien.class)
	public ResponseEntity<Adresse> findByIdWithPraticien(@PathVariable(name="id")Integer id){
		return findAdresseById(id);
	}
	
	private ResponseEntity<Adresse> findAdresseById(Integer id){
		Optional<Adresse> opt=adresseRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Adresse>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Adresse>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value= {"/{id}"})
	public ResponseEntity<Adresse> update(@PathVariable(name="id") Integer id,@Valid@RequestBody Adresse adresse){
		Optional<Adresse> opt=adresseRepository.findById(id);
		if (opt.isPresent()) {
			Adresse adresseEnBase=opt.get();//versionàjour
			adresseEnBase.setNumero((adresse.getNumero()!=null)?adresse.getNumero():adresseEnBase.getNumero());
			adresseEnBase.setRue((adresse.getRue()!=null)?adresse.getRue():adresseEnBase.getRue());
			adresseEnBase.setCp((adresse.getCp()!=null)?adresse.getCp():adresseEnBase.getCp());
			adresseEnBase.setVille((adresse.getVille()!=null)?adresse.getVille():adresseEnBase.getVille());
			adresseEnBase.setPraticien((adresse.getPraticien()!=null)?adresse.getPraticien():adresseEnBase.getPraticien());

			adresseRepository.save(adresseEnBase);
			return new  ResponseEntity<>(HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Adresse> delete(@PathVariable(name="id") Integer id){
		Optional<Adresse> opt=adresseRepository.findById(id);
		if (opt.isPresent()) {
			adresseRepository.deleteById(id);
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
