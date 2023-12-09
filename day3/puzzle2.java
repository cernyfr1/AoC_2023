package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public class puzzle2 {

    static int result = 0;
    static ArrayList<char[]> lines = new ArrayList<>();


    public static void main(String[] args) throws Exception {

        File input = new File("day3/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        br.lines().forEach(line -> lines.add(line.toCharArray()));
        int asterisks = 0;

        for (int i = 0; i < lines.size(); i++) {
            char[] line = lines.get(i);
            for (int j = 0; j < line.length; j++) {
                if (line[j] == '*'){
                    asterisks++;
                    System.out.println(asterisks);
                    ArrayList<String> adjacent = scanForAdjacentNumbers(i, j);
                    if (adjacent.size() == 2){
                        //System.out.println(adjacent);
                        int mult = Integer.parseInt(adjacent.get(0)) * Integer.parseInt(adjacent.get(1));
                        //System.out.println(mult);
                        result += mult;
                    }
                }
            }
        }

        System.out.println("Result: " + result);
    }
    private static ArrayList<String> scanForAdjacentNumbers(int i, int j) {

        ArrayList<String> result = new ArrayList<>();

        if (j != lines.get(i).length - 1){
            if (Character.isDigit(lines.get(i)[j + 1])) result.add(scanRight(i, j + 1));
        }
        if (j != 0){
            if (Character.isDigit(lines.get(i)[j - 1])) result.add(scanLeft(i, j - 1));
        }
        result.addAll(scanUpDown(i - 1, j));
        result.addAll(scanUpDown(i + 1, j));


        System.out.println(result);

        return result;
    }
    private static ArrayList<String> scanUpDown(int i, int j ){
        ArrayList<String> result = new ArrayList<>();
        boolean merge = false;
        if (j != 0){
            String left = scanLeft(i, j - 1);
            if (!Objects.equals(left, "")) result.add(left);
        }
        if (Character.isDigit(lines.get(i)[j])){
            result.add(Character.toString(lines.get(i)[j]));
            merge = true;
        }
        if (j != lines.get(i).length - 1){
            String right = scanRight(i, j + 1);
            if (!Objects.equals(right, "")) result.add(right);
        }
        if (merge) {
            String s = "";
            for (String x : result) {
                s += x;
            }
            result.clear();
            result.add(s);
            return result;
        }
        return result;

    }
    private static String scanRight(int i, int j) {
        ArrayList<Integer> number = new ArrayList<>();
        while (Character.isDigit(lines.get(i)[j])){
            number.add(Integer.parseInt(Character.toString(lines.get(i)[j])));
            if (j == lines.get(i).length - 1) {break;}
            j++;
        }
        if (number.isEmpty()) return "";
        String result = "";
        for (Integer x : number) {
            result += x.toString();
        }
        return result;
    }
    private static String scanLeft(int i, int j) {
        ArrayList<Integer> number = new ArrayList<>();
        while (Character.isDigit(lines.get(i)[j])){
            number.add(Integer.parseInt(Character.toString(lines.get(i)[j])));
            if (j == 0) {break;}
            j--;
        }
        if (number.isEmpty()) return "";
        String result = "";
        for (Integer x : number.reversed()) {
            result += x.toString();
        }
        return result;
    }
}
