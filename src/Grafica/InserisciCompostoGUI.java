package Grafica;

import Esami.ProvaParziale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe che gestisce l'interfaccia grafica per l'inserimento/modifica di un esame composto.
 * Estende la classe astratta InserisciGUI.
 */
public class InserisciCompostoGUI extends InserisciGUI {

    // Componenti GUI aggiuntivi
    private JButton btnModificaParziali = new JButton("Modifica Parziali");
    private InserisciParzialiGUI parzialiGUI;
    private int voto = 0;
    private JLabel jl = new JLabel(String.valueOf(voto));

    /**
     * Costruttore senza parametri che inizializza l'interfaccia grafica per l'inserimento di un esame composto.
     */
    public InserisciCompostoGUI() {
        super("Esame Composto", new GridLayout(9, 2));
        super.setCmpVotoFinale(jl);
        this.addComponents();

        // Azione del pulsante Modifica Parziali
        btnModificaParziali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (parzialiGUI == null)
                    parzialiGUI = new InserisciParzialiGUI(jl);
                else
                    parzialiGUI.setVisible(true);
            }
        });
    }

    /**
     * Costruttore che inizializza l'interfaccia grafica per la modifica di un esame composto.
     *
     * @param obj        Array di Object contenente i dati dell'esame
     * @param arrayList  ArrayList di oggetti ProvaParziale contenente i dati delle prove parziali
     */
    public InserisciCompostoGUI(Object[] obj, ArrayList<ProvaParziale> arrayList) {
        this();
        getTxtNome().setText(String.valueOf(obj[0]));
        getTxtCognome().setText(String.valueOf(obj[1]));
        getTxtInsegnamento().setText(String.valueOf(obj[2]));
        jl = (JLabel) getCmpVotoFinale();
        jl.setText("" + obj[3]);
        getTxtNumCrediti().setText(String.valueOf(obj[4]));
        super.getBtnModifica().setEnabled(true);
        super.getBtnInserisci().setEnabled(false);
        parzialiGUI = new InserisciParzialiGUI(jl, arrayList);
    }

    /**
     * Aggiunge i componenti GUI specifici per l'Esame Composto.
     */
    @Override
    public void addComponents() {
        super.addComponents();
        getJp().add(btnModificaParziali);
    }

    /**
     * Metodo per ottenere i dati dall'interfaccia grafica sotto forma di array di Object.
     *
     * @return Array di Object contenente i dati dell'esame inseriti/modificati
     */
    public Object[] getDataJtbl() {
        JLabel lblVotoFinale = (JLabel) getCmpVotoFinale();
        Object[] obj = {getTxtNome().getText(), getTxtCognome().getText(), getTxtInsegnamento().getText(),
                Integer.parseInt(lblVotoFinale.getText()), getTxtNumCrediti().getText(),
                "false", parzialiGUI.getNumParziali()};
        return obj;
    }

    /**
     * Metodo per ottenere l'istanza dell'oggetto InserisciParzialiGUI associato.
     *
     * @return Istanza di InserisciParzialiGUI
     */
    public InserisciParzialiGUI getParzialiGUI() {
        return parzialiGUI;
    }

    /**
     * Metodo per impostare l'istanza dell'oggetto InserisciParzialiGUI associato.
     *
     * @param parzialiGUI Istanza di InserisciParzialiGUI
     */
    public void setParzialiGUI(InserisciParzialiGUI parzialiGUI) {
        this.parzialiGUI = parzialiGUI;
    }

    /**
     * Metodo per ottenere il pulsante Modifica Parziali.
     *
     * @return JButton Modifica Parziali
     */
    public JButton getBtnModificaParziali() {
        return btnModificaParziali;
    }

    /**
     * Metodo per ottenere l'ArrayList di ProvaParziale associato.
     *
     * @return ArrayList di oggetti ProvaParziale
     */
    public ArrayList<ProvaParziale> getArrayListParziali() {
        return parzialiGUI.getPartials();
    }
}
