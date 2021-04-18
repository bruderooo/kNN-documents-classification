package com.tul.ksr.zad1;


import com.tul.ksr.zad1.model.Article;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KsrZad1 {
    public static void run() throws FileNotFoundException, ParseException {
        // Listowanie wszystkich plików
        List<String> allPaths = FileReader.listAllFilesInDirectory("ksr\\src\\main\\java\\com\\tul\\ksr\\zad1\\data\\articles");

        // Wczytanie plików i zwrócenie ich jako lista podzielana na Reutersy
        List<String> reuters = FileReader.getAllRouters(allPaths);

        // Zamiana listy Reutersów na Article
        List<Article> articles = FileReader.castStringsToArticles(reuters);

        // Teraz potrzeba ograniczyć ilość Article do tych co mają dokładnie jedno Places
        // a następnie dla tych co zostaną wyekstraktowac cechy.
        List<Article> correctOnePlacesArticles = new ArrayList<>(articles);
        for (Article article : articles) {
            if (!Extractor.isNumberOfPlacesEqualA(article, 1)) {
                correctOnePlacesArticles.remove(article);
            } else if (!Extractor.isPlaceEqualPlacesFromList(article)) {
                correctOnePlacesArticles.remove(article);
            }
        }

        // Teraz pod article mamy wyłącznie te artykuły które mają dokładnie jeden places
        articles = correctOnePlacesArticles;

        // Ekstrakcja cech
        for (Article a : articles) {
            Extractor.extractAndSetFeatures(a);
        }

        List<Article> lista = articles.stream()
                .filter(article -> article.getFeatures().getMostCommonCountry().equals("canada"))
                .collect(Collectors.toList());

        System.out.println(lista.get(5));
        System.out.println(lista.get(8));
        System.out.println(lista.get(65));
    }
}
