package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class puzzle2 {
    public static void main(String[] args) throws Exception{
        System.out.println(analyzeRace(parseInput()));
    }
    private static long[] parseInput() throws Exception {
        long[] race = new long[2];
        File input = new File("day6/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        br.lines().forEach(line -> {
            if (line.contains("Time:")) {
                line = line.split(":")[1];
                String newLine = line.replaceAll(" +", "");
                race[0] = Long.parseLong(newLine);
            }
            if (line.contains("Distance:")) {
                line = line.split(":")[1];
                String newLine = line.replaceAll(" +", "");
                race[1] = Long.parseLong(newLine);
            }
        });
        return race;
    }
    private static int analyzeRace(long[] race) {

        int result = 0;
        final long duration = race[0];
        final long distanceToBeat = race[1];

        for (long i = 0; i <= duration; i++) {
            if (i * (duration - i) > distanceToBeat) result++;
        }
        return result;
    }
}
