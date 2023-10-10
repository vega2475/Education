package AaDS.YandexCourse.lection1.practice;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        if((a + b <= c) || (b + c <= a) || (a + c <= b)){
            System.out.println("NO");
        }else{
            System.out.println("YES");
        }
    }
}
