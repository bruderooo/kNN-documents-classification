package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.Article;
import com.tul.ksr.zad1.model.ClassifiedArticle;
import com.tul.ksr.zad1.model.metrices.ChebyshevMetric;
import com.tul.ksr.zad1.model.metrices.EuclideanMetric;
import com.tul.ksr.zad1.model.metrices.Metric;
import com.tul.ksr.zad1.model.metrices.TaxicabMetric;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MenuZadFirst {
    public static void run(int k, int trainProportion, int testProportion, List<Integer> features, int metric) throws FileNotFoundException, ParseException {
        Metric metrica = getMetric(metric);

        // Listowanie wszystkich plików
        List<String> allPaths = FileReader.listAllFilesInDirectory("ksr\\src\\main\\java\\com\\tul\\ksr\\zad1\\data\\articles");

        // Wczytanie plików i zwrócenie ich jako lista podzielana na Reutersy
        List<String> reuters = FileReader.getAllRouters(allPaths);

        // Zamiana listy Reutersów na Article
        List<Article> articles = FileReader.castStringsToArticles(reuters);

        // Teraz potrzeba ograniczyć ilość Article do tych co mają dokładnie jedno Places
        // a następnie dla tych co zostaną wyekstrahowac cechy.
        // Teraz pod article mamy wyłącznie te artykuły które mają dokładnie jeden places
        articles = articles.stream()
                .filter(article -> Extractor.isNumberOfPlacesEqualA(article, 1))
                .filter(Extractor::isPlaceEqualPlacesFromList)
                .collect(Collectors.toList());

        // Ekstrakcja cech
        articles.forEach((article) -> Extractor.extractAndSetFeatures(article, features));

        Classifier classifier = new Classifier(articles, trainProportion, testProportion, metrica, k);
        List<ClassifiedArticle> out = classifier.classify();

        PerformanceRates performanceRates = new PerformanceRates(out);
        double p, c, d, f1;
        p = performanceRates.precision();
        c = performanceRates.recall();
        d = performanceRates.accuracy();
        f1 = performanceRates.fOneRate();

        System.out.println("Precision: " + p);
        System.out.println("Recall: " + c);
        System.out.println("Accuracy: " + d);
        System.out.println("F1: " + f1);
    }

    public static void run() throws FileNotFoundException, ParseException {
        Scanner input = new Scanner(System.in);

        // Wprowadzenie k dla k-NN
        System.out.print("\n" +
                "Podaj ilość sąsiadów dla algorytmu k-NN:\n" +
                "k = ");
        int k = input.nextInt();

        // Wprowadzenie proporcji zbioru treningowego i testowego
        System.out.print("\n" +
                "Podaj proporcje podziału zbioru uczącego i testowego.\n" +
                "Np. dla proporcji 60 do 40, podaj 60/40.\n" +
                "Twoja proporcja: ");
        String[] proportions = input.next().strip().split("/");
        int trainProportion = Integer.parseInt(proportions[0]);
        int testProportion = Integer.parseInt(proportions[1]);

        // Wprowadzenie zbioru cech dla których dokonuje się klasyfikacji
        System.out.print("\n" +
                "Wybierz cechy dla których będzie dokonywana klasyfikacja.\n" +
                "Możliwe cechy do wyboru to:\n" +
                "1. długość artykułu\n" +
                "2. liczba słów\n" +
                "3. najdłuższe słowo w artykule\n" +
                "4. długość najdłuższego słowa w artykule\n" +
                "5. najczęściej  występująca  waluta\n" +
                "6. druga najczęściej występująca waluta\n" +
                "7. najczęściej występujący kraj\n" +
                "8. drugi najczęściej występujący kraj\n" +
                "9. średnia  długość  słowa  w  artykule\n" +
                "10. liczba słów krótszych niż średnia długość słowa w tekście w ję-zyku  angielskim\n" +
                "Można podawać pojedyńcze cechy, oraz odzielać je przecinkami (np. 1,3,4,6-10)\n" +
                "Wybór: ");
        String[] features = input.next().split(",");

        List<Integer> range = new ArrayList<>();
        for (String feature : features) {
            if (feature.contains("-")) {
                String[] tmp = feature.split("-");
                range.addAll(IntStream.range(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]) + 1).boxed().collect(Collectors.toList()));
            } else {
                range.add(Integer.valueOf(feature));
            }
        }
        range = range.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Cechy które wybrałeś: " + range);

        // Wybór metryki
        System.out.print("\nWybierz metrykę:\n" +
                "1. Metryka euklidesa.\n" +
                "2. Metryka uliczna.\n" +
                "3. Metryka Czebyszewa.\n" +
                "Wybór: ");
        int metric = input.nextInt();

        run(k, trainProportion, testProportion, range, metric);
    }

    public static Metric getMetric(int index) {
        switch (index) {
            case 1:
                return new EuclideanMetric();
            case 2:
                return new TaxicabMetric();
            case 3:
                return new ChebyshevMetric();
            default:
                throw new IllegalStateException("Unexpected value: " + index);
        }
    }
}
