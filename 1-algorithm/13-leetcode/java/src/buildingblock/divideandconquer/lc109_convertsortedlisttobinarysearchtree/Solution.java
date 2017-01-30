package buildingblock.divideandconquer.lc109_convertsortedlisttobinarysearchtree;

import fundamentals.list.ListNode;
import fundamentals.tree.TreeNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class Solution {

    public TreeNode sortedListToBST(ListNode head) {
        return build(head, null);
    }

    private TreeNode build(ListNode head, ListNode tail) { /* [head,tail) exclusive */
        if (head == tail) return null;
        ListNode mid = head, fast = head;
        while (fast != tail && fast.next != tail) {
            mid = mid.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(mid.val);
        root.left = build(head, mid);
        root.right = build(mid.next, tail);
        return root;
    }

    // 3AC. Change list
    public TreeNode sortedListToBST3(ListNode head) {
        if (head == null) return null;
        ListNode pre = null, mid = head, fast = head;
        while (fast != null && fast.next != null) {
            pre = mid;
            mid = mid.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(mid.val);
        if (pre == null) return root;
        ListNode right = mid.next;
        pre.next = mid.next = null;

        root.left = sortedListToBST(head);
        root.right = sortedListToBST(right);
        return root;
    }

    // My 2nd: O(N) time, O(logN) space
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1.Find middle node and prev
        ListNode prev = null, mid = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            prev = mid;
            mid = mid.next;
            fast = fast.next.next;
        }

        // 2.Cut off and handle first half if exist
        TreeNode root = new TreeNode(mid.val);
        if (prev != null) {
            prev.next = null;
            root.left = sortedListToBST(head);
        }

        // 3.Cut off and handle second half
        ListNode half2 = mid.next;
        mid.next = null;
        root.right = sortedListToBST(half2);
        return root;
    }

    // My 1st
    public TreeNode sortedListToBST1(ListNode head) {
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
    public TreeNode sortedListToBST12(ListNode head) {
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
