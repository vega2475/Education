package AaDS.YandexCourse4.lection1;
// TODO - недоработано!!!
import java.util.Scanner;

class TaskB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        //QuickSort.sort(arr, 0, arr.length - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++){
            stringBuilder.append(arr[i]);
            if(i != arr.length - 1){
                stringBuilder.append(" ");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
