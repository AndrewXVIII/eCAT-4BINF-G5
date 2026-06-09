package com.arvedi.model;

public class Tecnico {

    private String nome;
    private String cognome;
    private String azienda;

    public Tecnico(String nome, String cognome) {
        this(nome, cognome, null);
    }

    public Tecnico(String nome, String cognome, String azienda) {
        this.nome = nome;
        this.cognome = cognome;
        this.azienda = azienda;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getAzienda() {
        return azienda;
    }

    public boolean isEsterno() {
        return azienda != null && !azienda.isBlank();
    }

    @Override
    public String toString() {
        if (isEsterno()) {
            return nome + " " + cognome + " (" + azienda + ")";
        }
        return nome + " " + cognome;
    }
}