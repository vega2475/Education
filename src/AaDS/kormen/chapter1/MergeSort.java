package AaDS.kormen.chapter1;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 2, 5, 4, 7, 11, 8};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void merge(int[] arr, int p, int q, int r){
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] subArr1 = new int[n1];
        int[] subArr2 = new int[n2];

        for (int i = 0; i < n1; i++) {
            subArr1[i] = arr[p + i];
        }

        for (int i = 0; i < n2; i++) {
            subArr2[i] = arr[(q + 1) + i];
        }

        int i = 0;
        int j = 0;
        int k = p;

        while(i < n1 && j < n2) {
            if(subArr1[i] < subArr2[j]){
                arr[k] = subArr1[i];
                i++;
            }else {
                arr[k] = subArr2[j];
                j++;
            }
            k++;
        }

        while(i < n1){
            arr[k] = subArr1[i];
            i++;
            k++;
        }

        while(j < n2){
            arr[k] = subArr2[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] arr, int p, int r){
        if(p < r){
            int q = (p+r)/2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }
}
