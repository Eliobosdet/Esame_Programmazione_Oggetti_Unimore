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
    public EsameComposto(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, int crediti, int n_parziali) {
        super(nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti);
        this.n_parziali = n_parziali;
    }

    /**
     * Costruttore che richiama super() e inizializza a 0 il numero di esami parziali
     */
    public EsameComposto() {
        super();
        this.n_parziali=0;
    }

    /**
     * Costruttore che crea l'oggetto richiamando super(nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti)
     * passandogli per parametro i dati (parsati) dell'array[] di Object
     * ed inizializza gli esami parziali
     * @param obj Array[] di Object contenente i dati dell'esame composto
     * @param arrayList Arraylist di ProvaParziale contenente i dati degli esami parziali
     */
    public EsameComposto(Object[] obj, ArrayList<ProvaParziale> arrayList) {
        super(String.valueOf(obj[0]),String.valueOf(obj[1]),String.valueOf(obj[2]),Integer.parseInt(String.valueOf(obj[3])),Integer.parseInt(String.valueOf(obj[4])));
        this.n_parziali = Integer.parseInt(String.valueOf(obj[6]));
        this.arrList_parziali = arrayList;
    }

    /**
     * Costruttore che crea l'oggetto richiamando this(obj,arraylist) passandogli per
     * parametro l'array[] di Object e l'arraylist di ProvaParziale, quest'ultimo ottenuto dal frame
     * @param obj Array[] di Object contenente i dati dell'esame composto
     * @param frame frame grafico associato all'esame composto
     */
    public EsameComposto(Object[] obj, InserisciCompostoGUI frame) {
        this(obj, frame.getArrayListParziali());
        setGui(frame);
        n_parziali = frame.getParzialiGUI().getNumParziali();
    }

    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {getNomeStudente(),getCognomeStudente(),getInsegnamento(),getVotoFinale(),getCrediti(),"false",n_parziali};
        return obj;
    }

    /**
     * Getter del numero dei parziali
     * @return numero degli esami parziali
     */
    public int getN_parziali() {
        return n_parziali;
    }

    /**
     * Getter dei parziali
     * @return Arraylist di ProvaParziale contenente le prove parziali
     */
    public ArrayList<ProvaParziale> getArrList_parziali() {
        return arrList_parziali;
    }


    public void setArrList_parziali(ArrayList<ProvaParziale> arrList_parziali) {
        this.arrList_parziali = arrList_parziali;
    }

    @Override
    public InserisciCompostoGUI getGui() {
        return (InserisciCompostoGUI) super.getGui();
    }
}
