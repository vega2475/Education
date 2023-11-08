package AaDS.YandexCourse4.lection1.util;
// TODO - недоработано!!!
import java.util.Random;

public class QuickSort {
     public static int[] partition(boolean predicate, int e, int g, int n, int pivot, int[] array){
        // Элементы меньше x
        if(predicate){
            for (; n < array.length; n++) {
                if(pivot < array[n]){
                    if(g == -1) g = n;
                }else if(pivot > array[n]){
                    if(g == -1){
                        if(e != -1){
                            swap(array, n, e);
                            e++;
                        }
                        continue;
                    }
                    if(e == -1){
                        swap(array, n, g);
                        g++;
                    }else{
                        int tempG = array[g];
                        int tempE = array[e];
                        array[e] = array[n];
                        e++;
                        array[g] = tempE;
                        g++;
                        array[n] = tempG;
                    }
                }else if(pivot == array[n]){
                    if(g == -1){
                        e = n;
                        continue;
                    }
                    int tempG = array[g];
                    if(e == -1){
                        swap(array, n, g);
                        e = g;
                        g++;
                    }else{
                        array[g] = array[e];
                        g++;
                        array[n] = tempG;
                    }

                }

            }

        }
        return new int[]{e, g};
    }

     public static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    public static void quickSort(int[] array, int left, int right){
        if(left < right){
            int p = new Random().nextInt(left, right);
            int q = array[p];
            quickSort(array, left, p);
            quickSort(array, p + 1, right);
        }
    }
}
