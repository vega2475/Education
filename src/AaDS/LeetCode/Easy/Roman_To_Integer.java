package AaDS.LeetCode.Easy;

import java.util.HashMap;

public class Roman_To_Integer {
    public static void main(String[] args) {
        System.out.println(romanToInt("CDCDXXX"));
    }

    public static int romanToInt(String s){
        HashMap<Character, Integer> map = new HashMap<>();

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int acc = 0;
        int i = 0;
        for (; i < s.length() - 1; i++) {
            switch (s.charAt(i)){
                case ('I'):
                    if(s.charAt(i + 1) == 'V'){
                        acc += 4;
                        i++;
                    }else if(s.charAt(i + 1) == 'X'){
                        acc+=9;
                        i++;
                    }else{
                        acc++;
                        continue;
                    }
                    break;
                case ('V'):
                    acc+=5;
                    break;

                case('X'):
                    if(s.charAt(i + 1) == 'L'){
                        acc+=40;
                        i++;
                    }else if(s.charAt(i + 1) == 'C'){
                        acc+=90;
                        i++;
                    }else{
                        acc+=10;
                    }
                    break;
                case ('L'):
                    acc+=50;
                    break;
                case ('C'):
                    if(s.charAt(i + 1) == 'D'){
                        acc+=400;
                        i++;
                    }else if(s.charAt(i + 1) == 'M'){
                        acc+=900;
                        i++;
                    }else{
                        acc+=100;
                    }
                    break;
                case ('D'):
                    acc+=500;
                    break;
                case ('M'):
                    acc+=1000;
                    break;
                default:
                    acc = 0;
            }
        }
        if(i < s.length()){
            acc += map.get(s.charAt(i));
        }

        return acc;
    }
}
