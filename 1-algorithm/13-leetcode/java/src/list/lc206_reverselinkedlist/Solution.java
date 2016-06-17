package list.lc206_reverselinkedlist;

import list.ListNode;

/**
 * Reverse a singly linked list.
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        // Invariant: nodes before n0 are reversed
        //  e.g. 1<-2<-3<-n0--n1->...
        // Init to hold invariant true
        ListNode n0 = head;
        ListNode n1 = n0.next;
        n0.next = null;

        while (n1 != null) {
            // Now reverse n0->n1 to n0<-n1 to maintain invariant before moving n0
            ListNode tmp = n1.next;
            n1.next = n0;

            // Move forward
            n0 = n1;
            n1 = tmp;
        }
        return n0;
    }
}
