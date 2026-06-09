package com.arvedi.model;

import java.time.LocalDate;

public class Intervento {

    private LocalDate data;
    private Cabina cabina;
    private Tecnico tecnico;
    private String esito;
    private String note;
    private Priorita priorita;

    public Intervento(
            LocalDate data,
            Cabina cabina,
            Tecnico tecnico,
            String esito,
            String note,
            Priorita priorita) {

        this.data = data;
        this.cabina = cabina;
        this.tecnico = tecnico;
        this.esito = esito;
        this.note = note;
        this.priorita = priorita;
    }

    public LocalDate getData() {
        return data;
    }

    public Cabina getCabina() {
        return cabina;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public String getEsito() {
        return esito;
    }

    public String getNote() {
        return note;
    }

    public Priorita getPriorita() {
        return priorita;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPriorita(Priorita priorita) {
        this.priorita = priorita;
    }

    @Override
    public String toString() {

        return data +
                " | " +
                cabina +
                " | " +
                tecnico +
                " | " +
                esito +
                " | " +
                priorita;
    }
}