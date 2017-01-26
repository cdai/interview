package fundamentals.list.removal.lc082_removeduplicatefromsortedlist2;

import fundamentals.list.ListNode;

/**
 * Given a sorted linked fundamentals.list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original fundamentals.list.
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 */
public class Solution {

    // Very clear solution! Better than mine.
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dmy = new ListNode(0), pre = dmy;
        dmy.next = head;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            while (cur.next != null && pre.next.val == cur.next.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {  // only 1 distinct element
                pre = pre.next;
            } else {                // found duplicates, delete them all
                pre.next = cur.next;
            }
        }
        return dmy.next;
    }

    // Same as 2AC. a little messy too.
    public ListNode deleteDuplicates31(ListNode head) {
        ListNode dmy = new ListNode(0), pre = dmy;
        dmy.next = head;
        while (pre.next != null && pre.next.next != null) {
            if (pre.next.val == pre.next.next.val) {
                ListNode n = pre.next.next;
                while (n.next != null && n.next.val == pre.next.val) n = n.next;
                pre.next = n.next; // delete all node in middle, no need to move pre
            } else {
                pre = pre.next;
            }
        }
        return dmy.next;
    }

    // Solution from programcreek
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Invariant: nodes before prev (inclusive) have no duplicates
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            if (prev.next.val == prev.next.next.val) {
                int dup = prev.next.val;
                while (prev.next != null && prev.next.val == dup) { // delete all duplicates
                    prev.next = prev.next.next;
                }
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    // My 2nd: a little trivial in loop body
    public ListNode deleteDuplicates21(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Invariant: nodes before prev (inclusive) have no duplicates
        ListNode prev = dummy;
        while (prev != null && prev.next != null) {
            // Find next distinct value
            ListNode cur = prev.next;
            ListNode dup = cur.next;
            while (dup != null && cur.val == dup.val) {
                dup = dup.next;
            }

            // dup is a node or null (reach end)
            if (dup == cur.next) {  // no duplicates, delete none, just move forward
                prev = prev.next;
            } else {                // found duplicates, bypass all of them
                prev.next = dup;
            }
        }
        return dummy.next;
    }

    public ListNode deleteDuplicates1(ListNode head) {
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
