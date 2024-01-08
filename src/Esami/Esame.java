package Esami;

import Grafica.InserisciGUI;

public interface Esame {
    public String getNomeStudente();
    public String getCognomeStudente();
    public String getInsegnamento();
    public int getVotoFinale();
    public int getCrediti();

    public Object[] getDataJtbl();
    public InserisciGUI getGui();

    public void setNomeStudente(String nomeStudente);
    public void setCognomeStudente(String cognomeStudente);
    public void setInsegnamento(String insegnamento);
    public void setVotoFinale(int votoFinale);
    public void setCrediti(int crediti);

    public void setDataJtbl(Object[] obj);
    public void setGui(InserisciGUI gui);

}


