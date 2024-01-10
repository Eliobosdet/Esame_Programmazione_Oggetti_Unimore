package Grafica;

import javax.swing.*;
import java.awt.*;

public class MediumFrame extends JFrame{
    private JPanel jp;

    public MediumFrame(String titolo, GridLayout gl) {
        jp = new JPanel(gl);
        jp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(jp);
        setFrame(titolo);
    }

    public MediumFrame(String titolo) {
        jp = new JPanel();
        jp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(jp);
        setFrame(titolo);
    }


    public MediumFrame(){
        this("Medium Frame");
    }

    public void setFrame(String titolo) {
        setTitle(titolo);
        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }

    public JPanel getJp() {
        return jp;
    }

    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    public JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
}
