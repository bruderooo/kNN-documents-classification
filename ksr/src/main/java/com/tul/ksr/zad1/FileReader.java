package com.tul.ksr.zad1;

import org.apache.commons.collections4.ListUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileReader {


    public static List<Object> castStringsToArticles(List<String> stringsReuters) {
        List<Object> reuters = new ArrayList<>();

        for (String strReuter : stringsReuters) {

        }

        return reuters;
    }

    /** Klasa odpowiedzialna za zwrócenie listy wszystkich plików w danym katalogu
     *
     * @param directory jest to ścieżka do folderu, którego zawartość ma być wypisana
     * @return lista z ścieżkami do plików
     */
    public static List<String> listAllFilesInDirectory(String directory) {
        List<String> list = new ArrayList<>();
        File folder = new File(directory);

        for (final File f : folder.listFiles()) {
            if (f.isFile()) {
                list.add(f.getAbsolutePath());
            }
        }
        return list;
    }

    // Zwraca liste wszystkich routersów jako
    public static List<String> getAllRouters(List<String> allFilesPath) throws FileNotFoundException {
        // Lista Reutersów
        List<String> allReuters = new ArrayList<>();

        for (String fileName : allFilesPath) {
            File file = new File(fileName);

            if (file.isFile()) {
                //  Łączenie dwóch liczb ;3
                List<String> tmp = Arrays.asList(readFile(file).split("(?=(<REUTERS))"));
                tmp.remove(0);

                allReuters = ListUtils.union(allReuters, tmp);
            }
        }

        return allReuters;
    }

    private static String readFile(File file) throws FileNotFoundException {
        StringBuilder data = new StringBuilder();
        Scanner myReader = new Scanner(file);

        while (myReader.hasNextLine()) {
            data.append(myReader.nextLine());
        }

        myReader.close();
        return data.toString();
    }
}
