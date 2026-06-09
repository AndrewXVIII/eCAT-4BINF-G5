
# 🏁 Project Setup + Template MVC Java + CLI + JavaFX (Modulare)

> **Obiettivo:** partire subito con un progetto Java organizzato in MVC, con doppia interfaccia (CLI/GUI), build Maven e moduli Java (JPMS).

---

## SEZIONE 1 — Project Setup (Come iniziare da GitHub)

### ✅ Prerequisiti
- **Git** installato (Git Bash su Windows va benissimo)
- **Java 21** installato
- **Maven** (già incluso in molti IDE; in alternativa useremo i comandi da terminale)

### 🚀 Passi (studenti)
1. **Aprite il terminale** (Git Bash su Windows / Terminale su macOS o Linux)
2. **Clonate il repository del template** (sostituite l’URL con quello reale) e chiamate il remote "template":
   ```bash
   git clone https://github.com/arvedi/teaching-java-mvc-template.git --origin template
   ```
3. **Entrate nella cartella del progetto** (esempio):
   ```bash
   cd teaching-java-mvc-template
   ```
4. **Create un vostro repository vuoto su GitHub** (dal sito GitHub: New → Repository → Create)
5. **Collegate il progetto al vostro repo** (sostituite con il vostro username e il vostro repo):
   ```bash
   git remote add origin https://github.com/TUO_USERNAME/TUO_REPO.git
   ```
6. **Caricate il progetto sul vostro repo**:
   - Repo vuoto → push normale
     ```bash
     git push --set-upstream origin main
     ```
   - Repo con file già presenti (e volete SOVRASCRIVERE) → push forzato
     ```bash
     git push --force --set-upstream origin main
     ```
7. **Rimuovete il remote “template”** (non serve più):
   ```bash
   git remote rm template
   ```

**Fatto!** Ora il codice è sul VOSTRO GitHub e potete lavorare liberamente (commit/push) senza toccare il repo del template.

---

## SEZIONE 2 — Documentazione per Studenti (Livello Base)

### 🧩 Introduzione
Questo progetto è un **template già pronto** che vi permette di creare applicazioni in Java usando due interfacce diverse:
- **CLI** (Command Line Interface) → interfaccia testuale
- **GUI** (JavaFX) → interfaccia grafica con bottoni

Il progetto segue la struttura **MVC (Model–View–Controller)**, un modo professionale di organizzare il codice.  
È scritto con **Java 21** ed è costruito con **Maven**, uno strumento che gestisce automaticamente librerie e compilazione.

È un progetto perfetto per imparare:
- Come organizzare un'app Java in pacchetti e ruoli chiari
- Come separare la logica dalla grafica
- Come creare un'app grafica con JavaFX
- Come compilare e far girare un progetto moderno

---

### 📦 Struttura del Progetto

```
src/
 ├─ main/
 │   ├─ java/
 │   │    ├─ module-info.java
 │   │    └─ com/arvedi/
 │   │         ├─ app/App.java
 │   │         ├─ controller/AppController.java
 │   │         ├─ model/Counter.java
 │   │         ├─ view/cli/CliView.java
 │   │         └─ view/gui/
 │   │               ├─ GuiApp.java
 │   │               └─ GuiController.java
 │   └─ resources/
 │        └─ view/gui/
 │               ├─ main.fxml
 │               └─ styles.css
 └─ test/
      └─ java/...
```

Ogni cartella ha un ruolo preciso. Vediamole una per una.

---

### 🧠 Cos’è il modello MVC?
**MVC** divide un programma in **3 parti** con ruoli diversi.

**Model — “i dati e le regole”**
- Contiene lo stato dell’applicazione (es: il numero di un contatore)
- Contiene la logica di base

**View — “l’interfaccia che vede l’utente”**
- Può essere testuale (CLI) o grafica (GUI)
- Si limita a mostrare dati e ricevere input

**Controller — “il ponte tra utente e dati”**
- Riceve comandi dalle view
- Modifica il Model
- Ritorna i valori aggiornati

**Perché è utile?**
- Chi fa la GUI non deve conoscere come funziona il Model
- Si può cambiare la GUI senza toccare la logica
- È più semplice da mantenere e far crescere

---

### 📁 I pacchetti (packages)
I pacchetti servono per organizzare il codice e capire “di cosa si occupa” ogni classe.

- **`model`** → classi che rappresentano lo **stato dell’app**
- **`controller`** → logica che manipola il modello
- **`view.cli`** → interfaccia **testuale** (CLI)
- **`view.gui`** → interfaccia **grafica** con JavaFX
- **`app`** → la classe `App.java` (punto di partenza del programma)

---

### 🔧 Cos’è Maven e perché lo usiamo?
Maven è uno strumento che:
- compila il progetto
- scarica librerie automaticamente
- crea eseguibili
- permette comandi come `mvn exec:java` e `mvn javafx:run`

Il file più importante è `pom.xml`, dove diciamo:
- quale versione di Java usare
- quali librerie scaricare (JavaFX, JUnit...)
- come eseguire l’app

---

### 🎨 Come funziona JavaFX
JavaFX è una libreria per fare GUI moderne in Java.  
È composta da moduli come:
- `javafx.controls` (bottoni, label, finestre)
- `javafx.fxml` (caricamento di interfacce da file `.fxml`)

Nel nostro progetto:
- `main.fxml` descrive la struttura della finestra
- `GuiController.java` contiene il codice che risponde ai bottoni
- `GuiApp.java` avvia l’app JavaFX

---

### 🧩 Cos’è `module-info.java` (spiegazione semplice)
In Java moderno (da Java 9), il codice può essere diviso in **moduli**.  
Un modulo è come una scatola che dice:
- quali librerie usa (`requires`)
- quali pacchetti mostra agli altri (`exports`)
- a quali pacchetti dare accesso “riflessivo” (per FXML → `opens ... to javafx.fxml`)

Nel nostro progetto serve soprattutto per **far partire JavaFX correttamente** ed evitare avvisi.

---

### 🧭 Flusso del programma
1. Parte `App.java`
2. Se non passi argomenti, compare il menu:
   - **1** = CLI (testo)
   - **2** = GUI (JavaFX)
3. Se scegli GUI → parte JavaFX, carica l’FXML e il `GuiController`
4. Se scegli CLI → parte la shell testuale

Entrambe le interfacce usano **lo stesso Controller** e **lo stesso Model**.


### ▶️ Esecuzione (Eclipse + Maven)

**Avvio con menu (scegli CLI/GUI):**
```bash
mvn exec:java
```

**Avvio diretto della GUI (JavaFX):**
```bash
mvn javafx:run
```

**Esecuzione dei test (JUnit):**
```bash
mvn test
```

> Suggerimento: in Eclipse potete creare una *Run Configuration* di tipo **Maven Build** con goal `exec:java` (menu) o `javafx:run` (GUI).

---

## ✅ Conclusione
Con questo template potete concentrarvi **sulla logica e sull’interfaccia**, senza perdere tempo a configurare librerie e build.  
La struttura MVC vi aiuta a crescere il progetto in modo ordinato: più funzionalità, più schermate, più test.

Buon lavoro!