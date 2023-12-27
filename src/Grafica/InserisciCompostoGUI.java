package Grafica;

import javax.swing.*;
import java.awt.*;

public class InserisciCompostoGUI extends InserisciGUI {

    private JLabel lblNumParziali = new JLabel("Numero parziali:");
    private JTextField txtNumParziali = new JTextField();
    private JButton btnModificaParziali = new JButton("Modifica Parziali");
    private MediumFrame parzialiGUI;

    public InserisciCompostoGUI() {
        super("Esame Composto",new GridLayout(9,2));
        //parzialiGUI = new MediumFrame("Inserisci Parziali",new GridLayout(10,2));
        super.setCmpVotoFinale(new JLabel("0"));
        this.addComponents();

    }

    @Override
    public boolean ctrlTextFields() {
        return super.ctrlTextFields() && txtNumParziali.getText().isEmpty();
    }

    @Override
    public void addComponents() {
        super.addComponents();
        getJp().add(lblNumParziali); getJp().add(txtNumParziali);
        getJp().add(getBtnInserisci()); getJp().add(getBtnModifica());
        getJp().add(btnModificaParziali); getJp().add(getLblError());
    }

    public Object[] getDataJtbl() {
        JTextField txtVotoFinale = (JTextField) getCmpVotoFinale();
        Object[] obj = {getTxtNome().getText().toUpperCase(), getTxtCognome().getText().toUpperCase(), getTxtInsegnamento().getText().toUpperCase(),
                Integer.parseInt(String.valueOf(txtVotoFinale.getText())), getTxtNumCrediti().getText().toUpperCase(),
                "Esame Semplice",getRdbtnLode().isSelected()};
        return obj;
    }

    public MediumFrame getParzialiGUI() {
        return parzialiGUI;
    }

    public void setParzialiGUI(MediumFrame parzialiGUI) {
        this.parzialiGUI = parzialiGUI;
    }
}
