package com.tul.ksr.zad1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticLists {

    static final List<String> CURRENCY_LIST = Arrays.asList(
            "dlr", "dollar", "franc", "pound", "stg", "sterling", "cts", "ecu", "bpd", "crown", "kwacha", "deutschemark", "mark", "guilder", "yen");

    static final List<String> COUNTRIES_LIST = Arrays.asList(
            "west-germany", "usa", "france", "uk", "canada", "japan");

    static final Map<String, String> COUNTRIES_MAP = new HashMap<>() {{
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
        put("germans", "west-germany");
        put("bonn", "west-germany");

        put("france", "france");
        put("french", "france");
        put("frenchmen", "france");
        put("frenchwomen", "france");
        put("paris", "france");

        put("uk", "uk");
        put("british", "uk");
        put("britons", "uk");
        put("england", "uk");
        put("london", "uk");

        put("canada", "canada");
        put("canadian", "canada");
        put("canadians", "canada");
        put("ottawa", "canada");

        put("japan", "japan");
        put("japanese", "japan");
        put("tokyo", "japan");
    }};
}
