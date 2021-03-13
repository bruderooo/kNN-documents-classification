package com.tul.ksr.zad1;

public class Features {

    int noWords;
    String topic;
    int keyWordOccurrences;
    int postYear;
    int noAuthors;

    public Features(int noWords, String topic, int keyWordOccurrences, int postYear, int noAuthors) {
        this.noWords = noWords;
        this.topic = topic;
        this.keyWordOccurrences = keyWordOccurrences;
        this.postYear = postYear;
        this.noAuthors = noAuthors;
    }

    public float distanceTo(Features features) {
        return 1;
    }
}
