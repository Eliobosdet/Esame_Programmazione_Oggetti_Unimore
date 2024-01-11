package Threads;

import Grafica.Form;

public class AggiornaListTabella extends Thread{
    private Form form;

    public AggiornaListTabella(Form form) {
        this.form = form;
    }

    @Override
    public void run() {
        while (true) {
            form.setEsamiTable(form.getEsami());
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
