package miscellaneous.math.arithmetic.lc445_addtwonumbers2;

import fundamentals.list.ListNode;

import java.util.Stack;

/**
 *
 */
public class Solution {

    // Generate reversed list directly
    // nil,nil  1,nil  1,2  1,9  1,9->9  1->2,3->4->5
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>();
        for (; l1 != null; l1 = l1.next) s1.push(l1.val);
        for (; l2 != null; l2 = l2.next) s2.push(l2.val);

        ListNode pre = null;
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int v1 = s1.isEmpty() ? 0 : s1.pop();
            int v2 = s2.isEmpty() ? 0 : s2.pop();
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            ListNode cur = new ListNode(sum % 10);
            cur.next = pre;
            pre = cur;
        }
        return pre;
    }

    public ListNode addTwoNumbers_my(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>();
        for (; l1 != null; l1 = l1.next) s1.push(l1.val);
        for (; l2 != null; l2 = l2.next) s2.push(l2.val);

        ListNode dmy = new ListNode(0), pre = dmy;
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {
            int v1 = s1.isEmpty() ? 0 : s1.pop();
            int v2 = s2.isEmpty() ? 0 : s2.pop();
            int sum = v1 + v2 + carry;
            pre = pre.next = new ListNode(sum % 10);
            carry = sum / 10;
        }

        pre = null;
        ListNode cur = dmy.next;
        while (cur != null) {
            ListNode suc = cur.next;
            cur.next = pre;
            pre = cur;
            cur = suc;
        }
        return pre;
    }

}
