package fundamentals.list.reorder.lc086_partitionlist;

import fundamentals.list.ListNode;

/**
 * Given a linked fundamentals.list and a value x, partition it such that
 * all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 *  Given 1->4->3->2->5->2 and x = 3,
 *  return 1->2->2->4->3->5.
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }

        ListNode n0p = new ListNode(-1);
        n0p.next = head;
        head = n0p; //error1: update head

        ListNode nip = n0p;
        while (nip.next != null) {
            // Maintain invariant:
            //  val of all nodes [-,n0) < x
            //  val of all nodes [n0,ni) >= x
            // For insertion, we use n0p(prev of n0) and nip
            if (nip.next.val < x) { //error2: nip.next not nip
                if (nip != n0p) {
                    ListNode tmp = nip.next;
                    nip.next = tmp.next;
                    tmp.next = n0p.next;
                    n0p.next = tmp;
                } else { //error3: don't move nip if tmp is moved away
                    nip = nip.next;
                }
                n0p = n0p.next;
            } else {
                nip = nip.next;
            }
        }
        return head.next;
    }
}
