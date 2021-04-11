package com.tul.ksr.zad1.model;

import lombok.Data;

import java.util.List;


@Data
public class Article {

    private final List<String> places;
    private final List<String> textBody;
    private Features features;

    public Article(List<String> places, List<String> textBody) {
        this.places = places;
        this.textBody = textBody;
    }
}
