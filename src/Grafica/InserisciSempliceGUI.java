package Grafica;

import javax.swing.*;

public class InserisciSempliceGUI extends JFrame {
        private JFrame jf = new JFrame("Inserisci Esame Semplice");
        private JPanel jp = new JPanel();
        private JTextField txtNome;
        private JTextField txtCogome;
        public InserisciSempliceGUI() {
            jf.setContentPane(jp);
            jf.setSize(400,400);
            jf.setResizable(false);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(EXIT_ON_CLOSE);




            jf.setVisible(true);
        }
}
