package com.ism;

import com.ism.entities.Patient;
import com.ism.entities.Medecin;
import com.ism.repositories.bd.PatientRepository;
import com.ism.repositories.bd.MedecinRepository;
import com.ism.repositories.bd.RendezVousRepository;
import com.ism.repositories.core.Database;
import com.ism.repositories.core.ITables;
import com.ism.repositories.bd.impl.PatientRepositoryImpl;
import com.ism.repositories.bd.impl.MedecinRepositoryImpl;
import com.ism.repositories.bd.impl.RendezVousRepositoryImpl;
import com.ism.repositories.core.MySQLImpl;
import com.ism.service.PatientServiceImpl;
import com.ism.service.MedecinServiceImpl;
import com.ism.service.RVServiceImpl;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Connexion à la base de données MySQL
        Database database = new MySQLImpl();

        // Initialisation des repositories
        PatientRepository patientRepository = new PatientRepositoryImpl(database);
        MedecinRepository medecinRepository = new MedecinRepositoryImpl(database);
        RendezVousRepository rendezVousRepository = new RendezVousRepositoryImpl(database);

        // Initialisation des services
        PatientServiceImpl patientService = new PatientServiceImpl(patientRepository);
        MedecinServiceImpl medecinService = new MedecinServiceImpl(medecinRepository);
        RVServiceImpl rendezVousService = new RVServiceImpl(rendezVousRepository);

        int choix;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("------- Gestion des Rendez-vous Médicaux ---------");
            System.out.println("1- Créer un patient");
            System.out.println("2- Créer un médecin");
            System.out.println("3- Planifier un rendez-vous");
            System.out.println("4- Afficher les rendez-vous du jour");
            System.out.println("5- Afficher les rendez-vous d'un médecin par jour");
            System.out.println("6- Annuler un rendez-vous");
            System.out.println("7- Quitter");

            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("Entrez le nom du patient");
                    String nomPatient = scanner.nextLine();
                    Patient patient = new Patient(nomPatient);
                    patientService.add(patient);
                    break;
                case 2:
                    System.out.println("Entrez le nom du médecin");
                    String nomMedecin = scanner.nextLine();
                    System.out.println("Entrez la spécialité du médecin");
                    String specialiteMedecin = scanner.nextLine();
                    Medecin medecin = new Medecin(nomMedecin, specialiteMedecin);
                    medecinService.add(medecin);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    break;
            }
        } while (choix != 7);
    }
}
