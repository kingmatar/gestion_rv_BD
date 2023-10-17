package com.ism.service;
import com.ism.entities.Personne;
import java.util.List;

public interface PersonneService {
    int creerPatient(String nomComplet, String antecedents);
    int creerMedecin(String nomComplet, String specialite);
    Personne getPersonneParId(int id);
    List<Personne> listerToutesLesPersonnes();
}
