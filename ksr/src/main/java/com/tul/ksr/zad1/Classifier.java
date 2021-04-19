package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.Article;
import com.tul.ksr.zad1.model.ClassifiedArticle;
import com.tul.ksr.zad1.model.KnnResult;
import com.tul.ksr.zad1.model.metrices.Metric;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.tul.ksr.zad1.MapUtil.predictedPlace;

public class Classifier {
    private final List<Article> articles;
    private final double trainingRatio;
    private final double testRatio;
    private final Metric metric;
    private final int k;

    public Classifier(List<Article> articles, double trainingRatio, double testRatio, Metric metric, int k) {
        this.articles = articles;
        this.trainingRatio = trainingRatio;
        this.testRatio = testRatio;
        this.metric = metric;
        this.k = k;
    }

    public List<ClassifiedArticle> classify() {
        int trainingSize = (int) Math.ceil((articles.size() * (trainingRatio / (trainingRatio + testRatio))));
        List<ClassifiedArticle> classifiedArticles = new ArrayList<>();

        for (int testIndex = trainingSize; testIndex < articles.size(); testIndex++) {
            List<KnnResult> allResults = new ArrayList<>();
            for (int trainingIndex = 0; trainingIndex < trainingSize; trainingIndex++) {

                double distance = metric.distanceTo(articles.get(trainingIndex), articles.get(testIndex));
                String places = articles.get(trainingIndex).getPlaces().get(0);

                if (allResults.size() < k) {
                    allResults.add(new KnnResult(distance, places));
                } else {
                    KnnResult max = allResults.stream()
                            .max(Comparator.comparing(KnnResult::getMetricDistance))
                            .get();

                    if (max.getMetricDistance() > distance) {
                        allResults.set(allResults.indexOf(max), new KnnResult(distance, places));
                    }
                }
            }

            Map.Entry<String, Integer> knnMap = predictedPlace(allResults);
            classifiedArticles.add(new ClassifiedArticle(articles.get(testIndex), knnMap.getKey()));
        }

        return classifiedArticles;
    }


}