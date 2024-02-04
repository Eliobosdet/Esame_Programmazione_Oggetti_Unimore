package Main;
import Grafica.Form;
import Threads.SalvataggioAutomatico;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Classe principale del programma per la gestione degli esami.
 */
public class Main extends JFrame {

    /**
     * Metodo principale (main) del programma.
     *
     * @param args Argomenti della riga di comando (non utilizzati in questo caso).
     */
    public static void main(String[] args) {
        // Impostazioni del frame principale
        Form form = new Form();
        JFrame jf = new JFrame("Gestione Esami");

        SalvataggioAutomatico threadSalvataggio = new SalvataggioAutomatico(form);
        //AggiornaListTabella threadAggiornaLista = new AggiornaListTabella(form);

        jf.setContentPane(form.getJpnl());
        jf.setSize(900, 700);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        jf.setVisible(true);

        threadSalvataggio.start();
        //threadAggiornaLista.start();

        // Listener per gestire la chiusura della finestra
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Verifica se ci sono modifiche non salvate
                if (!form.isSaved_changes()) {
                    // Chiedi all'utente se desidera salvare le modifiche prima di chiudere
                    int response = form.askToSave();
                    if (response == 0) // Risposta = SI
                        form.saveTableOnFile();
                    else if (response != -1) // Risposta != CHIUDI MSG
                        System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        });
    }
}
