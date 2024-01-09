package Esami;

import Grafica.InserisciCompostoGUI;
import Grafica.InserisciGUI;

import java.util.ArrayList;

public class EsameComposto extends AbstractEsame {
    private int n_parziali;
    private ArrayList<ProvaParziale> arrList_parziali;
    public EsameComposto(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, int crediti, int n_parziali) {
        super(nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti);
        this.n_parziali = n_parziali;
    }

    public EsameComposto() {
        super();
        this.n_parziali=0;
    }

    public EsameComposto(Object[] obj, ArrayList<ProvaParziale> arrayList) {
        super(String.valueOf(obj[0]),String.valueOf(obj[1]),String.valueOf(obj[2]),Integer.parseInt(String.valueOf(obj[3])),Integer.parseInt(String.valueOf(obj[4])));
        this.n_parziali = Integer.parseInt(String.valueOf(obj[6]));

    }

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

    public int getN_parziali() {
        return n_parziali;
    }

    public ArrayList<ProvaParziale> getArrList_parziali() {
        return arrList_parziali;
    }

    public void setN_parziali(int n_parziali) {
        this.n_parziali = n_parziali;
    }

    public void setCrediti(int crediti) {
        setCrediti(crediti);
    }

    public void setArrList_parziali(ArrayList<ProvaParziale> arrList_parziali) {
        this.arrList_parziali = arrList_parziali;
    }

    @Override
    public InserisciCompostoGUI getGui() {
        return (InserisciCompostoGUI) super.getGui();
    }
}
