package fundamentals.list.removal.lc237_deletenodeinalinkedlist;

import fundamentals.list.ListNode;

/**
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
 * the linked list should become 1 -> 2 -> 4 after calling your function.
 */
public class Solution {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        new Solution().deleteNode(n2);

        for (ListNode n = n1; n != null; n = n.next) {
            System.out.print(n.val);
        }
        System.out.println();
    }

    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public void deleteNode2(ListNode node) {
        if (node == null || node.next == null) { // node is not null or tail
            return;
        }

        ListNode prev = null;
        // Invariant: prev is previous of node, node.val = next value
        while (node.next != null) {
            node.val = node.next.val;
            prev = node;
            node = node.next;
        }
        prev.next = null;
    }

}
