package Grafica;

import Esami.ProvaParziale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 * Finestra grafica per l'inserimento dei voti parziali.
 */
public class InserisciParzialiGUI extends JFrame {
    // Numero di parziali di default
    int numParziali = 2;

    // Lista dei componenti grafici
    ArrayList<JComponent> components = new ArrayList<>();

    // Lista delle prove parziali
    ArrayList<ProvaParziale> partials = new ArrayList<>();

    // Componenti GUI
    JComboBox<Integer> cmbxNumParziali;
    JLabel lblVoto;
    JButton btnSalva = new JButton("Salva");

    // Layout e pannelli
    GridLayout gl = new GridLayout(numParziali, 3);
    JPanel container = new JPanel();
    JPanel jp1 = new JPanel(), jp2 = new JPanel(), jp3 = new JPanel(), jp4 = new JPanel();

    /**
     * Costruttore senza parametri per la finestra di inserimento voti parziali.
     */
    public InserisciParzialiGUI() {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        loadJp1();
        loadJp2();
        loadJp3();
        loadJp4();
        container.add(jp1);
        container.add(jp2);
        container.add(jp3);
        container.add(jp4);

        // Listener per il cambio del numero di parziali
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

    /**
     * Costruttore con un parametro per la finestra di inserimento voti parziali.
     *
     * @param jl JLabel per visualizzare il voto finale.
     */
    public InserisciParzialiGUI(JLabel jl) {
        this();
        actionBtnSalva(jl);
    }

    /**
     * Costruttore con due parametri per la finestra di inserimento voti parziali.
     *
     * @param jl      JLabel per visualizzare il voto finale.
     * @param arrList ArrayList contenente le prove parziali da visualizzare.
     */
    public InserisciParzialiGUI(JLabel jl, ArrayList<ProvaParziale> arrList) {
        this(jl);
        cmbxNumParziali.setSelectedIndex(arrList.size() - 2);
        partials = arrList;
        numParziali = arrList.size();
        setDataByFile();
    }

    /**
     * Costruttore con tre parametri per la finestra di inserimento voti parziali.
     *
     * @param jl          JLabel per visualizzare il voto finale.
     * @param a           ArrayList contenente le prove parziali da visualizzare.
     * @param numParziali Numero di parziali da visualizzare.
     */
    public InserisciParzialiGUI(JLabel jl, ArrayList<ProvaParziale> a, int numParziali) {
        this();
        this.numParziali = numParziali;
        cmbxNumParziali.setSelectedIndex(numParziali - 2);
        setIndex(a);
        actionBtnSalva(jl);
    }

    /**
     * Imposta gli indici per le JComboBox basandosi sulle prove parziali fornite.
     *
     * @param a ArrayList di prove parziali.
     */
    private void setIndex(ArrayList<ProvaParziale> a) {
        int i_parz = 0;
        for (int i = 1; i < numParziali * 3; i += 2, i_parz++) {
            JComboBox<Integer> j = (JComboBox<Integer>) components.get(i);
            j.setSelectedIndex(a.get(i_parz).getVotoFinale());
            i++;
            JComboBox<Integer> j2 = (JComboBox<Integer>) components.get(i);
            j2.setSelectedIndex(a.get(i_parz).getVotoFinale());
        }
    }

    /**
     * Imposta le caratteristiche della finestra.
     */
    private void setFrame() {
        add(container);
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    /**
     * Aggiunge un listener al pulsante "Salva" per eseguire azioni quando viene cliccato.
     *
     * @param jl JLabel per visualizzare il voto finale.
     */
    public void actionBtnSalva(JLabel jl) {
        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Controlla le percentuali prima di salvare
                if (ctrlPerc()) {
                    // Legge i voti parziali e li salva
                    readPartials();
                    setVisible(false);
                    // Calcola e visualizza il voto finale
                    System.out.println(String.valueOf(calcVoto()));
                    jl.setText(String.valueOf(calcVoto()));
                    System.out.println(jl.getText());
                } else {
                    // Avvisa l'utente se le percentuali non sono corrette
                    JOptionPane.showMessageDialog(null, "Percentuali non corrette.\n In caso di numero parziali dispari, l'arrotondamento delle percentuali potrebbe essere 99%");
                }
            }
        });
    }

    /**
     * Carica il pannello jp1 con un layout specifico e aggiunge la JComboBox per la scelta del numero di parziali.
     */
    private void loadJp1() {
        jp1.setLayout(new BorderLayout());
        jp1.setMaximumSize(new Dimension(800, 300));
        createCmbxNumParziali();
        jp1.add(cmbxNumParziali, BorderLayout.CENTER);
    }

    /**
     * Carica il pannello jp2 con un layout specifico e aggiunge le etichette per i numeri parziali, il voto e la percentuale.
     */
    public void loadJp2() {
        jp2.setLayout(new GridLayout(1, 3));
        jp2.setMaximumSize(new Dimension(800, 200));
        jp2.add(new JLabel("Numero parziale"));
        jp2.add(new JLabel("Voto parziale(0-30)"));
        jp2.add(new JLabel("Percentuale parziale(%)"));
    }

    /**
     * Carica il pannello jp3 con un layout specifico, crea i componenti e li aggiunge al pannello.
     */
    public void loadJp3() {
        jp3.setLayout(gl);
        jp3.setMaximumSize(new Dimension(800, 1080));
        createComponents();
        addComponents();
    }

    /**
     * Carica il pannello jp4 con un layout specifico e aggiunge il pulsante "Salva".
     */
    private void loadJp4() {
        jp4.setLayout(new BorderLayout());
        jp4.add(btnSalva, BorderLayout.CENTER);
        jp4.setMaximumSize(new Dimension(800, 300));
    }

    /**
     * Calcola e restituisce il voto finale basandosi sui voti parziali e le percentuali.
     *
     * @return Il voto finale.
     */
    public int calcVoto() {
        int med = 0;
        for (ProvaParziale p : partials) {
            System.out.println("Voto: " + p.getVotoFinale() + " Peso: " + p.getPesoPercentuale());
            med += (p.getVotoFinale() * p.getPesoPercentuale());
            System.out.println("media: " + med);
        }
        return med / 100;
    }

    /**
     * Legge i voti parziali dalle JComboBox e li salva nella lista di prove parziali.
     */
    public void readPartials() {
        partials.clear();
        for (int i = 1; i < numParziali * 3; i += 2) {
            JComboBox<Integer> jc = (JComboBox<Integer>) components.get(i);
            int voto = jc.getItemAt(jc.getSelectedIndex());
            System.out.println("voto: " + voto);
            i++;
            JComboBox<Integer> jc2 = (JComboBox<Integer>) components.get(i);
            int perc = jc2.getItemAt(jc2.getSelectedIndex());
            System.out.println("perc: " + perc);
            partials.add(new ProvaParziale(voto, perc));
        }
    }

    /**
     * Controlla che la somma delle percentuali sia uguale a 100.
     *
     * @return True se le percentuali sono corrette, altrimenti False.
     */
    public boolean ctrlPerc() {
        int sum = 0;
        for (int i = 2; i < numParziali * 3; i += 3) {
            JComboBox<Integer> jc = (JComboBox<Integer>) components.get(i);
            System.out.println(jc.getItemAt(jc.getSelectedIndex()));
            sum += jc.getItemAt(jc.getSelectedIndex());
        }
        return sum == 100;
    }

    /**
     * Crea i componenti grafici per la visualizzazione dei voti parziali.
     */
    private void createComponents() {
        int counter = 1;
        components.clear();
        System.out.println(components);
        for (int i = 0; i < numParziali; i++, counter++) {
            components.add(new JLabel("" + counter));
            components.add(new JComboBox<Integer>(this.getVectorVoti()));
            components.add(new JComboBox<Integer>(this.getVectorPerc()));
        }
        System.out.println(components);
    }

    /**
     * Aggiunge i componenti creati al pannello jp3.
     */
    private void addComponents() {
        gl = new GridLayout(numParziali, 3);
        for (JComponent jc : components) {
            jp3.add(jc);
        }
    }

    /**
     * Crea una JComboBox con i numeri di parziali disponibili.
     */
    private void createCmbxNumParziali() {
        Vector<Integer> v = new Vector<>();
        for (int i = 2; i < 11; i++)
            v.add(i);
        cmbxNumParziali = new JComboBox(v);
    }

    /**
     * Restituisce un vettore di voti possibili per le prove parziali.
     *
     * @return Vettore di voti possibili.
     */
    private Vector<Integer> getVectorVoti() {
        Vector<Integer> v = new Vector<>();
        for (int i = 18; i < 31; i++)
            v.add(i);
        return v;
    }

    /**
     * Restituisce un vettore di percentuali possibili per le prove parziali.
     *
     * @return Vettore di percentuali possibili.
     */
    private Vector<Integer> getVectorPerc() {
        Vector<Integer> v = new Vector<>();
        for (int i = 0; i < 101; i++)
            v.add(i);
        return v;
    }

    /**
     * Imposta i dati delle prove parziali basandosi su un file di input.
     */
    private void setDataByFile() {
        int j = 0;
        JComboBox jc;
        for (int i = 1; i < numParziali * 3; i += 2, j++) {
            jc = (JComboBox) components.get(i);
            jc.setSelectedIndex(partials.get(j).getVotoFinale() - 18);
            i++;
            jc = (JComboBox) components.get(i);
            jc.setSelectedIndex(partials.get(j).getPesoPercentuale());
        }
    }


    /**
     * Restituisce la JComboBox per la selezione del numero di prove parziali.
     *
     * @return JComboBox per il numero di prove parziali.
     */
    public JComboBox<Integer> getCmbxNumParziali() {
        return cmbxNumParziali;
    }

    /**
     * Restituisce il pulsante "Salva".
     *
     * @return Pulsante "Salva".
     */
    public JButton getBtnSalva() {
        return btnSalva;
    }

    /**
     * Restituisce il pannello jp2.
     *
     * @return Pannello jp2.
     */
    public JPanel getJp2() {
        return jp2;
    }

    /**
     * Restituisce il pannello jp3.
     *
     * @return Pannello jp3.
     */
    public JPanel getJp3() {
        return jp3;
    }

    /**
     * Restituisce il numero di prove parziali.
     *
     * @return Numero di prove parziali.
     */
    public int getNumParziali() {
        return numParziali;
    }

    /**
     * Restituisce la lista di prove parziali.
     *
     * @return Lista di prove parziali.
     */
    public ArrayList<ProvaParziale> getPartials() {
        return partials;
    }

    /**
     * Imposta la lista di prove parziali.
     *
     * @param partials Nuova lista di prove parziali.
     */
    public void setPartials(ArrayList<ProvaParziale> partials) {
        this.partials = partials;
    }

}
