package com.ism.entities;

import java.util.Date;

public class Personne {
    private int id;
    private String nomComplet;
    private static int nbr;

    public Personne() {
    }

    public Personne(int id, String nomComplet) {
        this.id = id;
        this.nomComplet = nomComplet;
    }

    public Personne(String nomComplet) {
        this.id = nbr++;
        this.nomComplet = nomComplet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
}

