package day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class puzzle2 {

    private static ArrayList<ArrayList<ArrayList<Integer>>> lines = new ArrayList<>();
    private static int result = 0;
    public static void main(String[] args) throws Exception{

        File input = new File("day4/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        br.lines().forEach(line -> {
            line = line.split(":")[1];
            String winningNumbers = line.split("\\|")[0];
            String lineNumbers = line.split("\\|")[1];

            Scanner winScanner = new Scanner(winningNumbers);
            ArrayList<Integer> winList = new ArrayList<>();
            while (winScanner.hasNextInt()) {
                winList.add(winScanner.nextInt());
            }
            Scanner scanner = new Scanner(lineNumbers);
            ArrayList<Integer> numList = new ArrayList<>();
            while (scanner.hasNextInt()) {
                numList.add(scanner.nextInt());
            }
            ArrayList<ArrayList<Integer>> lineList = new ArrayList<>();
            lineList.add(winList);
            lineList.add(numList);
            lines.add(lineList);
        });

        for (int i = 0; i < lines.size(); i++) {
            scanLine(i);
        }

    }
    private static void scanLine(int lineIndex) {

        if (lineIndex < lines.size()){
            ArrayList<Integer> winList = lines.get(lineIndex).get(0);
            ArrayList<Integer> numList = lines.get(lineIndex).get(1);

            int matches = (int) numList.stream().filter(integer -> winList.stream().anyMatch(n -> (n.equals(integer)))).count();

            result++;
            System.out.println(result);

            for (int i = lineIndex + 1; i < lineIndex + matches + 1; i++) {
                scanLine(i);
            }
        }
    }
}
