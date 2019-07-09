package projet.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import projet.metier.Motif;
import projet.metier.Praticien;
import projet.metier.view.JsonViews;
import projet.repository.MotifRepository;
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
@RequestMapping("/rest/motif")
@CrossOrigin(origins="*")
public class MotifRestController {
	@Autowired
	private MotifRepository motifRepository;
	
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Motif>> findAll(){
		return list();
	}
	
	
	public ResponseEntity<List<Motif>> list(){
		return new ResponseEntity<List<Motif>>(motifRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value= {"","/"})
	public ResponseEntity<Void> create(@RequestBody Motif motif,BindingResult br, UriComponentsBuilder ucb){
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		motifRepository.save(motif);
		HttpHeaders headers=new HttpHeaders();
		URI uri=ucb.path("/rest/motif/{id}").buildAndExpand(motif.getId()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	//trouver un motif par son id
	@GetMapping(value= {"/{id}"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Motif> findById(@PathVariable(name="id")Integer id){
		return findMotifById(id);
	}
	
	
	private ResponseEntity<Motif> findMotifById( Integer id){
		Optional<Motif> opt=motifRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Motif>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Motif>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value= {"/{id}"})
	public ResponseEntity<Motif> update(@PathVariable(name="id") Integer id,@Valid@RequestBody Motif motif){
		Optional<Motif> opt=motifRepository.findById(id);
		if (opt.isPresent()) {
			Motif motifEnBase=opt.get();//versionàjour
			motifEnBase.setDuree((motif.getDuree()!=null)?motif.getDuree():motifEnBase.getDuree());
			motifEnBase.setLibelle((motif.getLibelle()!=null)?motif.getLibelle():motifEnBase.getLibelle());
			motifEnBase.setPraticien((motif.getPraticien()!=null)?motif.getPraticien():motifEnBase.getPraticien());

			motifRepository.save(motifEnBase);
			return new  ResponseEntity<>(HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Motif> delete(@PathVariable(name="id") Integer id){
		Optional<Motif> opt=motifRepository.findById(id);
		if (opt.isPresent()) {
			motifRepository.deleteById(id);
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
