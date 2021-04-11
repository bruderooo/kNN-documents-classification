package com.tul.ksr.zad1;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MenuZadFirst {
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
                "11. liczba słów dłuższych niż średnia długość słowa w tekście w ję-zyku angielskim.\n" +
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
    }
}
