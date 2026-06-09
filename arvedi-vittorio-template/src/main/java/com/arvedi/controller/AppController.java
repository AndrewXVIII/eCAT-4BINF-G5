package com.arvedi.controller;

import com.arvedi.model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppController {

    private static final Logger logger = Logger.getLogger(AppController.class.getName());
    private static final Path LOG_DIR = Path.of("logs");
    private static final Path DATA_DIR = Path.of("data");
    private static final Path CABINE_FILE = DATA_DIR.resolve("cabine.csv");
    private static final Path TECNICI_FILE = DATA_DIR.resolve("tecnici.csv");
    private static final Path TIPOLOGIE_FILE = DATA_DIR.resolve("tipologie.csv");
    private static final Path QUADRI_FILE = DATA_DIR.resolve("quadri.csv");
    private static final Path CONTROLLI_FILE = DATA_DIR.resolve("controlli.csv");
    private static final Path INTERVENTI_FILE = DATA_DIR.resolve("interventi.csv");

    static {
        try {
            Files.createDirectories(LOG_DIR);
            Files.createDirectories(DATA_DIR);
            FileHandler handler = new FileHandler(LOG_DIR.resolve("app.log").toString(), true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Impossibile inizializzare il logger su file", e);
        }
    }

    private final List<Cabina> cabine;
    private final List<Tecnico> tecnici;
    private final List<TipologiaQuadro> tipologie;
    private final List<QuadroElettrico> quadri;
    private final List<Intervento> interventi;
    private final List<Controllo> controlli;

    public AppController() {
        cabine = new ArrayList<>();
        tecnici = new ArrayList<>();
        tipologie = new ArrayList<>();
        quadri = new ArrayList<>();
        interventi = new ArrayList<>();
        controlli = new ArrayList<>();
        caricaDati();
    }

    // CABINE

    public void aggiungiCabina(Cabina cabina) {
        cabine.add(cabina);
        logger.info("Cabina aggiunta: " + cabina);
        salvaDati();
    }

    public List<Cabina> getCabine() {
        return cabine;
    }

    // TECNICI

    public void aggiungiTecnico(Tecnico tecnico) {
        tecnici.add(tecnico);
        logger.info("Tecnico aggiunto: " + tecnico);
        salvaDati();
    }

    public List<Tecnico> getTecnici() {
        return tecnici;
    }

    // TIPOLOGIE

    public void aggiungiTipologia(TipologiaQuadro tipologia) {
        tipologie.add(tipologia);
        logger.info("Tipologia aggiunta: " + tipologia);
        salvaDati();
    }

    public List<TipologiaQuadro> getTipologie() {
        return tipologie;
    }

    // QUADRI

    public void aggiungiQuadro(QuadroElettrico quadro) {
        quadri.add(quadro);
        logger.info("Quadro aggiunto: " + quadro);
        salvaDati();
    }

    public List<QuadroElettrico> getQuadri() {
        return quadri;
    }

    // CONTROLLI

    public void aggiungiControllo(Controllo controllo) {
        controlli.add(controllo);
        logger.info("Controllo aggiunto: " + controllo);
        salvaDati();
    }

    public List<Controllo> getControlli() {
        return controlli;
    }

    // INTERVENTI

    public void aggiungiIntervento(Intervento intervento) {
        interventi.add(intervento);
        logger.info("Intervento registrato: " + intervento);
        salvaDati();
    }

    public void aggiornaIntervento(Intervento intervento, String esito, String note, Priorita priorita) {
        intervento.setEsito(esito);
        intervento.setNote(note);
        intervento.setPriorita(priorita);
        logger.info("Intervento aggiornato: " + intervento);
        salvaDati();
    }

    public List<Intervento> getInterventi() {
        return interventi;
    }

    public List<Intervento> cercaPerTecnico(Tecnico tecnico) {
        List<Intervento> risultato = new ArrayList<>();
        for (Intervento intervento : interventi) {
            if (intervento.getTecnico().equals(tecnico)) {
                risultato.add(intervento);
            }
        }
        return risultato;
    }

    public List<Intervento> cercaPerCabina(Cabina cabina) {
        List<Intervento> risultato = new ArrayList<>();
        for (Intervento intervento : interventi) {
            if (intervento.getCabina().equals(cabina)) {
                risultato.add(intervento);
            }
        }
        return risultato;
    }

    public List<Intervento> cercaPerData(LocalDate data) {
        List<Intervento> risultato = new ArrayList<>();
        for (Intervento intervento : interventi) {
            if (intervento.getData().equals(data)) {
                risultato.add(intervento);
            }
        }
        return risultato;
    }

    public List<Intervento> cercaPerTecnicoData(Tecnico tecnico, LocalDate data) {
        List<Intervento> risultato = new ArrayList<>();
        for (Intervento intervento : interventi) {
            if (intervento.getTecnico().equals(tecnico) && intervento.getData().equals(data)) {
                risultato.add(intervento);
            }
        }
        return risultato;
    }

    public List<Intervento> cercaPerCabinaData(Cabina cabina, LocalDate data) {
        List<Intervento> risultato = new ArrayList<>();
        for (Intervento intervento : interventi) {
            if (intervento.getCabina().equals(cabina) && intervento.getData().equals(data)) {
                risultato.add(intervento);
            }
        }
        return risultato;
    }

    public List<Intervento> cercaPerPrioritaTecnico(Priorita priorita, Tecnico tecnico) {
        List<Intervento> risultato = new ArrayList<>();
        for (Intervento intervento : interventi) {
            if (intervento.getPriorita() == priorita && intervento.getTecnico().equals(tecnico)) {
                risultato.add(intervento);
            }
        }
        return risultato;
    }

    public int numeroCabine() {
        return cabine.size();
    }

    public int numeroTecnici() {
        return tecnici.size();
    }

    public int numeroQuadri() {
        return quadri.size();
    }

    public int numeroInterventi() {
        return interventi.size();
    }

    public int interventiOggi() {
        LocalDate oggi = LocalDate.now();
        int count = 0;
        for (Intervento intervento : interventi) {
            if (intervento.getData().equals(oggi)) {
                count++;
            }
        }
        return count;
    }

    public int interventiSettimana() {
        LocalDate oggi = LocalDate.now();
        int count = 0;
        for (Intervento intervento : interventi) {
            long days = ChronoUnit.DAYS.between(intervento.getData(), oggi);
            if (days >= 0 && days < 7) {
                count++;
            }
        }
        return count;
    }

    public int interventiMese() {
        LocalDate oggi = LocalDate.now();
        int count = 0;
        for (Intervento intervento : interventi) {
            if (intervento.getData().getYear() == oggi.getYear() && intervento.getData().getMonth() == oggi.getMonth()) {
                count++;
            }
        }
        return count;
    }

    public Cabina cabinaConPiuInterventi() {
        Map<Cabina, Integer> conteggi = new HashMap<>();
        for (Intervento intervento : interventi) {
            conteggi.merge(intervento.getCabina(), 1, Integer::sum);
        }
        return conteggi.entrySet().stream()
                .max((a, b) -> Integer.compare(a.getValue(), b.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Tecnico tecnicoConPiuInterventi() {
        Map<Tecnico, Integer> conteggi = new HashMap<>();
        for (Intervento intervento : interventi) {
            conteggi.merge(intervento.getTecnico(), 1, Integer::sum);
        }
        return conteggi.entrySet().stream()
                .max((a, b) -> Integer.compare(a.getValue(), b.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public void esportaInterventiTxt(Path target) {
        List<String> lines = new ArrayList<>();
        for (Intervento intervento : interventi) {
            lines.add(intervento.toString());
        }
        try {
            Files.write(target, lines, StandardCharsets.UTF_8);
            logger.info("Interventi esportati su " + target);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Errore durante l'esportazione degli interventi", e);
        }
    }

    private void caricaDati() {
        try {
            if (Files.exists(CABINE_FILE)) {
                for (String line : Files.readAllLines(CABINE_FILE, StandardCharsets.UTF_8)) {
                    if (!line.isBlank()) {
                        String[] parts = line.split(";", -1);
                        if (parts.length >= 2) {
                            cabine.add(new Cabina(parts[0], parts[1]));
                        }
                    }
                }
            }
            if (Files.exists(TECNICI_FILE)) {
                for (String line : Files.readAllLines(TECNICI_FILE, StandardCharsets.UTF_8)) {
                    if (!line.isBlank()) {
                        String[] parts = line.split(";", -1);
                        if (parts.length >= 3) {
                            String azienda = parts[2].isBlank() ? null : parts[2];
                            tecnici.add(new Tecnico(parts[0], parts[1], azienda));
                        }
                    }
                }
            }
            if (Files.exists(TIPOLOGIE_FILE)) {
                for (String line : Files.readAllLines(TIPOLOGIE_FILE, StandardCharsets.UTF_8)) {
                    if (!line.isBlank()) {
                        tipologie.add(new TipologiaQuadro(line));
                    }
                }
            }
            if (Files.exists(QUADRI_FILE)) {
                for (String line : Files.readAllLines(QUADRI_FILE, StandardCharsets.UTF_8)) {
                    if (!line.isBlank()) {
                        String[] parts = line.split(";", -1);
                        if (parts.length >= 3) {
                            Cabina cabina = findCabina(parts[1]);
                            TipologiaQuadro tipologia = findTipologia(parts[2]);
                            quadri.add(new QuadroElettrico(parts[0], cabina, tipologia));
                        }
                    }
                }
            }
            if (Files.exists(CONTROLLI_FILE)) {
                for (String line : Files.readAllLines(CONTROLLI_FILE, StandardCharsets.UTF_8)) {
                    if (!line.isBlank()) {
                        String[] parts = line.split(";", -1);
                        if (parts.length >= 2) {
                            Controllo controllo = new Controllo(parts[0], findTipologia(parts[1]));
                            controlli.add(controllo);
                        }
                    }
                }
            }
            if (Files.exists(INTERVENTI_FILE)) {
                for (String line : Files.readAllLines(INTERVENTI_FILE, StandardCharsets.UTF_8)) {
                    if (!line.isBlank()) {
                        String[] parts = line.split(";", -1);
                        if (parts.length >= 8) {
                            LocalDate data = LocalDate.parse(parts[0]);
                            Cabina cabina = findCabina(parts[1]);
                            Tecnico tecnico = findTecnico(parts[2], parts[3], parts[4].isBlank() ? null : parts[4]);
                            String esito = parts[5];
                            String note = parts[6];
                            Priorita priorita = Priorita.valueOf(parts[7]);
                            interventi.add(new Intervento(data, cabina, tecnico, esito, note, priorita));
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Errore durante il caricamento dei dati", e);
        }
    }

    private Cabina findCabina(String codice) {
        return cabine.stream()
                .filter(c -> c.getCodice().equals(codice))
                .findFirst()
                .orElseGet(() -> {
                    Cabina nuova = new Cabina(codice, "");
                    cabine.add(nuova);
                    return nuova;
                });
    }

    private TipologiaQuadro findTipologia(String descrizione) {
        return tipologie.stream()
                .filter(t -> t.getDescrizione().equals(descrizione))
                .findFirst()
                .orElseGet(() -> {
                    TipologiaQuadro nuova = new TipologiaQuadro(descrizione);
                    tipologie.add(nuova);
                    return nuova;
                });
    }

    private Tecnico findTecnico(String nome, String cognome, String azienda) {
        return tecnici.stream()
                .filter(t -> t.getNome().equals(nome) && t.getCognome().equals(cognome) && ((t.getAzienda() == null && azienda == null) || (t.getAzienda() != null && t.getAzienda().equals(azienda))))
                .findFirst()
                .orElseGet(() -> {
                    Tecnico nuovo = new Tecnico(nome, cognome, azienda);
                    tecnici.add(nuovo);
                    return nuovo;
                });
    }

    private void salvaDati() {
        try {
            List<String> cabinelines = new ArrayList<>();
            for (Cabina cabina : cabine) {
                cabinelines.add(cabina.getCodice() + ";" + cabina.getUbicazione());
            }
            Files.write(CABINE_FILE, cabinelines, StandardCharsets.UTF_8);

            List<String> tecnicilines = new ArrayList<>();
            for (Tecnico tecnico : tecnici) {
                tecnicilines.add(tecnico.getNome() + ";" + tecnico.getCognome() + ";" + (tecnico.getAzienda() == null ? "" : tecnico.getAzienda()));
            }
            Files.write(TECNICI_FILE, tecnicilines, StandardCharsets.UTF_8);

            List<String> tipologielines = new ArrayList<>();
            for (TipologiaQuadro tipologia : tipologie) {
                tipologielines.add(tipologia.getDescrizione());
            }
            Files.write(TIPOLOGIE_FILE, tipologielines, StandardCharsets.UTF_8);

            List<String> quadrilines = new ArrayList<>();
            for (QuadroElettrico quadro : quadri) {
                quadrilines.add(quadro.getCodice() + ";" + quadro.getCabina().getCodice() + ";" + quadro.getTipologia().getDescrizione());
            }
            Files.write(QUADRI_FILE, quadrilines, StandardCharsets.UTF_8);

            List<String> controllilines = new ArrayList<>();
            for (Controllo controllo : controlli) {
                controllilines.add(controllo.getDescrizione() + ";" + controllo.getTipologia().getDescrizione());
            }
            Files.write(CONTROLLI_FILE, controllilines, StandardCharsets.UTF_8);

            List<String> interventilines = new ArrayList<>();
            for (Intervento intervento : interventi) {
                interventilines.add(
                        intervento.getData() + ";" +
                                intervento.getCabina().getCodice() + ";" +
                                intervento.getTecnico().getNome() + ";" +
                                intervento.getTecnico().getCognome() + ";" +
                                (intervento.getTecnico().getAzienda() == null ? "" : intervento.getTecnico().getAzienda()) + ";" +
                                intervento.getEsito() + ";" +
                                intervento.getNote() + ";" +
                                intervento.getPriorita()
                );
            }
            Files.write(INTERVENTI_FILE, interventilines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Errore durante il salvataggio dei dati", e);
        }
    }
}
