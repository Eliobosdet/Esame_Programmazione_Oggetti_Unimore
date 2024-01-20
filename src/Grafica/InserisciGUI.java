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
    private JButton btnInserisci = new JButton("Inserisci");
    private JButton btnModifica = new JButton("Salva");

    public InserisciGUI(String titolo) {
        this(titolo,new GridLayout(6,2));
    }

    public InserisciGUI(String titolo, GridLayout gl) {
        super(titolo,gl);
        btnModifica.setEnabled(false);
    }

    public InserisciGUI(String titolo, GridLayout gl, Object[] obj) {
        super(titolo,gl);
        rdbtnLode.setEnabled(false);
        btnInserisci.setEnabled(false);
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
        getJp().add(btnInserisci); getJp().add(btnModifica);
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

    public Object[] getDataJtbl() {
        Object[] obj = {removeTrailingSpaces(getTxtNome().getText()), removeTrailingSpaces(getTxtCognome().getText()), removeTrailingSpaces(getTxtInsegnamento().getText()),
                Integer.parseInt(String.valueOf(cmpVotoFinale)), getTxtNumCrediti().getText(),
                getRdbtnLode().isSelected()};
        return obj;
    }

    public String removeTrailingSpaces(String input) {
        int length = input.length();

        // Trova l'indice del primo carattere non spazio da destra
        int endIndex = length - 1;
        while (endIndex >= 0 && Character.isWhitespace(input.charAt(endIndex))) {
            endIndex--;
        }

        // Estrai la sottostringa senza spazi alla fine
        return input.substring(0, endIndex + 1);
    }

    public void setComponents(Object[] obj) {
        setTxtNome(new JTextField(String.valueOf(obj[0])));
        setTxtCognome(new JTextField(String.valueOf(obj[1])));
        setTxtInsegnamento(new JTextField(String.valueOf(obj[2])));
        setTxtNumCrediti(new JTextField(String.valueOf(obj[4])));
        setRdbtnLode(new JRadioButton(String.valueOf(obj[5])));
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

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public void setTxtCognome(JTextField txtCognome) {
        this.txtCognome = txtCognome;
    }

    public void setTxtInsegnamento(JTextField txtInsegnamento) {
        this.txtInsegnamento = txtInsegnamento;
    }

    public void setTxtNumCrediti(JTextField txtNumCrediti) {
        this.txtNumCrediti = txtNumCrediti;
    }

    public void setRdbtnLode(JRadioButton rdbtnLode) {
        this.rdbtnLode = rdbtnLode;
    }


}
