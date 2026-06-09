package com.arvedi.model;

public class Controllo {

    private String descrizione;
    private TipologiaQuadro tipologia;

    public Controllo(String descrizione, TipologiaQuadro tipologia) {
        this.descrizione = descrizione;
        this.tipologia = tipologia;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public TipologiaQuadro getTipologia() {
        return tipologia;
    }

    @Override
    public String toString() {
        return descrizione + " - " + tipologia;
    }
}

