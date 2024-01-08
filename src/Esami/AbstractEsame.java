package Esami;

import Grafica.InserisciGUI;

public abstract class AbstractEsame
        implements Esame {
    private String nomeStudente;
    private String cognomeStudente;
    private String insegnamento;
    private String tipoEsame;
    private int votoFinale;
    private int crediti;
    private InserisciGUI gui;


    public AbstractEsame(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, int crediti) {
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        this.insegnamento = insegnamento;
        this.votoFinale = votoFinale;
        this.crediti = crediti;
    }

    public AbstractEsame() {
        this.nomeStudente = null;
        this.cognomeStudente = null;
        this.insegnamento = null;
        this.votoFinale = 0;
        this.crediti = 0;
    }

    public AbstractEsame(InserisciGUI gui) {
        this.gui = gui;
    }











    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {nomeStudente, cognomeStudente, insegnamento, votoFinale, crediti, tipoEsame};
        return obj;
    }

    @Override
    public void setDataJtbl(Object[] obj) {
        this.nomeStudente = String.valueOf(obj[0]);
        this.cognomeStudente = String.valueOf(obj[1]);
        this.insegnamento = String.valueOf(obj[2]);
        this.votoFinale = Integer.parseInt(String.valueOf(obj[3]));
        this.crediti = Integer.parseInt(String.valueOf(obj[4]));
        this.tipoEsame = String.valueOf(obj[5]);
    }

    @Override
    public InserisciGUI getGui() {
        return gui;
    }

    @Override
    public void setGui(InserisciGUI gui) {
        this.gui = gui;
    }

    @Override
    public int getCrediti() {
        return crediti;
    }

    @Override
    public void setCrediti(int crediti) {
        this.crediti = crediti;
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
