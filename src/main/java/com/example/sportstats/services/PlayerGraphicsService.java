package com.example.sportstats.services;

import com.example.sportstats.repositories.PlayerInfoRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class PlayerGraphicsService {
    @Autowired
    private PlayerInfoRepository playerInfoRepository;

    public void generateAgeChart() {
        List<Object[]> teamList = playerInfoRepository.findTeamAverageAge();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Object[] team : teamList) {
            String teamName = (String) team[0];
            double averageAge = (Double) team[1];

            dataset.addValue(averageAge, "Teams", teamName);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Average Age in Teams",
                "Teams",
                "Average Age",
                dataset
        );

        // Увеличение точности значений на оси Y
        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();

        // Установка начального значения оси Y на 25
        yAxis.setLowerBound(25);

        // Установка формата чисел
        yAxis.setNumberFormatOverride(new DecimalFormat("#.##"));


        try {
            ChartUtils.saveChartAsJPEG(new File("graphics/average_ages.jpg"), barChart, 600, 800);
            System.out.println("График успешно сохранен в файл average_ages.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateAgePieChart() {
        List<Object[]> teamList = playerInfoRepository.findTeamAverageAge();

        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Object[] team : teamList) {
            String teamName = (String) team[0];
            double averageAge = (Double) team[1];

            dataset.setValue(teamName, averageAge);
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Average Age Distribution in Teams",
                dataset,
                true,
                true,
                false
        );

        // Добавление процентного соотношения внутри каждого сегмента
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} - {1} ({2})"));

        try {
            ChartUtils.saveChartAsJPEG(new File("graphics/average_ages_pie.jpg"), pieChart, 600, 800);
            System.out.println("Круговая диаграмма успешно сохранена в файл average_ages_pie.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
