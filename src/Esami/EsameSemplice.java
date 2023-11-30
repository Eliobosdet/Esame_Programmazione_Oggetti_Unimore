package Esami;

public class EsameSemplice extends AbstractEsame {
    protected boolean lode;
    protected int crediti;
    public EsameSemplice(String nomeStudente, String cognomeStudente, String insegnamento,int votoFinale,boolean lode,int crediti) {
        super(nomeStudente,cognomeStudente,insegnamento,votoFinale);
        this.lode=lode;
        this.crediti=crediti;
    }

    public EsameSemplice(){
        super();
        this.lode=false;
        this.crediti=0;
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
