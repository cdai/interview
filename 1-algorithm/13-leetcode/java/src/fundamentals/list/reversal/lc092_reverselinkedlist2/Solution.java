package fundamentals.list.reversal.lc092_reverselinkedlist2;

import fundamentals.list.ListNode;

/**
 * Reverse a linked fundamentals.list from position m to n. Do it in-place and in one-pass.
 * For example:
 *  Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 *  return 1->4->3->2->5->NULL.
 * Note: Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of fundamentals.list.
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (!(head != null && 1 <= m && m <= n) || m == n) { // error1: corner-case1 m=n
            return head;
        }

        ListNode n0 = head, n1 = n0.next, nmp = null;
        for (int i = 1; (n1 != null) && (i < n); i++) {
            if (i <= m-1) {
                if (i == m-1) {
                    nmp = n0;
                }
                n0 = n0.next;
                n1 = n0.next; // error2: forget to move n1
            } else {
                // reverse n0->n1 to n0<-n1
                ListNode tmp = n1.next;
                n1.next = n0;

                // move forward
                n0 = n1;
                n1 = tmp;
            }
        }

        // must n <= length, otherwise no way to do in one pass
        if (nmp == null) { // error4: corner-case2 m=1 (namely head is reversed as well)
            head.next = n1;
            head = n0;
        } else {
            nmp.next.next = n1; // error3: forget which cause dead loop... should've drawn
            nmp.next = n0;
        }
        return head;
    }
}
