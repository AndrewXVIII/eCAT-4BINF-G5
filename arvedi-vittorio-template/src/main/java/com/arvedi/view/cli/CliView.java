package com.arvedi.view.cli;

import com.arvedi.controller.AppController;
import com.arvedi.model.*;



import java.nio.file.Path;
import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;

public class CliView {

    private final AppController controller;
    private final Scanner scanner;

    public CliView(AppController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int scelta;

        do {
            System.out.println("\n=== GESTIONALE CABINE ===");
            System.out.println("1. Inserisci Cabina");
            System.out.println("2. Visualizza Cabine");
            System.out.println("3. Inserisci Tecnico");
            System.out.println("4. Visualizza Tecnici");
            System.out.println("5. Inserisci Intervento");
            System.out.println("6. Visualizza Interventi");
            System.out.println("7. Inserisci Tipologia");
            System.out.println("8. Visualizza Tipologie");
            System.out.println("9. Inserisci Quadro");
            System.out.println("10. Visualizza Quadri");
            System.out.println("11. Inserisci Controllo");
            System.out.println("12. Visualizza Controlli");
            System.out.println("13. Dashboard");
            System.out.println("14. Ricerca Interventi");
            System.out.println("15. Modifica Intervento");
            System.out.println("16. Esporta Interventi");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            String input = scanner.nextLine().trim();
            scelta = parseInt(input);

            switch (scelta) {
                case 1:
                    inserisciCabina();
                    break;
                case 2:
                    mostraCabine();
                    break;
                case 3:
                    inserisciTecnico();
                    break;
                case 4:
                    mostraTecnici();
                    break;
                case 5:
                    inserisciIntervento();
                    break;
                case 6:
                    mostraInterventi();
                    break;
                case 7:
                    inserisciTipologia();
                    break;
                case 8:
                    mostraTipologie();
                    break;
                case 9:
                    inserisciQuadro();
                    break;
                case 10:
                    mostraQuadri();
                    break;
                case 11:
                    inserisciControllo();
                    break;
                case 12:
                    mostraControlli();
                    break;
                case 13:
                    dashboard();
                    break;
                case 14:
                    ricercaInterventi();
                    break;
                case 15:
                    modificaIntervento();
                    break;
                case 16:
                    esportaInterventi();
                    break;
                case 0:
                    System.out.println("Arrivederci");
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
        } while (scelta != 0);
    }

    private int parseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void inserisciCabina() {
        System.out.print("Codice cabina: ");
        String codice = scanner.nextLine();

        System.out.print("Ubicazione: ");
        String ubicazione = scanner.nextLine();

        controller.aggiungiCabina(new Cabina(codice, ubicazione));
        System.out.println("Cabina inserita");
    }

    private void mostraCabine() {
        System.out.println("\n--- CABINE ---");
        for (Cabina c : controller.getCabine()) {
            System.out.println(c);
        }
    }

    private void inserisciTecnico() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Cognome: ");
        String cognome = scanner.nextLine();

        System.out.print("Azienda (vuoto se interno): ");
        String azienda = scanner.nextLine().trim();
        if (azienda.isEmpty()) {
            azienda = null;
        }

        controller.aggiungiTecnico(new Tecnico(nome, cognome, azienda));
        System.out.println("Tecnico inserito");
    }

    private void mostraTecnici() {
        System.out.println("\n--- TECNICI ---");
        for (Tecnico t : controller.getTecnici()) {
            System.out.println(t);
        }
    }

    private void inserisciIntervento() {
        if (controller.getCabine().isEmpty()) {
            System.out.println("Inserire prima almeno una cabina");
            return;
        }
        if (controller.getTecnici().isEmpty()) {
            System.out.println("Inserire prima almeno un tecnico");
            return;
        }

        System.out.println("\nCABINE:");
        for (int i = 0; i < controller.getCabine().size(); i++) {
            System.out.println(i + " - " + controller.getCabine().get(i));
        }
        System.out.print("Seleziona cabina: ");
        int cabinaIndex = parseInt(scanner.nextLine());
        if (cabinaIndex < 0 || cabinaIndex >= controller.getCabine().size()) {
            System.out.println("Indice cabina non valido");
            return;
        }
        Cabina cabina = controller.getCabine().get(cabinaIndex);

        System.out.println("\nTECNICI:");
        for (int i = 0; i < controller.getTecnici().size(); i++) {
            System.out.println(i + " - " + controller.getTecnici().get(i));
        }
        System.out.print("Seleziona tecnico: ");
        int tecnicoIndex = parseInt(scanner.nextLine());
        if (tecnicoIndex < 0 || tecnicoIndex >= controller.getTecnici().size()) {
            System.out.println("Indice tecnico non valido");
            return;
        }
        Tecnico tecnico = controller.getTecnici().get(tecnicoIndex);

        System.out.print("Esito: ");
        String esito = scanner.nextLine();

        System.out.print("Note: ");
        String note = scanner.nextLine();

        System.out.print("Priorita (BASSA, MEDIA, ALTA): ");
        Priorita priorita;
        try {
            priorita = Priorita.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (Exception e) {
            priorita = Priorita.MEDIA;
        }

        controller.aggiungiIntervento(new Intervento(
                LocalDate.now(),
                cabina,
                tecnico,
                esito,
                note,
                priorita
        ));

        System.out.println("Intervento registrato");
    }

    private void mostraInterventi() {
        System.out.println("\n--- INTERVENTI ---");
        for (Intervento i : controller.getInterventi()) {
            System.out.println(i);
        }
    }

    private void modificaIntervento() {
        if (controller.getInterventi().isEmpty()) {
            System.out.println("Nessun intervento disponibile");
            return;
        }

        mostraInterventi();
        System.out.print("Seleziona intervento da modificare: ");
        int index = parseInt(scanner.nextLine());
        if (index < 0 || index >= controller.getInterventi().size()) {
            System.out.println("Indice intervento non valido");
            return;
        }

        Intervento intervento = controller.getInterventi().get(index);
        System.out.println("Intervento selezionato: " + intervento);

        System.out.print("Nuovo esito (lascia vuoto per mantenere): ");
        String esito = scanner.nextLine();
        if (esito.isBlank()) {
            esito = intervento.getEsito();
        }

        System.out.print("Nuove note (lascia vuoto per mantenere): ");
        String note = scanner.nextLine();
        if (note.isBlank()) {
            note = intervento.getNote();
        }

        System.out.print("Nuova priorita (BASSA, MEDIA, ALTA, vuoto per mantenere): ");
        String prioritaInput = scanner.nextLine().trim().toUpperCase();
        Priorita priorita = intervento.getPriorita();
        if (!prioritaInput.isEmpty()) {
            try {
                priorita = Priorita.valueOf(prioritaInput);
            } catch (Exception e) {
                System.out.println("Priorita non valida, mantenuta la precedente");
            }
        }

        controller.aggiornaIntervento(intervento, esito, note, priorita);
        System.out.println("Intervento aggiornato");
    }

    private void inserisciTipologia() {
        System.out.print("Descrizione tipologia: ");
        String descrizione = scanner.nextLine();
        controller.aggiungiTipologia(new TipologiaQuadro(descrizione));
        System.out.println("Tipologia inserita");
    }

    private void mostraTipologie() {
        System.out.println("\n--- TIPOLOGIE ---");
        for (TipologiaQuadro t : controller.getTipologie()) {
            System.out.println(t);
        }
    }

    private void inserisciQuadro() {
        if (controller.getCabine().isEmpty()) {
            System.out.println("Inserire prima almeno una cabina");
            return;
        }
        if (controller.getTipologie().isEmpty()) {
            System.out.println("Inserire prima almeno una tipologia");
            return;
        }

        System.out.print("Codice quadro: ");
        String codice = scanner.nextLine();

        System.out.println("\nCABINE:");
        for (int i = 0; i < controller.getCabine().size(); i++) {
            System.out.println(i + " - " + controller.getCabine().get(i));
        }
        System.out.print("Seleziona cabina: ");
        int cabinaIndex = parseInt(scanner.nextLine());
        if (cabinaIndex < 0 || cabinaIndex >= controller.getCabine().size()) {
            System.out.println("Indice cabina non valido");
            return;
        }
        Cabina cabina = controller.getCabine().get(cabinaIndex);

        System.out.println("\nTIPOLOGIE:");
        for (int i = 0; i < controller.getTipologie().size(); i++) {
            System.out.println(i + " - " + controller.getTipologie().get(i));
        }
        System.out.print("Seleziona tipologia: ");
        int tipologiaIndex = parseInt(scanner.nextLine());
        if (tipologiaIndex < 0 || tipologiaIndex >= controller.getTipologie().size()) {
            System.out.println("Indice tipologia non valido");
            return;
        }
        TipologiaQuadro tipologia = controller.getTipologie().get(tipologiaIndex);

        controller.aggiungiQuadro(new QuadroElettrico(codice, cabina, tipologia));
        System.out.println("Quadro inserito");
    }

    private void mostraQuadri() {
        System.out.println("\n--- QUADRI ---");
        for (QuadroElettrico q : controller.getQuadri()) {
            System.out.println(q);
        }
    }

    private void inserisciControllo() {
        if (controller.getTipologie().isEmpty()) {
            System.out.println("Inserire prima almeno una tipologia");
            return;
        }

        System.out.print("Descrizione controllo: ");
        String descrizione = scanner.nextLine();

        System.out.println("\nTIPOLOGIE:");
        for (int i = 0; i < controller.getTipologie().size(); i++) {
            System.out.println(i + " - " + controller.getTipologie().get(i));
        }
        System.out.print("Seleziona tipologia: ");
        int tipologiaIndex = parseInt(scanner.nextLine());
        if (tipologiaIndex < 0 || tipologiaIndex >= controller.getTipologie().size()) {
            System.out.println("Indice tipologia non valido");
            return;
        }
        TipologiaQuadro tipologia = controller.getTipologie().get(tipologiaIndex);

        controller.aggiungiControllo(new Controllo(descrizione, tipologia));
        System.out.println("Controllo inserito");
    }

    private void mostraControlli() {
        System.out.println("\n--- CONTROLLI ---");
        for (Controllo c : controller.getControlli()) {
            System.out.println(c);
        }
    }

    private void dashboard() {
        System.out.println("\n--- DASHBOARD ---");
        System.out.println("Cabine: " + controller.numeroCabine());
        System.out.println("Tecnici: " + controller.numeroTecnici());
        System.out.println("Quadri: " + controller.numeroQuadri());
        System.out.println("Interventi: " + controller.numeroInterventi());
        System.out.println("Interventi oggi: " + controller.interventiOggi());
        System.out.println("Interventi settimana: " + controller.interventiSettimana());
        System.out.println("Interventi mese: " + controller.interventiMese());
        System.out.println("Cabina con più interventi: " + formatObject(controller.cabinaConPiuInterventi()));
        System.out.println("Tecnico con più interventi: " + formatObject(controller.tecnicoConPiuInterventi()));
    }

    private void ricercaInterventi() {
        System.out.println("\n--- RICERCA INTERVENTI ---");
        System.out.println("1. Per tecnico");
        System.out.println("2. Per cabina");
        System.out.println("3. Per data");
        System.out.println("4. Per tecnico + data");
        System.out.println("5. Per cabina + data");
        System.out.println("6. Per priorita + tecnico");
        System.out.print("Seleziona opzione: ");

        int scelta = parseInt(scanner.nextLine());
        switch (scelta) {
            case 1:
                cercaPerTecnico();
                break;
            case 2:
                cercaPerCabina();
                break;
            case 3:
                cercaPerData();
                break;
            case 4:
                cercaPerTecnicoData();
                break;
            case 5:
                cercaPerCabinaData();
                break;
            case 6:
                cercaPerPrioritaTecnico();
                break;
            default:
                System.out.println("Scelta non valida");
        }
    }

    private void cercaPerTecnico() {
        if (controller.getTecnici().isEmpty()) {
            System.out.println("Non ci sono tecnici disponibili");
            return;
        }

        System.out.println("\nTECNICI:");
        for (int i = 0; i < controller.getTecnici().size(); i++) {
            System.out.println(i + " - " + controller.getTecnici().get(i));
        }
        System.out.print("Seleziona tecnico: ");
        int index = parseInt(scanner.nextLine());
        if (index < 0 || index >= controller.getTecnici().size()) {
            System.out.println("Indice tecnico non valido");
            return;
        }
        Tecnico tecnico = controller.getTecnici().get(index);
        mostraElencoInterventi(controller.cercaPerTecnico(tecnico));
    }

    private void cercaPerCabina() {
        if (controller.getCabine().isEmpty()) {
            System.out.println("Non ci sono cabine disponibili");
            return;
        }

        System.out.println("\nCABINE:");
        for (int i = 0; i < controller.getCabine().size(); i++) {
            System.out.println(i + " - " + controller.getCabine().get(i));
        }
        System.out.print("Seleziona cabina: ");
        int index = parseInt(scanner.nextLine());
        if (index < 0 || index >= controller.getCabine().size()) {
            System.out.println("Indice cabina non valido");
            return;
        }
        Cabina cabina = controller.getCabine().get(index);
        mostraElencoInterventi(controller.cercaPerCabina(cabina));
    }

    private void cercaPerData() {
        System.out.print("Data (YYYY-MM-DD): ");
        String testo = scanner.nextLine();
        try {
            LocalDate data = LocalDate.parse(testo);
            mostraElencoInterventi(controller.cercaPerData(data));
        } catch (Exception e) {
            System.out.println("Formato data non valido");
        }
    }

    private void cercaPerTecnicoData() {
        if (controller.getTecnici().isEmpty()) {
            System.out.println("Non ci sono tecnici disponibili");
            return;
        }
        if (controller.getInterventi().isEmpty()) {
            System.out.println("Non ci sono interventi disponibili");
            return;
        }

        System.out.println("\nTECNICI:");
        for (int i = 0; i < controller.getTecnici().size(); i++) {
            System.out.println(i + " - " + controller.getTecnici().get(i));
        }
        System.out.print("Seleziona tecnico: ");
        int index = parseInt(scanner.nextLine());
        if (index < 0 || index >= controller.getTecnici().size()) {
            System.out.println("Indice tecnico non valido");
            return;
        }
        Tecnico tecnico = controller.getTecnici().get(index);
        System.out.print("Data (YYYY-MM-DD): ");
        String testo = scanner.nextLine();
        try {
            LocalDate data = LocalDate.parse(testo);
            mostraElencoInterventi(controller.cercaPerTecnicoData(tecnico, data));
        } catch (Exception e) {
            System.out.println("Formato data non valido");
        }
    }

    private void cercaPerCabinaData() {
        if (controller.getCabine().isEmpty()) {
            System.out.println("Non ci sono cabine disponibili");
            return;
        }
        if (controller.getInterventi().isEmpty()) {
            System.out.println("Non ci sono interventi disponibili");
            return;
        }

        System.out.println("\nCABINE:");
        for (int i = 0; i < controller.getCabine().size(); i++) {
            System.out.println(i + " - " + controller.getCabine().get(i));
        }
        System.out.print("Seleziona cabina: ");
        int index = parseInt(scanner.nextLine());
        if (index < 0 || index >= controller.getCabine().size()) {
            System.out.println("Indice cabina non valido");
            return;
        }
        Cabina cabina = controller.getCabine().get(index);
        System.out.print("Data (YYYY-MM-DD): ");
        String testo = scanner.nextLine();
        try {
            LocalDate data = LocalDate.parse(testo);
            mostraElencoInterventi(controller.cercaPerCabinaData(cabina, data));
        } catch (Exception e) {
            System.out.println("Formato data non valido");
        }
    }

    private void cercaPerPrioritaTecnico() {
        if (controller.getTecnici().isEmpty()) {
            System.out.println("Non ci sono tecnici disponibili");
            return;
        }
        if (controller.getInterventi().isEmpty()) {
            System.out.println("Non ci sono interventi disponibili");
            return;
        }

        System.out.print("Priorita (BASSA, MEDIA, ALTA): ");
        Priorita priorita;
        try {
            priorita = Priorita.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (Exception e) {
            System.out.println("Priorita non valida");
            return;
        }

        System.out.println("\nTECNICI:");
        for (int i = 0; i < controller.getTecnici().size(); i++) {
            System.out.println(i + " - " + controller.getTecnici().get(i));
        }
        System.out.print("Seleziona tecnico: ");
        int index = parseInt(scanner.nextLine());
        if (index < 0 || index >= controller.getTecnici().size()) {
            System.out.println("Indice tecnico non valido");
            return;
        }
        Tecnico tecnico = controller.getTecnici().get(index);
        mostraElencoInterventi(controller.cercaPerPrioritaTecnico(priorita, tecnico));
    }

    private void esportaInterventi() {
        Path out = Path.of("interventi.txt");
        controller.esportaInterventiTxt(out);
        System.out.println("Interventi esportati in " + out.toAbsolutePath());
    }

    private void mostraElencoInterventi(List<Intervento> interventi) {
        if (interventi.isEmpty()) {
            System.out.println("Nessun intervento trovato");
            return;
        }
        System.out.println("\n--- RISULTATI ---");
        for (Intervento intervento : interventi) {
            System.out.println(intervento);
        }
    }

    private String formatObject(Object object) {
        return object == null ? "Nessuno" : object.toString();
    }
}
