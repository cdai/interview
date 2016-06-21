package fundamentals.list.lc024_swapnodeinpairs;

import fundamentals.list.ListNode;

/**
 * Given a linked fundamentals.list, swap every two adjacent nodes and return its head.
 * For example, Given 1->2->3->4, you should return the fundamentals.list as 2->1->4->3.
 * Your algorithm should use only constant space.
 * You may not modify the values in the fundamentals.list, only nodes itself can be changed.
 *
 * Definition for singly-linked fundamentals.list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode n1 = head, n2 = null, prev = null;
        while (n1 != null && n1.next != null) {
            n2 = n1.next;

            // 1.Maintain prev and save head if necessary
            if (prev != null) {
                prev.next = n2;
            } else {
                head = n2;
            }
            prev = n1;

            // 2.Swap pair
            n1.next = n2.next;
            n2.next = n1;

            n1 = n1.next;
        }
        return head;
    }
}
