package Grafica;

import Esami.AbstractEsame;
import Esami.Esame;
import Esami.EsameSemplice;
import javafx.fxml.FXMLLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class Main extends JFrame {

    public static void main(String[] args) {
     //FRAME SETTINGS
        Form form = new Form();
        JFrame jf = new JFrame("Gestione Esami");
        jf.setContentPane(form.getJpnl());
        jf.setSize(800,600);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        String[] columns = {"NomeStudente","CognomeStudente","Insegnamento","VotoFinale","NumeroCrediti","Lode","TipoEsame"};  //COLONNE DELLA TABLE
        DefaultTableModel tblmdl = new DefaultTableModel();
        form.getJtbl().setModel(tblmdl);

        for (String s:
             columns) {
            tblmdl.addColumn(s);
        }

        ArrayList<Esame> jtblData = new ArrayList<Esame>();
        jtblData.add(new EsameSemplice());
        System.out.println(jtblData.get(jtblData.size()-1).toString()); //getLast
        Object[] obj = {"Paolo","Rossi","Matematica","30"};

        tblmdl.addRow(obj);
        tblmdl.addRow(obj);







        jf.setVisible(true);
    }
}
