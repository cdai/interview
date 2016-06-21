package fundamentals.list.lc025_swapnodeinkpairs;

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
    public ListNode reverseKGroup(ListNode head, int k) {
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
