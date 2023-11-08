package AaDS.YandexCourse4.lection1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        String[] input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = scanner.nextLine();
        }

        System.out.println("Initial array:");
        System.out.println(String.join(", ", input));
        System.out.println("**********");

        int maxLength = Arrays.stream(input).map(String::length).max(Integer::compare).orElse(0);

        for (int i = 0; i < maxLength; i++) {
            System.out.println("Phase " + (i + 1));
            input = radixSort(input, i);
            printBuckets(input, i);
            System.out.println("**********");
        }

        System.out.println("Sorted array:");
        System.out.println(String.join(", ", input));
    }

    private static String[] radixSort(String[] input, int digitIndex) {
        int radix = 10; // assuming digits are in the range 0-9
        List<String>[] buckets = new ArrayList[radix];

        for (int i = 0; i < radix; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (String s : input) {
            int index = s.length() - digitIndex - 1;
            int digit = (index >= 0) ? Character.getNumericValue(s.charAt(index)) : 0;
            buckets[digit].add(s);
        }

        List<String> result = new ArrayList<>();
        for (List<String> bucket : buckets) {
            result.addAll(bucket);
        }

        return result.toArray(new String[0]);
    }

    private static void printBuckets(String[] buckets, int digitIndex) {
        for (int i = 0; i < 10; i++) {
            System.out.print("Bucket " + i + ": ");
            List<String> currentBucket = new ArrayList<>();
            for (String s : buckets) {
                int index = s.length() - digitIndex - 1;
                int digit = (index >= 0) ? Character.getNumericValue(s.charAt(index)) : 0;
                if (digit == i) {
                    currentBucket.add(s);
                }
            }
            if (!currentBucket.isEmpty()) {
                System.out.println(String.join(", ", currentBucket));
            } else {
                System.out.println("empty");
            }
        }
    }
}
