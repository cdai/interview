package fundamentals.list.removal.lc203_removelinkedlistelements;

import fundamentals.list.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 * Example:
 *  Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 *  Return: 1 --> 2 --> 3 --> 4 --> 5
 */
public class Solution {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next; // prev remains, so it cannot be null
            } else {
                prev = prev.next;           // prev.next is non-null, so prev cannot be null
            }
        }
        return dummy.next;
    }

    // My 1st
    public ListNode removeElements1(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {    // error: move only when next node is not deleted, otherwise NPE...
                prev = prev.next;
            }
        }
        return dummy.next;
    }

}
