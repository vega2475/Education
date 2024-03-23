package AaDS.YandexCourse4.lection2;

import java.util.Scanner;

public class TaskE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(countPalindromes(s));

    }


    // Алгоритм Манакера
    public static int countPalindromes(String s) {
        int n = s.length();
        char[] paddedString = new char[2 * n + 1];
        int[] P = new int[2 * n + 1];
        int center = 0;
        int right = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            paddedString[2 * i] = '#';
            paddedString[2 * i + 1] = s.charAt(i);
        }
        paddedString[2 * n] = '#';

        for (int i = 0; i < 2 * n + 1; i++) {
            int mirror = 2 * center - i;

            if (i < right) {
                P[i] = Math.min(right - i, P[mirror]);
            }

            int a = i + (1 + P[i]);
            int b = i - (1 + P[i]);
            while (a < 2 * n + 1 && b >= 0 && paddedString[a] == paddedString[b]) {
                P[i]++;
                a++;
                b--;
            }

            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }
            count += (P[i] + 1) / 2;
        }

        return count;
    }
}
