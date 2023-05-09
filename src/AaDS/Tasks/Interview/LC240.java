/**
 * @company Amazon
 */
package AaDS.Tasks.Interview;

public class LC240 {
    public static void main(String[] args) {
        int matrix[][] = matrix = new int[][]{{1, 4, 7, 11, 15},
                                              {2, 5, 8, 12, 19},
                                              {3, 6, 9, 16, 22},
                                              {10, 13, 14, 17, 24},
                                              {18, 21, 23, 26, 30}};
        System.out.println(searchMatrix(matrix, 14));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix == null || matrix[0].length == 0)return false;
        int n = matrix[0].length;
        int m = matrix.length;

        int i = 0;//col
        int j = n - 1;//row

        while (i < m && j >= 0){
            if(matrix[i][j] == target) return true;
            if(matrix[i][j] > target){
                j--;
            }else i++;
        }
        return false;
    }
}
