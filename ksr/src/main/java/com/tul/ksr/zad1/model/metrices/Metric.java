package com.tul.ksr.zad1.model.metrices;

import com.tul.ksr.zad1.model.Article;

public abstract class Metric {
    double distanceTo(Article article1, Article article2) {
        return 0;
    }

    double levDiff(String a, String b) {
        if (b.length() == 0) {
            return a.length();
        } else if (a.length() == 0) {
            return b.length();
        } else if (a.charAt(0) == b.charAt(0)) {
            return levDiff(tail(a), tail(b));
        } else {
            return 1 + min(levDiff(tail(a), b), levDiff(a, tail(b)), levDiff(tail(a), tail(b)));
        }
    }

    private double min(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }

    private String tail(String a) {
        return a.substring(1);
    }
}

