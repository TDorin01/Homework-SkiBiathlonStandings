package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileAthlete {


    List<String> allLines;
    List<Athlete> athletes = new ArrayList<>();
    Set<Athlete> sortedAthletes = new TreeSet<>(Comparator.comparingInt(Athlete::getSkiTimeResult));

    public void readFile() {
        try {
            Path path = Paths.get("file.csv");
            allLines = Files.readAllLines(path);
            for (String a : allLines) {
                String[] line = a.split(",");

                athletes.add(new Athlete(Integer.valueOf(line[0]), line[1], line[2], calculate(line[3]), line[4], line[5], line[6]));

            }
            for (Athlete athlete : athletes) {
                sortedAthletes.add(new Athlete(athlete.getAthleteName(), athlete.getSkiTimeResult()));

            }
            int index = 0;
            for (Athlete athlete : sortedAthletes) {
                if (index == 0) {
                    System.out.println("Winner : " + athlete.getAthleteName() + " " + athlete.getSkiTimeResult());
                } else if (index == 1) {
                    System.out.println("Runner-up : " + athlete.getAthleteName() + " " + athlete.getSkiTimeResult());

                } else if (index == 2) {
                    System.out.println("Third Place : " + athlete.getAthleteName() + " " + athlete.getSkiTimeResult());

                } else {
                    System.out.println("Next " + (index + 1) + " is " + " " + athlete.getAthleteName() + " " + athlete.getSkiTimeResult());
                }
                index++;

            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    int calculate(String s) {
        int finalTime = 0;
        int index = 0;
        String result = "";
        while (s.charAt(index) != ':') {
            result = result + s.charAt(index);
            index++;
        }
        finalTime = finalTime + Integer.valueOf(result) * 60;
        result = "";
        index++;

        while (index != s.length()) {
            result = result + s.charAt(index);
            index++;
        }
        finalTime = finalTime + Integer.valueOf(result);
        return finalTime;

    }


}
