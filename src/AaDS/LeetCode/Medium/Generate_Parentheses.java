package AaDS.LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

public class Generate_Parentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        solution("", 0, 0, list, n);
        return list;
    }

    public static void solution(String s, int left, int right, List<String> res, int n){
        if(s.length() == n*2){
            res.add(s);
            return;
        }

        if(left < n){
            solution(s + "(", left + 1, right, res, n);
        }

        if(left > right){
            solution( s +")", left, right + 1, res, n);
        }
    }
}
