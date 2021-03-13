package com.tul.ksr.zad1;

import lombok.ToString;

@ToString
public class Features {

    int noWords;
    int lengthOfLongestWord;
    String mostPopularCurrency;
    String secondPopularCurrency;
    String mostPopularCountry;
    String secondPopularCountry;
    int noDigits;

    public Features(int noWords, int lengthOfLongestWord, String mostPopularCurrency,
                    String secondPopularCurrency, String mostPopularCountry, String secondPopularCountry,
                    int noDigits) {
        this.noWords = noWords;
        this.lengthOfLongestWord = lengthOfLongestWord;
        this.mostPopularCurrency = mostPopularCurrency;
        this.secondPopularCurrency = secondPopularCurrency;
        this.mostPopularCountry = mostPopularCountry;
        this.secondPopularCountry = secondPopularCountry;
        this.noDigits = noDigits;
    }
}
