package Main;

import Esami.AbstractEsame;
import Esami.Esame;
import Esami.EsameSemplice;
import Grafica.Form;
import Threads.AggiornaListTabella;
import Threads.SalvataggioAutomatico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class Main extends JFrame {

    public static void main(String[] args) {
     //FRAME SETTINGS
        Form form = new Form();
        JFrame jf = new JFrame("Gestione Esami");

        SalvataggioAutomatico threadSalvataggio = new SalvataggioAutomatico(form);
        //AggiornaListTabella threadAggiornaLista = new AggiornaListTabella(form);

        jf.setContentPane(form.getJpnl());
        jf.setSize(900,700);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        jf.setVisible(true);

        threadSalvataggio.start();
        //threadAggiornaLista.start();

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!form.isSaved_changes()) {
                    int response = form.askToSave();
                    if(response==0) //Risposta = SI
                        form.saveTableOnFile();
                    else if(response != -1) //Risposta != CHIUDI MSG
                        System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        });
    }

}
