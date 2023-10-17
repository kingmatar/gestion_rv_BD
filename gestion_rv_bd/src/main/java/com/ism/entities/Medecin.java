package com.ism.entities;

import java.util.List;

public class Medecin extends Personne {
    private String specialite;

    public Medecin(int id, String nomComplet, String specialite) {
        super(id, nomComplet);
        this.specialite = specialite;
    }

    public Medecin(String nomComplet, String specialite) {
        super(nomComplet);
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}

