package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.Article;
import com.tul.ksr.zad1.model.ClassifiedArticle;
import com.tul.ksr.zad1.model.KnnResult;
import com.tul.ksr.zad1.model.metrices.Metric;

import java.util.*;
import java.util.stream.Collectors;

public class Classifier {
    List<Article> articles;
    double trainingRatio;
    double testRatio;
    Metric metric;
    int k;

    public Classifier(List<Article> articles, double trainingRatio, double testRatio, Metric metric, int k) {
        this.articles = articles;
        this.trainingRatio = trainingRatio;
        this.testRatio = testRatio;
        this.metric = metric;
        this.k = k;
    }

    public List<ClassifiedArticle> Classify() {

        int trainingSize = (int) Math.ceil((articles.size() * (trainingRatio / (trainingRatio + testRatio))));
        List<ClassifiedArticle> classifiedArticles = new ArrayList<>();

        for (int testIndex = trainingSize; testIndex < articles.size(); testIndex++) {
            List<KnnResult> allResults = new ArrayList<>();
            for (int trainingIndex = 0; trainingIndex < trainingSize; trainingIndex++) {
                allResults.add(
                        new KnnResult(
                                metric.distanceTo(articles.get(trainingIndex), articles.get(testIndex)),
                                articles.get(trainingIndex).getPlaces().get(0)));
            }

            List<KnnResult> kResults = new ArrayList<>();
            for (KnnResult aKnnResult : allResults) {
                if (kResults.size() < k) {
                    kResults.add(aKnnResult);
                } else {
                    kResults = kResults.stream().sorted(Comparator.comparingDouble(KnnResult::getMetricDistance))
                            .collect(Collectors.toList());
                    if(kResults.get(k -1).getMetricDistance() > aKnnResult.getMetricDistance()) {
                        kResults.add(k - 1, aKnnResult);
                    }
                }
            }

            Map.Entry<String, Integer> knnMap = predictedPlace(kResults);
            classifiedArticles.add(new ClassifiedArticle(articles.get(testIndex), knnMap.getKey()));

        }
        return classifiedArticles;
        //obliczanie miar jakości
    }

    public Map.Entry<String, Integer> predictedPlace(List<KnnResult> knnResults) {
        Map<String, Integer> map = new HashMap<>();
        for (KnnResult knnResult : knnResults) {

            if (!map.containsKey(knnResult.getTruePlace())) {
                map.put(knnResult.getTruePlace(), 1);
            } else {
                int count = map.get(knnResult.getTruePlace());
                map.put(knnResult.getTruePlace(), count + 1);
            }
        }
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }
}
