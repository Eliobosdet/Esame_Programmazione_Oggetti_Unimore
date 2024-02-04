package Threads;

import Grafica.Form;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Questa classe rappresenta un thread responsabile del salvataggio automatico periodico
 * delle modifiche effettuate in un oggetto {@link Form} su un file di testo.
 */
public class SalvataggioAutomatico extends Thread {

    private Form form;
    private static final String FILE_PATH = "./Salvataggi";
    private static final String FILE_NAME = "recent_saved_changes.txt";
    private File file;
    private BufferedWriter writer;

    /**
     * Costruttore che inizializza il thread con un riferimento a un'istanza di {@link Form}.
     * Verifica l'esistenza della cartella e del file per il salvataggio automatico,
     * creandoli se non esistono, e inizializza un BufferedWriter per la scrittura su file.
     *
     * @param form Oggetto di tipo {@link Form} su cui eseguire il salvataggio automatico.
     */
    public SalvataggioAutomatico(Form form) {
        this.form = form;
        file = new File(FILE_PATH);

        if (!file.exists())
            file.mkdirs();

        file = new File(FILE_PATH, FILE_NAME);
        try {
            writer = new BufferedWriter(new FileWriter(file, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che viene eseguito quando il thread viene avviato.
     * Si occupa di eseguire ciclicamente il salvataggio automatico su file,
     * verificando se sono state effettuate modifiche recenti.
     */
    @Override
    public void run() {
        while (true) {
            if (!form.isSaved_changes()) {
                System.out.println("Thread: Salvo su file " + FILE_PATH + "/" + FILE_NAME);
                try {
                    writer = new BufferedWriter(new FileWriter(file, false));
                    form.writeExams(writer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Thread: Nessuna modifica recente, non salvo");
            }
            try {
                // Intervallo di tempo tra i salvataggi automatici (in millisecondi)
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
