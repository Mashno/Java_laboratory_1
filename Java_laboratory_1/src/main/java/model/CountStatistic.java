/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.stat.*;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
/**
 *
 * @author Владислав
 */
public class CountStatistic {
    
    
    public Map<String, Object> countXLSX(HashMap<String, List<Double>> savedData) {
     Map<String, DescriptiveStatistics> statsMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        // Рассчитываем основные статистики для каждой выборки
        for (Map.Entry<String, List<Double>> entry : savedData.entrySet()) {
            String key = entry.getKey();
            List<Double> values = entry.getValue();
            DescriptiveStatistics stats = new DescriptiveStatistics();

            for (double v : values) {
                stats.addValue(v);
            }
            
            double mean = stats.getMean();
            double geometricMean = StatUtils.geometricMean(stats.getValues());
            double stdDev = stats.getStandardDeviation();
            double variance = stats.getVariance();
            double range = stats.getMax() - stats.getMin();
            double variationCoefficient = stdDev / mean;
            double confidenceLevel = 0.95;
            double confidenceIntervalHalfWidth = 1.96 * (stdDev / Math.sqrt(stats.getN()));
            double min = stats.getMin();
            double max = stats.getMax();

            Map<String, Object> sampleStats = new HashMap<>();
            sampleStats.put("Среднее арифметическое", mean);
            sampleStats.put("Среднее геометрическое", geometricMean);
            sampleStats.put("Стандартное отклонение", stdDev);
            sampleStats.put("Дисперсия", variance);
            sampleStats.put("Размах", range);
            sampleStats.put("Коэффициент вариации", variationCoefficient);
            sampleStats.put("Доверительный интервал", new double[]{mean - confidenceIntervalHalfWidth, mean + confidenceIntervalHalfWidth});
            sampleStats.put("Минимум", min);
            sampleStats.put("Максимум", max);
            sampleStats.put("Количество элементов", stats.getN());
            
            result.put(key, sampleStats);
            statsMap.put(key, stats);
        }

        Covariance covariance = new Covariance();
        Map<String, Double> covarianceResults = new HashMap<>();
        
        for (String key1 : statsMap.keySet()) {
            for (String key2 : statsMap.keySet()) {
                if (!key1.equals(key2)) {
                    double cov = covariance.covariance(statsMap.get(key1).getValues(), statsMap.get(key2).getValues());
                    covarianceResults.put(key1 + " - " + key2, cov);
                }
            }
        }
        
        result.put("Ковариация", covarianceResults);
        return result;
    }
}
