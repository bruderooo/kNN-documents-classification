package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.ClassifiedArticle;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PerformanceRates {
    double[][] confusionMatrix = new double[6][6];
    Map<String, Integer> matrixMap = new HashMap<>();

    PerformanceRates(List<ClassifiedArticle> articleArray) {
        matrixMap.put("west-germany", 0);
        matrixMap.put("usa", 1);
        matrixMap.put("france", 2);
        matrixMap.put("uk", 3);
        matrixMap.put("canada", 4);
        matrixMap.put("japan", 5);

        for (ClassifiedArticle classifiedArticle : articleArray) {
            confusionMatrix
                    [matrixMap.getOrDefault(classifiedArticle.getPredictedPlace(), 0)]
                    [matrixMap.getOrDefault(classifiedArticle.getArticle().getPlaces().get(0), 0)] += 1;
        }
    }

    public double precision() {
        double precisionSum = 0.0;

        for (int i = 0; i < 6; i++) {
            double rowSum = 0.0;
            for (int j = 0; j < 6; j++) {
                rowSum += confusionMatrix[i][j];
            }
            if (rowSum != 0) {
                precisionSum += (confusionMatrix[i][i] / rowSum);
            }
        }
        return (precisionSum / 6.0);
    }

    public double recall() {
        double recallSum = 0.0;

        for (int j = 0; j < 6; j++) {
            double columnSum = 0.0;
            for (int i = 0; i < 6; i++) {
                columnSum += confusionMatrix[i][j];
            }
            if (columnSum != 0) {
                recallSum += (confusionMatrix[j][j] / columnSum);
            }
        }
        return (recallSum / 6.0);
    }

    public double accuracy() {
        double countPositive = 0.0;
        double countAll = 0.0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == j) {
                    countPositive += confusionMatrix[i][j];
                }
                countAll += confusionMatrix[i][j];
            }
        }
        return countPositive / countAll;
    }

    public double fOneRate() {
        double precision = precision();
        double recall = recall();
        return (2 * precision * recall) / (precision + recall);
    }

    public String showMatrix() {
        StringBuilder text = new StringBuilder();
        text.append(";;Rzeczywiste;;;;;;\n");
        text.append(";;west-germany;usa;france;uk;canada;japan;\n");
        for (double[] row : confusionMatrix) {
            text.append(";;");
            for (double element : row) {
                text.append((int) element).append(";");
            }
            text.append("\n");
        }
        return text.toString();
    }
}
