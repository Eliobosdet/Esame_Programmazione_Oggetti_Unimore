package Grafica;

import Esami.Esame;
import Esami.EsameComposto;
import Esami.EsameSemplice;
import Esami.ProvaParziale;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class Form {
    //COMPONENTI GRAFICI
    private JTable jtbl;
    private JPanel jpnl;
    private JButton btnAggiungiEsame;
    private JComboBox cmboxTipoEsame;
    private JComboBox cmbxVotoParz1;
    private JComboBox cmbxVotoParz2;
    private JComboBox cmbxVotoParz3;
    private JComboBox cmbxVotoParz4;
    private JLabel lblVotoParz1;
    private JLabel lblVotoParz2;
    private JLabel lblVotoParz3;
    private JLabel lblVotoParz4;
    private JButton btnAggiungiParz;
    private JButton btnRimuoviParz;
    private JLabel lblPercParz1;
    private JLabel lblPercParz2;
    private JLabel lblPercParz3;
    private JLabel lblPercParz4;
    private JTextField txtPercParz1;
    private JTextField txtPercParz2;
    private JTextField txtPercParz3;
    private JTextField txtPercParz4;
    private JButton btnModificaTabella;
    private JButton btnEliminaEsame;


    //COMPONENTI LOGICI
    DefaultTableModel tblmdl = new DefaultTableModel();
    ArrayList<Esame> esami = new ArrayList<Esame>(); //Esami memorizzati
    //ArrayList<Object[]> dataJtbl = new ArrayList<Object[]>(); //Esami stampati
    private int N_PARZ = 2;
    public Form() {

        jtbl.setModel(tblmdl);
        String[] columns = {"NomeStudente","CognomeStudente","Insegnamento","VotoFinale","NumeroCrediti","Lode","TipoEsame"};  //COLONNE DELLA TABLE
        for (String s: columns) { tblmdl.addColumn(s); }

        TableColumnModel columnModel = jtbl.getColumnModel();

        CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
        columnModel.getColumn(5).setCellRenderer(checkBoxRenderer);
        columnModel.getColumn(5).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        tblmdl.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    System.out.println("TABLE CHANGED, row:" + row + ", column:" + column);

                    if (column == 3) {
                        int obj = Integer.parseInt(String.valueOf(tblmdl.getValueAt(row, column)));
                        Component component = checkBoxRenderer.getTableCellRendererComponent(jtbl, null, false, false, row, 5);
                        JCheckBox jCheckBox = (JCheckBox) component;
                        if(obj!=30) {
                            System.out.println("Nuovo voto: " + obj);
                            tblmdl.setValueAt(false, row, 5); // Deseleziona la casella di controllo
                            jCheckBox.setEnabled(false);
                        } else {
                            jCheckBox.setEnabled(true);

                        }


                        //if (component instanceof JCheckBox) {
                            //JCheckBox checkBox = (JCheckBox) component;


                        }
                    }
                }
        });

        jtbl.setRowSelectionAllowed(true);  //Permette la selezione delle righe



        btnAggiungiEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aggiungo Esame");
 
                if(cmboxTipoEsame.getSelectedIndex()==1) {  //Esame Composto
                    System.out.println("Esame composto");
                    int[] perc = {0,0,0,0}; //Percentuali che andrò a leggere
                    int[] voti = {0,0,0,0}; //Voti che andrò a leggere
                    if(ctrlPerc(perc)) {
                        //System.out.println("Percentuali corrette");
                        System.out.print("Percentuali: "); for (int i: perc) {System.out.print(i+" ");}
                        //LEGGO I VOTI DI OGNI PARZIALE
                        voti[0] = Integer.parseInt(String.valueOf(cmbxVotoParz1.getSelectedIndex()));
                        voti[1] = Integer.parseInt(String.valueOf(cmbxVotoParz2.getSelectedIndex()));
                        voti[2] = Integer.parseInt(String.valueOf(cmbxVotoParz3.getSelectedIndex()));
                        voti[3] = Integer.parseInt(String.valueOf(cmbxVotoParz4.getSelectedIndex()));
                        //AGGIUNGO ESAMI PARZIALI ALL'ESAME COMPOSTO

                        ArrayList<ProvaParziale> arrListParz = new ArrayList<ProvaParziale>();
                        EsameComposto ec = new EsameComposto();
                        addEsameComposto(ec,voti,perc);
                    }
                    else
                        System.out.println("Percentuali non corrette");

                } else {    //Esame Semplice
                    System.out.println("Esame semplice");
                    EsameSemplice es = new EsameSemplice();
                    //addEsameSemplice(es);
                    InserisciSempliceGUI frameSemplice = new InserisciSempliceGUI();
                    frameSemplice.getBtnInserisci().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("Inserimento avvenuto");
                            if(!frameSemplice.ctrlNumCrediti() || !frameSemplice.ctrlTextFields()){     //Caso di errore
                                frameSemplice.getLblError().setVisible(true);
                            }
                            else {
                                frameSemplice.getLblError().setVisible(false);
                                System.out.println(frameSemplice.toString());
                                Object[] obj = frameSemplice.getDataJtbl();
                                frameSemplice.getJf().dispose();
                                tblmdl.addRow(obj);
                            }
                        }
                    });
                }
            }
        });
        cmboxTipoEsame.addActionListener(new ActionListener() { //Cambio ComboBox Tipo Esame
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cmboxTipoEsame.getSelectedIndex()==1) {  //Esame Composto
                    btnAggiungiParz.setVisible(true);
                    btnRimuoviParz.setVisible(true);
                    lblVotoParz1.setVisible(true);
                    lblVotoParz2.setVisible(true);
                    cmbxVotoParz1.setVisible(true);
                    cmbxVotoParz2.setVisible(true);
                    lblPercParz1.setVisible(true);
                    lblPercParz2.setVisible(true);
                    txtPercParz1.setVisible(true);
                    txtPercParz2.setVisible(true);
                } else {
                    setPartialsNotVisible();
                }
            }
        });
        btnAggiungiParz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (N_PARZ) {
                    case 2:
                        lblVotoParz3.setVisible(true);
                        cmbxVotoParz3.setVisible(true);
                        lblPercParz3.setVisible(true);
                        txtPercParz3.setVisible(true);
                        N_PARZ++;
                        break;
                    case 3:
                        lblVotoParz4.setVisible(true);
                        cmbxVotoParz4.setVisible(true);
                        lblPercParz4.setVisible(true);
                        txtPercParz4.setVisible(true);
                        N_PARZ++;
                        break;
                    default:
                        System.err.println("I parziali possono essere da un minimo di 2 ad un massimo di 4");
                        break;
                }
                System.out.println("N_PARZ: "+N_PARZ);
            }
        });
        btnRimuoviParz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (N_PARZ) {
                    case 3:
                        lblVotoParz3.setVisible(false);
                        cmbxVotoParz3.setVisible(false);
                        lblPercParz3.setVisible(false);
                        txtPercParz3.setVisible(false);
                        N_PARZ--;
                        break;
                    case 4:
                        lblVotoParz4.setVisible(false);
                        cmbxVotoParz4.setVisible(false);
                        lblPercParz4.setVisible(false);
                        txtPercParz4.setVisible(false);
                        N_PARZ--;
                        break;
                    default:
                        System.err.println("I parziali possono essere da un minimo di 2 ad un massimo di 4");
                        break;
                }
                System.out.println("N_PARZ: "+N_PARZ);
            }
        });
    }

    private void addEsameComposto(EsameComposto ec, int[] voti, int[] perc) {
        ArrayList<ProvaParziale> arrListParz = new ArrayList<ProvaParziale>();
        for(int i = 0; i < N_PARZ; i++) {
            arrListParz.add(new ProvaParziale(voti[i],perc[i]));
        }
        ec.setArrList_parziali(arrListParz);
        //jtblData.add(new EsameComposto())
        tblmdl.addRow(ec.getDataJtbl());
    }

    private void addEsameSemplice(EsameSemplice es) {
        esami.add(es);
        //es.getDataJtbl().toString();
        //dataJtbl.add(es.getDataJtbl());
        //tblmdl.addRow(dataJtbl.get(dataJtbl.size()-1));
        tblmdl.addRow(es.getDataJtbl());
    }

    private void setPartialsNotVisible() {
        cmbxVotoParz2.setVisible(false);
        btnAggiungiParz.setVisible(false);
        btnRimuoviParz.setVisible(false);
        lblVotoParz1.setVisible(false);
        lblVotoParz2.setVisible(false);
        lblVotoParz3.setVisible(false);
        lblVotoParz4.setVisible(false);
        cmbxVotoParz1.setVisible(false);
        cmbxVotoParz2.setVisible(false);
        cmbxVotoParz3.setVisible(false);
        cmbxVotoParz4.setVisible(false);
        lblPercParz1.setVisible(false);
        lblPercParz2.setVisible(false);
        lblPercParz3.setVisible(false);
        lblPercParz4.setVisible(false);
        txtPercParz1.setVisible(false);
        txtPercParz2.setVisible(false);
        txtPercParz3.setVisible(false);
        txtPercParz4.setVisible(false);
    }

    private boolean ctrlPerc(int[] perc) {
        int somma = 0;
        for (int i: perc) {
            i=0;
        }
        if(txtPercParz1.getText().isEmpty() || txtPercParz1.getText().isEmpty()) {
            System.err.println("La somma delle percentuali non è valida o non raggiunge il 100%");
            return false;
        } else {
            try {
            perc[0] = Integer.parseInt(txtPercParz1.getText());
            perc[1] = Integer.parseInt(txtPercParz2.getText());
            } catch (NumberFormatException e) {
                //Gestisce l'inserimento errato delle percentuali
                System.err.println("NumberFormatException: Le percentuali inserite non hanno valori numerici");
            }
        }
        switch (N_PARZ) {
            case 3:
                if(txtPercParz3.getText().isEmpty()) {
                    System.err.println("La somma delle percentuali non è valida o non raggiunge il 100%");
                    return false;
                } else {
                    try{
                        perc[2] = Integer.parseInt(txtPercParz3.getText());
                    } catch (NumberFormatException e) {
                        //Gestisce l'inserimento errato delle percentuali
                        System.err.println("NumberFormatException: Le percentuali inserite non hanno valori numerici");
                    }
                }
                break;
            case 4:
                if(txtPercParz4.getText().isEmpty() || txtPercParz3.getText().isEmpty()) {
                    System.err.println("La somma delle percentuali non è valida o non raggiunge il 100%");
                    return false;
                } else {
                    try {
                        perc[2] = Integer.parseInt(txtPercParz3.getText());
                        perc[3] = Integer.parseInt(txtPercParz4.getText());
                    } catch (NumberFormatException e) {
                        //Gestisce l'inserimento errato delle percentuali
                        System.err.println("NumberFormatException: Le percentuali inserite non hanno valori numerici");
                    }
                }
                break;
            default:
                break;

        }
        for (int i: perc) {
            somma+=i;
        }

        if(somma!=100)
            return false;

        return true;
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
