package fundamentals.list.reversal.lc234_palindromelinkedlist;

import fundamentals.list.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class Solution {

    public static void main(String[] args) {
        ListNode head = new ListNode(-16557);
        head.next = new ListNode(-16557);
        System.out.println(new Solution().isPalindrome(head));
    }

    // My 2nd
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 1.Find middle node
        ListNode mid = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }

        // 2.Reverse second half
        ListNode prev = mid, cur = mid.next;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        mid.next = null;

        // 3.Compare
        ListNode from = head, to = prev;
        while (from != null && to != null) { // different from 1st, but it's ok
            if (from.val != to.val) {
                return false;
            }
            from = from.next;
            to = to.next;
        }
        return true;
    }

    // My 1st
    public boolean isPalindrome1(ListNode head) {
        if (head == null) {
            return true;
        }

        // 1.Find middle node
        ListNode mid = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }

        // 2.Reverse second half
        ListNode prev = mid, cur = mid.next;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }

        // 3.Check palindrome
        for (ListNode left = head, right = prev; right != mid; left = left.next, right = right.next) { // error: dead loop if only check left!=right
            if (left.val != right.val) {
                return false;
            }
        }
        return true;
    }

    // O(N) space
    public boolean isPalindrome2(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            vals.add(cur.val);
        }

        for (int i = 0, j = vals.size() - 1; i < j; i++, j--) {
            if (!vals.get(i).equals(vals.get(j))) { // error: != is wrong, use equals...
                return false;
            }
        }
        return true;
    }

}
