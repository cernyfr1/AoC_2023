package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class puzzle1 {

    private static final ArrayList<char[]> chars = new ArrayList<>();
    static int result = 0;


    public static void main(String[] args) throws Exception {

        ArrayList<String> lines = new ArrayList<>();
        File input = new File("day3/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        br.lines().forEach(line -> lines.add(line));
        for (String line : lines) {chars.add(line.toCharArray());}

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String newLine = line.replaceAll("[^0-9]", " ");
            newLine = newLine.replaceAll(" +", " ");
            Scanner scanner = new Scanner(newLine);
            ArrayList<Integer> list = new ArrayList<>();
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
            char[] lineChars = line.toCharArray();
            ArrayList<Integer> indexes = new ArrayList<>();
            if (Character.isDigit(lineChars[0])) indexes.add(0);
            for (int j = 0; j < lineChars.length - 1; j++){
                if (!Character.isDigit(lineChars[j]) && Character.isDigit(lineChars[j + 1])){
                    indexes.add(j + 1);
                }
            }

            scanForAdjacentSymbols(list, indexes, i).forEach(number -> result += number);
        }
        System.out.println(result);
    }
    private static ArrayList<Integer> scanForAdjacentSymbols(ArrayList<Integer> numbers, ArrayList<Integer> xIndexes, int y) {

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            ArrayList<Integer> indexesToCheck = new ArrayList<>();
            indexesToCheck.add(xIndexes.get(i) - 1);
            for (int j = xIndexes.get(i); j < numbers.get(i).toString().toCharArray().length + xIndexes.get(i); j++) {
                indexesToCheck.add(j);
            }
            indexesToCheck.add(indexesToCheck.get(indexesToCheck.size() - 1) + 1);
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j : indexesToCheck) {
                if (j > 0 && j < chars.get(y).length - 1){temp.add(j);}
            }
            indexesToCheck = temp;

            //System.out.println(indexesToCheck);
            boolean add = false;
            for (int index : indexesToCheck) {
                if (!Character.isDigit(chars.get(y)[index]) && chars.get(y)[index] != '.'){
                    add = true;
                }
                if (y != 0) {
                    if (!Character.isDigit(chars.get(y - 1)[index]) && chars.get(y - 1)[index] != '.'){
                        add = true;
                    }
                }
                if (y != chars.size() - 1) {
                    if (!Character.isDigit(chars.get(y + 1)[index]) && chars.get(y + 1)[index] != '.'){
                        add = true;
                    }
                }
            }
            if (add) result.add(numbers.get(i));

        }

        return result;
    }
}
