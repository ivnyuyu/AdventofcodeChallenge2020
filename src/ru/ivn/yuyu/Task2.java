package ru.ivn.yuyu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Part One.
 * Each line gives the password policy and then the password.
 * The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid.
 * For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.
 * How many passwords are valid according to their policies? Solution {@link #countValidPasswordsFirstStrategy(List<String>)}
 *
 *
 * Part Two.
 * Each policy actually describes two positions in the password, where 1 means the first character, 2 means the second character, and so on.
 * (Be careful; Toboggan Corporate Policies have no concept of "index zero"!) Exactly one of these positions must contain the given letter.
 * Other occurrences of the letter are irrelevant for the purposes of policy enforcement.
 * How many passwords are valid according to the new interpretation of the policies? Solution {@link #countValidPasswordsSecondStrategy(List<String>)}
 */

public class Task2 {
    private static final String REGULAR = "(\\d*)-(\\d*)\\s([a-z])+:\\s([a-z]+)";
    private static final Pattern PATTERN = Pattern.compile(REGULAR);


    public static void main(String[] args) {
        try {
            String fileName = ".\\resources\\input2.txt";
            List<String> a = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
            System.out.println(countValidPasswordsFirstStrategy(a));
            System.out.println(countValidPasswordsSecondStrategy(a));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static int countValidPasswordsFirstStrategy(List<String> list) {
        int count = 0;
        for (String temp : list) {
            Matcher matcher = PATTERN.matcher(temp);
            if (matcher.find()) {
                int minValue = Integer.parseInt(matcher.group(1));
                int maxValue = Integer.parseInt(matcher.group(2));
                char searchChar = matcher.group(3).charAt(0);
                String text = matcher.group(4);
                int countCharRepeat = 0;
                for (int i = 0; i < text.length(); i++) {
                    if (searchChar == text.charAt(i)) {
                        countCharRepeat++;
                    }
                }
                if (minValue <= countCharRepeat && maxValue >= countCharRepeat) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countValidPasswordsSecondStrategy(List<String> list) {
        int count = 0;
        for (String temp : list) {
            Matcher matcher = PATTERN.matcher(temp);
            if (matcher.find()) {
                int firstIndex = Integer.parseInt(matcher.group(1));
                int secondIndex = Integer.parseInt(matcher.group(2));
                char searchChar = matcher.group(3).charAt(0);
                String text = matcher.group(4);
                if (((text.charAt(firstIndex - 1) == searchChar) || (text.charAt(secondIndex - 1) == searchChar))
                        && (text.charAt(firstIndex - 1) != text.charAt(secondIndex - 1))) {
                    count++;
                }
            }
        }
        return count;
    }
}