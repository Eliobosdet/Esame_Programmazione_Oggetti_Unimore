package Esami;

import Grafica.InserisciGUI;

/**
 * Classe astratta che memorizza ed implementa attributi e metodi di un esame generico universitario
 * Essa si basa sull'interfaccia "Esame"
 */
public abstract class AbstractEsame
        implements Esame {
    private String nomeStudente;
    private String cognomeStudente;
    private String insegnamento;
    private String tipoEsame;
    private int votoFinale;
    private int crediti;
    private InserisciGUI gui;

    /**
     * Costruttore dell'oggetto AbstractEsame: inizializza i valori della classe con quelli passati per parametro
     * @param nomeStudente Nome dello studente
     * @param cognomeStudente Cognome dello studente
     * @param insegnamento Insegnamento dell'esame
     * @param votoFinale Voto finale dell'esame
     * @param crediti Crediti dell'esame
     */
    public AbstractEsame(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, int crediti) {
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        this.insegnamento = insegnamento;
        this.votoFinale = votoFinale;
        this.crediti = crediti;
    }

    /**
     * Costruttore dell'oggetto AbstractEsame: inizializza i valori della classe a "0" o "null"
     */
    public AbstractEsame() {
        this.nomeStudente = null;
        this.cognomeStudente = null;
        this.insegnamento = null;
        this.votoFinale = 0;
        this.crediti = 0;
    }

    /**
     * Getter dei dati della classe sotto forma di Object[]
     * @return array di Object contenente i dati dello studente e dell'esame svolto
     */
    @Override
    public Object[] getDataJtbl() {
        Object[] obj = {nomeStudente, cognomeStudente, insegnamento, votoFinale, crediti, tipoEsame};
        return obj;
    }

    /**
     * Setter dei dati della classe sotto forma di Object[]
     * @param obj array di Object contenente i dati dello studente e dell'esame svolto
     */
    @Override
    public void setDataJtbl(Object[] obj) {
        this.nomeStudente = String.valueOf(obj[0]);
        this.cognomeStudente = String.valueOf(obj[1]);
        this.insegnamento = String.valueOf(obj[2]);
        this.votoFinale = Integer.parseInt(String.valueOf(obj[3]));
        this.crediti = Integer.parseInt(String.valueOf(obj[4]));
        this.tipoEsame = String.valueOf(obj[5]);
    }

    /**
     * Getter dell'interfaccia grafica dell'esame
     * @return Interfaccia grafica per inserimento/modifica dei dati dell'esame
     */
    @Override
    public InserisciGUI getGui() {
        return gui;
    }

    /**
     * Setter dell'interfaccia grafica dell'esame
     * @param gui Interfaccia grafica per inserimento/modifica dei dati dell'esame
     */
    @Override
    public void setGui(InserisciGUI gui) {
        this.gui = gui;
    }

    /**
     * Getter dei crediti
     * @return Crediti dell'esame
     */
    @Override
    public int getCrediti() {
        return crediti;
    }

    /**
     * Setter dei crediti
     * @param crediti Crediti dell'esame
     */
    @Override
    public void setCrediti(int crediti) {
        this.crediti = crediti;
    }

    /**
     * Setter del nome studente
     * @return Nome dello studente che ha svolto l'esame
     */
    @Override
    public String getNomeStudente() {
        return nomeStudente;
    }

    /**
     * Getter del cognome studente
     * @return Cognome dello studente che ha svolto l'esame
     */
    @Override
    public String getCognomeStudente() {
        return cognomeStudente;
    }

    /**
     * Getter dell'insegnamento
     * @return Insegnamento dell'esame
     */
    @Override
    public String getInsegnamento() {
        return insegnamento;
    }

    /**
     * Getter del voto finale
     * @return Voto finale dell'esame svolto
     */
    @Override
    public int getVotoFinale() {
        return votoFinale;
    }

    /**
     * Setter del nome studente
     * @param nomeStudente Nome dello studente
     */
    @Override
    public void setNomeStudente(String nomeStudente) {
        this.nomeStudente = nomeStudente;
    }

    /**
     * Setter del cognome dello studentee
     * @param cognomeStudente cognome dello studente
     */
    @Override
    public void setCognomeStudente(String cognomeStudente) {
        this.cognomeStudente = cognomeStudente;
    }

    /**
     * Setter dell'insegnamento
     * @param insegnamento insegnamento dell'esame
     */
    @Override
    public void setInsegnamento(String insegnamento) {
        this.insegnamento = insegnamento;
    }

    /**
     * Setter del voto finale
     * @param votoFinale voto finale dell'esame
     */
    @Override
    public void setVotoFinale(int votoFinale) {
        this.votoFinale = votoFinale;
    }

    /**
     * Questo metodo ritorna i dati dello studente e dell'esame svolto sotto forma di stringa
     * @return Stringa contenente i dati dello studente e dell'esame
     */
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
