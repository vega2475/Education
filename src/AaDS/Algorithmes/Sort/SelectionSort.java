package AaDS.Algorithmes.Sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int [] arr = {2, 4, 5, 2, 33, 23, 23, 11};
        selectionSort(arr);
    }
    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int pos = i;
            int min = arr[i];
            //цикл выбора наименьшего элемента
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < min) {
                    pos = j;
                    min = arr[j];
                }
            }
            arr[pos] = arr[i];
            arr[i] = min;
        }
        System.out.println(Arrays.toString(arr));
    }
}
