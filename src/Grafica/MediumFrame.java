package Grafica;

import javax.swing.*;
import java.awt.*;

public class MediumFrame extends JFrame{
    private JFrame jf;
    private JPanel jp;

    public MediumFrame(String titolo, GridLayout gl) {
        jp = new JPanel(gl);
        jf = new JFrame(titolo);
        jf.add(jp);
        jf.setSize(400,400);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(HIDE_ON_CLOSE);
        jf.setVisible(true);
    }

    public MediumFrame(String titolo) {
        this(titolo,new GridLayout(6,2));
    }

    public MediumFrame(){
        this("Medium Frame");
    }

    public JPanel getJp() {
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JFrame getJf() {
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }
}
