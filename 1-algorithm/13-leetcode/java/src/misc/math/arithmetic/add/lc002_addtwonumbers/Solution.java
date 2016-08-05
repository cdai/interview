package misc.math.arithmetic.add.lc002_addtwonumbers;

import fundamentals.list.ListNode;

/**
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *  Output: 7 -> 0 -> 8
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode root = new ListNode(-1);
        ListNode prev = root;
        while (l1 != null || l2 != null) {
            prev.next = new ListNode(-1);
            int d1 = (l1 == null) ? 0 : l1.val;
            int d2 = (l2 == null) ? 0 : l2.val;
            int sum = d1 + d2 + carry;
            if (sum >= 10) {
                prev.next.val = sum % 10;
                carry = 1;
            } else {
                prev.next.val = sum;
                carry = 0;
            }
            prev = prev.next;
            l1 = (l1 == null) ? null : l1.next; // error: forget!
            l2 = (l2 == null) ? null : l2.next;
        }

        if (carry != 0) {
            prev.next = new ListNode(carry);
        }
        return root.next;
    }

}
