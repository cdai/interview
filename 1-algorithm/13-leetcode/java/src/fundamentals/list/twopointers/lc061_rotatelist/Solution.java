package fundamentals.list.twopointers.lc061_rotatelist;

import fundamentals.list.ListNode;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example: Given 1->2->3->4->5->NULL and k = 2, return 4->5->1->2->3->NULL.
 */
public class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k <= 0) return head;
        ListNode tail = head, pivot = head;

        // 1.Find length of list and tail
        int len = 1;
        for (; tail.next != null; tail = tail.next) len++;

        // 2.Find pivot
        k = len - k % len;
        while (--k > 0) pivot = pivot.next;

        // 3.Rotate list
        tail.next = head;
        head = pivot.next;
        pivot.next = null;
        return head;
    }

    // My 2nd:
    // Don't need a dummy node for this problem
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // 0.Get length of list and round k to [0,len-1]
        int len = 0;
        for (ListNode node = head; node != null; node = node.next) {
            len++;
        }
        k %= len;

        // 1.Find prev of kth node: k-1 nodes between (kprev,cur), since we need cur as last node to rotate
        // eg.[1,2,3,4,5], k=2: kprev=3, cur=5 (not null this time)
        ListNode kprev = head, cur = head;
        int i = 0;
        for ( ; cur.next != null; i++) {
            if (i >= k) {   // kprev=head(0), cur=k -> #nodes between them = cur - kprev - 1 = k-1
                kprev = kprev.next;
            }
            cur = cur.next;
        }

        // 2.Rotate. it's ok k=0 (eg.[1,2,3,4,5], k=0 -> kprev=cur=5)
        cur.next = head;
        head = kprev.next;
        kprev.next = null;
        return head;
    }

    // Most clean and best version from soulmachine
    public ListNode rotateRight21(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // 1.Get length and stop at the end
        ListNode cur = head;
        int len = 1;                // we already arrive at head
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }

        // 2.Go ahead len-k -> len-(len-k)-1=k-1 -> arrive prev of kth node!
        cur.next = head;
        for (int i = 0; i < len - k % len; i++) {
            cur = cur.next;
        }
        head = cur.next;
        cur.next = null;
        return head;
    }

    // From leetcode discuss: reuse cur used for calculating length, then it's easy to locate kprev
    public ListNode rotateRight3(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        // 1.Get length and keep cur at end for future use
        ListNode cur = head;
        int len = 1;                // we already arrive at head
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        k %= len;

        // 2.Find prev of kth
        ListNode kprev = head;
        for (int i = len - k; i > 1; i--) { // must i > 1
            kprev = kprev.next;
        }

        // 3.Reuse cur
        cur.next = head;
        head = kprev.next;
        kprev.next = null;
        return head;
    }

    // My 1st
    public ListNode rotateRight4(ListNode head, int k) {
        if (head == null || k < 0) {
            return head;
        }

        // NOTE: move to right by k places, k could be any large!!!
        // 1.Find length of list
        ListNode node = head;
        int len = 1;
        while ((node = node.next) != null) {
            len++;
        }

        // 2.Round k
        k = k % len;
        if (k == 0) {
            return head;
        }
        // assert: head(cur)!=nil, k from 1...len-1

        // 3.prev wait k time then start
        ListNode cur = head, prev = null;
        for (int i = 0; (cur.next != null); i++) {
            if (i == k - 1) {
                prev = head;
            } else if (i >= k) {
                prev = prev.next;
            }
            cur = cur.next;
        }

        // 4.Rotate
        if (prev != null) { // is this needed?
            cur.next = head;
            head = prev.next;
            prev.next = null;
        }
        return head;
    }

}
