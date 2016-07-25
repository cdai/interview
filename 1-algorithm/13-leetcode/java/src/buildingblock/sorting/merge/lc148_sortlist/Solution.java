package buildingblock.sorting.merge.lc148_sortlist;

import fundamentals.list.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class Solution {

    // Simplify base case
    public ListNode sortList(ListNode head) {
        // Only one node, just return
        if (head == null || head.next == null) {    // Optimze-0: simplify!!!
            return head;
        }
        // Now list has two nodes at least
        ListNode list1 = head;
        ListNode list2 = split(list1);
        return merge(sortList(list1), sortList(list2));
    }

    public ListNode sortList2(ListNode head) {
        // Only one node, just return
        if (head.next == null) {
            return head;
        }
        // Two nodes, swap if needed then return
        if (head.next.next == null) {
            if (head.val <= head.next.val) {
                return head;
            }
            head.next.next = head;
            head = head.next;
            head.next.next = null;
            return head;
        }
        // Now list has three nodes at least, which guarantee that list1 and list2 are different
        ListNode list1 = head;
        ListNode list2 = split(list1);
        return merge(sortList(list1), sortList(list2));
    }

    private ListNode split(ListNode list1) {
        ListNode slow = list1, fast = list1; // Optimze-1: both same to split from slow not previous, [1,2,3]->[1]-[2,3] or [1,2]-[3]
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode list2 = slow.next;     // Optimize-2: if only 2 nodes, then slow=fast=head. eg.[1,2]->[1]-[2]
        slow.next = null;
        return list2;
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        for (ListNode cur = dummy; list1 != null || list2 != null; cur = cur.next) {    // Optimize-3: use "for" to simplify "while"
            int val1 = (list1 != null) ? list1.val : Integer.MAX_VALUE;                 // Optimize-4: max node is not necessary
            int val2 = (list2 != null) ? list2.val : Integer.MAX_VALUE;
            if (val1 < val2) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
        }
        return dummy.next;
    }

}
