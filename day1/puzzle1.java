package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class puzzle1 {

    public static void main(String[] args) throws Exception {


        File input = new File("day1/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        ArrayList<Integer> lineNumbers = new ArrayList<>();
        br.lines().forEach(line -> lineNumbers.add(getLineNumber(testLine(line))));
        System.out.println(getResult(lineNumbers));


    }

    private static ArrayList<Integer> testLine(String line) {

        ArrayList<Integer> numbers = new ArrayList<>();
        char[] chars = line.toCharArray();

        for (char ch: chars) {
            if (Character.isDigit(ch)){
                numbers.add(Character.getNumericValue(ch));
            }
        }
        return numbers;
    }

    private static int getResult(ArrayList<Integer> numbers) {
        int result = 0;
        for (int i : numbers) {
            result += i;
        }
        return result;
    }

    private static int getLineNumber(ArrayList<Integer> line){
        return line.get(0) * 10 + line.get(line.size()-1);
    }
}