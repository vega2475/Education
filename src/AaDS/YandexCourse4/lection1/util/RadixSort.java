package AaDS.YandexCourse4.lection1.util;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 4, 4, 6, 1};
        countSort(arr, 6);
    }
    private static int[] countSort(int[] arr, int max){
        int[] freq = new int[max];
        for (int i : arr) {
            freq[i - 1]++;
        }
        int[] pos = new int[max];
        pos[0] = 0;
        for (int i = 1; i < max; i++) {
            pos[i]  = freq[i - 1] + pos[i - 1];
        }
        int[] res = new int[arr.length];
        for (int j : arr) {
            res[pos[j - 1]] = j;
            pos[j - 1]++;
        }
        return res;
    }

}
