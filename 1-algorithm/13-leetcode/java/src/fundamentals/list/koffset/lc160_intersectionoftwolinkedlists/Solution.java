package fundamentals.list.koffset.lc160_intersectionoftwolinkedlists;

import fundamentals.list.ListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 *  A:          a1 → a2
 *                     ↘
 *                      c1 → c2 → c3
 *                     ↗
 *  B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * Notes:
 *  1.If the two linked lists have no intersection at all, return null.
 *  2.The linked lists must retain their original structure after the function returns.
 *  3.You may assume there are no cycles anywhere in the entire linked structure.
 *  4.Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class Solution {

    public static void main(String[] args) {
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = new ListNode(5);
        ListNode headB = new ListNode(10);
        headB.next = new ListNode(11);
        headB.next.next = headA.next.next.next;

        System.out.println(new Solution().getIntersectionNode(headA, headB).val);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null; // return null if no intersection
        }

        int lenA = 1;
        for (ListNode nodeA = headA; ((nodeA = nodeA.next) != null); lenA++);

        int lenB = 1;
        for (ListNode nodeB = headB; ((nodeB = nodeB.next) != null); lenB++);

        // slow waits diff time to start
        int diff = Math.abs(lenA - lenB);
        ListNode fast = (lenA > lenB) ? headA : headB;
        ListNode slow = (fast == headB) ? headA : headB;  // error2: lenA<lenB,lenA>lenB both false for lenA=lenB=1
        for (int i = 0; (fast != slow); i++) {  // both fast and slow are null if no intersection, so no dead loop
            if (i >= diff) {
                slow = slow.next;
            }
            fast = fast.next;
        }
        return fast;
    }

}
