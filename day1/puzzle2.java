package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class puzzle2 {

    public static void main(String[] args) throws Exception {


        File input = new File("day1/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        ArrayList<Integer> lineNumbers = new ArrayList<>();
        br.lines().forEach(line -> lineNumbers.add(getLineNumber(testLine(line))));
        System.out.println(getResult(lineNumbers));


    }

    private static ArrayList<Integer> testLine(String line) {

        Integer[] numbers = new Integer[2];

        char[] chars = line.toCharArray();
        String[] digits = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        int lowestIndex = chars.length - 1;
        String firstDigit = "";
        int highestIndex = 0;
        String lastDigit = "";
        for (String digit : digits) {
            if (line.indexOf(digit) < lowestIndex && line.contains(digit)){
                lowestIndex = line.indexOf(digit);
                firstDigit = digit;
            }
            if (line.lastIndexOf(digit) > highestIndex && line.contains(digit)){
                highestIndex = line.lastIndexOf(digit);
                lastDigit = digit;
            }
        }
        switch (firstDigit) {
            case "one" -> numbers[0] = (1);
            case "two" -> numbers[0] = (2);
            case "three" -> numbers[0] = (3);
            case "four" -> numbers[0] = (4);
            case "five" -> numbers[0] = (5);
            case "six" -> numbers[0] = (6);
            case "seven" -> numbers[0] = (7);
            case "eight" -> numbers[0] = (8);
            case "nine" -> numbers[0] = (9);
        }
        switch (lastDigit) {
            case "one" -> numbers[1] = (1);
            case "two" -> numbers[1] = (2);
            case "three" -> numbers[1] = (3);
            case "four" -> numbers[1] = (4);
            case "five" -> numbers[1] = (5);
            case "six" -> numbers[1] = (6);
            case "seven" -> numbers[1] = (7);
            case "eight" -> numbers[1] = (8);
            case "nine" -> numbers[1] = (9);
        }



        for (int i = lowestIndex; i >= 0; i--){
            if (Character.isDigit(chars[i])){
                numbers[0] = Character.getNumericValue(chars[i]);
            }
        }
        for (int i = highestIndex; i < chars.length; i++){
            if (Character.isDigit(chars[i])){
                numbers[1] = Character.getNumericValue(chars[i]);
            }
        }
        return new ArrayList<>(Arrays.asList(numbers));
    }

    private static int getResult(ArrayList<Integer> numbers) {
        int result = 0;
        for (int i : numbers) {
            result += i;
        }
        return result;
    }

    private static int getLineNumber(ArrayList<Integer> line){
        return line.get(0) * 10 + line.get(1);
    }
}