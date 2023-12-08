package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class puzzle2 {

    public static void main(String[] args) throws Exception{

        ArrayList<Game> games = new ArrayList<>();
        int result = 0;

        File input = new File("day2/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        br.lines().forEach(game -> games.add(new Game(game)));

        for (Game game : games) {
            result += game.maxRed * game.maxGreen * game.maxBlue;
        }

        System.out.println(result);
    }
    private static class Game {

        public int id;
        public int maxRed;
        public int maxGreen;
        public int maxBlue;

        public Game(String game) {

            String[] arr = game.split(":");
            String[] sets = arr[1].split(";");

            id = Integer.parseInt(Arrays.stream(arr[0].split(" ")).toList().get(1));
            maxRed = 0;
            maxGreen = 0;
            maxBlue = 0;

            for (String set : sets) {
                String[] cubes = set.split(",");
                for (String cube : cubes) {
                    if (cube.contains("red")){
                        maxRed = Math.max(extractInt(cube), maxRed);
                    }
                    if (cube.contains("green")){
                        maxGreen = Math.max(extractInt(cube), maxGreen);
                    }
                    if (cube.contains("blue")){
                        maxBlue = Math.max(extractInt(cube), maxBlue);
                    }
                }
            }
        }

        private int extractInt(String str) {
            str = str.replaceAll("[^0-9]", "");
            str = str.replaceAll(" +", "");
            if (str.isEmpty())
                return -1;

            return Integer.parseInt(str);
        }
    }
}
