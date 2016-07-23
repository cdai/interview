package fundamentals.list.reversal.lc061_rotatelist;

import fundamentals.list.ListNode;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example: Given 1->2->3->4->5->NULL and k = 2, return 4->5->1->2->3->NULL.
 */
public class Solution {

    public ListNode rotateRight(ListNode head, int k) {
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
