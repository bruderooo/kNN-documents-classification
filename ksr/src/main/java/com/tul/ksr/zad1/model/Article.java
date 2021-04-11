package com.tul.ksr.zad1.model;

import lombok.Data;

import java.util.List;


@Data
public class Article {

    private final List<String> places;
    private final List<String> wordList;
    private int initArticleLen;
    private Features features;

    public Article(List<String> places, List<String> wordList, int initArticleLen) {
        this.places = places;
        this.wordList = wordList;
        this.initArticleLen = initArticleLen;
    }
}
