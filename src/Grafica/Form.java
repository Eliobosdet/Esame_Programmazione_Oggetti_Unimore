package Grafica;

import Esami.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Form {
    //COMPONENTI GRAFICI
    private JTable jtbl;
    private JPanel jpnl;
    private JButton btnAggiungiEsame;
    private JComboBox cmboxTipoEsame;
    private JButton btnModificaEsame;
    private JButton btnEliminaEsame;
    private JButton btnMostraParziali;
    private JButton btnSalva;
    private JButton btnCarica;

    //COMPONENTI LOGICI
    DefaultTableModel tblmdl = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    private static String FILE_PATH = ".";
    private JFileChooser jFileChooser = new JFileChooser();
    private File file;
    private ArrayList<Esame> esami = new ArrayList<>(); //Esami memorizzati

    private boolean saved_changes = true;

    public Form() {

        //Set JTableModel
        jtbl.setModel(tblmdl);
        jtbl.setRowSelectionAllowed(true);  //Permette la selezione delle righe
        String[] columns = {"NomeStudente", "CognomeStudente", "Insegnamento", "VotoFinale", "NumeroCrediti", "Lode", "NumeroParziali"};  //COLONNE DELLA TABLE
        for (String s : columns) {
            tblmdl.addColumn(s);
        }

        jFileChooser.setCurrentDirectory(new File(FILE_PATH));

        btnAggiungiEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aggiungo Esame");

                if (cmboxTipoEsame.getSelectedIndex() == 1) {  //Esame Composto
                    System.out.println("Esame composto");
                    InserisciCompostoGUI f = new InserisciCompostoGUI();
                    f.getBtnInserisci().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            if (!f.ctrlNumCrediti() || !f.ctrlTextFields()) {     //Caso di errore
                                JOptionPane.showMessageDialog(null, "Valori non inseriti e/o errati");
                            } else {
                                System.out.println("Inserisco parziali");
                                if (f.getParzialiGUI() == null)
                                    f.setParzialiGUI(new InserisciParzialiGUI((JLabel) f.getCmpVotoFinale()));
                                else {
                                    System.out.println("Inserimento avvenuto | " + f);
                                    addEsameComposto(f);
                                }
                                setSaved_changes(false);
                            }
                        }
                    });
                } else {    //Esame Semplice
                    System.out.println("Esame semplice");
                    InserisciSempliceGUI f = new InserisciSempliceGUI();
                    f.getBtnInserisci().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!f.ctrlNumCrediti() || !f.ctrlTextFields()) {     //Caso di errore
                                JOptionPane.showMessageDialog(null, "Valori non inseriti e/o errati");
                            } else {
                                System.out.println("Inserimento avvenuto | " + f);
                                addEsameSemplice(f);
                                setSaved_changes(false);
                            }
                        }
                    });
                }

            }
        });
        btnModificaEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtbl.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Nessuna riga selezionata!");
                    return;
                }
                int indice = 0;
                try {
                    indice = jtbl.getSelectedRow();
                } catch (Exception exeption) {
                    System.err.println("Nessuna riga selezionata");
                    return;
                }
                modifyEsame(indice);
                setSaved_changes(false);
            }
        });
        btnEliminaEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (jtbl.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Nessuna riga selezionata!");
                    return;
                }
                int indice = jtbl.getSelectedRow();
                tblmdl.removeRow(indice);
                esami.remove(indice);
                setSaved_changes(false);
            }
        });
        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(saveTableOnFile());
            }
        });
        btnCarica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadTableFromFile();
            }
        });
    }

    private void loadTableFromFile() {
        if(!saved_changes && askToSave() == JOptionPane.YES_OPTION)
            saveTableOnFile();
        if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                esami.clear();
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    Object[] obj = new Object[7];
                    Esame e;

                    for(int i = 0; i < 7; i++) {
                        obj[i] = values[i];
                    }

                    if (values.length > 7) {    //se Esame composto
                        System.out.println("Esame composto");
                        ArrayList<ProvaParziale> arrayList = new ArrayList<>();
                        int nParz = Integer.parseInt(values[6]);
                        for(int i = 7; i < 7+(nParz*2); i+=2) {
                            int voto = Integer.parseInt(values[i]);
                            int perc = Integer.parseInt(values[i+1]);
                            arrayList.add(new ProvaParziale(voto,perc));
                        }
                        e = new EsameComposto(obj,arrayList);

                    } else {
                        e = new EsameSemplice(obj);
                    }
                    esami.add(e);
                }
                fillTableModel();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setSaved_changes(true);
        }
    }
    private void fillTableModel() {
        tblmdl.setRowCount(0);  //pulisce la tabella prima dell'inserimento
        for (Esame e : esami) {
            Object[] obj = new Object[7];
            if (e instanceof EsameSemplice es)
                obj = es.getDataJtbl();
            else if (e instanceof EsameComposto ec) {
                obj = ec.getDataJtbl();
            }
            tblmdl.addRow(obj);
        }
    }

    private void modifyEsame(int indice) {
        InserisciGUI f;
        if(esami.get(indice) instanceof EsameSemplice es) {
            f = es.getGui();
        } else {
            EsameComposto ec = (EsameComposto) esami.get(indice);
            f = ec.getGui();
        }
        //int finalIndice = indice;
        f.reopen();
        f.getBtnModifica().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!f.ctrlNumCrediti() || !f.ctrlTextFields()){     //Caso di errore
                    JOptionPane.showMessageDialog(null,"Valori non inseriti e/o errati");
                }
                else {
                    System.out.println("Modifica avvenuta | "+f);
                    resetValuesEsame(f, indice);
                }
            }
        });
    }
    private void resetValuesEsame(InserisciGUI f, int row) {
        Esame e = esami.get(row);
        Object[] obj = f.getDataJtbl();
        if(f instanceof InserisciSempliceGUI)
            e = new EsameSemplice(obj,(InserisciSempliceGUI) f);
        else
            e = new EsameComposto(obj, (InserisciCompostoGUI) f);

        esami.set(row,e);

        for (int i = 0; i < obj.length; i++)
            tblmdl.setValueAt(obj[i],row,i);
        f.dispose();
    }

    private void addEsameSemplice(InserisciSempliceGUI frame) {
        Object[] obj = frame.getDataJtbl();
        EsameSemplice es = new EsameSemplice(obj,frame);
        esami.add(es);
        //System.out.println(esami);
        tblmdl.addRow(obj);
        frame.dispose();
    }
    private void addEsameComposto(InserisciCompostoGUI frame) {
        Object[] obj = frame.getDataJtbl();
        EsameComposto ec = new EsameComposto(obj,frame);
        ec.setArrList_parziali(frame.getArrayListParziali());
        esami.add(ec);
        tblmdl.addRow(obj);
        frame.dispose();
    }

    public void writeExams(BufferedWriter writer) {
        try {
            for (Esame e: esami) {
                System.out.println(e.getDataJtbl().toString());
                if (e instanceof EsameSemplice) {
                    EsameSemplice es = (EsameSemplice) e;
                    Object[] obj = es.getDataJtbl();
                    for (Object o: obj) {
                        writer.write(o.toString()+",");
                    }
                } else {
                    EsameComposto ec = (EsameComposto) e;
                    Object[] obj = ec.getDataJtbl();
                    for (Object o: obj) {
                        writer.write(o.toString()+",");
                    }
                    for (ProvaParziale pp:ec.getArrList_parziali()) {
                        writer.write(pp.getVotoFinale()+","+pp.getPesoPercentuale()+",");
                    }
                }
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean saveTableOnFile() {
        if(tblmdl.getRowCount()==0)
            return false;
        if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            int response = JOptionPane.NO_OPTION;
            if(jFileChooser.getSelectedFile().exists()) {
                System.out.println("Esiste");
                response = JOptionPane.showOptionDialog(null,"Vuoi sovrascrivere il file su cui salvare i dati?","Conferma",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if(response != JOptionPane.YES_OPTION) {
                    return false;
                }
            }

            file = new File(jFileChooser.getSelectedFile().getAbsolutePath());

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
                writeExams(writer);
                writer.close();
                System.out.println("Scrittura nel file avvenuta con successo!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            setSaved_changes(true);
        }
        return true;
    }

    public int askToSave() {
        return JOptionPane.showOptionDialog(null,"Vuoi prima salvare le modifiche?","Salvataggio",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
    }

    //GETTERS AND SETTERS

    public JPanel getJpnl() {
        return jpnl;
    }

    public void setSaved_changes(boolean saved_changes) {
        this.saved_changes = saved_changes;
    }

    public boolean isSaved_changes() {
        return saved_changes;
    }
}
