package ru.ivn.yuyu;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Part One.
 * Starting at the top-left corner of your map and following a slope of right 3 and down 1, how many trees would you encounter?
 * Solution: {@link findThreeOnRoad(List<String>,int,int)}
 *
 * Part Two.
 * Determine the number of trees you would encounter if, for each of the following slopes,
 * you start at the top-left corner and traverse the map all the way to the bottom:
 * Right 1, down 1.
 * Right 3, down 1. (This is the slope you already checked.)
 * Right 5, down 1.
 * Right 7, down 1.
 * Right 1, down 2.
 * What do you get if you multiply together the number of trees encountered on each of the listed slopes? Solution: {@link main(String[])}
 */
public class Task3 {
    public static void main(String[] args) {
        try {
            String fileName = ".\\resources\\input3.txt";
            List<String> a = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
            int aRes = findThreeOnRoad(a, 1, 1);
            int bRes = findThreeOnRoad(a, 3, 1);
            int cRes = findThreeOnRoad(a, 5, 1);
            int dRes = findThreeOnRoad(a, 7, 1);
            int eRes = findThreeOnRoad(a, 1, 2);
            long result = (long) aRes * bRes * cRes * dRes * eRes;
            System.out.println("Right 3, down 1: " + bRes);
            System.out.println("Multiply: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int findThreeOnRoad(List<String> road, int rightMove, int downMove) {
        int count = 0;
        int roadWidth = road.get(0).length();
        for (int i = downMove, j = 1; i < road.size(); i += downMove, j++) {
            if ((road.get(i).charAt((rightMove * j) % roadWidth)) == '#') {
                count++;
            }
        }
        return count;
    }


}
