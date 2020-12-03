package ru.ivn.yuyu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Part One. Find the two entries that sum to 2020; what do you get if you multiply them together? Solution {@link #findResultFirst(Set<Integer>)}
 * Part Two. What is the product of the three entries that sum to 2020? Solution: {@link #findResultSecond(Set<Integer>)}
 */

public class Task1 {


    public static void main(String[] args) {


        String fileName = ".\\resources\\input1.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            Set<Integer> a = stream.map(Integer::parseInt).filter(integer -> integer < 2020).collect(Collectors.toSet());
            System.out.println(findResultFirst(a));
            System.out.println(findResultSecond(a));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static Integer findResultFirst(Set<Integer> map) {
        for (Integer k : map) {
            if (map.contains(2020 - k)) {
                return k * (2020 - k);
            }
        }
        throw new RuntimeException("Map don`t have two numbers which sum are 2020");
    }

    private static Integer findResultSecond(Set<Integer> map) {
        for (Integer k : map) {
            for (Integer r : map) {
                if (k + r > 2020) continue;
                if (map.contains(2020 - (k + r))) {
                    return k * r * (2020 - (k + r));
                }
            }
        }
        throw new RuntimeException("Map don`t have three numbers which sum are 2020");
    }
}


