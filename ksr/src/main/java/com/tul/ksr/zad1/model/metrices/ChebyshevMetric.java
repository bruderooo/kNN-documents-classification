package com.tul.ksr.zad1.model.metrices;

import com.tul.ksr.zad1.model.Article;
import com.tul.ksr.zad1.model.Features;

public class ChebyshevMetric extends Metric {

    @Override
    public double distanceTo(Article article1, Article article2) {
        Features features1 = article1.getFeatures();
        Features features2 = article2.getFeatures();

        return max(features1.getArticleLength() - features2.getArticleLength(),
                features1.getNumberOfWords() - features2.getNumberOfWords(),
                levDiff(features1.getLongestWord(), features2.getLongestWord()),
                features1.getLengthOfLongestWord() - features2.getLengthOfLongestWord(),
                levDiff(features1.getMostCommonCurrency(), features2.getMostCommonCurrency()),
                levDiff(features1.getSecondCommonCurrency(), features2.getSecondCommonCurrency()),
                levDiff(features1.getMostCommonCountry(), features2.getMostCommonCountry()),
                levDiff(features1.getSecondCommonCountry(), features2.getSecondCommonCountry()),
                features1.getWordShortThanFive() - features2.getWordShortThanFive());
    }

    private double max(double... args) {
        double maxValues = Double.MIN_VALUE;

        for (double element : args) {
            if (element >= maxValues) {
                maxValues = element;
            }
        }
        return maxValues;
    }
}
