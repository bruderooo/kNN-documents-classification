package com.tul.ksr.zad1;

import com.tul.ksr.zad1.model.KnnResult;

import java.util.*;
import java.util.Map.Entry;

public class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        // Sortuje malejąco
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

    public static Map.Entry<String, Integer> predictedPlace(List<KnnResult> knnResults) {
        Map<String, Integer> map = new HashMap<>();
        for (KnnResult knnResult : knnResults) {

            if (!map.containsKey(knnResult.getTruePlace())) {
                map.put(knnResult.getTruePlace(), 1);
            } else {
                int count = map.get(knnResult.getTruePlace());
                map.put(knnResult.getTruePlace(), count + 1);
            }
        }
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }

    public static String getRandomMapKeyValueElement(Map<String, Integer> map) {
        Set<String> keySet = map.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIdx = new Random().nextInt(size);

        String randomKey = keyList.get(randIdx);
        Integer randomValue = map.get(randomKey);

        return randomKey;
    }
}