package com.ism.service;
import com.ism.entities.RendezVous;
import java.util.Date;
import java.util.List;

public interface RVService {
    int planifierRendezVous(int idPatient, int idMedecin, Date date);
    int annulerRendezVous(int idRendezVous);
    List<RendezVous> listerRendezVousDuJour(Date date);
    List<RendezVous> listerRendezVousMedecinParJour(int idMedecin, Date date);
}

