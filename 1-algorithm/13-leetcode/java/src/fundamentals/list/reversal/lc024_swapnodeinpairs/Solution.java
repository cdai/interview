package fundamentals.list.reversal.lc024_swapnodeinpairs;

import fundamentals.list.ListNode;

/**
 * Given a linked fundamentals.list, swap every two adjacent nodes and return its head.
 * For example, Given 1->2->3->4, you should return the fundamentals.list as 2->1->4->3.
 * Your algorithm should use only constant space.
 * You may not modify the values in the fundamentals.list, only nodes itself can be changed.
 *
 * Definition for singly-linked fundamentals.list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 */
public class Solution {

    // O(N): prev->first->second => prev->second->first, then move prev to first to maintain invariant
    // invariant: nodes behind prev (inclusive) are swapped already
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode dmy = new ListNode(0), prev = dmy;
        dmy.next = head;
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            prev.next = first.next;
            first.next = first.next.next;
            prev.next.next = first;
            prev = prev.next.next;
        }
        return dmy.next;
    }

    // My 2nd
    // O(N)
    public ListNode swapPairs2(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1.We need dummy header to operate head
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 2.Swap one by one (invariant: nodes behind prev (inclusive) are swapped already)
        // when prev reach end -> all nodes have been swapped
        ListNode prev = dummy;
        do {
            prev = swap(prev);
        } while (prev != null);

        return dummy.next;
    }

    // prev->first->second => prev->second->first, then move prev to first to maintain invariant
    private ListNode swap(ListNode prev) {
        if (prev.next == null || prev.next.next == null) {
            return null;
        }
        ListNode first = prev.next;
        ListNode second = first.next;

        prev.next = second;
        first.next = second.next;
        second.next = first;
        return first;
    }

    // My 1st
    public ListNode swapPairs1(ListNode head) {
        ListNode n1 = head, n2 = null, prev = null;
        while (n1 != null && n1.next != null) {
            n2 = n1.next;

            // 1.Maintain prev and save head if necessary
            if (prev != null) {
                prev.next = n2;
            } else {
                head = n2;
            }
            prev = n1;

            // 2.Swap pair
            n1.next = n2.next;
            n2.next = n1;

            n1 = n1.next;
        }
        return head;
    }
}
