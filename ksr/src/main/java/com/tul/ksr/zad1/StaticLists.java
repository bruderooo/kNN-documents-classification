package com.tul.ksr.zad1;

import java.util.*;

public class StaticLists {
    // Jak to zrobić żęby nie musieć sprawdzać i liczby pojedyńczej i mnogiej?
    // już wiem i jest to nawet zaimplementowane xd
    static final List<String> CURRENCY_LIST = Arrays.asList("dlr", "franc", "pound", "cts", "pct", "ecu", "bpd", "crown", "kwacha", "stg", "mark", "guilder");

    static final List<String> COUNTRIES_LIST = Arrays.asList("west-germany", "usa", "france", "uk", "canada", "japan");

    static final Map<String, String> COUNTRIES_MAP;

    static {
        Map<String, String> map = new HashMap<>() {{
            put("us", "usa");
            put("usa", "usa");
            put("america", "usa");
            put("american", "usa");
            put("americas", "usa");
            put("americans", "usa");
            put("washington", "usa");

            put("west-germany", "west-germany");
            put("germany", "west-germany");
            put("german", "west-germany");
            put("bonn", "west-germany");

            put("france", "france");
            put("frances", "france");
            put("french", "france");
            put("paris", "france");

            put("uk", "uk");
            put("british", "uk");
            put("england", "uk");
            put("englands", "uk");
            put("london", "uk");

            put("canada", "canada");
            put("canadas", "canada");
            put("canadian", "canada");
            put("ottawa", "canada");

            put("japan", "japan");
            put("Japans", "japan");
            put("japanese", "japan");
            put("tokyo", "japan");
        }};
        COUNTRIES_MAP = Collections.unmodifiableMap(map);
    }
}
