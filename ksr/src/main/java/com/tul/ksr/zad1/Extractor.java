package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.Article;
import com.tul.ksr.zad1.model.Features;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.tul.ksr.zad1.MapUtil.mapKeysFromList;
import static com.tul.ksr.zad1.MapUtil.sortByValue;
import static com.tul.ksr.zad1.StaticLists.COUNTRIES_LIST;
import static com.tul.ksr.zad1.StaticLists.CURRENCY_LIST;

public class Extractor {
    static List<String> extractPlaces(String reuter) {
        List<String> placesWithoutD = Arrays.asList(extractTextFromXmlTags(reuter, "PLACES").split("(?=(<D))"));

        for (int i = 0; i < placesWithoutD.size(); i++) {
            placesWithoutD.set(i, extractTextFromXmlTags(placesWithoutD.get(i), "D"));
        }

        return placesWithoutD;
    }

    static String extractTextFromXmlTags(String text, String tag) {
        int tagBegin, tagEnd;

        tagBegin = text.indexOf("<" + tag + ">") + ("<" + tag + ">").length();
        tagEnd = text.indexOf("</" + tag + ">");

        if (tagBegin == -1 || tagEnd == -1) {
            return "";
        } else {
            return text.substring(tagBegin, tagEnd);
        }
    }

    static List<String> clearAndCastStringToList(String dirtyString) {
        return Arrays.asList(clearBodyText(dirtyString).split("\\s+"));
    }

    private static String clearBodyText(String dirtyString) {
        return dirtyString
                .replace("\n", " ").replace("\t", " ")
                .replace("&#2;", " ").replace("&#3;", " ")
                .replace("&#5;", " ").replace("&#27;", " ")
                .replace("&lt;", "").replace("&gt;", "")
                .replace("<", " ").replace(">", "")
                .replace(",", "").replace(".", " ")
                .replace(";", "").replace(":", "")
                .replace("'", " ").replace("\"", "")
                .replace("(", "").replace(")", "")
                .replace("[", "").replace("]", "")
                .replace("+", "").replace("=", "")
                .replace("*", "").replace(" - ", " ")
                .replace(" -- ", " ").replace("Reuter", "")
                .replace("/", " ").replace("?", "")
                .replace("!", "").toLowerCase();
    }

    public static void extractAndSetFeatures(Article article) {
        // Słownik z kluczami jako kraje, i wartościami równymi zero
        // które to są placeholderami dla konkretnych występowań danych kluczów
        Map<String, Integer> currencyMap = mapKeysFromList(CURRENCY_LIST);
        Map<String, Integer> countryMap = mapKeysFromList(COUNTRIES_LIST);

        // Cecha 1: długość artykułu sprzed extractu
        int articleLen = article.getInitArticleLen();

        // Cecha 2: ilość słów
        int noWords = article.getWordList().size();

        // Cecha 3: najdłuższe słowo
        // Cecha 4: długość tego słowa
        String longestWord = "";

        int sumLen = 0;

        // Cecha 10: ilość słów krótszych niż średnia długość słów w języku angielskim
        int numberOfShortWords = 0;

        for (String word : article.getWordList()) {
            // Poszukiwanie najdłuższego słowa, czyli Cecha 3 tu jest liczona
            // Oraz na podstawie tej cechy, będzie liczona Cecha 4
            int wordLen = word.length();
            if (wordLen > longestWord.length()) {
                longestWord = word;
            }

            if (wordLen < 5) {
                numberOfShortWords++;
            }

            // Cecha 5: najczęściej występująca waluta
            // Cecha 6: druga najczęściej występująca waluta
            // Sprawdza czy dane słowo jest kluczem, a drugi wariant to zabezpieczenie przeciw
            // sytuacji w której słowo byłoby w liczbie mnogiej
            if (wordLen > 1) {
                String wordWithoutLastChar = word.substring(0, wordLen - 1);
                if (currencyMap.containsKey(word)) {
                    currencyMap.replace(word, currencyMap.get(word) + 1);
                } else if (currencyMap.containsKey(wordWithoutLastChar)) {
                    currencyMap.replace(wordWithoutLastChar, currencyMap.get(wordWithoutLastChar) + 1);
                }
            }

            // Cecha 7: najczęściej występujący kraj
            // Cecha 8: drugi najczęściej występujący kraj
            // W tym miejscu zliczam wystąpienie wszystkich krajów
            if (countryMap.containsKey(word)) {
                countryMap.replace(word, countryMap.get(word) + 1);
            }

            // Cecha 9: średnia długość słów, tu sume długości liczymy
            sumLen += wordLen;
        }

        // Tu sortuje mape po wartościach, a następnie bierze pierwsze dwa klucze
        // czyli dwa najczęściej pojawiające się kraje
        Map<String, Integer> map;
        Iterator<Map.Entry<String, Integer>> entry;
        Map.Entry<String, Integer> next;
        int checkInteger;

        // sprawdzanie dla waluty
        map = sortByValue(currencyMap);
        entry = map.entrySet().iterator();
        next = entry.next();
        String mostCommonCurrency = next.getKey();
        checkInteger = next.getValue();
        String secondCommonCurrency = entry.next().getKey();
        if (checkInteger == 0) {
            mostCommonCurrency = "";
            secondCommonCurrency = "";
        }

        // sprawdzanie dla kraju
        map = sortByValue(countryMap);
        entry = map.entrySet().iterator();
        next = entry.next();
        String mostCommonCountry = next.getKey();
        checkInteger = next.getValue();
        String secondCommonCountry = entry.next().getKey();

        if (checkInteger == 0) {
            mostCommonCountry = "";
            secondCommonCountry = "";
        }


        Features features = new Features(articleLen,
                noWords, longestWord, longestWord.length(), mostCommonCurrency,
                secondCommonCurrency, mostCommonCountry, secondCommonCountry,
                sumLen / noWords, numberOfShortWords);

        article.setFeatures(features);
    }

    public static boolean isNumberOfPlacesEqualA(Article article, int A) {
        return article.getPlaces().size() == A;
    }
}
