package com.tul.ksr.zad1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KnnResult {
    private double metricDistance;
    private String truePlace;
}
