package Threads;

import Grafica.Form;

/**
 * Questa classe rappresenta un thread che si occupa di aggiornare periodicamente
 * la tabella degli esami in una finestra di tipo {@link Form}.
 */
public class AggiornaListTabella extends Thread {
    private Form form;

    /**
     * Costruttore che inizializza il thread con un riferimento a un'istanza di {@link Form}.
     *
     * @param form Oggetto di tipo {@link Form} su cui eseguire gli aggiornamenti della tabella.
     */
    public AggiornaListTabella(Form form) {
        this.form = form;
    }

    /**
     * Metodo che viene eseguito quando il thread viene avviato.
     * Si occupa di eseguire ciclicamente l'aggiornamento della tabella nella finestra associata.
     */
    @Override
    public void run() {
        while (true) {
            // Aggiorna la tabella degli esami nella finestra
            form.setEsamiTable(form.getEsami());

            try {
                // Aggiorna ogni 100 millisecondi (può essere personalizzato)
                sleep(100);
            } catch (InterruptedException e) {
                // Gestisce eventuali interruzioni del thread
                e.printStackTrace();
            }
        }
    }
}
