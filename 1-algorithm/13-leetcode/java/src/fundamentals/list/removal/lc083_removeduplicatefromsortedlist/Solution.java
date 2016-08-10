package fundamentals.list.removal.lc083_removeduplicatefromsortedlist;

import fundamentals.list.ListNode;

/**
 * Given a sorted linked fundamentals.list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
public class Solution {

    // Version-1: Compare cur and cur.next without prev
    // Note both cur and cur.next could reach null
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    // Version-2: compare cur and prev
    // Note update of prev and cur
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }

        // Invariant: node prior to prev (inclusive) has no duplicates
        ListNode cur = head.next, prev = head;
        while (cur != null) {
            if (cur.val == prev.val) {
                prev.next = cur.next;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return head;
    }

    // My 1st: prev != null could avoid, see 2nd solution above
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode prev = null, cur = head;
        while (cur != null) {
            if (prev != null && prev.val == cur.val) {
                prev.next = cur.next;
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}
