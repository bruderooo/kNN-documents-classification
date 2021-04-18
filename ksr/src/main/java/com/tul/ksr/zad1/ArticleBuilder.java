package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.Article;

import java.util.List;

public class ArticleBuilder {

    public static Article buildArticle(String reuter) {
        List<String> places = Extractor.extractPlaces(reuter);

        // Exstrakcja textu z body
        String textBody = Extractor.extractTextFromXmlTags(reuter, "BODY");
        List<String> wordsList = Extractor.clearAndCastStringToList(textBody);

        return new Article(places, wordsList, textBody.length());
    }
}
