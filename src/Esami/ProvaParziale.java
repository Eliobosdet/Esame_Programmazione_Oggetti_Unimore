package Esami;

public class ProvaParziale {
    protected int pesoPercentuale;
    private int votoFinale;

    /**
     * Constructor to initialize the ProvaParziale object with a final score and a percentage weight.
     *
     * @param votoFinale        The final score of the partial exam.
     * @param pesoPercentuale   The percentage weight of the partial exam.
     */
    public ProvaParziale(int votoFinale, int pesoPercentuale) {
        this.votoFinale = votoFinale;
        this.pesoPercentuale = pesoPercentuale;
    }

    /**
     * Getter for the final score of the partial exam.
     *
     * @return The final score of the partial exam.
     */
    public int getVotoFinale() {
        return votoFinale;
    }

    /**
     * Getter for the percentage weight of the partial exam.
     *
     * @return The percentage weight of the partial exam.
     */
    public int getPesoPercentuale() {
        return pesoPercentuale;
    }

    /**
     * Setter for the final score of the partial exam.
     *
     * @param votoFinale The final score to set.
     */
    public void setVotoFinale(int votoFinale) {
        this.votoFinale = votoFinale;
    }

    /**
     * Setter for the percentage weight of the partial exam.
     *
     * @param pesoPercentuale The percentage weight to set.
     */
    public void setPesoPercentuale(int pesoPercentuale) {
        this.pesoPercentuale = pesoPercentuale;
    }
}
