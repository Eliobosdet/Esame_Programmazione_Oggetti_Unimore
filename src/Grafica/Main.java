package Grafica;

import Esami.AbstractEsame;
import Esami.Esame;
import Esami.EsameSemplice;

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
        jf.setSize(900,900);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
