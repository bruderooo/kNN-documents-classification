package com.tul.ksr.zad1.model.metrices;

import com.tul.ksr.zad1.model.Article;
import com.tul.ksr.zad1.model.Features;

public class TaxicabMetric extends Metric {

    @Override
    public double distanceTo(Article article1, Article article2) {
        Features features1 = article1.getFeatures();
        Features features2 = article2.getFeatures();

        return sum(features1.getArticleLength() - features2.getArticleLength(),
                features1.getNumberOfWords() - features2.getNumberOfWords(),
                levDiff(features1.getLongestWord(), features2.getLongestWord()),
                features1.getLengthOfLongestWord() - features2.getLengthOfLongestWord(),
                levDiff(features1.getMostCommonCurrency(), features2.getMostCommonCurrency()),
                levDiff(features1.getSecondCommonCurrency(), features2.getSecondCommonCurrency()),
                levDiff(features1.getMostCommonCountry(), features2.getMostCommonCountry()),
                levDiff(features1.getSecondCommonCountry(), features2.getSecondCommonCountry()),
                features1.getWordShortThanFive() - features2.getWordShortThanFive());
    }

    private double sum(double... args) {
        float tmp = 0;

        for (double element : args) {
            tmp += Math.abs(element);
        }

        return tmp;
    }
}
