package Lambda.Lambda1;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LambdaComparator {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();

        list.add("Dima");
        list.add("Nik");
        list.add("Pup");
        list.add("Aboba");
        list.add("Nickolay");

        Comparator<String> comparator = (s1, s2) -> {
            if(s1.length() > s2.length())return 1;
            else if(s1.length() < s2.length())return -1;
            else return 0;
        };

        list.sort(comparator);

        System.out.println(list);
    }
}
