package com.tul.ksr;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.tul.ksr.zad1.MenuZadFirst.run;

public class App {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        List<Integer> features = IntStream.range(1, 11).boxed().collect(Collectors.toList());
        run(7, 60, 40, features, 2);
    }
}
