package com.arvedi.model;

public class TipologiaQuadro {

    private String descrizione;

    public TipologiaQuadro(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return descrizione;
    }
}