package Esami;

public abstract class AbstractEsame
        implements Esame
{
    protected String nomeStudente;
    protected String cognomeStudente;
    protected String insegnamento;
    protected int votoFinale;

    public AbstractEsame(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale) {
        this.nomeStudente=nomeStudente;
        this.cognomeStudente=cognomeStudente;
        this.insegnamento=insegnamento;
        this.votoFinale=votoFinale;
    }

    public AbstractEsame(){
        this.nomeStudente = "nome";
        this.cognomeStudente = "cognome";
        this.insegnamento = "insegnamento";
        this.votoFinale = 0;
    };

    @Override
    public String getNomeStudente() {
        return nomeStudente;
    }
    @Override
    public String getCognomeStudente() {
        return cognomeStudente;
    }
    @Override
    public String getInsegnamento() {return insegnamento;}
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
    public void setInsegnamento(String insegnamento) {this.insegnamento = insegnamento;}
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
