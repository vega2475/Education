package AaDS.LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

class Add_Two_Numbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(5, new ListNode(4, new ListNode(9)));
        ListNode l2 = new ListNode(5, new ListNode(6));
        System.out.println(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode answer = null;

        List<String> arr1 = new ArrayList<>();
        List<String> arr2 = new ArrayList<>();

        while(l1 != null){
            arr1.add(String.valueOf(l1.val));
            l1 = l1.next;
        }

        while(l2 != null){
            arr2.add(String.valueOf(l2.val));
            l2 = l2.next;
        }


        StringBuilder sb = new StringBuilder();

        for (int i = arr1.size() - 1; i >= 0; i--) {
            sb.append(arr1.get(i));
        }

        String s1 = sb.toString();

        StringBuilder sb2 = new StringBuilder();

        for (int i = arr2.size() - 1; i >= 0; i--) {
            sb2.append(arr2.get(i));
        }

        String s2 = sb2.toString();

        StringBuilder reverse = new StringBuilder(sum(s1, s2)).reverse();
        String s = reverse.toString();
        for (int i = s.length() - 1; i >= 0; i--) {
            answer = new ListNode(s.charAt(i) - 48, answer);
        }


        return answer;
    }

    public static String sum(String s1, String s2){
        StringBuilder result = new StringBuilder();

        int i = s1.length() - 1;
        int j = s2.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = (i >= 0) ? s1.charAt(i) - '0' : 0;
            int digit2 = (j >= 0) ? s2.charAt(j) - '0' : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            int digit = sum % 10;

            result.insert(0, digit); // Добавляем цифру в начало результата

            i--;
            j--;
        }

        return result.toString();
    }

    private static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
