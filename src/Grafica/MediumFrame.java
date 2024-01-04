package Grafica;

import javax.swing.*;
import java.awt.*;

public class MediumFrame extends JFrame{
    private JPanel jp;

    public MediumFrame(String titolo, GridLayout gl) {
        jp = new JPanel(gl);
        setTitle(titolo);
        add(jp);
        setSize(400,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
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
}
