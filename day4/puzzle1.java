package day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class puzzle1 {

    public static void main(String[] args) throws Exception{

        ArrayList<String> lines = new ArrayList<>();
        File input = new File("day4/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        br.lines().forEach(line -> lines.add(line));
        int result = 0;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
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
            int matches = (int) numList.stream().filter(integer -> winList.stream().anyMatch(n -> (n.equals(integer)))).count();
            double preresult = 1 * Math.pow(2, matches - 1);
            if (preresult != 0.5){
                result += preresult;
            }
        }
        System.out.println(result);
    }
}
