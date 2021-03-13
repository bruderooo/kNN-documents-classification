package com.tul.ksr.zad1;


import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
        List<Article> onePlacesArticles = new ArrayList<>(articles);
        for (Article article : articles) {
            if (!article.isNumberOfPlacesEqualA(1)) {
                onePlacesArticles.remove(article);
            }
        }

        articles = onePlacesArticles;

        // Ekstrakcja cech
        for (Article a : articles) {
            a.extractAndSetFeatures();
        }

        // Tak z ciekawości wyświetlam sobie 10 artykuł żeby zobaczyć co tam się udało
        // wyekstraktować
        System.out.println(articles.get(12));
    }
}
