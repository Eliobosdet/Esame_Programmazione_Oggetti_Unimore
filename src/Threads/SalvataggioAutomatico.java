package Threads;

import Grafica.Form;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SalvataggioAutomatico extends Thread {

    private Form form;
    private static String FILE_PATH = "./Salvataggi";
    private static String FILE_NAME = "recent_saved_changes.txt";
    private File file;
    private BufferedWriter writer;
    public SalvataggioAutomatico(Form form) {
        this.form = form;
        file = new File(FILE_PATH);

        if(!file.exists())
            file.mkdirs();

        file = new File(FILE_PATH,FILE_NAME);
        try {
            writer = new BufferedWriter(new FileWriter(file, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            if(!form.isSaved_changes()) {
                System.out.println("Thread: Salvo su file "+FILE_PATH+"/"+FILE_NAME);
                try {
                    writer = new BufferedWriter(new FileWriter(file, false));
                    form.writeExams(writer);
                } catch (IOException e) {e.printStackTrace();}
            } else {
                System.out.println("Thread: Nessuna modifica recente, non salvo");
            }
            try {
                sleep(60000);
            } catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}
