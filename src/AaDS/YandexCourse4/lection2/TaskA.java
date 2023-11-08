/**
 * Необходимо сравнить 2 подстроки одной строки за O(1) с помощью хешей.
 */

package AaDS.YandexCourse4.lection2;

import AaDS.YandexCourse4.lection2.util.Hash;

import java.util.Scanner;

// P берем 10^9 + 7, x берем 10 на этапе отладки, x берем больше 256 (257)
public class TaskA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int q = scanner.nextInt();
        int n = s.length();
        int p = 1000000007;
        int x_ = 257;
        long[] h = new long[n + 1];
        long[] x = new long[n + 1];
        x[0] = 1;
        s = " " + s;
        Hash.fill(h, x, s, p, x_);
        for (int i = 0; i < q; i++) {
            int len = scanner.nextInt();
            int from1 = scanner.nextInt();
            int from2 = scanner.nextInt();
            if(Hash.isEqual(from1, from2, p, h, x, len)){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }
    }
}
