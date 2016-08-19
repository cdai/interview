package miscellaneous.math.arithmetic.add.lc002_addtwonumbers;

import fundamentals.list.ListNode;

/**
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *  Output: 7 -> 0 -> 8
 */
public class Solution {

    // Elegant solution from leetcode discuss
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), prev = head;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) { // Nice! Safe since there's l1 and l2 null check
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            int sum = val1 + val2 + carry;
            carry = sum / 10;                           // Great! So no need to keep reset to 0 in mind now
            prev = prev.next = new ListNode(sum % 10);
            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }
        return head.next;
    }

    // My AC2: too ugly...
    public ListNode addTwoNumbers_ugly(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), prev = dummy;
        int carry = 0;          // error1: forget carry
        while (l1 != null || l2 != null) {
            int val1 = 0, val2 = 0;
            if (l1 != null) {
                val1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val2 = l2.val;
                l2 = l2.next;
            }
            int sum = val1 + val2 + carry;
            if (sum >= 10) {
                carry = 1;
                sum %= 10;
            } else {            // error2: forget clear
                carry = 0;
            }
            prev.next = new ListNode(sum);
            prev = prev.next;
        }
        if (carry == 1) {       // error3: forget last one
            prev.next = new ListNode(1);
            prev = prev.next;
        }
        return dummy.next;
    }

    // My 1AC: looks not bad
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
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
