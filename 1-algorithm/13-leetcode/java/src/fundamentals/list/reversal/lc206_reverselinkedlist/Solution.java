package fundamentals.list.reversal.lc206_reverselinkedlist;

import fundamentals.list.ListNode;

/**
 * Reverse a singly linked fundamentals.list.
 */
public class Solution {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        System.out.println(new Solution().reverseList(head));
    }

    // 4AC
    // pre cur->suc => pre<-cur suc...
    // invariant: [0...pre] is reversed
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode suc = cur.next;
            cur.next = pre;
            pre = cur;
            cur = suc;
        }
        return pre;
    }

    // 4AC tail recursion
    public ListNode reverse(ListNode head) {
        return rev(head, null);
    }

    private ListNode rev(ListNode cur, ListNode pre) {
        if (cur == null) return pre;
        ListNode suc = cur.next;
        cur.next = pre;
        return rev(suc, cur);
    }

    // My 3AC
    public ListNode reverseList3(ListNode node) {
        if (node == null || node.next == null) return node;

        ListNode next = node.next;
        ListNode head = reverseList(next);
        next.next = node;
        node.next = null;
        return head;
    }

    // My 2nd
    // Recursive version: reverseList(head) return new head
    public ListNode reverseList2_recursive(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode second = head.next;
        head.next = null;

        ListNode newHead = reverseList(second);
        second.next = head;
        return newHead;
    }

    // My 2nd
    // Iterative version
    public ListNode reverseList2(ListNode head) {
        if (head == null) return null;

        // Note: at last, dummy<->first causes dead lock...
        //ListNode dummy = new ListNode(0);
        //dummy.next = head;

        // prev->cur => prev<=cur, then move prev and cur off by one
        // invariant: nodes behind prev (inclusive) are reversed already
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }

    // My 1st
    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return head;
        }

        // Invariant: nodes before n0 are reversed
        //  e.g. 1<-2<-3<-n0--n1->...
        // Init to hold invariant true
        ListNode n0 = head;
        ListNode n1 = n0.next;
        n0.next = null;

        while (n1 != null) {
            // Now reverse n0->n1 to n0<-n1 to maintain invariant before moving n0
            ListNode tmp = n1.next;
            n1.next = n0;

            // Move forward
            n0 = n1;
            n1 = tmp;
        }
        return n0;
    }
}
