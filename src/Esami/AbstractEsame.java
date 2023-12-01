package Esami;

public abstract class AbstractEsame
        implements Esame {
    protected String nomeStudente;
    protected String cognomeStudente;
    protected String insegnamento;
    protected String tipoEsame;
    protected int votoFinale;
    protected int crediti;

    public AbstractEsame(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, int crediti, String tipoEsame) {
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        this.insegnamento = insegnamento;
        this.votoFinale = votoFinale;
        this.crediti = crediti;
        this.tipoEsame = tipoEsame;
    }

    public AbstractEsame(String tipoEsame) {
        this.tipoEsame = tipoEsame;
        this.nomeStudente = null;
        this.cognomeStudente = null;
        this.insegnamento = null;
        this.votoFinale = 0;
        this.crediti = 0;
    }



    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {nomeStudente, cognomeStudente, insegnamento, votoFinale, crediti, tipoEsame};
        return obj;
    }

    @Override
    public String getTipoEsame() {
        return null;
    }

    @Override
    public int getCrediti() {
        return 0;
    }

    @Override
    public void setTipoEsame(String tipoEsame) {

    }

    @Override
    public void setCrediti(int crediti) {

    }

    @Override
    public String getNomeStudente() {
        return nomeStudente;
    }

    @Override
    public String getCognomeStudente() {
        return cognomeStudente;
    }

    @Override
    public String getInsegnamento() {
        return insegnamento;
    }

    @Override
    public int getVotoFinale() {
        return votoFinale;
    }

    @Override
    public void setNomeStudente(String nomeStudente) {
        this.nomeStudente = nomeStudente;
    }

    @Override
    public void setCognomeStudente(String cognomeStudente) {
        this.cognomeStudente = cognomeStudente;
    }

    @Override
    public void setInsegnamento(String insegnamento) {
        this.insegnamento = insegnamento;
    }

    @Override
    public void setVotoFinale(int votoFinale) {
        this.votoFinale = votoFinale;
    }

    @Override
    public String toString() {
        return "AbstractEsame{" +
                "nomeStudente='" + nomeStudente + '\'' +
                ", cognomeStudente='" + cognomeStudente + '\'' +
                ", insegnamento='" + insegnamento + '\'' +
                ", votoFinale=" + votoFinale +
                '}';
    }
}
