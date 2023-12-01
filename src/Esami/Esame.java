package Esami;

public interface Esame {
    public Object[] getDataJtbl();
    public String getNomeStudente();
    public String getCognomeStudente();
    public String getInsegnamento();
    public int getVotoFinale();

    public void setNomeStudente(String nomeStudente);
    public void setCognomeStudente(String cognomeStudente);
    public void setInsegnamento(String insegnamento);
    public void setVotoFinale(int votoFinale);

}


