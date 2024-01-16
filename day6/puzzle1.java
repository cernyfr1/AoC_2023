package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class puzzle1 {
    public static void main(String[] args) throws Exception{

        int result = 1;
        ArrayList<int[]> races = parseInput();
        for (int[] race : races) {
            result *= analyzeRace(race);
        }
        System.out.println(result);

    }
    private static ArrayList<int[]> parseInput() throws Exception{
        File input = new File("day6/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        ArrayList<Integer> times = new ArrayList<>();
        ArrayList<Integer> distances = new ArrayList<>();
        br.lines().forEach(line -> {
            if (line.contains("Time:")) {
                line = line.split(":")[1];
                Scanner scanner = new Scanner(line);
                while (scanner.hasNextInt()) {
                    times.add(scanner.nextInt());
                }
            }
            if (line.contains("Distance:")) {
                line = line.split(":")[1];
                Scanner scanner = new Scanner(line);
                while (scanner.hasNextInt()) {
                    distances.add(scanner.nextInt());
                }
            }
        });
        ArrayList<int[]> races = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            races.add(new int[]{times.get(i), distances.get(i)});
        }
        return races;
    }
    private static int analyzeRace(int[] race) {

        int result = 0;
        final int duration = race[0];
        final int distanceToBeat = race[1];

        for (int i = 0; i <= duration; i++) {
            if (i * (duration - i) > distanceToBeat) result++;
        }
        return result;
    }
}
