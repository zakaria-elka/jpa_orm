package me.bouzri.spring1;

import me.bouzri.spring1.entities.Patient;
import me.bouzri.spring1.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class Spring1Application implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Spring1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(
                new Patient(null, "Mohamed", new Date(), false, 40));
        patientRepository.save(
                new Patient(null, "Amina", new Date(), false, 10));
        patientRepository.save(
                new Patient(null, "Asmae", new Date(), false, 5));

        for (int i = 0; i < 100; i ++)
        {
            patientRepository.save(
                    new Patient(null, "Mohamed"  , new Date(), Math.random() > 0.5, (int) (Math.random() * 10)));
        }

        List<Patient> patients = patientRepository.findAll();

        Patient p1 = patientRepository.findById(2L).orElse(null);

        System.out.println(p1.getNom() + " - " + p1.getDateNaissance());

        p1.setNom("Amina El assri");

        patientRepository.save(p1);


        Page<Patient> patientsPages = patientRepository.findAll(PageRequest.of(1, 10));



        List<Patient> Content = patientsPages.getContent();

        Content.forEach(p -> {

            System.out.println("Patient " + p.getId() + " :  " + p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println("===================");
        });

        patientRepository.delete(p1);
    }
}
