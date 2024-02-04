package Esami;

import Grafica.InserisciGUI;
import Grafica.InserisciSempliceGUI;

/**
 * Classe che memorizza ed implementa attributi e metodi di un esame di tipo semplice
 * Nello specifico: un esame svolto da uno studente, composto da una sola prova
 * Essa si basa sull'interfaccia Esame
 */
public class EsameSemplice extends AbstractEsame {
    private boolean lode;

    /**
     * Costruttore che inizializza un oggetto EsameSemplice con i dati specificati.
     *
     * @param nomeStudente    Nome dello studente.
     * @param cognomeStudente Cognome dello studente.
     * @param insegnamento     Insegnamento dell'esame.
     * @param votoFinale       Voto finale dell'esame.
     * @param crediti          Numero di crediti dell'esame.
     * @param lode             Indica se l'esame è con lode o meno.
     */
    public EsameSemplice(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, int crediti, boolean lode) {
        super(nomeStudente, cognomeStudente, insegnamento, votoFinale, crediti);
        this.lode = lode;
    }

    /**
     * Costruttore che crea un oggetto EsameSemplice richiamando il costruttore della superclasse e inizializzando a false l'attributo "lode".
     */
    public EsameSemplice() {
        super();
        this.lode = false;
        super.setCrediti(0);
    }

    /**
     * Costruttore che crea un oggetto EsameSemplice richiamando il costruttore precedente e inizializza l'attributo "lode" a true o false.
     *
     * @param obj Array di Object contenente i dati dell'esame semplice.
     */
    public EsameSemplice(Object[] obj) {
        super(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), Integer.parseInt(String.valueOf(obj[3])), Integer.parseInt(String.valueOf(obj[4])));
        this.lode = Boolean.parseBoolean(obj[5].toString());
    }

    /**
     * Costruttore che crea un oggetto EsameSemplice richiamando il costruttore precedente e inizializza l'attributo "lode" a true o false.
     *
     * @param obj   Array di Object contenente i dati dell'esame semplice.
     * @param frame Frame grafico associato all'esame semplice.
     */
    public EsameSemplice(Object[] obj, InserisciSempliceGUI frame) {
        this(obj);
        setGui(frame);
    }

    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {getNomeStudente(), getCognomeStudente(), getInsegnamento(), getVotoFinale(), getCrediti(), lode, "0"};
        return obj;
    }

    @Override
    public void setDataJtbl(Object[] obj) {
        super.setDataJtbl(obj);
        this.lode = Boolean.parseBoolean(String.valueOf(obj[6]));
    }

    @Override
    public InserisciSempliceGUI getGui() {
        return (InserisciSempliceGUI) super.getGui();
    }

    @Override
    public void setGui(InserisciGUI gui) {
        super.setGui(gui);
    }

    /**
     * Restituisce true se l'esame è con lode, altrimenti restituisce false.
     *
     * @return true se l'esame è con lode, altrimenti false.
     */
    public boolean isLode() {
        return lode;
    }

    /**
     * Imposta se l'esame è con lode o meno.
     *
     * @param lode true se l'esame è con lode, altrimenti false.
     */
    public void setLode(boolean lode) {
        this.lode = lode;
    }

    @Override
    public String toString() {
        return super.toString() + "EsameSemplice{" +
                "lode=" + lode +
                ", crediti=" + getCrediti() +
                '}';
    }
}
