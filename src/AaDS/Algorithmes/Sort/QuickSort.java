/**
 * Общий механизм сортировки
 * Выбрать элемент из массива. Назовём его опорным.
 * Разбиение: перераспределение элементов в массиве таким образом, что элементы, меньшие опорного, помещаются перед ним,
 * а большие или равные - после. Рекурсивно применить первые два шага к двум подмассивам слева и справа от опорного элемента.
 * @link - https://ru.wikipedia.org/wiki/%D0%91%D1%8B%D1%81%D1%82%D1%80%D0%B0%D1%8F_%D1%81%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0
 */
package AaDS.Algorithmes.Sort;

import java.util.Arrays;

public class QuickSort {
    static int c = 0;
    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(int[] arr, int from, int to){
        if(from < to){
            int pivot = partition(arr, from, to); // Опорный элемент (возвращает индекс элемента по которому происходит разделение)
            System.out.println(pivot);
            quickSort(arr, from, pivot - 1); //Сортировка левого подмассива
            quickSort(arr, pivot, to); //Сортировка правого подмассива
        }
    }
    // Алгоритм разбиения массива на 2 подмассива
    private static int partition(int[] arr, int from, int to){
        int leftIndex = from;
        int rightIndex = to;

        int pivot = arr[from];
        while(leftIndex <= rightIndex){
            c++;
            //Движемся от начала массива к концу пока не найдем в левой части массива, какой-то элемент который больше опорного.
            while (arr[leftIndex] < pivot){
                c++;
                leftIndex++;
            }
            //Движемся от конца массива к началу пока не найдем в правой части массива, какой-то элемент который меньше опорного.
            while (arr[rightIndex] > pivot){
                c++;
                rightIndex--;
            }
            //После того как найдена пара элементов которые в левой части и больше опорного, а в правой части и меньше опорного, они синхронно меняются местами
            if(leftIndex <= rightIndex){
                c++;
                swap(arr, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        //System.out.println(c);
        return leftIndex;
    }
    private static void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}

class Test{
    public static void main(String[] args) {
        int [] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};
        System.out.println(Arrays.toString(array));
        QuickSort.quickSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println();
    }
}