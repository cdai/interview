package lintcode.list;

import fundamentals.list.ListNode;

/**
 */
public class Solution {

    // 166-Nth to Last Node in List
    // Think it easy: when p has moved n steps,
    //  nth (nth=nth.next in if) move along with p (p=p.next in for header)
    //i=0    1    2    3
    //  3 -> 2 -> 1 -> 5 -> null, n=2
    //nth/p  |    |    |     |  (view at beginning of each iteration)
    //nth    p    |    |     |
    //nth         p    |     |
    //      nth        p     |
    //           nth         p
    ListNode nthToLast(ListNode head, int n) {
        ListNode nth = head;
        int i = 0; /* steps that p moves forward (at loop beginning) */
        for (ListNode p = head; p != null; p = p.next) {
            if (i++ >= n) {
                nth = nth.next;
            }
        }
        return nth;
    }

    // 511-Swap Two Nodes in Linked List
    // Note many edge cases: v1 or v2 not found; v2 is before v1; v1 is predecessor of v2; v1 is head
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for (ListNode n = dmy, p1 = null; n.next != null; n = n.next) {
            if (n.next.val == v1 || n.next.val == v2) {
                if (p1 == null) {
                    p1 = n;
                } else {
                    ListNode p2 = n, n1 = p1.next, n2 = p2.next, suc1 = n1.next;
                    p1.next = n2;
                    if (n1 == p2) {
                        n1.next = n2.next;
                        n2.next = n1;
                    } else {
                        p2.next = n1;
                        n1.next = n2.next;
                        n2.next = suc1;
                    }
                    return dmy.next;
                }
            }
        }
        return head;
    }

    public ListNode swapNodes_my(ListNode head, int v1, int v2) {
        ListNode dmy = new ListNode(0), pre1 = null, pre2 = null;
        dmy.next = head;
        for (ListNode n = dmy; n.next != null; n = n.next) {
            if (n.next.val == v1) {
                pre1 = n;
            } else if (n.next.val == v2) {
                pre2 = n;
            }
        }

        // If cannot find both, do nothing!
        if (pre1 == null || pre2 == null) {
            return head;
        }

        // Note edge case that n1 and n2 is neighbor
        ListNode n1 = pre1.next, suc1 = n1.next, n2 = pre2.next;
        if (n1 == pre2) {
            pre1.next = n2;
            n1.next = n2.next;
            n2.next = n1;
        } else if (n2 == pre1) {
            pre2.next = n1;
            n2.next = n1.next;
            n1.next = n2;
        } else {
            pre1.next = n2;
            pre2.next = n1;
            n1.next = n2.next;
            n2.next = suc1;
        }
        return dmy.next;
    }

}
