package buildingblock.sorting.merge.lc148_sortlist;

import fundamentals.list.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class Solution {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        head.next.next = new ListNode(3);
        System.out.println(new Solution().sortList(head));
    }

    // Clean but use O(logN) stack space
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head; // Codes below can handle 1,2,...nodes, but this could improve performance

        ListNode mid = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }
        ListNode head2 = mid.next;
        mid.next = null;
        return merge(sortList(head), sortList(head2));
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dmy = new ListNode(0), prev = dmy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = (list1 != null) ? list1 : list2;
        return dmy.next;
    }


    // What's people called: "Bottom-up merge sort"
    public ListNode sortList_bottomup(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1.Get total length of list
        int length = 0;
        for (ListNode n = head; n != null; n = n.next) {
            length++;
        }

        // 2.Split and merge in increasing step (or block size whatever)
        ListNode dummy = new ListNode(0);
        for (int step = 1; step < length; step <<= 1) { // note: stop when step >= length, so merge(list/2, list/2) must complete!
            ListNode prev = dummy;
            ListNode cur = head;                        // error1: cur must start from head not dummy.next (null)
            while (cur != null) {
                ListNode half1 = cur;
                cur = split(cur, step);
                ListNode half2 = cur;
                cur = split(cur, step);
                prev = merge(prev, half1, half2);
            }
            head = dummy.next;
        }
        return dummy.next;
    }

    private ListNode split(ListNode node, int step) {
        while (step-- > 1 && node != null) {
            node = node.next;
        }
        if (node != null) {
            ListNode nextHead = node.next;              // error2: don't forget cut list
            node.next = null;
            return nextHead;
        }
        return null;
    }

    // Merge and append to prev, return tail finally
    private ListNode merge(ListNode prev, ListNode half1, ListNode half2) {
        while (half1 != null || half2 != null) {
            int val1 = (half1 != null) ? half1.val : Integer.MAX_VALUE;
            int val2 = (half2 != null) ? half2.val : Integer.MAX_VALUE;
            if (val1 < val2) {
                prev.next = half1;
                half1 = half1.next;
            } else {
                prev.next = half2;
                half2 = half2.next;
            }
            prev = prev.next;
        }
        return prev;
    }

    // Clear but use O(logN) stack space

    // My 1st: Simplify base case
    public ListNode sortList1(ListNode head) {
        // Only one node, just return
        if (head == null || head.next == null) {    // Optimze-0: simplify!!!
            return head;
        }
        // Now list has two nodes at least
        ListNode list1 = head;
        ListNode list2 = split(list1);
        return merge(sortList(list1), sortList(list2));
    }

    public ListNode sortList2(ListNode head) {
        // Only one node, just return
        if (head.next == null) {
            return head;
        }
        // Two nodes, swap if needed then return
        if (head.next.next == null) {
            if (head.val <= head.next.val) {
                return head;
            }
            head.next.next = head;
            head = head.next;
            head.next.next = null;
            return head;
        }
        // Now list has three nodes at least, which guarantee that list1 and list2 are different
        ListNode list1 = head;
        ListNode list2 = split(list1);
        return merge(sortList(list1), sortList(list2));
    }

    private ListNode split(ListNode list1) {
        ListNode slow = list1, fast = list1; // Optimze-1: both same to split from slow not previous, [1,2,3]->[1]-[2,3] or [1,2]-[3]
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode list2 = slow.next;     // Optimize-2: if only 2 nodes, then slow=fast=head. eg.[1,2]->[1]-[2]
        slow.next = null;
        return list2;
    }

    private ListNode merge1(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        for (ListNode cur = dummy; list1 != null || list2 != null; cur = cur.next) {    // Optimize-3: use "for" to simplify "while"
            int val1 = (list1 != null) ? list1.val : Integer.MAX_VALUE;                 // Optimize-4: max node is not necessary
            int val2 = (list2 != null) ? list2.val : Integer.MAX_VALUE;
            if (val1 < val2) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
        }
        return dummy.next;
    }

}
