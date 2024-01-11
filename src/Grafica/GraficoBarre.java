package Grafica;

import Esami.Esame;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

import java.util.ArrayList;

public class GraficoBarre {

    private CategoryChart chart;

    public GraficoBarre(ArrayList<Esame> esami) {
        // Creare un nuovo CategoryChartBuilder
        CategoryChartBuilder chartBuilder = new CategoryChartBuilder();
        chartBuilder.width(800).height(600).title("Grafico Esami").xAxisTitle("Esame").yAxisTitle("Voto");

        // Ottenere l'istanza di CategoryChart
        chart = chartBuilder.build();

        // Impostare lo stile direttamente sulla CategoryChart
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);

        // Esempio: Aggiungi serie al grafico
        ArrayList<String> nomiEsami = new ArrayList<>();
        ArrayList<Integer> votiEsami = new ArrayList<>();

        for (Esame esame : esami) {
            String s = esame.getNomeStudente() + " " + esame.getCognomeStudente() + " " + esame.getInsegnamento();
            nomiEsami.add(s);
            votiEsami.add(esame.getVotoFinale());
        }

        // Aggiungi la serie direttamente sulla CategoryChart
        chart.addSeries("Voto Esame", nomiEsami, votiEsami);
    }

    // Metodo per ottenere il CategoryChart creato
    public CategoryChart getCategoryChart() {
        return chart;
    }
}
