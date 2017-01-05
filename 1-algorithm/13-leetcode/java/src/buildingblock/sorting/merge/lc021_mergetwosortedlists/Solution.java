package buildingblock.sorting.merge.lc021_mergetwosortedlists;

import fundamentals.list.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class Solution {

    // 3AC
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        ListNode dmy = new ListNode(0), p = dmy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p = p.next = l1;
                l1 = l1.next;
            } else {
                p = p.next = l2;
                l2 = l2.next;
            }
        }
        p.next = (l1 != null) ? l1 : l2;
        return dmy.next;
    }

    // Recursive solution
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return l1 != null ? l1 : l2;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // Jump out of loop if one list reach end, so more efficient
    public ListNode mergeTwoLists_iterative2(ListNode l1, ListNode l2) {
        ListNode dmy = new ListNode(0), prev = dmy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = (l1 != null) ? l1 : l2;
        return dmy.next;
    }

    // My 2nd: common iterative solution
    public ListNode mergeTwoLists_iterative(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        while (l1 != null || l2 != null) {
            int val1 = (l1 != null) ? l1.val : Integer.MAX_VALUE;
            int val2 = (l2 != null) ? l2.val : Integer.MAX_VALUE;
            if (val1 < val2) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        return dummy.next;
    }

    // My 1st
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode first = new ListNode(-1);
        ListNode prev = first;
        while (l1 != null || l2 != null) {
            int d1 = (l1 == null) ? Integer.MAX_VALUE : l1.val;
            int d2 = (l2 == null) ? Integer.MAX_VALUE : l2.val;

            if (d1 < d2) {     // error: l1, l2 would not be moved together!
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        return first.next;
    }

}
