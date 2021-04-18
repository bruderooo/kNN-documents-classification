package com.tul.ksr.zad1;


import com.tul.ksr.zad1.model.Article;
import com.tul.ksr.zad1.model.ClassifiedArticle;
import com.tul.ksr.zad1.model.metrices.EuclideanMetric;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class KsrZad1 {

    public static void run() throws FileNotFoundException, ParseException {
        // Listowanie wszystkich plików
        List<String> allPaths = FileReader.listAllFilesInDirectory("ksr\\src\\main\\java\\com\\tul\\ksr\\zad1\\data\\articles");

        // Wczytanie plików i zwrócenie ich jako lista podzielana na Reutersy
        List<String> reuters = FileReader.getAllRouters(allPaths);

        print("Wszystkie pliki zostały wczytane oraz podzielone względem tagu REUTERS");

        // Zamiana listy Reutersów na Article
        List<Article> articles = FileReader.castStringsToArticles(reuters);

        print("Pojedyncze artykuły zostały zamienione na obiekty typu Article");

        // Teraz potrzeba ograniczyć ilość Article do tych co mają dokładnie jedno Places
        // a następnie dla tych co zostaną wyekstrahowac cechy.
        // Teraz pod article mamy wyłącznie te artykuły które mają dokładnie jeden places
        articles = articles.stream()
                .filter(article -> Extractor.isNumberOfPlacesEqualA(article, 1))
                .filter(Extractor::isPlaceEqualPlacesFromList)
                .collect(Collectors.toList());

        // Ekstrakcja cech
        articles.forEach(Extractor::extractAndSetFeatures);
        print("Cechy dla każdego artykuły zostały wyekstrahowane");

        articles = articles.stream()
                .limit(1000)
                .collect(Collectors.toList());

        Classifier classifier = new Classifier(articles, 60, 40, new EuclideanMetric(), 6);
        List<ClassifiedArticle> out = classifier.classify();

        System.out.println(out.stream()
                .map(e -> e.getArticle().getPlaces().get(0) + " " + e.getPredictedPlace())
                .collect(Collectors.toList()));
    }

    private static void print(String msg) {
        System.out.println(msg);
    }
}
