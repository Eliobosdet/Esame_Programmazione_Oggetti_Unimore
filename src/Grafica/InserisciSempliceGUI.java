package Grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class InserisciSempliceGUI extends InserisciGUI {

    private JComboBox cmbxVotoFinale;

    public InserisciSempliceGUI() {
        super("Esame Semplice", new GridLayout(8, 2));
        setCmbxVotoFinale();
        addComponents();
        cmbxVotoFinale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("cmbox cambiata");
                if (String.valueOf(cmbxVotoFinale.getSelectedIndex()).equals("30"))
                    getRdbtnLode().setEnabled(true);
                else {
                    getRdbtnLode().setSelected(false);
                    getRdbtnLode().setEnabled(false);
                }
            }
        });
    }

    public void setCmbxVotoFinale() {
        Vector<Integer> v = new Vector<>();
        for (int i = 0; i < 31; i++)
            v.add(i);
        cmbxVotoFinale = new JComboBox(v);
        super.setCmpVotoFinale(cmbxVotoFinale);
    }

    public void reopen() {
        super.reopen("Modifica Esame",new GridLayout(8,2));
        cmbxVotoFinale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("cmbox cambiata");
                if (String.valueOf(cmbxVotoFinale.getSelectedIndex()).equals("30"))
                    getRdbtnLode().setEnabled(true);
                else {
                    getRdbtnLode().setSelected(false);
                    getRdbtnLode().setEnabled(false);
                }
            }
        });
    }

    @Override
    public void addComponents() {
        super.addComponents();
        getJp().add(getBtnInserisci());getJp().add(getBtnModifica());
        getJp().add(getLblError());
    }

    /* private void initialize() {
            createFrame();
            addComponents();

            jf.add(jp);
            jf.setVisible(true);
        }*/

    public Object[] getDataJtbl() {
        Object[] obj = {getTxtNome().getText().toUpperCase(), getTxtCognome().getText().toUpperCase(), getTxtInsegnamento().getText().toUpperCase(),
                Integer.parseInt(String.valueOf(cmbxVotoFinale.getSelectedIndex()).toUpperCase()), getTxtNumCrediti().getText().toUpperCase(),
                getRdbtnLode().isSelected(), "Esame Semplice"};
        return obj;
    }








    @Override
    public String toString() {
        String s = "Esame semplice: ";
        for (Object obj : this.getDataJtbl()) {
            s += obj.toString() + ",";
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }
}
