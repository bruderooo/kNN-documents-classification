package com.tul.ksr.zad1.model;

import lombok.Data;

@Data
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

    public Features(int articleLength, int numberOfWords, String longestWord, int lengthOfLongestWord,
                    String mostCommonCurrency, String secondCommonCurrency, String mostCommonCountry,
                    String secondCommonCountry, double avgWordLength, int wordShortThanFive) {
        this.articleLength = articleLength / 100;
        this.numberOfWords = numberOfWords / 100;
        this.longestWord = longestWord;
        this.lengthOfLongestWord = lengthOfLongestWord;
        this.mostCommonCurrency = mostCommonCurrency;
        this.secondCommonCurrency = secondCommonCurrency;
        this.mostCommonCountry = mostCommonCountry;
        this.secondCommonCountry = secondCommonCountry;
        this.avgWordLength = avgWordLength;
        this.wordShortThanFive = wordShortThanFive;
    }
}
