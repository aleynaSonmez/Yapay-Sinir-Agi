/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yapaysiniragi;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This program demonstrates how to draw XY line chart with XYDataset using
 * JFreechart library.
 *
 * @author www.codejava.net
 *
 */
public class GrafikCiz extends JFrame {

    List<Double> hataVerileri;

    public GrafikCiz() {
        super("Grafik");
        hataVerileri = new ArrayList<>();

        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    void calistir() {
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
    }

    private JPanel createChartPanel() {
        String chartTitle = "100 Epok İle İterasyon - Hata Değeri Grafiği";
        String xAxisLabel = "İterasyon";
        String yAxisLabel = "Hata Değeri";

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        customizeChart(chart);

        File imageFile = new File("İterasyon - Hata Değeri Grafiği.png");
        int width = 640;
        int height = 480;

        try {
            ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return new ChartPanel(chart);
    }
    XYSeries series1 = new XYSeries("Hata Değeri = Ortalama Hata Kare (MSE)");

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (int i = 0; i < hataVerileri.size(); i++) {
            series1.add(i, hataVerileri.get(i));
        }

        dataset.addSeries(series1);

        return dataset;
    }

    private void customizeChart(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        plot.setOutlinePaint(Color.GREEN);
        plot.setOutlineStroke(new BasicStroke(2.0f));
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.BLACK);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.GREEN);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.GREEN);

    }

}
