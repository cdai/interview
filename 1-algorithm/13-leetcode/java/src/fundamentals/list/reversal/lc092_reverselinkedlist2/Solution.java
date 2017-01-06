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

    // pre->...->cur->suc => pre->suc->...->cur->...
    public ListNode reverseBetween(ListNode head, int m , int n) {
        ListNode dmy = new ListNode(0), pre = dmy;
        dmy.next = head;
        for (int i = 1; i < m && pre != null; i++)
            pre = pre.next;

        if (pre == null) return head;

        ListNode cur = pre.next;
        for (int i = m; i < n && cur != null && cur.next != null; i++) {
            ListNode suc = cur.next;
            cur.next = suc.next;
            suc.next = pre.next;
            pre.next = suc;
            //cur = cur.next;
        }
        return dmy.next;
    }

    // My 3AC. Start from m, reverse n-m-1 times.
    public ListNode reverseBetween3(ListNode head, int m, int n) {
        if (head == null) return null;

        ListNode dmy = new ListNode(0), prev = dmy;
        dmy.next = head;
        for (int i = 1; i < m; i++) prev = prev.next;

        // Head-insert: prev->...->cur->then->... => prev->then...->cur->...
        ListNode cur = prev.next;
        for (int i = m; i < n && cur != null && cur.next != null; i++) { // For safe
            ListNode then = cur.next;
            cur.next = then.next;
            then.next = prev.next;
            prev.next = then;
        }
        return dmy.next;
    }

    // My 2nd: from leetcode discuss and soulmachine
    // Alternative for list reversal. Elegant!
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 1.Find node in front of range to be reversed
        ListNode prev = dummy;
        for (int i = 0; i < m - 1; i++) {
            prev = prev.next;
        }

        // 2.Do the reversal
        // Invariant: prev->...->cur->then->... => prev->then...->cur->...
        // After that, "cur" stay the same, update "then"
        ListNode cur = prev.next;
        for (int i = 0; i < n - m; i++) {
            ListNode then = cur.next;
            cur.next = then.next;
            then.next = prev.next;
            prev.next = then;
        }
        return dummy.next;
    }

    // My 1st: ugly...
    public ListNode reverseBetween1(ListNode head, int m, int n) {
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
