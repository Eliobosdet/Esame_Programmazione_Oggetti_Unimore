package Grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class InserisciSempliceGUI extends JFrame {

        private JFrame jf = new JFrame("Inserisci Esame Semplice");
        private JPanel jp = new JPanel(new GridLayout(8,2));

        private JLabel lblNome=new JLabel("Nome studente: ");;
        private JLabel lblCognome=new JLabel("Cognome studente: ");
        private JLabel lblInsegnamento=new JLabel("Insegnamento: ");
        private JLabel lblVotoFinale = new JLabel("Voto finale: ");
        private JLabel lblNumCrediti = new JLabel("Numero crediti: ");
        private JLabel lblLode = new JLabel("Lode: ");

        private JTextField txtNome = new JTextField();
        private JTextField txtCognome = new JTextField();
        private JTextField txtInsegnamento = new JTextField();
        private JTextField txtNumCrediti = new JTextField();

        private String s = "Qualcosa è andato storto,Riprova.";
        private JLabel lblError = new JLabel(s);

        private JComboBox cmbxVotoFinale;
        private JRadioButton rdbtnLode = new JRadioButton();
        private JButton btnInserisci = new JButton("Inserisci");
        private JButton btnModificaSemplice = new JButton("Modifica");

        public InserisciSempliceGUI() {
            btnModificaSemplice.setVisible(false);
            initializeCombo();
            initialize();
            cmbxVotoFinale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("cmbox cambiata");
                    if(String.valueOf(cmbxVotoFinale.getSelectedIndex()).equals("30"))
                        rdbtnLode.setEnabled(true);
                    else {
                        rdbtnLode.setSelected(false);
                        rdbtnLode.setEnabled(false);
                    }
                }
            });
        }

        public void reopen() {
            btnInserisci.setVisible(false);
            btnModificaSemplice.setVisible(true);
            initialize();
            cmbxVotoFinale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("cmbox cambiata");
                    if(String.valueOf(cmbxVotoFinale.getSelectedIndex()).equals("30"))
                        rdbtnLode.setEnabled(true);
                    else {
                        rdbtnLode.setSelected(false);
                        rdbtnLode.setEnabled(false);
                    }
                }
            });
        }

        private void initialize() {
            createFrame();
            addComponents();

            rdbtnLode.setEnabled(false);
            lblError.setForeground(Color.red);
            lblError.setVisible(false);

            jf.add(jp);
            jf.setVisible(true);
        }

        public Object[] getDataJtbl() {
            Object[] obj = {txtNome.getText().toUpperCase(),txtCognome.getText().toUpperCase(),txtInsegnamento.getText().toUpperCase(),
            Integer.parseInt(String.valueOf(cmbxVotoFinale.getSelectedIndex()).toUpperCase()), txtNumCrediti.getText().toUpperCase(),
            rdbtnLode.isSelected(),"ESAME SEMPLICE"};
            return obj;
        }


    public boolean ctrlTextFields() {
            if(txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() ||
                    txtInsegnamento.getText().isEmpty() || txtNumCrediti.getText().isEmpty() || !isNumeric(txtNumCrediti.getText()))
                return false;
            return true;
        }

        public boolean ctrlNumCrediti() {
            int n = -1;
            try {
                n = Integer.parseInt(txtNumCrediti.getText());
            } catch (NumberFormatException e) {
                System.err.println("Formato dei crediti di tipo non Integer");
                return false;
            }
            if( n < 0) {
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
        @Override
        public String toString() {
        String s = "Esame semplice: ";
        for (Object obj: this.getDataJtbl()) {
            s += obj.toString() + ",";
        }
        s = s.substring(0,s.length()-1);
        return s;
    }


        private void createFrame() {
        jf.setSize(400,400);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

        private void initializeCombo() {
            Vector<Integer> v = new Vector<>();
            for(int i = 0; i < 31; i++)
                v.add(i);
            cmbxVotoFinale = new JComboBox(v);
        }

        private void addComponents() {
        jp.add(lblNome);jp.add(txtNome);
        jp.add(lblCognome);jp.add(txtCognome);
        jp.add(lblInsegnamento);jp.add(txtInsegnamento);
        jp.add(lblVotoFinale);jp.add(cmbxVotoFinale);
        jp.add(lblNumCrediti);jp.add(txtNumCrediti);
        jp.add(lblLode);jp.add(rdbtnLode);
        jp.add(btnInserisci);jp.add(btnModificaSemplice);
        jp.add(lblError);
    }

    public JFrame getJf() {
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public JPanel getJp() {
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel getLblNome() {
        return lblNome;
    }

    public void setLblNome(JLabel lblNome) {
        this.lblNome = lblNome;
    }

    public JLabel getLblCognome() {
        return lblCognome;
    }

    public void setLblCognome(JLabel lblCognome) {
        this.lblCognome = lblCognome;
    }

    public JLabel getLblInsegnamento() {
        return lblInsegnamento;
    }

    public void setLblInsegnamento(JLabel lblInsegnamento) {
        this.lblInsegnamento = lblInsegnamento;
    }

    public JLabel getLblVotoFinale() {
        return lblVotoFinale;
    }

    public JButton getBtnModificaSemplice() {
        return btnModificaSemplice;
    }

    public void setLblVotoFinale(JLabel lblVotoFinale) {
        this.lblVotoFinale = lblVotoFinale;
    }

    public JLabel getLblNumCrediti() {
        return lblNumCrediti;
    }

    public void setLblNumCrediti(JLabel lblNumCrediti) {
        this.lblNumCrediti = lblNumCrediti;
    }

    public JLabel getLblLode() {
        return lblLode;
    }

    public void setLblLode(JLabel lblLode) {
        this.lblLode = lblLode;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(JTextField txtNome) {
        this.txtNome = txtNome;
    }

    public JTextField getTxtCognome() {
        return txtCognome;
    }

    public void setTxtCognome(JTextField txtCognome) {
        this.txtCognome = txtCognome;
    }

    public JTextField getTxtInsegnamento() {
        return txtInsegnamento;
    }

    public void setTxtInsegnamento(JTextField txtInsegnamento) {
        this.txtInsegnamento = txtInsegnamento;
    }

    public JTextField getTxtNumCrediti() {
        return txtNumCrediti;
    }

    public void setTxtNumCrediti(JTextField txtNumCrediti) {
        this.txtNumCrediti = txtNumCrediti;
    }

    public JLabel getLblError() {
        return lblError;
    }

    public void setLblError(JLabel lblError) {
        this.lblError = lblError;
    }

    public JComboBox getCmbxVotoFinale() {
        return cmbxVotoFinale;
    }

    public void setCmbxVotoFinale(JComboBox cmbxVotoFinale) {
        this.cmbxVotoFinale = cmbxVotoFinale;
    }

    public JRadioButton getRdbtnLode() {
        return rdbtnLode;
    }

    public void setRdbtnLode(JRadioButton rdbtnLode) {
        this.rdbtnLode = rdbtnLode;
    }

    public JButton getBtnInserisci() {
        return btnInserisci;
    }

    public void setBtnInserisci(JButton btnInserisci) {
        this.btnInserisci = btnInserisci;
    }
}
