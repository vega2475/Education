package AaDS.YandexCourse.lection1.practice;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s1 = scanner.nextLine().split(" ");
        int troom = Integer.parseInt(s1[0]);
        int tcond = Integer.parseInt(s1[1]);
        String mode = scanner.nextLine();

        System.out.println(solve(mode, troom, tcond));
    }

    public static int solve(String mode, int troom, int tcond){
        if(troom < tcond){
            if(mode.equals("heat") || mode.equals("auto")){
                return tcond;
            }
            else
                return troom;
        }
        else if(troom > tcond){
            if(mode.equals("freeze") || mode.equals("auto")){
                return tcond;
            }else {
                return troom;
            }
        }
        return troom;
    }

}
