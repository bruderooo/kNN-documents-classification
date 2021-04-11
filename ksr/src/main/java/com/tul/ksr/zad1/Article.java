package com.tul.ksr.zad1;

import lombok.ToString;

import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.tul.ksr.zad1.MapUtil.sortByValue;
import static com.tul.ksr.zad1.StaticLists.COUNTRIES_LIST;
import static com.tul.ksr.zad1.StaticLists.CURRENCY_LIST;

@ToString
public class Article {

    private final List<String> places;
    private final List<String> textBody;
    private Features features;

    public Article(String reuter) throws ParseException {
        this.places = extractPlaces(reuter);

        // Exstrakcja textu z body
        this.textBody = clearAndCastStringToList(extractTextFromXmlTags(reuter, "BODY"));
    }

    private List<String> extractPlaces(String reuter) {
        List<String> placesWithoutD = Arrays.asList(extractTextFromXmlTags(reuter, "PLACES").split("(?=(<D))"));

        for (int i = 0; i < placesWithoutD.size(); i++) {
            placesWithoutD.set(i, extractTextFromXmlTags(placesWithoutD.get(i), "D"));
        }

        return placesWithoutD;
    }

    private String extractTextFromXmlTags(String text, String tag) {
        int tagBegin, tagEnd;

        tagBegin = text.indexOf("<" + tag + ">") + ("<" + tag + ">").length();
        tagEnd = text.indexOf("</" + tag + ">");

        if (tagBegin == -1 || tagEnd == -1) {
            return "";
        } else {
            return text.substring(tagBegin, tagEnd);
        }
    }

    private List<String> clearAndCastStringToList(String dirtyString) {
        return Arrays.asList(clearBodyText(dirtyString).split("\\s+"));
    }

    private String clearBodyText(String dirtyString) {
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
                .replace("!", "");
    }

    public void extractAndSetFeatures() {
        // Cecha 1: ilość słów; działa
        int noWords = textBody.size();

        int noDigits = 0;
        int longestWordLen = 0;

        Map<String, Integer> countryOccurency = new LinkedHashMap<>();
        Map<String, Integer> currencyOccurency = new LinkedHashMap<>();

        for (String word : textBody) {
            // Cecha 2: najdłuższe słowo; działa
            if (word.length() > longestWordLen) {
                longestWordLen = word.length();
            }

            //<editor-fold desc="To narazie nie działa">
            // Cecha 3: najpopularniejsza waluta
            // Cecha 4: druga najpopularniejsza waluta
            // W tym miejscu jest sprawdzane czy aktualne słowo znajduje się w liście walut
            currencyOccurency = updateMapsWithOccurrences(currencyOccurency, word, CURRENCY_LIST);

            // Cecha 5: najpopularniejszy kraj
            // Cecha 6: drugi najpopularniejszy kraj
            // W tym miejscu jest sprawdzane czy aktualny kraj znajduje się w liście krajów
            countryOccurency = updateMapsWithOccurrences(countryOccurency, word, COUNTRIES_LIST);
            //</editor-fold>


            // Cecha 7: ilość cyfr; działa
            if (word.matches(".*\\d.*")) {
                noDigits++;
            }
        }

        int tmpLength;

        //<editor-fold desc="To też nie działa jeszcze ;/ xd">
        // Wzięcie najpopularniejszych walut
        currencyOccurency = sortByValue(currencyOccurency);
        Object[] currencyOccurencyArray = currencyOccurency.keySet().toArray();
        tmpLength = currencyOccurencyArray.length;
        String mostPopularCurrency = "";
        String secondPopularCurrency = "";
        if (tmpLength > 2) {
            mostPopularCurrency = (String) currencyOccurencyArray[tmpLength - 1];
            secondPopularCurrency = (String) currencyOccurencyArray[tmpLength - 2];
        }

        // Wzięcie najpopularniejszych krajow
        countryOccurency = sortByValue(countryOccurency);
        Object[] countryOccurencyArray = countryOccurency.keySet().toArray();
        tmpLength = countryOccurencyArray.length;
        String mostPopularCountry = "";
        String secondPopularCountry = "";
        if (tmpLength > 2) {
            mostPopularCountry = (String) countryOccurencyArray[tmpLength - 1];
            secondPopularCountry = (String) countryOccurencyArray[tmpLength - 2];
        }
        //</editor-fold>

        features = new Features(
                noWords, longestWordLen, mostPopularCurrency,
                secondPopularCurrency, mostPopularCountry, secondPopularCountry,
                noDigits);
    }

    private Map<String, Integer> updateMapsWithOccurrences(Map<String, Integer> wordOccurrence, String word, List<String> staticWordsList) {
        Map<String, Integer> newMap = new LinkedHashMap<>(wordOccurrence);

        for (String staticWord : staticWordsList) {
            if (word.equals(staticWord)) {
                if (newMap.containsKey(staticWord)) {
                    int numbers = newMap.get(staticWord);
                    newMap.replace(staticWord, ++numbers);
                } else {
                    newMap.put(staticWord, 1);
                }
                break;
            }
        }

        return newMap;
    }

    public boolean isNumberOfPlacesEqualA(int A) {
        return places.size() == A;
    }
}
