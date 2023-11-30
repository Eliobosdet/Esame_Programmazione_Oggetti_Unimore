package Grafica;

import Esami.Esame;
import Esami.EsameComposto;
import Esami.EsameSemplice;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    ArrayList<Esame> esami = new ArrayList<Esame>(); //Esami memorizzati
    ArrayList<Object[]> dataJtbl = new ArrayList<Object[]>(); //Esami stampati
    private int N_PARZ = 2;
    public Form() {

        DefaultTableModel tblmdl = new DefaultTableModel();
        jtbl.setModel(tblmdl);
        String[] columns = {"NomeStudente","CognomeStudente","Insegnamento","VotoFinale","NumeroCrediti","Lode","TipoEsame"};  //COLONNE DELLA TABLE
        for (String s: columns) { tblmdl.addColumn(s); }

        jtbl.setRowSelectionAllowed(true);  //Permette la selezione delle righe



        btnAggiungiEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aggiungo Esame");

                int[] perc = {0,0,0,0}; //Percentuali che andrò a leggere
                if(cmboxTipoEsame.getSelectedIndex()==1) {  //Esame Composto
                    if(ctrlPerc(perc)) {
                        System.out.println("Percentuali corrette");
                        System.out.print("Percentuali: "); for (int i: perc) {System.out.print(i);}
                        //AGGIUNGO ESAME COMPOSTO E CORRISPETTIVI ESAMI PARZIALI
                        //jtblData.add(new EsameComposto())
                    }
                    else
                        System.out.println("Percentuali non corrette");

                } else {
                    System.out.println("Esame semplice");
                    EsameSemplice es = new EsameSemplice();
                    esami.add(es);
                    es.getDataJtbl().toString();
                    Object[] obj = {"cere"};
                    dataJtbl.add(es.getDataJtbl());
                    tblmdl.addRow(dataJtbl.get(dataJtbl.size()-1));
                    System.out.println("a");
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
