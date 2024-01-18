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
        actionCmbxVotoFinale();
    }

    public InserisciSempliceGUI(Object[] obj) {
        super("Esame Semplice", new GridLayout(8,2), obj);
        setCmbxVotoFinale();
        this.setComponents(obj);
        actionCmbxVotoFinale();
    }

    public void setCmbxVotoFinale() {
        Vector<Integer> v = new Vector<>();
        for (int i = 18; i < 31; i++)
            v.add(i);
        cmbxVotoFinale = new JComboBox(v);
        super.setCmpVotoFinale(cmbxVotoFinale);
    }

    @Override
    public void reopen() {
        super.reopen();
        super.addComponents();
        actionCmbxVotoFinale();
    }

    public Object[] getDataJtbl() {
        Object[] obj = {removeTrailingSpaces(getTxtNome().getText()), removeTrailingSpaces(getTxtCognome().getText()), removeTrailingSpaces(getTxtInsegnamento().getText()),
                Integer.parseInt(String.valueOf(cmbxVotoFinale.getSelectedIndex()+18)), getTxtNumCrediti().getText(),
                getRdbtnLode().isSelected(),"0"};
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

    @Override
    public void setComponents(Object[] obj) {
        super.setComponents(obj);
        cmbxVotoFinale.setSelectedIndex(Integer.parseInt(String.valueOf(obj[4]))-18);
    }

    private void actionCmbxVotoFinale() {
        cmbxVotoFinale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("cmbox cambiata");
                if ((Integer)cmbxVotoFinale.getItemAt(cmbxVotoFinale.getSelectedIndex())==30)
                    getRdbtnLode().setEnabled(true);
                else {
                    getRdbtnLode().setSelected(false);
                    getRdbtnLode().setEnabled(false);
                }
            }
        });
    }
}
