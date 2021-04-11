package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.Article;
import com.tul.ksr.zad1.model.metrices.Metric;

import java.util.List;

public class Classifier {
    List<Article> trainingArticles;
    List<Article> testArticles;
    double trainingRatio;
    double testRatio;
    Metric metric;

    public Classifier(List<Article> trainingArticles, List<Article> testArticles, double trainingRatio, double testRatio, Metric metric) {
        this.trainingArticles = trainingArticles;
        this.testArticles = testArticles;
        this.trainingRatio = trainingRatio;
        this.testRatio = testRatio;
        this.metric = metric;
    }

    public void Classify() {
    }
}
