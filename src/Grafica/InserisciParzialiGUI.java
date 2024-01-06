package Grafica;

import Esami.ProvaParziale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class InserisciParzialiGUI extends JFrame{
    int numParziali = 2;
    ArrayList<JComponent> components = new ArrayList<>();
    ArrayList<ProvaParziale> partials = new ArrayList<>();

    JComboBox<Integer> cmbxNumParziali;
    JButton btnSalva = new JButton("Salva");
    JLabel lblError = new JLabel("Percentuali non corrette.");

    GridLayout gl = new GridLayout(numParziali,3);
    JPanel container = new JPanel();
    JPanel jp1 = new JPanel() , jp2 = new JPanel(), jp3 = new JPanel(), jp4 = new JPanel();

    public InserisciParzialiGUI() {
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));

        jp1.setLayout(new BorderLayout());
        jp1.setMaximumSize(new Dimension(800,300));
        setCmbxNumParziali();

        jp1.add(cmbxNumParziali, BorderLayout.CENTER);

        jp2.setLayout(new GridLayout(1,3));
        jp2.setMaximumSize(new Dimension(800,200));
        jp2.add(new JLabel("Numero parziale"));
        jp2.add(new JLabel("Voto parziale(0-30)"));
        jp2.add(new JLabel("Percentuale parziale(%)"));

        jp3.setLayout(gl);
        jp3.setMaximumSize(new Dimension(800,1080));
        createComponents();
        addComponents();

        jp4.setLayout(new BorderLayout());
        jp4.add(btnSalva,BorderLayout.CENTER);
        jp4.setMaximumSize(new Dimension(800,300));

        container.add(jp1);
        container.add(jp2);
        container.add(jp3);
        container.add(jp4);

        this.cmbxNumParziali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("cmbx cambiata");
                numParziali = cmbxNumParziali.getItemAt(cmbxNumParziali.getSelectedIndex());
                jp3.removeAll();
                createComponents();
                addComponents();
                jp3.setLayout(gl);
                revalidate();
                repaint();
            }
        });

        add(container);
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }

    public int calcVoto() {
        int med = 0;
        for (ProvaParziale p:
             partials) {
            med += (p.getVotoFinale()*p.getPesoPercentuale());
        }
        return med / 100;
    }

    public void readPartials() {
        for(int i = 1; i < numParziali*3; i+=2) {
            JComboBox<Integer> jc = (JComboBox<Integer>) components.get(i);
            int voto = jc.getItemAt(jc.getSelectedIndex());
            i++;
            JComboBox<Integer> jc2 = (JComboBox<Integer>) components.get(i);
            int perc = jc.getItemAt(jc.getSelectedIndex());
            partials.add(new ProvaParziale(voto,perc));
        }
    }

    public boolean ctrlPerc() {
        int sum = 0;
        for(int i = 2; i < numParziali*3; i+=3) {
            JComboBox<Integer> jc = (JComboBox<Integer>) components.get(i);
            System.out.println(jc.getItemAt(jc.getSelectedIndex()));
            sum += jc.getItemAt(jc.getSelectedIndex());
        }
        return sum == 100;
    }

    private void createComponents() {
        int counter = 1;
        components.clear();
        System.out.println(components);
        for(int i = 0; i < numParziali; i++, counter++) {
            components.add(new JLabel(""+counter));
            components.add(new JComboBox<Integer>(this.getVectorVoti()));
            components.add(new JComboBox<Integer>(this.getVectorPerc()));
        }
        System.out.println(components);
    }

    private void addComponents() {
        gl = new GridLayout(numParziali,3);
        for (JComponent jc: components) {
            jp3.add(jc);
        }
    }

    private void setCmbxNumParziali() {
        Vector<Integer> v = new Vector<>();
        for (int i = 2; i < 11; i++)
            v.add(i);
        cmbxNumParziali= new JComboBox(v);
    }

    private Vector<Integer> getVectorVoti() {
        Vector<Integer> v = new Vector<>();
        for (int i = 0; i < 31; i++)
            v.add(i);
        return v;
    }

    private Vector<Integer> getVectorPerc() {
        Vector<Integer> v = new Vector<>();
        for (int i = 0; i < 101; i++)
            v.add(i);
        return v;
    }

    public JComboBox<Integer> getCmbxNumParziali() {
        return cmbxNumParziali;
    }

    public JButton getBtnSalva() {
        return btnSalva;
    }

    public JLabel getLblError() {
        return lblError;
    }

    public JPanel getJp3() {
        return jp3;
    }
}
