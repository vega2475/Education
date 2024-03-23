/**
 * Z Function
 */

package AaDS.YandexCourse4.lection2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int[] res = zFunction(s);
        System.out.println(Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    static int[] zFunction(String s){
        int[] zf = new int[s.length()];
        int left = 0, right = 0;
        for (int i = 1; i < zf.length; i++) {
            zf[i] = Math.max(0, Math.min(right - i, zf[i - left]));
            while (i + zf[i] < zf.length && s.charAt(zf[i]) == s.charAt(i + zf[i])){
                zf[i]++;
            }
            if(i + zf[i] > right){
                left = i;
                right = i + zf[i];
            }
        }
        return zf;
    }
}
