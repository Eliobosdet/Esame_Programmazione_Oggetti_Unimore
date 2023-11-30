package Grafica;

import Esami.Esame;

import javax.swing.*;
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


    //COMPONENTI LOGICI
    ArrayList<Esame> jtblData = new ArrayList<Esame>(); //Esami sostenuti
    private int N_PARZ = 2;
    public Form() {
        btnAggiungiEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Aggiungo Esame");

                cmboxTipoEsame.setSelectedIndex(0);
                setPartialsNotVisible();
                N_PARZ=2;
            }
        });
        cmboxTipoEsame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cmboxTipoEsame.getSelectedIndex()==1) {
                    btnAggiungiParz.setVisible(true);
                    btnRimuoviParz.setVisible(true);
                    lblVotoParz1.setVisible(true);
                    lblVotoParz2.setVisible(true);
                    cmbxVotoParz1.setVisible(true);
                    cmbxVotoParz2.setVisible(true);
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
                        N_PARZ++;
                        break;
                    case 3:
                        lblVotoParz4.setVisible(true);
                        cmbxVotoParz4.setVisible(true);
                        N_PARZ++;
                        break;
                    default:
                        System.err.println("I parziali possono essere da un minimo di 2 ad un massimo di 4");
                        break;
                }
            }
        });
        btnRimuoviParz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (N_PARZ) {
                    case 3:
                        lblVotoParz3.setVisible(false);
                        cmbxVotoParz3.setVisible(false);
                        N_PARZ--;
                        break;
                    case 4:
                        lblVotoParz4.setVisible(false);
                        cmbxVotoParz4.setVisible(false);
                        N_PARZ--;
                        break;
                    default:
                        System.err.println("I parziali possono essere da un minimo di 2 ad un massimo di 4");
                        break;
                }
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
