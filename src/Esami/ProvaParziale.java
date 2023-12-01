package Esami;

public class ProvaParziale {
    protected int pesoPercentuale;
    private int votoFinale;

    public ProvaParziale(int votoFinale, int pesoPercentuale) {
        this.votoFinale = votoFinale;
        this.pesoPercentuale = pesoPercentuale;
    }

    public int getVotoFinale() {
        return votoFinale;
    }

    public void setVotoFinale(int votoFinale) {
        this.votoFinale = votoFinale;
    }
    public void setPesoPercentuale(int pesoPercentuale) {
        this.pesoPercentuale = pesoPercentuale;
    }

    public int getPesoPercentuale() {
        return pesoPercentuale;
    }
}
