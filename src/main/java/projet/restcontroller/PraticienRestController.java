package projet.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import projet.metier.Praticien;
import projet.metier.view.JsonViews;
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
@RequestMapping("/rest/praticien")
@CrossOrigin(origins="*")
public class PraticienRestController {
	@Autowired
	private PraticienRepository praticienRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Praticien>> findAll(){
		return list();
	}
	
	@JsonView(JsonViews.PraticienAvecRdv.class)
	@GetMapping(value= {"/rdv"})
	public ResponseEntity<List<Praticien>> findAllWithRdv(){
		return list();
	}
	@JsonView(JsonViews.PraticienAvecMotif.class)
	@GetMapping(value= {"/motif"})
	public ResponseEntity<List<Praticien>> findAllWithMotif(){
		return list();
	}
	
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	@GetMapping(value= {"/specialite"})
	public ResponseEntity<List<Praticien>> findAllWithSpecialiteAndAdresse(){
		return list();
	}
	
	public ResponseEntity<List<Praticien>> list(){
		List<Praticien> lp = praticienRepository.findAll();
		for (Praticien p : lp) 
		System.out.println(p.getPraticienSpecialite());
		return new ResponseEntity<List<Praticien>>(lp,HttpStatus.OK);
	}
	
	@PostMapping(value= {"","/"})
	public ResponseEntity<Void> create(@RequestBody Praticien praticien,BindingResult br, UriComponentsBuilder ucb){
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		praticienRepository.save(praticien);
		HttpHeaders headers=new HttpHeaders();
		URI uri=ucb.path("/rest/praticien/{id}").buildAndExpand(praticien.getUsername()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	@GetMapping(value= {"/{id}"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Praticien> findById(@PathVariable(name="id")Integer id){
		return findPraticienById(id);
	}
	
	@GetMapping(value= {"/{id}/rdv"})
	@JsonView(JsonViews.PraticienAvecRdv.class)
	public ResponseEntity<Praticien> findByIdWithRdv(@PathVariable(name="id")Integer id){
		return findPraticienById(id);
	}
	
	private ResponseEntity<Praticien> findPraticienById( Integer id){
		Optional<Praticien> opt=praticienRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Praticien>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Praticien>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value= {"/{id}"})
	public ResponseEntity<Praticien> update(@PathVariable(name="id") Integer id,@Valid@RequestBody Praticien praticien){
		Optional<Praticien> opt=praticienRepository.findById(id);
		if (opt.isPresent()) {
			Praticien praticienEnBase=opt.get();//versionàjour
			praticienEnBase.setPrenom((praticien.getPrenom()!=null)?praticien.getPrenom():praticienEnBase.getPrenom());
			praticienEnBase.setNom_praticien((praticien.getNom_praticien()!=null)?praticien.getNom_praticien():praticienEnBase.getNom_praticien());
			praticienEnBase.setDrdv((praticien.getDrdv()!=null)?praticien.getDrdv():praticienEnBase.getDrdv());
			praticienEnBase.setAdresses((praticien.getAdresses()!=null)?praticien.getAdresses():praticienEnBase.getAdresses());
			//praticienEnBase.setSpecs((praticien.getSpecs()!=null)?praticien.getSpecs():praticienEnBase.getSpecs());
			praticienEnBase.setMotifs((praticien.getMotifs()!=null)?praticien.getMotifs():praticienEnBase.getMotifs());
			
			praticienRepository.save(praticienEnBase);
			return new  ResponseEntity<>(HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Praticien> delete(@PathVariable(name="id") Integer id){
		Optional<Praticien> opt=praticienRepository.findById(id);
		if (opt.isPresent()) {
			praticienRepository.deleteById(id);
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	@GetMapping("/rspe/{spe}")
	public ResponseEntity<List<Praticien>> searchPratSpe(@PathVariable (name="spe") String s) {
		return new ResponseEntity<List<Praticien>>(praticienRepository.findAllCustom1(s.toLowerCase()) , HttpStatus.OK);
	}
	
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	@GetMapping("/rville/{spe}")
	public ResponseEntity<List<Praticien>> searchPratVille(@PathVariable (name="spe") String s) {
		return new ResponseEntity<List<Praticien>>(praticienRepository.findAllCustom2(s.toLowerCase()) , HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@JsonView(JsonViews.PraticienAvecSpecialite.class)
	@GetMapping("/rnom/{spe}")
	public ResponseEntity<List<Praticien>> searchPratNom(@PathVariable (name="spe") String s) {
		return new ResponseEntity<List<Praticien>>(praticienRepository.findAllCustom3(s.toLowerCase()) , HttpStatus.OK);
	}
	
	
	
}
