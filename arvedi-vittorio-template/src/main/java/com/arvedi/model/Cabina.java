package com.arvedi.model;

public class Cabina {

    private String codice;
    private String ubicazione;

    public Cabina(String codice, String ubicazione) {
        this.codice = codice;
        this.ubicazione = ubicazione;
    }

    public String getCodice() {
        return codice;
    }

    public String getUbicazione() {
        return ubicazione;
    }

    @Override
    public String toString() {
        return codice + " - " + ubicazione;
    }
}