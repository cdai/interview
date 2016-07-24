package fundamentals.list.removal.lc083_removeduplicatefromsortedlist;

import fundamentals.list.ListNode;

/**
 * Given a sorted linked fundamentals.list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
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