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


    //COMPONENTI LOGICI
    DefaultTableModel tblmdl = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    ArrayList<Esame> esami = new ArrayList<>(); //Esami memorizzati
    ArrayList<InserisciSempliceGUI> frameSemplici = new ArrayList<>();
    ArrayList<InserisciCompostoGUI> frameComposti = new ArrayList<>();
    //ArrayList<Object[]> dataJtbl = new ArrayList<Object[]>(); //Esami stampati
    private int N_PARZ = 2;
    public Form() {

        jtbl.setModel(tblmdl);
        String[] columns = {"NomeStudente","CognomeStudente","Insegnamento","VotoFinale","NumeroCrediti","Lode","TipoEsame"};  //COLONNE DELLA TABLE
        for (String s: columns) { tblmdl.addColumn(s); }

        jtbl.setRowSelectionAllowed(true);  //Permette la selezione delle righe

        btnAggiungiEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aggiungo Esame");

                if(cmboxTipoEsame.getSelectedIndex()==1) {  //Esame Composto
                    System.out.println("Esame composto");
                    InserisciCompostoGUI f = new InserisciCompostoGUI();
                    frameComposti.add(f);
                }
                else {    //Esame Semplice
                    System.out.println("Esame semplice");
                    InserisciSempliceGUI f = new InserisciSempliceGUI();
                    frameSemplici.add(f);
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
                InserisciSempliceGUI f = frameSemplici.get(indice);
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
                            modifyEsameSemplice(f, finalIndice);
                        }
                    }
                });
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
                frameSemplici.remove(indice);
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
        EsameSemplice es = new EsameSemplice(obj);
        esami.add(es);
        System.out.println(esami);
        tblmdl.addRow(obj);
        frame.getJf().dispose();
    }

    private void modifyEsameSemplice(InserisciSempliceGUI f, int row) {
        Esame e = esami.get(row);
        Object[] obj = f.getDataJtbl();
        e = new EsameSemplice(obj);
        for (int i = 0; i < obj.length; i++)
            tblmdl.setValueAt(obj[i],row,i);
        f.getJf().dispose();
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
