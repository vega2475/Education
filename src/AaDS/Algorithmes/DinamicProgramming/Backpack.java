package AaDS.Algorithmes.DinamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
    public static void main(String[] args) {
        int [] weights = {3, 4, 5, 8, 9};
        int [] prices = {1, 6, 4, 7, 6};

        int count = weights.length;
        int maxWeight = 13;

        int[][] A;
        A = new int [count + 1][];
        for (int i = 0; i < count + 1; i++) {
            A[i] = new int[maxWeight + 1];
        }

        // Make a optimality table
        for (int k = 0; k <= count; k++) {
            for (int s = 0; s <= maxWeight; s++) {
                if(k == 0 || s == 0){
                    A[k][s] = 0;
                }else{
                    if(s >= weights[k - 1]){
                        A[k][s] = Math.max(A[k - 1][s], A[k - 1][s - weights[k - 1]] + prices[k - 1]);
                    }else{
                        A[k][s] = A[k - 1][s];
                    }
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        traceResult(A, weights, count, maxWeight, list);
        for(Integer integer : list) {
            System.out.println(integer);
        }
    }

    private static void traceResult(int[][] A, int[] weight, int k, int s, List<Integer> result){
        if(A[k][s] == 0){
            return;
        }
        if(A[k][s] == A[k - 1][s]){
            traceResult(A, weight, k - 1, s, result);
        }else{
            traceResult(A, weight, k - 1, s - weight[k - 1], result);
            result.add(k);
        }
    }

}
