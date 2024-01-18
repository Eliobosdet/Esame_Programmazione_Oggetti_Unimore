package Grafica;

import Esami.ProvaParziale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class InserisciParzialiGUI extends JFrame{
    int numParziali = 2;
    ArrayList<JComponent> components = new ArrayList<>();
    ArrayList<ProvaParziale> partials = new ArrayList<>();

    JComboBox<Integer> cmbxNumParziali;
    JLabel lblVoto;
    JButton btnSalva = new JButton("Salva");

    GridLayout gl = new GridLayout(numParziali,3);
    JPanel container = new JPanel();
    JPanel jp1 = new JPanel() , jp2 = new JPanel(), jp3 = new JPanel(), jp4 = new JPanel();

    public InserisciParzialiGUI() {
        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        loadJp1();
        loadJp2();
        loadJp3();
        loadJp4();
        container.add(jp1);container.add(jp2);container.add(jp3);container.add(jp4);

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

        setFrame();
    }

    public InserisciParzialiGUI(JLabel jl) {
        this();
        actionBtnSalva(jl);
    }

    public InserisciParzialiGUI(ArrayList<ProvaParziale> arrList, JLabel jl) {
        this(jl);
        cmbxNumParziali.setSelectedIndex(arrList.size()-2);

    }

    public InserisciParzialiGUI(JLabel jl, ArrayList<ProvaParziale> a, int numParziali) {
        this();
        this.numParziali = numParziali;
        cmbxNumParziali.setSelectedIndex(numParziali-2);
        setIndex(a);
        actionBtnSalva(jl);
    }

    private void setIndex(ArrayList<ProvaParziale> a) {
        int i_parz = 0;
        for(int i = 1; i < numParziali*3; i+=2, i_parz++) {
            JComboBox<Integer> j = (JComboBox<Integer>)components.get(i);
            j.setSelectedIndex(a.get(i_parz).getVotoFinale());
            i++;
            JComboBox<Integer> j2 = (JComboBox<Integer>)components.get(i);
            j2.setSelectedIndex(a.get(i_parz).getVotoFinale());
        }
    }

    private void setFrame() {
        add(container);
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }

    public void actionBtnSalva(JLabel jl) {
        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(ctrlPerc()) {
                    readPartials();
                    setVisible(false);
                    System.out.println(String.valueOf(calcVoto()));
                    jl.setText(String.valueOf(calcVoto()));
                    System.out.println(jl.getText());
                }
                else {
                    JOptionPane.showMessageDialog(null,"Percentuali non corrette.\n In caso di numero parziali dispari, l'arrotondamento delle percentuali potrebbe essere 99%");
                }
            }
        });
    }

    private void loadJp1() {
        jp1.setLayout(new BorderLayout());
        jp1.setMaximumSize(new Dimension(800,300));
        createCmbxNumParziali();
        jp1.add(cmbxNumParziali, BorderLayout.CENTER);
    }
    public void loadJp2() {
        jp2.setLayout(new GridLayout(1,3));
        jp2.setMaximumSize(new Dimension(800,200));
        jp2.add(new JLabel("Numero parziale"));
        jp2.add(new JLabel("Voto parziale(0-30)"));
        jp2.add(new JLabel("Percentuale parziale(%)"));
    }
    public void loadJp3() {
        jp3.setLayout(gl);
        jp3.setMaximumSize(new Dimension(800,1080));
        createComponents();
        addComponents();
    }
    private void loadJp4() {
        jp4.setLayout(new BorderLayout());
        jp4.add(btnSalva,BorderLayout.CENTER);
        jp4.setMaximumSize(new Dimension(800,300));
    }

    public int calcVoto() {
        int med = 0;
        for (ProvaParziale p:
             partials) {
            System.out.println("Voto: "+p.getVotoFinale()+" Peso: "+p.getPesoPercentuale());
            med += (p.getVotoFinale()*p.getPesoPercentuale());
            System.out.println("media: "+med);
        }
        return med / 100;
    }
    public void readPartials() {
        partials.clear();
        for(int i = 1; i < numParziali*3; i+=2) {
            JComboBox<Integer> jc = (JComboBox<Integer>) components.get(i);
            int voto = jc.getItemAt(jc.getSelectedIndex());
            System.out.println("voto: "+voto);
            i++;
            JComboBox<Integer> jc2 = (JComboBox<Integer>) components.get(i);
            int perc = jc2.getItemAt(jc2.getSelectedIndex());
            System.out.println("perc: "+perc);
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

    private void createCmbxNumParziali() {
        Vector<Integer> v = new Vector<>();
        for (int i = 2; i < 11; i++)
            v.add(i);
        cmbxNumParziali= new JComboBox(v);
    }

    private Vector<Integer> getVectorVoti() {
        Vector<Integer> v = new Vector<>();
        for (int i = 18; i < 31; i++)
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

    public JPanel getJp2() {
        return jp2;
    }

    public JPanel getJp3() {
        return jp3;
    }

    public int getNumParziali() {
        return numParziali;
    }

    public ArrayList<ProvaParziale> getPartials() {
        return partials;
    }

    public void setPartials(ArrayList<ProvaParziale> partials) {
        this.partials = partials;
    }

}
