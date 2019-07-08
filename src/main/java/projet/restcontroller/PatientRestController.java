package projet.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import projet.metier.Patient;
import projet.metier.Praticien;
import projet.metier.view.JsonViews;
import projet.repository.PatientRepository;
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
@RequestMapping("/rest/patient")
@CrossOrigin(origins="*")
public class PatientRestController {
	@Autowired
	private PatientRepository patientRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping(value= {"","/"})
	public ResponseEntity<List<Patient>> findAll(){
		return list();
	}
	
	@JsonView(JsonViews.PatientAvecRdv.class)
	@GetMapping(value= {"/"})
	public ResponseEntity<List<Patient>> findAllWithRdv(){
		return list();
	}
	
	public ResponseEntity<List<Patient>> list(){
		return new ResponseEntity<List<Patient>>(patientRepository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value= {"","/"})
	public ResponseEntity<Void> create(@RequestBody Patient patient,BindingResult br, UriComponentsBuilder ucb){
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		patientRepository.save(patient);
		HttpHeaders headers=new HttpHeaders();
		URI uri=ucb.path("/rest/patient/{username}").buildAndExpand(patient.getUsername()).toUri();
		headers.setLocation(uri);
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	//trouver un praticien par son id
	@GetMapping(value= {"/{username}"})
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Patient> findByUsername(@PathVariable(name="username")String username){
		return findPatientByUsername(username);
	}
	
	@GetMapping(value= {"/{username}/rdv"})
	@JsonView(JsonViews.PatientAvecRdv.class)
	public ResponseEntity<Patient> findByUsernameWithRdv(@PathVariable(name="username")String username){
		return findPatientByUsername(username);
	}
	
	private ResponseEntity<Patient> findPatientByUsername( String username){
		Optional<Patient> opt=patientRepository.findByUsername(username);
		if (opt.isPresent()) {
			return new ResponseEntity<Patient>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value= {"/{username}"})
	public ResponseEntity<Patient> update(@PathVariable(name="username") String username,@Valid@RequestBody Patient patient){
		Optional<Patient> opt=patientRepository.findByUsername(username);
		if (opt.isPresent()) {
			Patient patientEnBase=opt.get();//versionàjour
			patientEnBase.setPrenom((patient.getPrenom()!=null)?patient.getPrenom():patientEnBase.getPrenom());
			patientEnBase.setNom((patient.getNom()!=null)?patient.getNom():patientEnBase.getNom());
			patientEnBase.setPrdv((patient.getPrdv()!=null)?patient.getPrdv():patientEnBase.getPrdv());
			patientEnBase.setPassword((patient.getPassword()!=null)?patient.getPassword():patientEnBase.getPassword());
			
			patientRepository.save(patientEnBase);
			return new  ResponseEntity<>(HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Patient> delete(@PathVariable(name="username") Integer id){
		Optional<Patient> opt=patientRepository.findById(id);
		if (opt.isPresent()) {
			patientRepository.deleteById(id);
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
