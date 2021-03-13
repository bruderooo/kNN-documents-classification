package com.tul.ksr.zad1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Article {

    private List<String> places;
    private List<String> textBody;

//    private featuresVector;

    public Article(String reuter) throws ParseException {
        // Places są dodatkowo wypisane w tagu <D>
        this.places = extractListFromPlacesOrPeople(reuter, "PLACES");

        // Exstrakcja textu z body
        this.textBody = clearAndCastStringToList(extractTextFromXmlTags(reuter, "BODY"));
    }

    private List<String> extractListFromPlacesOrPeople(String reuter, String mainTag) {
        return Arrays.asList(extractTextFromXmlTags(reuter, mainTag).split("(?=(<D))"));
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

    private String clearString(String dirtString) {
        return dirtString
                .replace("\n", " ")
                .replace("\t", " ")
                .replace("&#2;", " ")
                .replace("&#3;", " ")
                .replace("&#5;", " ")
                .replace("&#27;", " ")
                .replace("&lt;", "")
                .replace("&gt;", "")
                .toLowerCase();
    }

    private String clearBodyText(String dirtyString) {
        return clearString(dirtyString)
                .replace("&lt;", " ").replace("&gt;", "")
                .replace("<", " ").replace(">", "")
                .replace("\n", " ").replace("\t", " ")
                .replace(",", "").replace(".", " ")
                .replace(";", "").replace(":", "")
                .replace("'", " ").replace("\"", "")
                .replace("(", "").replace(")", "")
                .replace("[", "").replace("]", "")
                .replace("+", "").replace("=", "")
                .replace("*", "").replace(" - ", " ")
                .replace(" -- ", " ").replace("Reuter", "")
                .replace("/", " ").replace("?", "")
                .replace("!", " ").toLowerCase();
    }
}
