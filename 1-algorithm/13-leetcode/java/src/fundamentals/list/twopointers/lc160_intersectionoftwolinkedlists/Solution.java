package fundamentals.list.twopointers.lc160_intersectionoftwolinkedlists;

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
        if (headA == null || headB == null) return null;
        int lenA = 0, lenB = 0;
        for (ListNode n = headA; n != null; n = n.next) lenA++;
        for (ListNode n = headB; n != null; n = n.next) lenB++;

        // Align list A and B
        ListNode nodeA = headA, nodeB = headB;
        for (; lenA > lenB; lenA--) nodeA = nodeA.next;
        for (; lenA < lenB; lenB--) nodeB = nodeB.next;

        while (nodeA != nodeB) {// check first for eg.1->2,1->3
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return nodeA;
    }

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = (nodeA == null) ? headB : nodeA.next;
            nodeB = (nodeB == null) ? headA : nodeB.next;
        }
        return nodeA;
    }

    // O(N) time + O(1) space
    // 1.Get the length of the two lists.
    // 2.Align them to the same start point.
    // 3.Move them together until finding the intersection point, or the end null
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);
        for (; lenA > lenB; lenA--) headA = headA.next;
        for (; lenA < lenB; lenB--) headB = headB.next;
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    // Very smart solution from leetcode discuss!
    // O( lcm (m, n)). worst case is O( m*n)
    // Think A: 1->2->3; B:4->5->6->3
    public ListNode getIntersectionNode_crazy(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // Suppose lenA= a + c, lenB = b + c, lenB > lenA.
        // Explain: After switching, ListNode a move on list B by (b + c - a - c) before b move onto list A.
        //          At that time, a and b move towards the end together with same position!
        // Note: It'll end up with a=b!=null or a=b=null whatever. It's also correct if lenA=lenB, loop terminates before switch.
        ListNode a = headA, b = headB;
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }
        return a;
    }

    // O(N) time, O(1) space
    public ListNode getIntersectionNode21(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = length(headA);
        int lenB = length(headB);

        // Move on longer list to make it same length with shorter one
        ListNode shorter = (lenA < lenB) ? headA : headB;
        ListNode longer = (shorter == headB) ? headA : headB;
        for (int i = 0; i < Math.abs(lenA - lenB); i++) {
            longer = longer.next;
        }

        // Now we can move together
        for (int i = 0; i < Math.min(lenA, lenB); i++) {
            if (shorter == longer) {
                return shorter;
            }
            shorter = shorter.next;
            longer = longer.next;
        }
        return null;
    }

    private int length(ListNode node) {
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }
        return len;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
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
