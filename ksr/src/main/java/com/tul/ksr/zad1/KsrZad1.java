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
   //            .filter(article -> !article.getPlaces().get(0).equals("usa"))
 //               .limit(40_000)
                .collect(Collectors.toList());

        Classifier classifier = new Classifier(articles, 60, 40, new EuclideanMetric(), 7);
        List<ClassifiedArticle> out = classifier.classify();

        PerformanceRates performanceRates = new PerformanceRates(out);
        System.out.println(performanceRates.precision());
        System.out.println(performanceRates.recall());
        System.out.println(performanceRates.accuracy());
        System.out.println(performanceRates.fOneRate());
    }

    private static void print(String msg) {
        System.out.println(msg);
    }
}
