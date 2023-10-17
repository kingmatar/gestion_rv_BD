package com.ism.service;

import com.ism.repositories.bd.RendezVousRepository;
import com.ism.entities.RendezVous;
import java.util.Date;
import java.util.List;

public class RVServiceImpl implements RVService {
    private RendezVousRepository rvRepository;

    public RVServiceImpl(RendezVousRepository rvRepository) {
        this.rvRepository = rvRepository;
    }

    @Override
    public int planifierRendezVous(int idPatient, int idMedecin, Date date) {
        RendezVous rendezVous = new RendezVous(0, idPatient, idMedecin, date);
        return RendezVousRepository.insererRendezVous(rendezVous);
    }

    @Override
    public int annulerRendezVous(int idRendezVous) {
        return RendezVousRepository.supprimerRendezVous(idRendezVous);
    }

    @Override
    public List<RendezVous> listerRendezVousDuJour(Date date) {
        return RendezVousRepository.getRendezVousDuJour(date);
    }

    @Override
    public List<RendezVous> listerRendezVousMedecinParJour(int idMedecin, Date date) {
        return RendezVousRepository.getRendezVousMedecinParJour(idMedecin, date);
    }

    public void afficherRendezVousDuJour() {
    }

    public void afficherRendezVousMedecinParJour(int idMedecin) {
    }
}

