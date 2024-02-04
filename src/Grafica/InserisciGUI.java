package Grafica;

import javax.swing.*;
import java.awt.*;

/**
 * Classe astratta che fornisce la struttura di base per la GUI di inserimento/modifica di esami.
 */
public abstract class InserisciGUI extends MediumFrame {

    private JLabel lblNome = new JLabel("Nome studente: ");
    private JLabel lblCognome = new JLabel("Cognome studente: ");
    private JLabel lblInsegnamento = new JLabel("Insegnamento: ");
    private JLabel lblVotoFinale = new JLabel("Voto finale: ");
    private JLabel lblNumCrediti = new JLabel("Numero crediti: ");
    private JLabel lblLode = new JLabel("Lode: ");
    private JTextField txtNome = new JTextField();
    private JTextField txtCognome = new JTextField();
    private JTextField txtInsegnamento = new JTextField();
    private JComponent cmpVotoFinale = new JComponent() {
    };
    private JTextField txtNumCrediti = new JTextField();
    private JRadioButton rdbtnLode = new JRadioButton();
    private JButton btnInserisci = new JButton("Inserisci");
    private JButton btnModifica = new JButton("Salva");

    /**
     * Costruttore per la GUI di inserimento/modifica di esami.
     *
     * @param titolo Titolo della finestra
     */
    public InserisciGUI(String titolo) {
        this(titolo, new GridLayout(6, 2));
    }

    /**
     * Costruttore per la GUI di inserimento/modifica di esami.
     *
     * @param titolo Titolo della finestra
     * @param gl     Layout della finestra
     */
    public InserisciGUI(String titolo, GridLayout gl) {
        super(titolo, gl);
        btnModifica.setEnabled(false);
    }

    /**
     * Costruttore per la GUI di inserimento/modifica di esami con dati preimpostati.
     *
     * @param titolo Titolo della finestra
     * @param gl     Layout della finestra
     * @param obj    Array di oggetti contenente i dati preimpostati
     */
    public InserisciGUI(String titolo, GridLayout gl, Object[] obj) {
        super(titolo, gl);
        rdbtnLode.setEnabled(false);
        btnInserisci.setEnabled(false);
    }

    /**
     * Riapre la finestra di inserimento/modifica.
     */
    public void reopen() {
        super.setVisible(true);
        btnModifica.setEnabled(true);
        btnInserisci.setEnabled(false);
    }

    /**
     * Aggiunge i componenti grafici alla finestra.
     */
    public void addComponents() {
        getJp().add(lblNome);
        getJp().add(txtNome);
        getJp().add(lblCognome);
        getJp().add(txtCognome);
        getJp().add(lblInsegnamento);
        getJp().add(txtInsegnamento);
        getJp().add(lblVotoFinale);
        getJp().add(cmpVotoFinale);
        getJp().add(lblNumCrediti);
        getJp().add(txtNumCrediti);
        getJp().add(lblLode);
        getJp().add(rdbtnLode);
        getJp().add(btnInserisci);
        getJp().add(btnModifica);
    }

    /**
     * Controlla che tutti i campi di testo siano compilati correttamente.
     *
     * @return true se i campi sono compilati correttamente, altrimenti false
     */
    public boolean ctrlTextFields() {
        if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() ||
                txtInsegnamento.getText().isEmpty() || txtNumCrediti.getText().isEmpty() || !isNumeric(txtNumCrediti.getText()))
            return false;
        return true;
    }

    /**
     * Controlla che il campo "Numero crediti" sia un numero positivo.
     *
     * @return true se il campo è un numero positivo, altrimenti false
     */
    public boolean ctrlNumCrediti() {
        int n = -1;
        try {
            n = Integer.parseInt(getTxtNumCrediti().getText());
        } catch (NumberFormatException e) {
            System.err.println("Formato dei crediti di tipo non Integer");
            return false;
        }
        return n >= 0;
    }

    /**
     * Verifica se una stringa è un numero.
     *
     * @param strNum Stringa da verificare
     * @return true se la stringa è numerica, altrimenti false
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Ottiene i dati inseriti nella finestra per la tabella degli esami.
     *
     * @return Array di oggetti contenente i dati inseriti
     */
    public Object[] getDataJtbl() {
        Object[] obj = {removeTrailingSpaces(getTxtNome().getText()), removeTrailingSpaces(getTxtCognome().getText()), removeTrailingSpaces(getTxtInsegnamento().getText()),
                Integer.parseInt(String.valueOf(cmpVotoFinale)), getTxtNumCrediti().getText(),
                getRdbtnLode().isSelected()};
        return obj;
    }

    /**
     * Rimuove gli spazi alla fine di una stringa.
     *
     * @param input Stringa da cui rimuovere gli spazi
     * @return Stringa senza spazi alla fine
     */
    public String removeTrailingSpaces(String input) {
        int length = input.length();

        // Trova l'indice del primo carattere non spazio da destra
        int endIndex = length - 1;
        while (endIndex >= 0 && Character.isWhitespace(input.charAt(endIndex))) {
            endIndex--;
        }

        // Estrae la sottostringa senza spazi alla fine
        return input.substring(0, endIndex + 1);
    }

    /**
     * Imposta i componenti della finestra con i dati forniti.
     *
     * @param obj Array di oggetti contenente i dati da impostare
     */
    public void setComponents(Object[] obj) {
        setTxtNome(new JTextField(String.valueOf(obj[0])));
        setTxtCognome(new JTextField(String.valueOf(obj[1])));
        setTxtInsegnamento(new JTextField(String.valueOf(obj[2])));
        setTxtNumCrediti(new JTextField(String.valueOf(obj[4])));
        setRdbtnLode(new JRadioButton(String.valueOf(obj[5])));
    }

    /**
     * Restituisce il campo di testo per il nome.
     *
     * @return Campo di testo per il nome
     */
    public JTextField getTxtNome() {
        return txtNome;
    }

    /**
     * Restituisce il campo di testo per il cognome.
     *
     * @return Campo di testo per il cognome
     */
    public JTextField getTxtCognome() {
        return txtCognome;
    }

    /**
     * Restituisce il campo di testo per l'insegnamento.
     *
     * @return Campo di testo per l'insegnamento
     */
    public JTextField getTxtInsegnamento() {
        return txtInsegnamento;
    }

    /**
     * Restituisce il campo di testo per il numero di crediti.
     *
     * @return Campo di testo per il numero di crediti
     */
    public JTextField getTxtNumCrediti() {
        return txtNumCrediti;
    }

    /**
     * Restituisce il pulsante di opzione per la lode.
     *
     * @return Pulsante di opzione per la lode
     */
    public JRadioButton getRdbtnLode() {
        return rdbtnLode;
    }

    /**
     * Restituisce il pulsante "Inserisci".
     *
     * @return Pulsante "Inserisci"
     */
    public JButton getBtnInserisci() {
        return btnInserisci;
    }

    /**
     * Restituisce il pulsante "Modifica".
     *
     * @return Pulsante "Modifica"
     */
    public JButton getBtnModifica() {
        return btnModifica;
    }

    /**
     * Restituisce il componente grafico per il voto finale.
     *
     * @return Componente grafico per il voto finale
     */
    public JComponent getCmpVotoFinale() {
        return cmpVotoFinale;
    }

    /**
     * Imposta il componente grafico per il voto finale.
     *
     * @param cmpVotoFinale Componente grafico per il voto finale
     */
    public void setCmpVotoFinale(JComponent cmpVotoFinale) {
        this.cmpVotoFinale = cmpVotoFinale;
    }

    /**
     * Imposta il campo di testo per il nome.
     *
     * @param txtNome Campo di testo per il nome
     */
    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    /**
     * Imposta il campo di testo per il cognome.
     *
     * @param txtCognome Campo di testo per il cognome
     */
    public void setTxtCognome(JTextField txtCognome) {
        this.txtCognome = txtCognome;
    }

    /**
     * Imposta il campo di testo per l'insegnamento.
     *
     * @param txtInsegnamento Campo di testo per l'insegnamento
     */
    public void setTxtInsegnamento(JTextField txtInsegnamento) {
        this.txtInsegnamento = txtInsegnamento;
    }

    /**
     * Imposta il campo di testo per il numero di crediti.
     *
     * @param txtNumCrediti Campo di testo per il numero di crediti
     */
    public void setTxtNumCrediti(JTextField txtNumCrediti) {
        this.txtNumCrediti = txtNumCrediti;
    }

    /**
     * Imposta il pulsante di opzione per la lode.
     *
     * @param rdbtnLode Pulsante di opzione per la lode
     */
    public void setRdbtnLode(JRadioButton rdbtnLode) {
        this.rdbtnLode = rdbtnLode;
    }

}
