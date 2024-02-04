package Grafica;

import Esami.Esame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe per la creazione di un grafico a barre utilizzando JFreeChart.
 */
public class GraficoBarre {

    private JFreeChart chart;
    JFrame jFrame;

    /**
     * Costruttore della classe GraficoBarre.
     *
     * @param esami Lista di esami da rappresentare nel grafico.
     */
    public GraficoBarre(ArrayList<Esame> esami) {
        // Creare il dataset
        CategoryDataset dataset = createDataset(esami);

        // Creare il CategoryChart
        chart = createChart(dataset);

        // Creare il pannello del grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        // Creare il frame e aggiungere il pannello del grafico
        if (jFrame == null)
            setFrame(chartPanel);
        else
            jFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
    }

    /**
     * Imposta il frame con il pannello del grafico.
     *
     * @param chartPanel Pannello del grafico.
     */
    private void setFrame(ChartPanel chartPanel) {
        jFrame = new JFrame("Grafico a Barre");
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    /**
     * Restituisce il JFreeChart creato.
     *
     * @return JFreeChart del grafico.
     */
    public JFreeChart getChart() {
        return chart;
    }

    /**
     * Crea il CategoryDataset a partire dalla lista di esami.
     *
     * @param esami Lista di esami.
     * @return CategoryDataset.
     */
    private CategoryDataset createDataset(ArrayList<Esame> esami) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Esame esame : esami) {
            String nomeStudente = esame.getNomeStudente() + " " + esame.getCognomeStudente();
            String insegnamento = esame.getInsegnamento();
            int votoFinale = esame.getVotoFinale();

            dataset.addValue(votoFinale, nomeStudente, insegnamento);
        }

        return dataset;
    }

    /**
     * Crea il CategoryChart.
     *
     * @param dataset CategoryDataset.
     * @return JFreeChart del grafico.
     */
    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Grafico Voti Esami", // Titolo del grafico
                "Insegnamento",       // Etichetta asse x
                "Voto Finale",        // Etichetta asse y
                dataset);             // Dataset

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotazione etichette asse x per maggiore leggibilità

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return barChart;
    }

    /**
     * Restituisce il frame del grafico.
     *
     * @return Frame del grafico.
     */
    public JFrame getjFrame() {
        return jFrame;
    }
}
