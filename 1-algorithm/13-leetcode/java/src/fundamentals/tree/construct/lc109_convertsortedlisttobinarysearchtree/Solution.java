package fundamentals.tree.construct.lc109_convertsortedlisttobinarysearchtree;

import fundamentals.list.ListNode;
import fundamentals.tree.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class Solution {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1.Find middle node: head != null (list has at least 1 node)
        ListNode prev = null, mid = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            prev = (prev == null) ? head : prev.next;
            mid = mid.next;
            fast = fast.next.next;
        }

        // 2.Split list into two parts: prev=null,mid=head or prev,mid is approriate
        ListNode list1 = (prev == null) ? null : head;
        ListNode list2 = mid.next;
        if (prev != null) {
            prev.next = null;
        }
        mid.next = null;

        // 3.Construct recursively: mid is not null, but list1 and list2 could be
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(list1);
        root.right = sortedListToBST(list2);
        return root;
    }

    // Correct but different from judge. eg.[1,2] -> me [2,1], judge [1,null,2]
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1.Find middle node: head != null (list has at least 1 node)
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = (slow == fast) ? slow : slow.next; // wait one step, so slow.next is middle when terminating
            fast = fast.next.next;
        }

        // 2.Split list into two parts: slow is head (1 or 2 nodes) or slow.next = middle node
        ListNode mid = (slow.next == null) ? slow : slow.next;
        ListNode list1 = (head == mid) ? null : head;
        ListNode list2 = mid.next;
        slow.next = null;
        mid.next = null;

        // 3.Construct recursively: mid is not null, but list1 and list2 could be
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST2(list1);
        root.right = sortedListToBST2(list2);
        return root;
    }

}
