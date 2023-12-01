package Esami;

import java.util.ArrayList;

public class EsameComposto extends AbstractEsame {
    protected int n_parziali;
    protected ArrayList<ProvaParziale> arrList_parziali;
    public EsameComposto(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, int crediti, int n_parziali) {
        super(nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti,"Esame Composto");
        this.n_parziali = n_parziali;
    }

    public EsameComposto() {
        super("Composto");
        this.n_parziali=0;
    }

    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti,"false",tipoEsame};
        return obj;
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
