package com.ism.service;

import com.ism.repositories.bd.PersonneRepository;
import com.ism.entities.Personne;
import com.ism.entities.Patient;

import java.util.List;

public class PersonneServiceImpl implements PersonneService {
    private PersonneRepository personneRepository;

    public PersonneServiceImpl(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @Override
    public int creerPatient(String nomComplet, String antecedents) {
        Patient patient = new Patient(0, nomComplet, antecedents);
        return personneRepository.insererPersonne(patient);
    }

    @Override
    public int creerMedecin(String nomComplet, String specialite) {
        Medecin medecin = new Medecin(0, nomComplet, specialite);
        return personneRepository.insererPersonne(medecin);
    }

    @Override
    public Personne getPersonneParId(int id) {
        return personneRepository.getPersonneById(id);
    }

    @Override
    public List<Personne> listerToutesLesPersonnes() {
        return personneRepository.getAll();
    }
}
