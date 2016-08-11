package fundamentals.list.reorder.lc147_insertionsortlist;

import fundamentals.list.ListNode;

/**
 * Sort a linked list using insertion sort.
 */
public class Solution {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        n1.next = new ListNode(5);
        n1.next.next = new ListNode(4);
        n1.next.next.next = new ListNode(1);
        new Solution().insertionSortList(n1);
    }

    // Solution from leetcode discuss
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        //dummy.next = head;        // Cause cycle. Consider dummy as another linked list

        // Invariant: nodes before cur is sorted
        ListNode cur = head;
        while (cur != null) {
            ListNode pos = dummy;
            while (pos.next != null && pos.next.val <= cur.val) {
                pos = pos.next;
            }
            ListNode tmp = cur.next;
            cur.next = pos.next;
            pos.next = cur;
            cur = tmp;
        }
        return dummy.next;
    }

    // My 1st
    public ListNode insertionSortList1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = head;
        while (cur.next != null) {
            // Find where to insert cur.next, or stop at cur
            ListNode pos = dummy;
            while (pos.next.val < cur.next.val) {
                pos = pos.next;
            }
            //  pos(a),pos.next(b),...cur(c),cur.next(d),cur.next.next(e)
            //  => a,d,b,...,c,e
            if (pos != cur) {
                ListNode tmp = pos.next;
                pos.next = cur.next;
                cur.next = cur.next.next;
                pos.next.next = tmp;
            } else {
                cur = cur.next; // error1: cur.next is updated already above, but it must update here!
            }
        }
        return dummy.next;
    }

    private void print(ListNode list) {
        do {
            System.out.print(list.val + "->");
        } while ((list = list.next) != null);
        System.out.println();
    }

}
