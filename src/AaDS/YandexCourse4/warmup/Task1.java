package AaDS.YandexCourse4.warmup;

import java.util.Scanner;

class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String [] first = scanner.nextLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        String[] second = scanner.nextLine().split(" ");

        int [] arr = new int[second.length];
        int i = 0;
        for (String s : second) {
            arr[i] = Integer.parseInt(s);
            i++;
        }

        for (int j = 0; j < m; j++) {
            String[] operation = scanner.nextLine().split(" ");
            int start = Integer.parseInt(operation[0]);
            int end = Integer.parseInt(operation[1]);
            System.out.println(findNotMin(arr, start, end));
        }
    }

    private static String findNotMin(int [] arr, int start, int end){
        int digit = arr[start];
        for (int i = start; i <= end; i++) {
            if(arr[i] != digit) return String.valueOf(Math.max(arr[i], digit));
        }
        return "NOT FOUND";
    }
}
