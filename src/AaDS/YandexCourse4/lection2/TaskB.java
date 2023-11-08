package AaDS.YandexCourse4.lection2;

import AaDS.YandexCourse4.lection2.util.Hash;

import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int n = s.length();
        int p = 1000000007;
        int x_ = 257;
        long[] h = new long[n + 1];
        long[] x = new long[n + 1];
        s = " " + s;
        x[0] = 1;
        Hash.fill(h, x, s, p, x_);
        for (int i = 2; i < s.length(); i++) {
            if(Hash.isEqual(1, i, p, h, x, n + 1 - i)){
                System.out.println(i - 1);
                return;
            }
        }
        System.out.println(n);
    }
}
