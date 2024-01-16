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

public class GraficoBarre {

    private JFreeChart chart;
    JFrame jFrame;

    public GraficoBarre(ArrayList<Esame> esami) {
        // Creare il dataset
        CategoryDataset dataset = createDataset(esami);

        // Creare il CategoryChart
        chart = createChart(dataset);

        // Creare il pannello del grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        // Creare il frame e aggiungere il pannello del grafico
        if(jFrame == null)
            setFrame(chartPanel);
        else
            jFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
    }

    private void setFrame(ChartPanel chartPanel) {
        jFrame = new JFrame("Grafico a Barre");
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    // Metodo per ottenere il CategoryChart creato
    public JFreeChart getChart() {
        return chart;
    }

    // Metodo per creare il CategoryDataset a partire dagli esami
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

    // Metodo per creare il CategoryChart
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

    public JFrame getjFrame() {
        return jFrame;
    }
}