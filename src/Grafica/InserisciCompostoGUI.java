package Grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserisciCompostoGUI extends InserisciGUI {

    //private JLabel lblNumParziali = new JLabel("Numero parziali:");
    //private JTextField txtNumParziali = new JTextField();
    private JButton btnModificaParziali = new JButton("Modifica Parziali");
    private InserisciParzialiGUI parzialiGUI;
    private int voto = 0;

    public InserisciCompostoGUI() {
        super("Esame Composto",new GridLayout(9,2));
        //parzialiGUI = new MediumFrame("Inserisci Parziali",new GridLayout(10,2));
        super.setCmpVotoFinale(new JLabel(String.valueOf(voto)));
        this.addComponents();

        btnModificaParziali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(parzialiGUI == null)
                    parzialiGUI = new InserisciParzialiGUI();
                else
                    parzialiGUI.setVisible(true);
            }
        });

        parzialiGUI.getBtnSalva().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(parzialiGUI.ctrlPerc()) {
                    parzialiGUI.readPartials();
                    if(parzialiGUI.getLblError().getParent() == parzialiGUI.getJp3())
                        parzialiGUI.getJp3().remove(parzialiGUI.getLblError());
                    parzialiGUI.setVisible(false);
                    calcVoto();
                }
                else
                    parzialiGUI.getJp3().add(parzialiGUI.getLblError());
            }
        });

    }

    /*@Override
    public boolean ctrlTextFields() {
        return super.ctrlTextFields() && !txtNumParziali.getText().isEmpty();
    }*/

    @Override
    public void addComponents() {
        super.addComponents();
        //getJp().add(lblNumParziali); getJp().add(txtNumParziali);
        getJp().add(getBtnInserisci()); getJp().add(getBtnModifica());
        getJp().add(btnModificaParziali); getJp().add(getLblError());
    }

    /*public boolean ctrlNumParziali() {
        return Integer.parseInt(txtNumParziali.getText()) > 1;
    }*/

    public Object[] getDataJtbl() {
        JTextField txtVotoFinale = (JTextField) getCmpVotoFinale();
        Object[] obj = {getTxtNome().getText().toUpperCase(), getTxtCognome().getText().toUpperCase(), getTxtInsegnamento().getText().toUpperCase(),
                Integer.parseInt(String.valueOf(txtVotoFinale.getText())), getTxtNumCrediti().getText().toUpperCase(),
                "Esame Semplice",getRdbtnLode().isSelected()};
        return obj;
    }

    public InserisciParzialiGUI getParzialiGUI() {
        return parzialiGUI;
    }

    public void setParzialiGUI(InserisciParzialiGUI parzialiGUI) {
        this.parzialiGUI = parzialiGUI;
    }

    public JButton getBtnModificaParziali() {
        return btnModificaParziali;
    }

    private void calcVoto() {

    }
}
