package Esami;

public interface Esame {
    public Object[] getDataJtbl();
    public String getNomeStudente();
    public String getCognomeStudente();
    public String getInsegnamento();
    public String getTipoEsame();
    public int getVotoFinale();
    public int getCrediti();

    public void setNomeStudente(String nomeStudente);
    public void setCognomeStudente(String cognomeStudente);
    public void setInsegnamento(String insegnamento);
    public void setTipoEsame(String tipoEsame);
    public void setVotoFinale(int votoFinale);
    public void setCrediti(int crediti);

}


