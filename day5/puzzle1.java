package day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class puzzle1 {

    public static void main(String[] args) throws Exception {

        ArrayList<Long> seeds = new ArrayList<>();
        ArrayList<Long[]> seedSoil = new ArrayList<>();
        ArrayList<Long[]> soilFert = new ArrayList<>();
        ArrayList<Long[]> fertWater = new ArrayList<>();
        ArrayList<Long[]> waterLight = new ArrayList<>();
        ArrayList<Long[]> lightTemp = new ArrayList<>();
        ArrayList<Long[]> tempHum = new ArrayList<>();
        ArrayList<Long[]> humLoc = new ArrayList<>();
        File input = new File("day5/input.txt").getAbsoluteFile();
        BufferedReader br = new BufferedReader(new FileReader(input));
        AtomicReference<ArrayList<Long[]>> writeTo = new AtomicReference<>(seedSoil);
        br.lines().forEach(line -> {
            if (line.contains("seeds:")) {
                line = line.split(":")[1];
                Scanner scanner = new Scanner(line);
                while (scanner.hasNextLong()) {
                    seeds.add(scanner.nextLong());
                }
            }
            if (line.contains("to-soil")) writeTo.set(seedSoil);
            if (line.contains("to-fert")) writeTo.set(soilFert);
            if (line.contains("to-water")) writeTo.set(fertWater);
            if (line.contains("to-light")) writeTo.set(waterLight);
            if (line.contains("to-temp")) writeTo.set(lightTemp);
            if (line.contains("to-hum")) writeTo.set(tempHum);
            if (line.contains("to-loc")) writeTo.set(humLoc);

            if (!line.isEmpty() && Character.isDigit(line.toCharArray()[0])) {
                Scanner scanner = new Scanner(line);
                Long[] triplet = new Long[3];
                while (scanner.hasNextLong()) {
                    triplet = new Long[]{scanner.nextLong(), scanner.nextLong(), scanner.nextLong()};
                }
                writeTo.get().add(triplet);
            }

        });
        ArrayList<Long> results = new ArrayList<>();
        seeds.forEach(seed -> results.add(convert(humLoc, convert(tempHum, convert(lightTemp, convert(waterLight, convert(fertWater,convert(soilFert, convert(seedSoil, seed)))))))));
        final Long[] min = {results.get(0)};
        results.forEach(result -> {
            if (result < min[0]) min[0] = result;
        });
        System.out.println(min[0]);

    }
    private static long convert(ArrayList<Long[]> map, long value){
        for (Long[] range : map) {
            long rangeEnd = range[1] + range[2];
            if (value >= range[1] && value <= rangeEnd){
                return range[0] + value - range[1];
            }
        }
        return value;
    }
}
