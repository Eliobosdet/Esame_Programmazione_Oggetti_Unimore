package Esami;

public class ProvaParziale extends AbstractEsame {
    protected float pesoPercentuale;

    public ProvaParziale(String nomeStudente, String cognomeStudente, String insegnamento, int votoFinale, float pesoPercentuale) {
        super(nomeStudente,cognomeStudente,insegnamento,votoFinale);
        this.pesoPercentuale = pesoPercentuale;
    }

    public void setPesoPercentuale(float pesoPercentuale) {
        this.pesoPercentuale = pesoPercentuale;
    }

    public float getPesoPercentuale() {
        return pesoPercentuale;
    }
}
