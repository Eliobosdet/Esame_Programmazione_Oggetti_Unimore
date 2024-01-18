package Esami;

import Grafica.InserisciGUI;
import Grafica.InserisciSempliceGUI;

public class EsameSemplice extends AbstractEsame {
    private boolean lode;

    public EsameSemplice(String nomeStudente, String cognomeStudente, String insegnamento,int votoFinale,int crediti, boolean lode) {
        super(nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti);
        this.lode=lode;
    }

    public EsameSemplice(Object[] obj) {
        super(String.valueOf(obj[0]),String.valueOf(obj[1]),String.valueOf(obj[2]),Integer.parseInt(String.valueOf(obj[3])),Integer.parseInt(String.valueOf(obj[4])));
        this.lode = Boolean.parseBoolean(obj[5].toString());
    }

    public EsameSemplice(Object[] obj,InserisciSempliceGUI frame) {
        this(obj);
        setGui(frame);
    }

    /*public EsameSemplice(InserisciSempliceGUI gui) {
        super(gui);
    }*/

    public EsameSemplice(){
        super();
        this.lode=false;
        super.setCrediti(0);
    }


    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {getNomeStudente(),getCognomeStudente(),getInsegnamento(),getVotoFinale(),getCrediti(),lode,"0"};
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

    public boolean isLode() {
        return lode;
    }

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
