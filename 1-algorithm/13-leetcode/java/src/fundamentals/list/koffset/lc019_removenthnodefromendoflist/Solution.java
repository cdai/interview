package fundamentals.list.koffset.lc019_removenthnodefromendoflist;

import fundamentals.list.ListNode;

/**
 * Given a linked fundamentals.list, remove the nth node from the end of fundamentals.list and return its head.
 * For example, given linked fundamentals.list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked fundamentals.list becomes 1->2->3->5.
 * Note: Given n will always be valid. Try to do this in one pass.
 *
 * Definition for singly-linked fundamentals.list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {

    // 2nd: More clear version!
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 1.Find nth node: there're n nodes between (nprev, cur)
        // -> when terminating (cur=null), nprev is previous node of nth
        ListNode nprev = dummy, cur = dummy;
        for (int i = 0; cur != null; i++) {
            if (i >= n + 1) {       // Prove: nprev starts off when cur is already N+1 away -> #nodes in the middle = cur - nprev - 1 = n
                nprev = nprev.next;
            }
            cur = cur.next;
        }

        // 2.Delete nth node: nprev is at least dummy, which means delete first node
        // given n is always valid -> exclude the case n is too large or even negative
        nprev.next = nprev.next.next;
        return dummy.next;
    }

    // 1st
    public ListNode removeNthFromEnd2(ListNode head, int n) {
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
}
