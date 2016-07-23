package fundamentals.list.reversal.lc143_reorderlist;

import fundamentals.list.ListNode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 * For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class Solution {

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        // 1.Find middle node where slow stops at
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow == fast) { // eg. 1, 1->2
            return;
        }

        // 2.Cut off and reverse second half
        ListNode prev = null, cur = slow.next;
        while (cur != null) { // node must != null, at least 1->2(slow)->3(fast)
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        slow.next = null;

        // 3.Merge second half into first half
        ListNode half1 = head, half2 = prev;
        while (half1 != null && half2 != null) {
            ListNode next1 = half1.next;
            ListNode next2 = half2.next;
            half1.next = half2;
            half2.next = next1;
            half1 = next1;
            half2 = next2;
        }
    }

}
