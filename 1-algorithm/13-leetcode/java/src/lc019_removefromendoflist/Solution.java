package lc019_removefromendoflist;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example, given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note: Given n will always be valid. Try to do this in one pass.
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode prev = null;
        int i;
        for (i = 0; cur != null; i++, cur=cur.next) {
            if (i == n) {       //error1: i==n+1
                prev = head;    //error2: prev=cur;
            } else if (i > n) { //i>n+1
                prev = prev.next;
            }
        }

        if (i > n) {
            prev.next = prev.next.next;
        } else if (i == n) {
            head = head.next;
        }
        return head;
    }

    private class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) { val = x; }
    }
}
