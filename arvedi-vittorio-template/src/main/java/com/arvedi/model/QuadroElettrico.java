package com.arvedi.model;

public class QuadroElettrico {

    private String codice;
    private Cabina cabina;
    private TipologiaQuadro tipologia;

    public QuadroElettrico(
            String codice,
            Cabina cabina,
            TipologiaQuadro tipologia) {

        this.codice = codice;
        this.cabina = cabina;
        this.tipologia = tipologia;
    }

    public String getCodice() {
        return codice;
    }

    public Cabina getCabina() {
        return cabina;
    }

    public TipologiaQuadro getTipologia() {
        return tipologia;
    }

    @Override
    public String toString() {
        return codice + " | " + cabina + " | " + tipologia;
    }
}