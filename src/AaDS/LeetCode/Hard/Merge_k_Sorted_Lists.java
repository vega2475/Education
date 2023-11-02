package AaDS.LeetCode.Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Merge_k_Sorted_Lists {

    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[]{new ListNode(1, new ListNode(4, new ListNode(5))),
        new ListNode(1, new ListNode(3, new ListNode(4)))};
        System.out.println(mergeKLists(listNodes));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();

        for (ListNode listNode : lists) {
            ListNode node = listNode;
            while (node != null) {
                list.add(node.val);
                node = node.next;
            }
        }

        list.sort(Integer::compareTo);

        ListNode ans = null;

        for (int i = list.size() - 1; i < list.size(); i++) {
            ans = new ListNode(list.get(i), ans);
        }

        return ans;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}


