package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.Article;

import java.util.List;

import static com.tul.ksr.zad1.Extractor.*;

public class ArticleBuilder {
    public static Article buildArticle(String reuter) {
        List<String> places = extractPlaces(reuter);

        // Exstrakcja textu z body
        String textBody = extractTextFromXmlTags(reuter, "BODY");
        List<String> wordsList = clearAndCastStringToList(textBody);

        return new Article(places, wordsList, textBody.length());
    }
}
