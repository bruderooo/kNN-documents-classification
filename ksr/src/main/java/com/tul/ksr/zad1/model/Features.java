package com.tul.ksr.zad1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Features {
    int articleLength;
    int numberOfWords;
    String longestWord;
    int lengthOfLongestWord;
    String mostCommonCurrency;
    String secondCommonCurrency;
    String mostCommonCountry;
    String secondCommonCountry;
    double avgWordLength;
    int wordShortThanFive;

    public Features(Features features) {
        articleLength = features.getArticleLength();
        numberOfWords = features.getNumberOfWords();
        longestWord = features.getLongestWord();
        lengthOfLongestWord = features.getLengthOfLongestWord();
        mostCommonCurrency = features.getMostCommonCurrency();
        secondCommonCurrency = features.getSecondCommonCurrency();
        mostCommonCountry = features.getMostCommonCountry();
        secondCommonCountry = features.getSecondCommonCountry();
        avgWordLength = features.getAvgWordLength();
        wordShortThanFive = features.getWordShortThanFive();
    }

    public void clearFeature(int numberOf) {
        switch (numberOf) {
            case 1:
                articleLength = 0;
                return;
            case 2:
                numberOfWords = 0;
                return;
            case 3:
                longestWord = "";
                return;
            case 4:
                lengthOfLongestWord = 0;
                return;
            case 5:
                mostCommonCurrency = "";
                return;
            case 6:
                secondCommonCurrency = "";
                return;
            case 7:
                mostCommonCountry = "";
                return;
            case 8:
                secondCommonCountry = "";
                return;
            case 9:
                avgWordLength = 0;
                return;
            case 10:
                wordShortThanFive = 0;
        }
    }
}
