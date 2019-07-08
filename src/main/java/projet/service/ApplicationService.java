package projet.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projet.metier.Motif;
import projet.metier.Patient;
import projet.metier.Praticien;
import projet.metier.Rdv;
import projet.metier.Specialite;
import projet.repository.MotifRepository;
import projet.repository.PatientRepository;
import projet.repository.PraticienRepository;
import projet.repository.RdvRepository;
import projet.repository.UserRepository;

@Service
public class ApplicationService implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	 private PatientRepository pr ;
	
	@Autowired
	 private MotifRepository mr;
	
	@Autowired
	 private PraticienRepository dr ;
	
	@Autowired
	 private RdvRepository rr ;
	 
	
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello world");
		Patient p = new Patient();
		Motif m = new Motif();
		Praticien d = new Praticien();
		Rdv r = new Rdv();
		
		p.setUsername("Patrick");
		d.setUsername("Pouzet");
		ArrayList<Specialite> l = new ArrayList<Specialite>();
		l.add(Specialite.Chirurgien);
		l.add(Specialite.Dentiste);
		d.setSpecs(l);
		m.setPraticien(d);
		m.setDuree(60);
		r.setPraticien(d);
		r.setPatient(p);
		r.setMotif(m);
		pr.save(p);
		mr.save(m);
		dr.save(d);
		rr.save(r);
		System.out.println("goodbye world");
		/*
		 * for (User user : userRepository.findAll()) {
		 * user.setPassword(passwordEncoder.encode(user.getPassword()); 
		 * userRepository.save(user);
		 * }
		 */
		
	}

	/*
	 * private static String REST_SERVICE_URL = "http://localhost:8080/boot/rest";
	 * 
	 * private void listAllSoldat() { System.out.println("liste des soldats");
	 * RestTemplate rt = new RestTemplate(); List<LinkedHashMap<String, Object>>
	 * liste = rt.getForObject(REST_SERVICE_URL + "/soldat", List.class);
	 * List<Soldat> soldats = new ArrayList<Soldat>(); Soldat soldat = null; for
	 * (LinkedHashMap<String, Object> map : liste) { soldat = new Soldat();
	 * soldat.setId(Integer.parseInt(map.get("id").toString()));
	 * soldat.setPrenom(map.get("nom").toString()); soldats.add(soldat); }
	 * System.out.println(soldats); }
	 * 
	 * private String create() { System.out.println("creation soldat"); RestTemplate
	 * rt = new RestTemplate();
	 * 
	 * Soldat s = new Soldat(); s.setPrenom("Kairi"); s.setNom("KH"); URI uri =
	 * rt.postForLocation(REST_SERVICE_URL + "/soldat", s, Soldat.class);
	 * System.out.println(uri.toASCIIString()); return uri.toASCIIString();
	 * 
	 * }
	 * 
	 * private void getSoldat(String url) { System.out.println("get soldat");
	 * RestTemplate rt = new RestTemplate(); Soldat s = rt.getForObject(url,
	 * Soldat.class); System.out.println(s); }
	 * 
	 * private void update() { System.out.println("update"); RestTemplate rt = new
	 * RestTemplate(); Soldat s = new Soldat(); s.setPv(200);
	 * 
	 * rt.put(REST_SERVICE_URL + "/soldat/5", s); }
	 * 
	 * private void delete(String url) { System.out.println("delete"); RestTemplate
	 * rt = new RestTemplate(); rt.delete(url); }
	 */

}
