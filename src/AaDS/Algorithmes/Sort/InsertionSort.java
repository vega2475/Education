package AaDS.Algorithmes.Sort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int [] arr = {2, 4, 5, 2, 33, 23, 23, 11};
        insertionSort(arr);
    }
    public static void insertionSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int current = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > current){
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = current;
        }
        System.out.println(Arrays.toString(arr));
    }
}
