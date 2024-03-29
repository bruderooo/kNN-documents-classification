package com.tul.ksr.zad1.model.metrices;

import com.tul.ksr.zad1.model.Article;

public abstract class Metric {
    public abstract double distanceTo(Article article1, Article article2);

    public static int levDiff(String s1, String s2) {
        return dist(s1.toCharArray(), s2.toCharArray());
    }

    private static int dist(char[] s1, char[] s2) {
        int[][] d = new int[s1.length + 1][s2.length + 1];

        for (int i = 0; i < s1.length + 1; i++) {
            d[i][0] = i;
        }

        for (int j = 0; j < s2.length + 1; j++) {
            d[0][j] = j;
        }

        for (int i = 1; i < s1.length + 1; i++) {
            for (int j = 1; j < s2.length + 1; j++) {
                int d1 = d[i - 1][j] + 1;
                int d2 = d[i][j - 1] + 1;
                int d3 = d[i - 1][j - 1];
                if (s1[i - 1] != s2[j - 1]) {
                    d3 += 1;
                }
                d[i][j] = Math.min(Math.min(d1, d2), d3);
            }
        }
        return d[s1.length][s2.length];
    }
}

