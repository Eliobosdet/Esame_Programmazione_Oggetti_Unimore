package Grafica;

import Esami.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    JFileChooser jFileChooser = new JFileChooser();
    File file;
    ArrayList<Esame> esami = new ArrayList<>(); //Esami memorizzati

    public Form() {

        jtbl.setModel(tblmdl);
        String[] columns = {"NomeStudente","CognomeStudente","Insegnamento","VotoFinale","NumeroCrediti","TipoEsame","Lode","NumeroParziali"};  //COLONNE DELLA TABLE
        for (String s: columns) { tblmdl.addColumn(s); }

        jtbl.setRowSelectionAllowed(true);  //Permette la selezione delle righe

        btnAggiungiEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aggiungo Esame");

                if(cmboxTipoEsame.getSelectedIndex()==1) {  //Esame Composto
                    System.out.println("Esame composto");
                    InserisciCompostoGUI f = new InserisciCompostoGUI();
                    f.getBtnInserisci().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            if(!f.ctrlNumCrediti() || !f.ctrlTextFields()){     //Caso di errore
                                f.setLblError("Qualcosa è andato storto,Riprova.");
                                f.getLblError().setVisible(true);
                            } else {
                                System.out.println("Inserisco parziali");
                                if(f.getParzialiGUI() == null)
                                    f.setParzialiGUI(new InserisciParzialiGUI((JLabel)  f.getCmpVotoFinale()));
                                else
                                    addEsameComposto(f);
                            }
                        }
                    });
                    f.getBtnModificaParziali().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            /*if(!f.ctrlNumParziali()) {
                                f.setLblError("Numero di parziali non sufficiente.");
                                f.getLblError().setVisible(true);
                            } else {
                                f.setParzialiGUI(new InserisciParzialiGUI());
                            }*/
                            //if(f.getTxtNumCrediti().getText())
                            //f.setParzialiGUI(new InserisciParzialiGUI());
                        }
                    });
                }
                else {    //Esame Semplice
                    System.out.println("Esame semplice");
                    InserisciSempliceGUI f = new InserisciSempliceGUI();
                    f.getBtnInserisci().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(!f.ctrlNumCrediti() || !f.ctrlTextFields()){     //Caso di errore
                                f.getLblError().setVisible(true);
                            }
                            else {
                                System.out.println("Inserimento avvenuto | "+f);
                                f.getLblError().setVisible(false);
                                addEsameSemplice(f);
                            }
                        }
                    });
                }
            }
        });



        btnModificaEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtbl.getSelectedRow()==-1){
                    System.err.println("Nessuna riga selezionata!");
                    return;
                }
                int indice=0;
                try {
                    indice = jtbl.getSelectedRow();
                } catch (Exception exeption) {
                    System.err.println("Nessuna riga selezionata");
                    return;
                }
                modifyEsame(indice);
            }
        });



        btnEliminaEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(jtbl.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null,"Nessuna riga selezionata!");
                    return;
                }
                int indice = jtbl.getSelectedRow();
                tblmdl.removeRow(indice);
                esami.remove(indice);
            }
        });


        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                    file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                        for (Esame e: esami) {
                            if (e instanceof EsameSemplice) {
                                EsameSemplice es = (EsameSemplice) e;
                                Object[] obj = es.getDataJtbl();
                                for (Object o: obj) {
                                    writer.write(o.toString()+",");
                                }
                            } else {
                                EsameComposto es = (EsameComposto) e;
                                Object[] obj = es.getDataJtbl();
                                for (Object o: obj) {
                                    writer.write(o.toString()+",");
                                }
                                for (ProvaParziale pp:es.getArrList_parziali()) {
                                    writer.write(pp.getVotoFinale()+","+pp.getPesoPercentuale()+",");
                                }
                            }
                            writer.newLine();
                        }

                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void modifyEsame(int indice) {
        EsameSemplice es = (EsameSemplice) esami.get(indice);
        InserisciSempliceGUI f = es.getGui();
        int finalIndice = indice;
        f.reopen();
        f.getBtnModifica().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!f.ctrlNumCrediti() || !f.ctrlTextFields()){     //Caso di errore
                    f.getLblError().setVisible(true);
                }
                else {
                    System.out.println("Modifica avvenuta | "+f);
                    f.getLblError().setVisible(false);
                    resetValuesEsameSemplice(f, finalIndice);
                }
            }
        });
    }

    private void addEsameComposto(InserisciCompostoGUI frame) {
        Object[] obj = frame.getDataJtbl();
        EsameComposto ec = new EsameComposto(obj,frame);
        ec.setArrList_parziali(frame.getArrayListParziali());
        esami.add(ec);
        tblmdl.addRow(obj);
        frame.dispose();
    }

    private void addEsameSemplice(InserisciSempliceGUI frame) {
        Object[] obj = frame.getDataJtbl();
        EsameSemplice es = new EsameSemplice(obj,frame);
        esami.add(es);
        System.out.println(esami);
        tblmdl.addRow(obj);
        frame.dispose();
    }


    private void resetValuesEsameSemplice(InserisciSempliceGUI f, int row) {
        Esame e = esami.get(row);
        Object[] obj = f.getDataJtbl();
        e = new EsameSemplice(obj);
        for (int i = 0; i < obj.length; i++)
            tblmdl.setValueAt(obj[i],row,i);
        f.dispose();
    }




    //GETTERS AND SETTERS

    public JPanel getJpnl() {
        return jpnl;
    }
}
