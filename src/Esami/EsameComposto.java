package Esami;

import java.util.ArrayList;

public class EsameComposto extends AbstractEsame {
    protected int n_parziali;
    protected int crediti;
    protected ArrayList<ProvaParziale> arrList_parziali;
    public EsameComposto(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, int n_parziali, int crediti) {
        super(nomeStudente,cognomeStudente,insegnamento,votoFinale);
        this.n_parziali = n_parziali;
        this.crediti=crediti;
    }

    public EsameComposto() {
        super();
        this.n_parziali=0;
        this.crediti=0;
    }

    public EsameComposto(ArrayList<ProvaParziale> arrList_parziali) {

    }

    public int getN_parziali() {
        return n_parziali;
    }

    public int getCrediti() {
        return crediti;
    }

    public ArrayList<ProvaParziale> getArrList_parziali() {
        return arrList_parziali;
    }

    public void setN_parziali(int n_parziali) {
        this.n_parziali = n_parziali;
    }

    public void setCrediti(int crediti) {
        this.crediti = crediti;
    }

    public void setArrList_parziali(ArrayList<ProvaParziale> arrList_parziali) {
        this.arrList_parziali = arrList_parziali;
    }
}
