package AaDS.YandexCourse4.lection3;

import java.util.Arrays;
import java.util.Scanner;

public class TaskA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int S = scanner.nextInt();
        int F = scanner.nextInt();

        int[][] matrix = new int[N+1][N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;
        boolean[] visited = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        for (int i = 1; i <= N; i++) {
            int minVertexIdx = findMinIndex(visited, dist);

            for (int j = 1; j <= N; j++) {
                if (!visited[j] && matrix[minVertexIdx][j] != -1 && dist[minVertexIdx] != Integer.MAX_VALUE) {
                    int d = dist[minVertexIdx] + matrix[minVertexIdx][j];
                    if (d < dist[j]) {
                        dist[j] = d;
                    }
                }
            }
            visited[minVertexIdx] = true;
        }
        System.out.println(dist[F] == Integer.MAX_VALUE ? -1 : dist[F]);
    }

    private static int findMinIndex(boolean[] visited, int[] dist){
        int minVertexIdx = -1;
        for (int i = 1; i < dist.length; i++) {
            if (!visited[i] && (minVertexIdx == -1 || dist[i] < dist[minVertexIdx])) {
                minVertexIdx = i;
            }
        }
        return minVertexIdx;
    }
}
