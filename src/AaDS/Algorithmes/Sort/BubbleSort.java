package AaDS.Algorithmes.Sort;

import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        for(int i = 0; i < 100000; i++){
            arr[i] = new Random().nextInt(1000);
        }
        long a = System.currentTimeMillis();
        bubbleSort(arr);

        System.out.println(System.currentTimeMillis() - a);
    }

    public static void bubbleSort(int[] arr){
        for(int i = arr.length - 1; i >= 0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        //System.out.println(Arrays.toString(arr));
    }
}
