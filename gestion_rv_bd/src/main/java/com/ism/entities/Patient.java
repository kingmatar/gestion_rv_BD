package com.ism.entities;

public class Patient extends Personne {
    private String antecedents;

    public Patient(int id, String nomComplet, String antecedents) {
        super(id, nomComplet);
        this.antecedents = antecedents;
    }

    public Patient(String nomComplet, String antecedents) {
        super(nomComplet);
        this.antecedents = antecedents;
    }

    public Patient(String nomPatient) {
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }
}
