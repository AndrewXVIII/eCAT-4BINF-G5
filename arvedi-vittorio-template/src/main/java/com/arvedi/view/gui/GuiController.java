package com.arvedi.view.gui;

import com.arvedi.controller.AppController;
import com.arvedi.model.Cabina;
import com.arvedi.model.Controllo;
import com.arvedi.model.Intervento;
import com.arvedi.model.Priorita;
import com.arvedi.model.QuadroElettrico;
import com.arvedi.model.Tecnico;
import com.arvedi.model.TipologiaQuadro;

import java.nio.file.Path;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the JavaFX GUI.
 *
 * IMPORTANT:
 *  This is NOT the MVC Controller!
 *  This is the GUI Controller linked to main.fxml.
 *
 * The actual application logic is handled by AppController (MVC).
 * This class only manages GUI components and sends actions to AppController.
 */
public class GuiController {

    private final AppController controller;
    private Intervento selectedIntervento;

    @FXML private TextField txtCabinaCodice;
    @FXML private TextField txtCabinaUbicazione;
    @FXML private Button btnAddCabina;
    @FXML private ListView<Cabina> lstCabine;

    @FXML private TextField txtTecnicoNome;
    @FXML private TextField txtTecnicoCognome;
    @FXML private TextField txtTecnicoAzienda;
    @FXML private Button btnAddTecnico;
    @FXML private ListView<Tecnico> lstTecnici;

    @FXML private ComboBox<Cabina> cbInterventoCabina;
    @FXML private ComboBox<Tecnico> cbInterventoTecnico;
    @FXML private ComboBox<Priorita> cbPriorita;
    @FXML private TextField txtEsito;
    @FXML private TextArea txtNote;
    @FXML private Button btnAddIntervento;
    @FXML private Button btnUpdateIntervento;
    @FXML private Button btnExportInterventi;
    @FXML private ListView<Intervento> lstInterventi;

    @FXML private TextField txtTipologia;
    @FXML private Button btnAddTipologia;
    @FXML private TableView<TipologiaQuadro> tableTipologie;
    @FXML private TableColumn<TipologiaQuadro, String> colTipologiaDescrizione;

    @FXML private TextField txtCodiceQuadro;
    @FXML private ComboBox<Cabina> comboCabina;
    @FXML private ComboBox<TipologiaQuadro> comboTipologia;
    @FXML private Button btnAddQuadro;
    @FXML private TableView<QuadroElettrico> tableQuadri;
    @FXML private TableColumn<QuadroElettrico, String> colQuadroCodice;
    @FXML private TableColumn<QuadroElettrico, Cabina> colQuadroCabina;
    @FXML private TableColumn<QuadroElettrico, TipologiaQuadro> colQuadroTipologia;

    @FXML private TextField txtDescrizioneControllo;
    @FXML private ComboBox<TipologiaQuadro> comboTipologiaControllo;
    @FXML private Button btnAddControllo;
    @FXML private TableView<Controllo> tableControlli;
    @FXML private TableColumn<Controllo, String> colControlloDescrizione;
    @FXML private TableColumn<Controllo, TipologiaQuadro> colControlloTipologia;

    @FXML private Label lblNumeroCabine;
    @FXML private Label lblNumeroTecnici;
    @FXML private Label lblNumeroQuadri;
    @FXML private Label lblNumeroInterventi;
    @FXML private Label lblInterventiOggi;
    @FXML private Label lblInterventiSettimana;
    @FXML private Label lblInterventiMese;
    @FXML private Label lblCabinaPiuInterventi;
    @FXML private Label lblTecnicoPiuInterventi;

    @FXML private ComboBox<Tecnico> comboRicercaTecnico;
    @FXML private ComboBox<Cabina> comboRicercaCabina;
    @FXML private ComboBox<Priorita> comboRicercaPriorita;
    @FXML private TextField txtRicercaData;
    @FXML private Button btnCercaPerTecnico;
    @FXML private Button btnCercaPerCabina;
    @FXML private Button btnCercaPerData;
    @FXML private Button btnCercaPerTecnicoData;
    @FXML private Button btnCercaPerCabinaData;
    @FXML private Button btnCercaPerPrioritaTecnico;
    @FXML private ListView<Intervento> lstRicercaRisultati;

    @FXML private Label lblMessage;

    private final ObservableList<Cabina> cabineItems = FXCollections.observableArrayList();
    private final ObservableList<Tecnico> tecniciItems = FXCollections.observableArrayList();
    private final ObservableList<Intervento> interventiItems = FXCollections.observableArrayList();
    private final ObservableList<TipologiaQuadro> tipologieItems = FXCollections.observableArrayList();
    private final ObservableList<QuadroElettrico> quadriItems = FXCollections.observableArrayList();
    private final ObservableList<Controllo> controlliItems = FXCollections.observableArrayList();
    private final ObservableList<Intervento> ricercaItems = FXCollections.observableArrayList();

    public GuiController(AppController controller) {
        this.controller = controller;
    }

    @FXML
    public void initialize() {
        lstCabine.setItems(cabineItems);
        lstTecnici.setItems(tecniciItems);
        lstInterventi.setItems(interventiItems);
        tableTipologie.setItems(tipologieItems);
        tableQuadri.setItems(quadriItems);
        tableControlli.setItems(controlliItems);
        lstRicercaRisultati.setItems(ricercaItems);

        cbInterventoCabina.setItems(cabineItems);
        cbInterventoTecnico.setItems(tecniciItems);
        cbPriorita.setItems(FXCollections.observableArrayList(Priorita.values()));
        comboCabina.setItems(cabineItems);
        comboTipologia.setItems(tipologieItems);
        comboTipologiaControllo.setItems(tipologieItems);
        comboRicercaTecnico.setItems(tecniciItems);
        comboRicercaCabina.setItems(cabineItems);
        comboRicercaPriorita.setItems(FXCollections.observableArrayList(Priorita.values()));

        colTipologiaDescrizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        colQuadroCodice.setCellValueFactory(new PropertyValueFactory<>("codice"));
        colQuadroCabina.setCellValueFactory(new PropertyValueFactory<>("cabina"));
        colQuadroTipologia.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
        colControlloDescrizione.setCellValueFactory(new PropertyValueFactory<>("descrizione"));
        colControlloTipologia.setCellValueFactory(new PropertyValueFactory<>("tipologia"));

        lstCabine.setPlaceholder(new Label("Non ci sono cabine"));
        lstTecnici.setPlaceholder(new Label("Non ci sono tecnici"));
        lstInterventi.setPlaceholder(new Label("Non ci sono interventi"));
        lstRicercaRisultati.setPlaceholder(new Label("Nessun intervento trovato"));

        lstInterventi.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            selectedIntervento = newValue;
            if (newValue != null) {
                cbInterventoCabina.setValue(newValue.getCabina());
                cbInterventoTecnico.setValue(newValue.getTecnico());
                cbPriorita.setValue(newValue.getPriorita());
                txtEsito.setText(newValue.getEsito());
                txtNote.setText(newValue.getNote());
            }
        });

        refresh();

        btnAddCabina.setOnAction(e -> handleAddCabina());
        btnAddTecnico.setOnAction(e -> handleAddTecnico());
        btnAddIntervento.setOnAction(e -> handleAddIntervento());
        btnUpdateIntervento.setOnAction(e -> handleUpdateIntervento());
        btnExportInterventi.setOnAction(e -> handleExportInterventi());
        btnAddTipologia.setOnAction(e -> handleAddTipologia());
        btnAddQuadro.setOnAction(e -> handleAddQuadro());
        btnAddControllo.setOnAction(e -> handleAddControllo());
        btnCercaPerTecnico.setOnAction(e -> handleSearchByTecnico());
        btnCercaPerCabina.setOnAction(e -> handleSearchByCabina());
        btnCercaPerData.setOnAction(e -> handleSearchByData());
        btnCercaPerTecnicoData.setOnAction(e -> handleSearchByTecnicoData());
        btnCercaPerCabinaData.setOnAction(e -> handleSearchByCabinaData());
        btnCercaPerPrioritaTecnico.setOnAction(e -> handleSearchByPrioritaTecnico());
    }

    private void handleAddCabina() {
        String codice = txtCabinaCodice.getText().trim();
        String ubicazione = txtCabinaUbicazione.getText().trim();

        if (codice.isEmpty() || ubicazione.isEmpty()) {
            showMessage("Compila codice e ubicazione");
            return;
        }

        controller.aggiungiCabina(new Cabina(codice, ubicazione));
        txtCabinaCodice.clear();
        txtCabinaUbicazione.clear();
        showMessage("Cabina inserita");
        refresh();
    }

    private void handleAddTecnico() {
        String nome = txtTecnicoNome.getText().trim();
        String cognome = txtTecnicoCognome.getText().trim();
        String azienda = txtTecnicoAzienda.getText().trim();

        if (nome.isEmpty() || cognome.isEmpty()) {
            showMessage("Compila nome e cognome");
            return;
        }

        if (azienda.isEmpty()) {
            azienda = null;
        }

        controller.aggiungiTecnico(new Tecnico(nome, cognome, azienda));
        txtTecnicoNome.clear();
        txtTecnicoCognome.clear();
        txtTecnicoAzienda.clear();
        showMessage("Tecnico inserito");
        refresh();
    }

    private void handleAddIntervento() {
        Cabina cabina = cbInterventoCabina.getValue();
        Tecnico tecnico = cbInterventoTecnico.getValue();
        Priorita priorita = cbPriorita.getValue();
        String esito = txtEsito.getText().trim();
        String note = txtNote.getText().trim();

        if (controller.getCabine().isEmpty()) {
            showMessage("Prima inserisci almeno una cabina");
            return;
        }

        if (controller.getTecnici().isEmpty()) {
            showMessage("Prima inserisci almeno un tecnico");
            return;
        }

        if (cabina == null || tecnico == null || priorita == null || esito.isEmpty()) {
            showMessage("Seleziona cabina, tecnico, priorita e inserisci esito");
            return;
        }

        controller.aggiungiIntervento(new Intervento(
                LocalDate.now(),
                cabina,
                tecnico,
                esito,
                note,
                priorita
        ));

        cbInterventoCabina.getSelectionModel().clearSelection();
        cbInterventoTecnico.getSelectionModel().clearSelection();
        cbPriorita.getSelectionModel().clearSelection();
        txtEsito.clear();
        txtNote.clear();
        showMessage("Intervento registrato");
        refresh();
    }

    private void handleAddTipologia() {
        String descrizione = txtTipologia.getText().trim();
        if (descrizione.isEmpty()) {
            showMessage("Inserisci una descrizione per la tipologia");
            return;
        }

        controller.aggiungiTipologia(new TipologiaQuadro(descrizione));
        txtTipologia.clear();
        showMessage("Tipologia inserita");
        refresh();
    }

    private void handleAddQuadro() {
        String codice = txtCodiceQuadro.getText().trim();
        Cabina cabina = comboCabina.getValue();
        TipologiaQuadro tipologia = comboTipologia.getValue();

        if (codice.isEmpty() || cabina == null || tipologia == null) {
            showMessage("Compila codice, cabina e tipologia per il quadro");
            return;
        }

        controller.aggiungiQuadro(new QuadroElettrico(codice, cabina, tipologia));
        txtCodiceQuadro.clear();
        comboCabina.getSelectionModel().clearSelection();
        comboTipologia.getSelectionModel().clearSelection();
        showMessage("Quadro inserito");
        refresh();
    }

    private void handleAddControllo() {
        String descrizione = txtDescrizioneControllo.getText().trim();
        TipologiaQuadro tipologia = comboTipologiaControllo.getValue();

        if (descrizione.isEmpty() || tipologia == null) {
            showMessage("Compila descrizione e tipologia per il controllo");
            return;
        }

        controller.aggiungiControllo(new Controllo(descrizione, tipologia));
        txtDescrizioneControllo.clear();
        comboTipologiaControllo.getSelectionModel().clearSelection();
        showMessage("Controllo inserito");
        refresh();
    }

    private void handleSearchByTecnico() {
        Tecnico tecnico = comboRicercaTecnico.getValue();
        if (tecnico == null) {
            showMessage("Seleziona un tecnico per la ricerca");
            return;
        }
        ricercaItems.setAll(controller.cercaPerTecnico(tecnico));
        showMessage("Risultati aggiornati");
    }

    private void handleSearchByCabina() {
        Cabina cabina = comboRicercaCabina.getValue();
        if (cabina == null) {
            showMessage("Seleziona una cabina per la ricerca");
            return;
        }
        ricercaItems.setAll(controller.cercaPerCabina(cabina));
        showMessage("Risultati aggiornati");
    }

    private void handleSearchByData() {
        String testo = txtRicercaData.getText().trim();
        if (testo.isEmpty()) {
            showMessage("Inserisci una data per la ricerca");
            return;
        }
        try {
            LocalDate data = LocalDate.parse(testo);
            ricercaItems.setAll(controller.cercaPerData(data));
            showMessage("Risultati aggiornati");
        } catch (Exception e) {
            showMessage("Formato data non valido, usa YYYY-MM-DD");
        }
    }

    private void handleUpdateIntervento() {
        if (selectedIntervento == null) {
            showMessage("Seleziona un intervento dall'elenco per aggiornare");
            return;
        }

        String esito = txtEsito.getText().trim();
        String note = txtNote.getText().trim();
        Priorita priorita = cbPriorita.getValue();

        if (esito.isEmpty() || priorita == null) {
            showMessage("Inserisci esito e priorita per aggiornare l'intervento");
            return;
        }

        controller.aggiornaIntervento(selectedIntervento, esito, note, priorita);
        showMessage("Intervento aggiornato");
        refresh();
    }

    private void handleExportInterventi() {
        Path file = Path.of("interventi.txt");
        controller.esportaInterventiTxt(file);
        showMessage("Interventi esportati in " + file.toAbsolutePath());
    }

    private void handleSearchByTecnicoData() {
        Tecnico tecnico = comboRicercaTecnico.getValue();
        if (tecnico == null) {
            showMessage("Seleziona un tecnico per la ricerca");
            return;
        }
        String testo = txtRicercaData.getText().trim();
        if (testo.isEmpty()) {
            showMessage("Inserisci una data per la ricerca");
            return;
        }
        try {
            LocalDate data = LocalDate.parse(testo);
            ricercaItems.setAll(controller.cercaPerTecnicoData(tecnico, data));
            showMessage("Risultati aggiornati");
        } catch (Exception e) {
            showMessage("Formato data non valido, usa YYYY-MM-DD");
        }
    }

    private void handleSearchByCabinaData() {
        Cabina cabina = comboRicercaCabina.getValue();
        if (cabina == null) {
            showMessage("Seleziona una cabina per la ricerca");
            return;
        }
        String testo = txtRicercaData.getText().trim();
        if (testo.isEmpty()) {
            showMessage("Inserisci una data per la ricerca");
            return;
        }
        try {
            LocalDate data = LocalDate.parse(testo);
            ricercaItems.setAll(controller.cercaPerCabinaData(cabina, data));
            showMessage("Risultati aggiornati");
        } catch (Exception e) {
            showMessage("Formato data non valido, usa YYYY-MM-DD");
        }
    }

    private void handleSearchByPrioritaTecnico() {
        Priorita priorita = comboRicercaPriorita.getValue();
        Tecnico tecnico = comboRicercaTecnico.getValue();

        if (priorita == null || tecnico == null) {
            showMessage("Seleziona priorita e tecnico per la ricerca");
            return;
        }

        ricercaItems.setAll(controller.cercaPerPrioritaTecnico(priorita, tecnico));
        showMessage("Risultati aggiornati");
    }

    private void refresh() {
        cabineItems.setAll(controller.getCabine());
        tecniciItems.setAll(controller.getTecnici());
        tipologieItems.setAll(controller.getTipologie());
        quadriItems.setAll(controller.getQuadri());
        controlliItems.setAll(controller.getControlli());
        interventiItems.setAll(controller.getInterventi());

        lblNumeroCabine.setText(String.valueOf(controller.numeroCabine()));
        lblNumeroTecnici.setText(String.valueOf(controller.numeroTecnici()));
        lblNumeroQuadri.setText(String.valueOf(controller.numeroQuadri()));
        lblNumeroInterventi.setText(String.valueOf(controller.numeroInterventi()));
        lblInterventiOggi.setText(String.valueOf(controller.interventiOggi()));
        lblInterventiSettimana.setText(String.valueOf(controller.interventiSettimana()));
        lblInterventiMese.setText(String.valueOf(controller.interventiMese()));
        lblCabinaPiuInterventi.setText(controller.cabinaConPiuInterventi() == null ? "Nessuna" : controller.cabinaConPiuInterventi().toString());
        lblTecnicoPiuInterventi.setText(controller.tecnicoConPiuInterventi() == null ? "Nessuno" : controller.tecnicoConPiuInterventi().toString());

        btnAddIntervento.setDisable(controller.getCabine().isEmpty() || controller.getTecnici().isEmpty());
    }

    private void showMessage(String message) {
        lblMessage.setText(message);
    }
}
