package Grafica;

import Esami.ProvaParziale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InserisciCompostoGUI extends InserisciGUI {

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
                    parzialiGUI = new InserisciParzialiGUI((JLabel) getCmpVotoFinale());
                else
                    parzialiGUI.setVisible(true);
            }
        });
    }

    public InserisciCompostoGUI(Object[] obj) {
        this();
        getTxtNome().setText(String.valueOf(obj[0]));
        getTxtCognome().setText(String.valueOf(obj[1]));
        getTxtInsegnamento().setText(String.valueOf(obj[2]));
        JLabel jl = (JLabel)getCmpVotoFinale(); jl.setText(""+obj[3]);
        getTxtNumCrediti().setText(String.valueOf(obj[4]));
    }

    /*@Override
    public boolean ctrlTextFields() {
        return super.ctrlTextFields() && !txtNumParziali.getText().isEmpty();
    }*/



    @Override
    public void addComponents() {
        super.addComponents();
        getJp().add(btnModificaParziali);
    }

    /*public boolean ctrlNumParziali() {
        return Integer.parseInt(txtNumParziali.getText()) > 1;
    }*/

    public Object[] getDataJtbl() {
        JLabel lblVotoFinale = (JLabel) getCmpVotoFinale();
        Object[] obj = {getTxtNome().getText(), getTxtCognome().getText(), getTxtInsegnamento().getText(),
                Integer.parseInt(lblVotoFinale.getText()), getTxtNumCrediti().getText(),
                "false", parzialiGUI.getNumParziali()};
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

    public ArrayList<ProvaParziale> getArrayListParziali() {
        return parzialiGUI.getPartials();
    }

}
