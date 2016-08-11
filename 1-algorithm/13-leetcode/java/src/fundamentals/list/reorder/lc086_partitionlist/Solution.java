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

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        System.out.println(new Solution().partition(head, 3));
    }

    // Very concise and inspiring solution from leetcode discuss
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(0);
        ListNode largeHead = new ListNode(0);

        ListNode small = smallHead, large = largeHead;
        while (head != null) {
            if (head.val < x) {
                small = small.next = head;  // Nice!!!
            } else {
                large = large.next = head;
            }
            head = head.next;
        }
        small.next = largeHead.next;
        large.next = null;          // Note: use two new lists, don't wrong about cycle, only remember this one!
        return smallHead.next;
    }

    public ListNode partition2(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // nodes before [...,prev] are less than x
        // nodes between (prev, cur] are greater than x
        // nodes after [cur.next,...] are to be handled
        ListNode prev = dummy, cur = dummy;
        while (cur.next != null) {
            if (cur.next.val < x) {
                if (prev == cur) { // error: not work for edge case, eg.[dum,1,4],prev=cur=dum => dum->4 (1 is missing)
                    cur = cur.next;
                } else {
                    ListNode tmp = cur.next.next;
                    cur.next.next = prev.next;
                    prev.next = cur.next;
                    cur.next = tmp;
                }
                prev = prev.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    // My 1st
    public ListNode partition1(ListNode head, int x) {
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
