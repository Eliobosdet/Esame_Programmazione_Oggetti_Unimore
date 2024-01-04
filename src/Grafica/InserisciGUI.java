package Grafica;

import javax.swing.*;
import java.awt.*;

public abstract class InserisciGUI extends MediumFrame{

    private JLabel lblNome=new JLabel("Nome studente: ");;
    private JLabel lblCognome=new JLabel("Cognome studente: ");
    private JLabel lblInsegnamento=new JLabel("Insegnamento: ");
    private JLabel lblVotoFinale = new JLabel("Voto finale: ");
    private JLabel lblNumCrediti = new JLabel("Numero crediti: ");
    private JLabel lblLode = new JLabel("Lode: ");
    private JTextField txtNome = new JTextField();
    private JTextField txtCognome = new JTextField();
    private JTextField txtInsegnamento = new JTextField();
    private JComponent cmpVotoFinale = new JComponent() {};
    private JTextField txtNumCrediti = new JTextField();
    private JRadioButton rdbtnLode = new JRadioButton();
    private JLabel lblError = new JLabel("Qualcosa è andato storto,Riprova.");
    private JButton btnInserisci = new JButton("Inserisci");
    private JButton btnModifica = new JButton("Salva");

    public InserisciGUI(String titolo) {
        this(titolo,new GridLayout(6,2));
    }

    public InserisciGUI(String titolo, GridLayout gl) {
        super(titolo,gl);
        rdbtnLode.setEnabled(false);
        lblError.setForeground(Color.red);
        lblError.setVisible(false);
        btnModifica.setEnabled(false);
    }

    public void reopen() {
        super.setVisible(true);
        btnModifica.setEnabled(true);
        btnInserisci.setEnabled(false);
    }

    public void addComponents() {
        getJp().add(lblNome);getJp().add(txtNome);
        getJp().add(lblCognome);getJp().add(txtCognome);
        getJp().add(lblInsegnamento);getJp().add(txtInsegnamento);
        getJp().add(lblVotoFinale);getJp().add(cmpVotoFinale);
        getJp().add(lblNumCrediti);getJp().add(txtNumCrediti);
        getJp().add(lblLode);getJp().add(rdbtnLode);
    }

    public boolean ctrlTextFields() {
        if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() ||
                txtInsegnamento.getText().isEmpty() || txtNumCrediti.getText().isEmpty() || !isNumeric(txtNumCrediti.getText()))
            return false;
        return true;
    }

    public boolean ctrlNumCrediti() {
        int n = -1;
        try {
            n = Integer.parseInt(getTxtNumCrediti().getText());
        } catch (NumberFormatException e) {
            System.err.println("Formato dei crediti di tipo non Integer");
            return false;
        }
        if (n < 0) {
            return false;
        }
        return true;
    }

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






    //GETTER E SETTER

    public JTextField getTxtNome() {
        return txtNome;
    }

    public JTextField getTxtCognome() {
        return txtCognome;
    }

    public JTextField getTxtInsegnamento() {
        return txtInsegnamento;
    }

    public JTextField getTxtNumCrediti() {
        return txtNumCrediti;
    }

    public JRadioButton getRdbtnLode() {
        return rdbtnLode;
    }

    public JLabel getLblError() { return lblError; }

    public void setLblError(String s) { this.lblError.setText(s); }

    public JButton getBtnInserisci() {
        return btnInserisci;
    }

    public JButton getBtnModifica() {
        return btnModifica;
    }

    public JComponent getCmpVotoFinale() {
        return cmpVotoFinale;
    }

    public void setCmpVotoFinale(JComponent cmpVotoFinale) {
        this.cmpVotoFinale = cmpVotoFinale;
    }
}
