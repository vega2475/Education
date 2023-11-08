package AaDS.YandexCourse4.lection1.util;

public class MergeSort {
    public static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            result[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            result[k] = right[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // Базовый случай: массив уже отсортирован
        }

        int middle = arr.length / 2;
        int[] left = new int[middle];
        int[] right = new int[arr.length - middle];

        // Заполняем левый и правый подмассивы
        for (int i = 0; i < middle; i++) {
            left[i] = arr[i];
        }
        for (int i = middle; i < arr.length; i++) {
            right[i - middle] = arr[i];
        }

        // Рекурсивно сортируем левый и правый подмассивы
        mergeSort(left);
        mergeSort(right);

        // Объединяем отсортированные подмассивы
        merge(arr, left, right);
    }
}
