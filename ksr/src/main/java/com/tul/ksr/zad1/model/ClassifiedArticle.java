package com.tul.ksr.zad1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClassifiedArticle {
    private Article article;
    private String predictedPlace;
}
