package Grafica;

import Esami.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    //COMPONENTI LOGICI
    DefaultTableModel tblmdl = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    ArrayList<Esame> esami = new ArrayList<>(); //Esami memorizzati
    //ArrayList<InserisciSempliceGUI> frameSemplici = new ArrayList<>();
    //ArrayList<InserisciCompostoGUI> frameComposti = new ArrayList<>();
    //ArrayList<Object[]> dataJtbl = new ArrayList<Object[]>(); //Esami stampati
    private int N_PARZ = 2;
    public Form() {

        jtbl.setModel(tblmdl);
        String[] columns = {"NomeStudente","CognomeStudente","Insegnamento","VotoFinale","NumeroCrediti","TipoEsame","Lode"};  //COLONNE DELLA TABLE
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
                                    f.setParzialiGUI(new InserisciParzialiGUI());
                                //else


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
                    System.err.println("Nessuna riga selezionata!");
                    return;
                }
                int indice = jtbl.getSelectedRow();
                tblmdl.removeRow(indice);
                esami.remove(indice);
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

    private void addEsameComposto(EsameComposto ec, int[] voti, int[] perc) {
        ArrayList<ProvaParziale> arrListParz = new ArrayList<ProvaParziale>();
        for(int i = 0; i < N_PARZ; i++) {
            arrListParz.add(new ProvaParziale(voti[i],perc[i]));
        }
        ec.setArrList_parziali(arrListParz);
        tblmdl.addRow(ec.getDataJtbl());
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

    public JButton getBtnAggiungiEsame() {
        return btnAggiungiEsame;
    }

    public void setBtnAggiungiEsame(JButton btnAggiungiEsame) {
        this.btnAggiungiEsame = btnAggiungiEsame;
    }

    public JComboBox getCmboxTipoEsame() {
        return cmboxTipoEsame;
    }

    public void setCmboxTipoEsame(JComboBox cmboxTipoEsame) {
        this.cmboxTipoEsame = cmboxTipoEsame;
    }


    public JTable getJtbl() {
        return jtbl;
    }

    public void setJtbl(JTable jtbl) {
        this.jtbl = jtbl;
    }

    public JPanel getJpnl() {
        return jpnl;
    }

    public void setJpnl(JPanel jpnl) {
        this.jpnl = jpnl;
    }
}
