package miscellaneous.math.arithmetic.lc369_plusonelinkedlist;

import fundamentals.list.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class Solution {

    @Test
    void testZero() {
        Assertions.assertEquals(1, toNum(plusOne(toList(0))));
    }

    @Test
    void testNormal() {
        Assertions.assertEquals(124, toNum(plusOne(toList(123))));
    }

    @Test
    void testCarry() {
        Assertions.assertEquals(160, toNum(plusOne(toList(159))));
        Assertions.assertEquals(1000, toNum(plusOne(toList(999))));
    }

    // Iterative solution, find last non 9, then set 0 to all following numbers.
    public ListNode plusOne(ListNode head) {
        ListNode dmy = new ListNode(0), lastNot9 = dmy;
        dmy.next = head;
        for (ListNode n = head; n != null; n = n.next) {
            if (n.val != 9) lastNot9 = n; /* invariant: [lastNot9.next, end] all 9*/
        }
        lastNot9.val++;
        for (ListNode n = lastNot9.next; n != null; n = n.next) {
            n.val = 0;
        }
        return dmy.val == 1 ? dmy : head;
    }

    public ListNode plusOne_dfs(ListNode head) {
        if (dfs(head) > 0) {
            ListNode oldhead = head;
            head = new ListNode(1);
            head.next = oldhead;
        }
        return head;
    }

    private int dfs(ListNode head) {
        if (head == null) return 1;
        if (dfs(head.next) > 0) {
            int sum = 1 + head.val;
            head.val = sum % 10;
            return sum / 10;
        }
        return 0;
    }


    private ListNode toList(int num) {
        if (num == 0) return new ListNode(0);
        ListNode dmy = new ListNode(0);
        for (; num > 0; num /= 10) {
            ListNode node = new ListNode(num % 10);
            node.next = dmy.next;
            dmy.next = node;
        }
        return dmy.next;
    }

    private int toNum(ListNode head) {
        int num = 0;
        for (; head != null; head = head.next) {
            num = num * 10 + head.val;
        }
        return num;
    }

}
