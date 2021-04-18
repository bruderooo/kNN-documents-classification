package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.ClassifiedArticle;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PerformanceRates {
    int[][] confusionMatrix = new int[6][6];
    Map<String,Integer> matrixMap = new HashMap<>();
    PerformanceRates(List<ClassifiedArticle> articleArray) {

        matrixMap.put("west-germany", 0);
        matrixMap.put("usa", 1);
        matrixMap.put("france",2);
        matrixMap.put("uk", 3);
        matrixMap.put("canada",4);
        matrixMap.put("japan",5);
        for (ClassifiedArticle classifiedArticle : articleArray) {
            confusionMatrix
                    [matrixMap.getOrDefault(classifiedArticle.getPredictedPlace(), 0)]
                    [matrixMap.getOrDefault(classifiedArticle.getArticle().getPlaces().get(0), 0)]
                     += 1;
        }
    }

    public  double precision(){
        double precisionSum = 0.0;
        for (int i = 0; i < 6; i++)
        {
            int rowSum = 0;
            for(int j = 0; j < 6; j++) {
                rowSum += confusionMatrix[i][j];
            }
            precisionSum += (confusionMatrix[i][0] / (double)rowSum);
        }
        return (precisionSum / 6.0);
    }
    public double recall(){
        double recallSum = 0.0;

        for (int j = 0; j < 6; j++)
        {
            int columnSum = 0;
            for(int i = 0; i < 6; i++) {
                columnSum += confusionMatrix[i][j];
            }
            recallSum += (confusionMatrix[0][j] / (double)columnSum);
        }
        return (recallSum / 6.0);
    }
    public  double Accuracy(){
        int countPositive = 0;
        int countAll = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++) {
                if(i == j) {countPositive += confusionMatrix[i][j];}
                countAll += confusionMatrix[i][j];
            }
        }
        return countPositive /(double) countAll;
    }
    public  double fOneRate(){
        double precision = precision();
        double recall = recall();
        return ((2*precision*recall)/(precision + recall));
    }
}

