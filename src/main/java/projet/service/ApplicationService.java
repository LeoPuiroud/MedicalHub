package projet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import projet.metier.User;
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
		//rdvs();
		System.out.println("goodbye world");

		
		 // for (User user : userRepository.findAll()) {
		 // user.setPassword(passwordEncoder.encode(user.getPassword()));
		  //userRepository.save(user); }
		 

	}

	public void rdvs() throws ParseException {
		Praticien d = dr.findByUsernameWithRdv("pouzet").get();
		Praticien m = dr.findByUsernameWithRdv("mastour").get();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy, HH:mm");

		List<Rdv> drdv = new ArrayList<Rdv>();

		Date dateI = format.parse("01/07/2019, 08:00");
		Date dateF = format.parse("31/07/2019, 21:00");
		Date current = dateI;

		while (current.before(dateF)) {
			for (int i = 0; i < 24; i++) {
				Rdv rdv = new Rdv();
				rdv.setStart(current);
				rdv.setPraticien(d);
				drdv.add(rdv);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(current);
				calendar.add(Calendar.MINUTE, 30);
				current = calendar.getTime();
				rdv.setDend(current);
				rr.save(rdv);

			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(current);
			calendar.add(Calendar.HOUR, 12);
			current = calendar.getTime();

		}
		d.setDrdv(drdv);
		// dr.save(d);

		while (current.before(dateF)) {
			for (int i = 0; i < 24; i++) {
				Rdv rdv = new Rdv();
				rdv.setStart(current);
				rdv.setPraticien(m);
				drdv.add(rdv);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(current);
				calendar.add(Calendar.MINUTE, 30);
				current = calendar.getTime();
				rdv.setDend(current);
				rr.save(rdv);

			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(current);
			calendar.add(Calendar.HOUR, 12);
			current = calendar.getTime();

		}
		m.setDrdv(drdv);
		// dr.save(d);

	}

	public void testQueries() {
		System.out.println(ar.findByIdWithPraticien(101).get());
		System.out.println(mr.findByIdWithPraticien(401).get());

	}

	public void jeuxDeDonnees() {

		// Test Patient
		Patient p = new Patient();
		p.setUsername("leo");
		p.setEnable(true);
		p.setNom("Puiroud");
		p.setPrenom("Leo");
		p.setPassword("leo");
		pr.save(p);

		// Test Praticien et Adresse
		Praticien d = new Praticien();
		Praticien d2 = new Praticien();
		d.setNom_praticien("Mastour");
		d.setPrenom("Romain");
		d.setEnable(true);
		d.setUsername("mastour");
		d.setPassword("mastour");
		d2.setNom_praticien("Pouzet");
		d2.setPrenom("Martial");
		d2.setEnable(true);
		d2.setUsername("pouzet");
		d2.setPassword("pouzet");
		Adresse a = new Adresse(9, "rue Rougemont", "75009", "Paris");
		a.setPraticien(d);
		Adresse a2 = new Adresse(57, "rue Cadet", "75009", "Paris");
		a2.setPraticien(d2);
		Adresse a3 = new Adresse(25, "rue de Rivoli", "75001", "Paris");
		a3.setPraticien(d);

		dr.save(d);
		dr.save(d2);
		ar.save(a);
		ar.save(a2);
		ar.save(a3);

		// Test Specialites
		Specialite s1 = new Specialite();
		s1.setSpecialite("Gynécologue");
		Specialite s2 = new Specialite();
		s2.setSpecialite("Cardiologue");
		Specialite s3 = new Specialite();
		s3.setSpecialite("Fluor");

		sr.save(s1);
		sr.save(s2);
		sr.save(s3);

		PraticienSpecialite ps1 = new PraticienSpecialite();
		PraticienSpecialiteKey psk1 = new PraticienSpecialiteKey(d, s1);

		PraticienSpecialite ps2 = new PraticienSpecialite();
		PraticienSpecialiteKey psk2 = new PraticienSpecialiteKey(d, s2);

		PraticienSpecialite ps3 = new PraticienSpecialite();
		PraticienSpecialiteKey psk3 = new PraticienSpecialiteKey(d2, s3);

		ps1.setKey(psk1);
		ps2.setKey(psk2);
		ps3.setKey(psk3);

		// PraticienSpecialite ps2 = new PraticienSpecialite();
		// ps2.setKey(new PraticienSpecialiteKey(d, s2));

		psr.save(ps1);
		psr.save(ps2);
		psr.save(ps3);
		// psr.save(ps2);

		// Test Motif
		Motif m = new Motif();
		m.setDuree(30);
		m.setLibelle("première consultation");
		m.setPraticien(d);
		
		Motif m1 = new Motif();
		m1.setDuree(30);
		m1.setLibelle("j ai mal aux cheveux");
		m1.setPraticien(d2);

		mr.save(m1);


	}
}
