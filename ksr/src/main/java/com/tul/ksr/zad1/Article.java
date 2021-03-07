package com.tul.ksr.zad1;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Article {

    private Date postDate;
    private String topic;
    private List<String> places;
    private List<String> people;
    private String author;
    private List<String> title;
    private List<String> textBody;

    public Article(String reuter) {
        // Dla postdate trzeba wziąć wyciągnąć date z stringa
        this.postDate = extractTextFromXmlTags("DATE");
        this.topic = extractTextFromXmlTags("TOPICS");

        // Places są dodatkowo wypisane w tagu <D>
        this.places = extractTextFromXmlTags("PLACES");

        // Tak samo jak places wyżej ;/ ale na luzaku do zrobienia
        this.people = extractTextFromXmlTags("PEOPLE");
        this.author = clearString(extractTextFromXmlTags("AUTHOR"));
        this.title = clearAndCastStringToList(extractTextFromXmlTags("TITLE"));
        this.textBody = clearAndCastStringToList(extractTextFromXmlTags("BODY"));
    }

    public String extractTextFromXmlTags(String tag) {
        int tagBegin, tagEnd;

        tagBegin = tag.indexOf("<" + tag + ">");
        tagEnd = tag.indexOf("</" + tag + ">");

        if (tagBegin == -1 || tagEnd == -1) {
            return "";
        } else {
            return tag.substring(tagBegin, tagEnd);
        }
    }

    public List<String> clearAndCastStringToList(String dirtyString) {
        return Arrays.asList(clearBodyText(dirtyString).split("\\s+"));
    }

    public String clearString(String dirtString) {
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

    public String clearBodyText(String dirtyString) {
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
