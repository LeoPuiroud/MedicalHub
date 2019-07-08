package projet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import projet.metier.Adresse;
import projet.metier.Motif;
import projet.metier.Patient;
import projet.metier.Praticien;
import projet.metier.PraticienSpecialite;
import projet.metier.PraticienSpecialiteKey;
import projet.metier.Rdv;
import projet.metier.Specialite;
import projet.repository.AdresseRepository;
import projet.repository.MotifRepository;
import projet.repository.PatientRepository;
import projet.repository.PraticienRepository;
import projet.repository.PraticienSpecialiteRepository;
import projet.repository.RdvRepository;
import projet.repository.SpecialiteRepository;
import projet.repository.UserRepository;

@Service
public class ApplicationService implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PatientRepository pr;

	@Autowired
	private MotifRepository mr;

	@Autowired
	private PraticienRepository dr;

	@Autowired
	private RdvRepository rr;

	@Autowired
	private AdresseRepository ar;
	@Autowired
	private SpecialiteRepository sr;

	@Autowired
	private PraticienSpecialiteRepository psr;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello world");
//jeuxDeDonnees();

		System.out.println("goodbye world");
		/*
		 * for (User user : userRepository.findAll()) {
		 * user.setPassword(passwordEncoder.encode(user.getPassword());
		 * userRepository.save(user); }
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
public void jeuxDeDonnees() {
	// Test Patient
			Patient p = new Patient();
			p.setUsername("patrick");
			p.setEnable(true);
			p.setNom("Bruel");
			p.setPrenom("Patrick");
			pr.save(p);

			// Test Praticien et Adresse
			Praticien d = new Praticien();
			d.setNom("Pouzet");
			d.setPrenom("Martial");
			d.setEnable(true);
			d.setUsername("pouzet");
			Adresse a = new Adresse(9, "rue Rougemont", "75009", "Paris");
			a.setPraticien(d);
			dr.save(d);
			ar.save(a);

			// Test Specialites
			Specialite s1 = new Specialite();
			s1.setSpecialite("Gynécologue");
			Specialite s2 = new Specialite();
			s2.setSpecialite("Cardiologue");

			PraticienSpecialite ps1 = new PraticienSpecialite();
			ps1.setKey(new PraticienSpecialiteKey(d, s1));

			PraticienSpecialite ps2 = new PraticienSpecialite();
			ps2.setKey(new PraticienSpecialiteKey(d, s2));

			// psr.save(ps1);
			// psr.save(ps2);

			// Test Motif
			Motif m = new Motif();
			m.setDuree(30);
			m.setLibelle("première consultation");
			m.setPraticien(d);

			mr.save(m);

			// Test RDV
			Rdv r = new Rdv();
			r.setMotif(m);
			r.setPatient(p);
			r.setPraticien(d);

			rr.save(r);
}
}
