package Grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Classe che gestisce l'interfaccia grafica per l'inserimento/modifica di un esame semplice.
 * Estende la classe astratta InserisciGUI.
 */
public class InserisciSempliceGUI extends InserisciGUI {

    private JComboBox cmbxVotoFinale;

    /**
     * Costruttore senza parametri che inizializza l'interfaccia grafica per l'inserimento di un esame semplice.
     */
    public InserisciSempliceGUI() {
        super("Esame Semplice", new GridLayout(8, 2));
        setCmbxVotoFinale();
        actionCmbxVotoFinale();
        super.addComponents();
    }

    /**
     * Costruttore che inizializza l'interfaccia grafica per la modifica di un esame semplice.
     *
     * @param obj Array di Object contenente i dati dell'esame
     */
    public InserisciSempliceGUI(Object[] obj) {
        super("Esame Semplice", new GridLayout(8, 2), obj);
        setCmbxVotoFinale();
        this.setComponents(obj);
        super.addComponents();
        actionCmbxVotoFinale();
    }

    /**
     * Metodo per impostare il JComboBox per la scelta del voto finale.
     */
    public void setCmbxVotoFinale() {
        Vector<Integer> v = new Vector<>();
        for (int i = 18; i < 31; i++)
            v.add(i);
        cmbxVotoFinale = new JComboBox(v);
        super.setCmpVotoFinale(cmbxVotoFinale);
    }

    /**
     * Metodo per ottenere i dati dall'interfaccia grafica sotto forma di array di Object.
     *
     * @return Array di Object contenente i dati dell'esame inseriti/modificati
     */
    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {removeTrailingSpaces(getTxtNome().getText()), removeTrailingSpaces(getTxtCognome().getText()), removeTrailingSpaces(getTxtInsegnamento().getText()),
                Integer.parseInt(String.valueOf(cmbxVotoFinale.getSelectedIndex() + 18)), getTxtNumCrediti().getText(),
                getRdbtnLode().isSelected(), "0"};
        return obj;
    }

    /**
     * Metodo toString che restituisce una rappresentazione testuale dell'oggetto.
     *
     * @return Stringa contenente i dati dell'esame
     */
    @Override
    public String toString() {
        String s = "Esame semplice: ";
        for (Object obj : this.getDataJtbl()) {
            s += obj.toString() + ",";
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }

    /**
     * Metodo per impostare i componenti dell'interfaccia grafica con i dati passati.
     *
     * @param obj Array di Object contenente i dati dell'esame
     */
    @Override
    public void setComponents(Object[] obj) {
        super.setComponents(obj);
        cmbxVotoFinale.setSelectedIndex(Integer.parseInt(String.valueOf(obj[3])) - 18);
    }

    /**
     * Metodo per gestire l'azione del JComboBox Voto Finale.
     */
    private void actionCmbxVotoFinale() {
        cmbxVotoFinale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("cmbox cambiata");
                if ((Integer) cmbxVotoFinale.getItemAt(cmbxVotoFinale.getSelectedIndex()) == 30)
                    getRdbtnLode().setEnabled(true);
                else {
                    getRdbtnLode().setSelected(false);
                    getRdbtnLode().setEnabled(false);
                }
            }
        });
    }
}
