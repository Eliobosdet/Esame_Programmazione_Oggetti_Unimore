package Esami;

public class EsameSemplice extends AbstractEsame {
    protected boolean lode;
    protected int crediti;
    public EsameSemplice(String nomeStudente, String cognomeStudente, String insegnamento,int votoFinale,int crediti, boolean lode) {
        super(nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti,"Esame Semplice");
        this.lode=lode;
    }

    public EsameSemplice(){
        super("Semplice");
        this.lode=false;
        this.crediti=0;
    }

    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {nomeStudente,cognomeStudente,insegnamento,votoFinale,crediti,lode,tipoEsame};
        return obj;
    }

    public boolean isLode() {
        return lode;
    }

    public int getCrediti() {
        return crediti;
    }

    public void setLode(boolean lode) {
        this.lode = lode;
    }

    public void setCrediti(int crediti) {
        this.crediti = crediti;
    }

    @Override
    public String toString() {
        return super.toString() + "EsameSemplice{" +
                "lode=" + lode +
                ", crediti=" + crediti +
                '}';
    }
}
