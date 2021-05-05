package com.tul.ksr.zad1;

import java.util.*;
import java.util.Map.Entry;

public class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue(Collections.reverseOrder()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    public static Map<String, Integer> mapKeysFromList(List<String> list) {
        Map<String, Integer> map = new HashMap<>();

        for (String i : list) {
            map.put(i, 0);
        }

        return map;
    }
}