package com.ism.entities;

import java.util.Date;

public class RendezVous {
    private int id;
    private int idPatient;
    private int idMedecin;
    private Date date;

    public RendezVous(int id, int idPatient, int idMedecin, Date date) {
        this.id = id;
        this.idPatient = idPatient;
        this.idMedecin = idMedecin;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
