package fundamentals.list.twopointers.lc141_linkedlistcycle;

import fundamentals.list.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 * Follow up: Can you solve it without using extra space?
 */
public class Solution {

    // My 3AC.
    // My 2nd
    // Error: it's ok to put behind, since they will encounter anyway if cycle exists
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) { // no need to check slow
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;          // must put here!
        }
        return false;
    }

    // Reverse causing TLE...
    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode last = head;
        ListNode cur = head.next;
        while (cur != null) {
            // Find cycle
            if (cur == head) {
                return true;
            }
            // Reverse
            ListNode next = cur.next;
            cur.next = last;
            // Move forward
            last = cur;
            cur = next;
        }
        return false;
    }

}
