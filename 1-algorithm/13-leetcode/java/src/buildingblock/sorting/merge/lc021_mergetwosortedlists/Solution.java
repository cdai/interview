package buildingblock.sorting.merge.lc021_mergetwosortedlists;

import fundamentals.list.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
