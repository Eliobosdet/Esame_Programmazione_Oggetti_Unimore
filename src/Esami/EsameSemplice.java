package Esami;

public class EsameSemplice extends AbstractEsame {
    protected boolean lode;
    protected static String tipo = "Esame Semplice";

    public EsameSemplice(String nomeStudente, String cognomeStudente, String insegnamento,int votoFinale,int crediti, boolean lode) {
        super(nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti,tipo);
        this.lode=lode;
    }

    public EsameSemplice(Object[] obj) {
        super(String.valueOf(obj[0]),String.valueOf(obj[1]),String.valueOf(obj[2]),Integer.parseInt(String.valueOf(obj[3])),Integer.parseInt(String.valueOf(obj[4])),tipo);
        this.lode = (boolean) obj[5];
    }

    public EsameSemplice(){
        super(tipo);
        this.lode=false;
        this.crediti=0;
    }

    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti,lode,tipo};
        return obj;
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
