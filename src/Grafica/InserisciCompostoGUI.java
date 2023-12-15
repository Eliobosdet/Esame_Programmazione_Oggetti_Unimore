package Grafica;

import javax.swing.*;
import java.awt.*;

public class InserisciCompostoGUI extends InserisciGUI {

    private JButton btnModificaParziali = new JButton("Modifica Parziali");
    private MediumFrame parzialiGUI;

    public InserisciCompostoGUI() {
        super("Esame Composto",new GridLayout(9,2));
        parzialiGUI = new MediumFrame("Inserisci Parziali",new GridLayout(10,2));
        super.setCmpVotoFinale(new JLabel("0"));
        this.addComponents();

    }

    @Override
    public void addComponents() {
        super.addComponents();
        getJp().add(getBtnInserisci()); getJp().add(getBtnModifica());
        getJp().add(btnModificaParziali); getJp().add(getLblError());
    }
}
