package day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class puzzle2 {

    private  static final ArrayList<Long[]> seedRanges = new ArrayList<>();
    public static void main(String[] args) throws Exception{

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
                    Long start = scanner.nextLong();
                    Long end = start + scanner.nextLong();
                    checkSeedRanges(start, end);
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

        humLoc.sort((o1, o2) -> o1[0].compareTo(o2[0]));
        boolean solved = false;
        for (Long[] location : humLoc) {
            Long i = location[0];
            while ((i < (location[0] + 9) && !solved)) {
                long seed = reverseConvert(seedSoil, reverseConvert(soilFert, reverseConvert(fertWater, reverseConvert(waterLight, reverseConvert(lightTemp, reverseConvert(tempHum, reverseConvert(humLoc, i)))))));
                for (Long[] range : seedRanges) {
                    if (seed >= range[0] && seed <= range[1]){
                        solved = true;
                        System.out.println(i);
                        break;
                    }
                }
                i++;
            }
        }

    }
    private static long reverseConvert(ArrayList<Long[]> map, long value){
        for (Long[] range : map) {
            long rangeEnd = range[0] + range[2] - 1;
            if (value >= range[0] && value <= rangeEnd){
                return range[1] + value - range[0];
            }
        }
        return value;
    }
    private static void checkSeedRanges(Long start, Long end) {
        if (seedRanges.isEmpty()) seedRanges.add(new Long[]{start, end});
        int doesNotMatchAny = 0;
        for (Long[] range : seedRanges) {
            if ((start < range[0] && end < range[0]) || (start > range[1] && end > range[1])){
                doesNotMatchAny++;
            } else if (start < range[0] && end > range[0] && end < range[1]) {
                range[0] = start;
            } else if (end > range[1] && start > range[0] && start < range[1]) {
                range[1] = end;
            } else if (start < range[0] && end > range[1]) {
                range[0] = start;
                range[1] = end;
            }
        }
        if (doesNotMatchAny == seedRanges.size()) {
            seedRanges.add(new Long[]{start, end});
        }
    }
}
