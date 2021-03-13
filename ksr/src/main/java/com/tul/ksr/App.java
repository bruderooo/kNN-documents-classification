package com.tul.ksr;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int tagBegin, tagEnd;
        String text = "<tag>sadasdsdf</tag>";

        tagBegin = text.indexOf("<tag>") + "<tag>".length();
        tagEnd = text.indexOf("</tag>");

        System.out.println(text.substring(tagBegin, tagEnd));
    }
}
