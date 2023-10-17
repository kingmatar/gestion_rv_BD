package com.ism.entities;


public abstract class AbstractEntity {
    private int id;
    private String nomComplet;

    public AbstractEntity() {
        // Constructeur par défaut
    }

    public AbstractEntity(int id, String nomComplet) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AbstractEntity{" +
               "id=" + id +
               ", nomComplet='" + nomComplet + '\'' +
               '}';
    }
}
