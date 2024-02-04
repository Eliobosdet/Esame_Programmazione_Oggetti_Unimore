package Esami;

import Grafica.InserisciCompostoGUI;
import Grafica.InserisciGUI;

import java.util.ArrayList;

/**
 * Classe che memorizza ed implementa attributi e metodi di un esame di tipo composto
 * Nello specifico: un esame svolto da uno studente, composto da più prove parziali
 * Essa si basa sull'interfaccia Esame
 */
public class EsameComposto extends AbstractEsame {
    private int n_parziali;
    private ArrayList<ProvaParziale> arrList_parziali;

    /**
     * Costruttore che inizializza l'oggetto con i dati specificati.
     *
     * @param nomeStudente    Nome dello studente.
     * @param cognomeStudente Cognome dello studente.
     * @param insegnamento     Insegnamento dell'esame.
     * @param votoFinale       Voto finale dell'esame.
     * @param crediti          Numero di crediti dell'esame.
     * @param n_parziali       Numero di prove parziali dell'esame composto.
     */
    public EsameComposto(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, int crediti, int n_parziali) {
        super(nomeStudente, cognomeStudente, insegnamento, votoFinale, crediti);
        this.n_parziali = n_parziali;
    }

    /**
     * Costruttore che richiama il costruttore della superclasse e inizializza a 0 il numero di esami parziali.
     */
    public EsameComposto() {
        super();
        this.n_parziali = 0;
    }

    /**
     * Costruttore che crea l'oggetto richiamando il costruttore base e inizializza gli esami parziali.
     *
     * @param obj       Array di Object contenente i dati dell'esame composto.
     * @param arrayList ArrayList di ProvaParziale contenente i dati degli esami parziali.
     */
    public EsameComposto(Object[] obj, ArrayList<ProvaParziale> arrayList) {
        super(String.valueOf(obj[0]), String.valueOf(obj[1]), String.valueOf(obj[2]), Integer.parseInt(String.valueOf(obj[3])), Integer.parseInt(String.valueOf(obj[4])));
        this.n_parziali = Integer.parseInt(String.valueOf(obj[6]));
        this.arrList_parziali = arrayList;
    }

    /**
     * Costruttore che crea l'oggetto richiamando il costruttore precedente passandogli l'array di Object e l'ArrayList di ProvaParziale.
     *
     * @param obj   Array di Object contenente i dati dell'esame composto.
     * @param frame Frame grafico associato all'esame composto.
     */
    public EsameComposto(Object[] obj, InserisciCompostoGUI frame) {
        this(obj, frame.getArrayListParziali());
        setGui(frame);
        n_parziali = frame.getParzialiGUI().getNumParziali();
    }

    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {getNomeStudente(), getCognomeStudente(), getInsegnamento(), getVotoFinale(), getCrediti(), "false", n_parziali};
        return obj;
    }

    /**
     * Restituisce il numero delle prove parziali.
     *
     * @return Numero delle prove parziali.
     */
    public int getN_parziali() {
        return n_parziali;
    }

    /**
     * Restituisce l'ArrayList di ProvaParziale contenente le prove parziali.
     *
     * @return ArrayList di ProvaParziale.
     */
    public ArrayList<ProvaParziale> getArrList_parziali() {
        return arrList_parziali;
    }

    /**
     * Imposta l'ArrayList di ProvaParziale.
     *
     * @param arrList_parziali ArrayList di ProvaParziale.
     */
    public void setArrList_parziali(ArrayList<ProvaParziale> arrList_parziali) {
        this.arrList_parziali = arrList_parziali;
    }

    @Override
    public InserisciCompostoGUI getGui() {
        return (InserisciCompostoGUI) super.getGui();
    }
}
