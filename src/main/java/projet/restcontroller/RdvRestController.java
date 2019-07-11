package projet.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import projet.metier.Rdv;
import projet.metier.Praticien;
import projet.metier.view.JsonViews;
import projet.repository.RdvRepository;
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
@RequestMapping("/rest/rdv")
@CrossOrigin(origins="*")
public class RdvRestController {
	@Autowired
	private RdvRepository rdvRepository;
	
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Rdv>> findAll(){
		return list();
	}
	@JsonView(JsonViews.RdvAvecInfos.class)
	@GetMapping(value= {"/infos"})
	public ResponseEntity<List<Rdv>> findAllWithInfos(){
		return list();
	}
	
	private ResponseEntity<Rdv> findRdvByIdWithPraticien(Integer id){
		Optional<Rdv> opt=rdvRepository.findByIdWithPraticien(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Rdv>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Rdv>(HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<List<Rdv>> list(){
		return new ResponseEntity<List<Rdv>>(rdvRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value= {"","/"})
	public ResponseEntity<Void> create(@RequestBody Rdv rdv,BindingResult br, UriComponentsBuilder ucb){
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		rdvRepository.save(rdv);
		HttpHeaders headers=new HttpHeaders();
		URI uri=ucb.path("/rest/rdv/{id}").buildAndExpand(rdv.getId()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	@GetMapping(value= {"/{id}"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Rdv> findByUsername(@PathVariable(name="id")Integer id){
		return findRdvById(id);
	}
	
	
	private ResponseEntity<Rdv> findRdvById( Integer id){
		Optional<Rdv> opt=rdvRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Rdv>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Rdv>(HttpStatus.NOT_FOUND);
	}
	@CrossOrigin(origins="*")
	@PutMapping(value= {"/{id}"})
	public ResponseEntity<Rdv> update(@PathVariable(name="id") Integer id,@Valid@RequestBody Rdv rdv){
		Optional<Rdv> opt=rdvRepository.findById(id);
		if (opt.isPresent()) {
			Rdv rdvEnBase=opt.get();//versionàjour
			rdvEnBase.setStart((rdv.getStart()!=null)?rdv.getStart():rdvEnBase.getStart());
			rdvEnBase.setDend((rdv.getDend()!=null)?rdv.getDend():rdvEnBase.getDend());
			rdvEnBase.setMotif((rdv.getMotif()!=null)?rdv.getMotif():rdvEnBase.getMotif());
			rdvEnBase.setPatient((rdv.getPatient()!=null)?rdv.getPatient():rdvEnBase.getPatient());
			rdvEnBase.setPraticien((rdv.getPraticien()!=null)?rdv.getPraticien():rdvEnBase.getPraticien());

			rdvRepository.save(rdvEnBase);
			return new  ResponseEntity<>(HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Rdv> delete(@PathVariable(name="id") Integer id){
		Optional<Rdv> opt=rdvRepository.findById(id);
		if (opt.isPresent()) {
			rdvRepository.deleteById(id);
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
