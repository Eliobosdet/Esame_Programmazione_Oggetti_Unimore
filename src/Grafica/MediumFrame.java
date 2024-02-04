package Grafica;

import javax.swing.*;
import java.awt.*;

/**
 * Classe base per la creazione di frame con layout medio.
 */
public class MediumFrame extends JFrame {
    private JPanel jp;

    /**
     * Costruttore che imposta il titolo e il layout del pannello.
     *
     * @param titolo Titolo del frame.
     * @param gl     Layout da applicare al pannello.
     */
    public MediumFrame(String titolo, GridLayout gl) {
        jp = new JPanel(gl);
        jp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(jp);
        setFrame(titolo);
    }

    /**
     * Costruttore che imposta il titolo e utilizza un layout di default per il pannello.
     *
     * @param titolo Titolo del frame.
     */
    public MediumFrame(String titolo) {
        jp = new JPanel();
        jp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(jp);
        setFrame(titolo);
    }

    /**
     * Costruttore di default che imposta il titolo predefinito e utilizza un layout di default per il pannello.
     */
    public MediumFrame() {
        this("Medium Frame");
    }

    /**
     * Imposta le caratteristiche principali del frame.
     *
     * @param titolo Titolo del frame.
     */
    public void setFrame(String titolo) {
        setTitle(titolo);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Restituisce il pannello del frame.
     *
     * @return Pannello del frame.
     */
    public JPanel getJp() {
        return jp;
    }

    /**
     * Imposta il pannello del frame.
     *
     * @param jp Nuovo pannello del frame.
     */
    public void setJp(JPanel jp) {
        this.jp = jp;
    }

    /**
     * Crea una JLabel centrata con il testo specificato.
     *
     * @param text Testo della JLabel.
     * @return JLabel centrata.
     */
    public JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
}
