package list.lc084_removeduplicatefromsortedlist2;

import list.ListNode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        // Sentinel for simplicity
        ListNode prev = new ListNode(-1);
        prev.next = head;
        head = prev;

        ListNode cur = head.next;
        boolean isDup = false;
        while (cur != null) {
            if (prev.next == cur) {
                cur = cur.next;
                continue;
            }

            if (prev.next.val == cur.val) {
                isDup = true;
            } else {
                if (isDup) {
                    prev.next = cur;
                    isDup = false;
                } else {
                    prev = prev.next;
                }
            }
            cur = cur.next;
        }

        if (isDup) {
            prev.next = null;
        }

        return head.next;
    }
}
