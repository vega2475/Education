package AaDS.YandexCourse4.lection2.util;

public class Hash {
    public static boolean isEqual(int from1, int from2, int p, long[] h, long[] x, int slen){
        return  (h[from1 + slen - 1] + h[from2 - 1] * x[slen]) % p ==
                (h[from2 + slen - 1] + h[from1 - 1] * x[slen]) % p;
    }

    public static void fill(long[] h, long[] x, String s, int p, int x_){
        for (int i = 1; i < s.length(); i++) {
            h[i] = (h[i - 1] * x_ + s.charAt(i)) % p;
            x[i] = (x[i - 1] * x_) % p;
        }
    }
}
