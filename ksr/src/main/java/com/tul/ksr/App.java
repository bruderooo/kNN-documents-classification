package com.tul.ksr;

import com.tul.ksr.zad1.KsrZad1;
import com.tul.ksr.zad1.MenuZadFirst;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        MenuZadFirst.run();
        KsrZad1.run();
    }
}
