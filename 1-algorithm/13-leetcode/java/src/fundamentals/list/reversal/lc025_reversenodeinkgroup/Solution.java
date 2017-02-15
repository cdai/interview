package fundamentals.list.reversal.lc025_reversenodeinkgroup;

import fundamentals.list.ListNode;

/**
 * Given a linked fundamentals.list, reverse the nodes of a linked fundamentals.list k at a time and return its modified fundamentals.list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example, Given this linked fundamentals.list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Definition for singly-linked fundamentals.list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {

    // 4AC
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;

        ListNode dmy = new ListNode(0), pre = dmy;
        dmy.next = head;
        for (int i = 0; i < n / k; i++) { // pre->...->cur->suc => pre->suc->...->cur
            ListNode cur = pre.next;
            for (int j = 1; j < k; j++) { // reverse k-1 times in head-insert way
                ListNode suc = cur.next;
                cur.next = suc.next;
                suc.next = pre.next;
                pre.next = suc;
            }
            pre = cur; // cur is the tail at last
        }
        return dmy.next;
    }

    // 3AC
    public ListNode reverseKGroup3(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; i = i.next) n++;

        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for (ListNode prev = dmy; n >= k; n -= k) {
            ListNode cur = prev.next;
            for (int i = 1; i < k; i++) { // note only k-1 times
                ListNode then = cur.next;
                cur.next = then.next;
                then.next = prev.next;
                prev.next = then;
            }
            prev = cur;
        }
        return dmy.next;
    }

    // My 2nd
    public ListNode reverseKGroup_utilmethod(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        int len = 0;
        for (ListNode n = head; n != null; n = n.next) {
            len++;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        for (int i = 0; i < len / k; i++) {
            prev = reverseK(prev, k);
        }
        return dummy.next;
    }

    // Perform K-1 reversals for K group
    private ListNode reverseK(ListNode prev, int k) {
        ListNode cur = prev.next;
        if (cur != null) {      // when len is divisible by k, no node left in last batch
            while (k-- > 1) {
                ListNode then = cur.next;
                cur.next = then.next;
                then.next = prev.next;
                prev.next = then;
            }
        }
        return cur;
    }


    // My 1st
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (k < 2) {
            return head;
        }

        ListNode n1 = head, prev = null;
        while (n1 != null) {
            // 1.Check if k-th node exist
            ListNode nk = n1;
            int i;
            for (i = 1; i < k && (nk.next != null); i++) { //error3: forget i
                nk = nk.next;
            }
            if (i < k) {
                break;
            }

            // 2.Maintain prev and update head if necessary
            if (prev != null) {
                prev.next = nk;
            } else {
                head = nk;
            }
            prev = n1;

            // 3.Swap pair in k group
            ListNode n2 = n1.next; // error2: init twice in body
            while (n1 != nk) {
                ListNode n3 = n2.next;
                n2.next = n1;
                n1 = n2;
                n2 = n3;
            }
            prev.next = n2;
            n1 = n2; // error1: forget update n1 for outer loop
        }
        return head;
    }
}
