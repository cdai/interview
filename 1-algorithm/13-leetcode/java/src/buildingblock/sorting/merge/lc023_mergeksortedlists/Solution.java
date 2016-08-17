package buildingblock.sorting.merge.lc023_mergeksortedlists;

import fundamentals.list.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 */
public class Solution {

    // Recursive solution, too many recursions causing StackOverflow
    public ListNode mergeKLists(ListNode[] lists) {
        // Deal with null or single list
        if (lists.length < 2) {
            return lists.length == 0 ? null : lists[0];
        }
        // Reuse merge two lists code
        if (lists.length == 2) {
            if (lists[0] == null || lists[1] == null) {
                return lists[0] != null ? lists[0] : lists[1];
            }
            if (lists[0].val < lists[1].val) {
                lists[0].next = mergeKLists(new ListNode[] { lists[0].next, lists[1] });
                return lists[0];
            } else {
                lists[1].next = mergeKLists(new ListNode[] { lists[0], lists[1].next });
                return lists[1];
            }
        }
        // Divide list array and handle recursively
        return mergeKLists(new ListNode[] {
                mergeKLists(Arrays.copyOfRange(lists, 0, lists.length / 2)),
                mergeKLists(Arrays.copyOfRange(lists, lists.length / 2, lists.length))
        });
    }

    // My 2nd: use heap. O(NlogK)
    // It's totally wrong if you deal with K then fetch next K nodes
    public ListNode mergeKLists_heap(ListNode[] lists) {
        Queue<ListNode> heap = new PriorityQueue<>(
                (n1, n2) -> Integer.compare(n1.val, n2.val));
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        // Intialize heap with K nodes
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }

        // Compare K min nodes
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            prev.next = node;
            prev = prev.next;
            // Fetch one more from just polled node
            if (node.next != null) {
                heap.offer(node.next);
            }
        }
        return dummy.next;
    }

    // My 1st
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        for (ListNode l : lists) {
            if (l != null) {
                queue.offer(l);
            }
        }

        ListNode first = new ListNode(-1);
        ListNode prev = first;
        while (!queue.isEmpty()) {
            ListNode min = queue.poll();
            prev.next = min;
            prev = prev.next;
            if (min.next != null) {
                queue.offer(min.next);
            }
        }
        return first.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        List<ListNode> klists = new LinkedList<>();
        for (ListNode l : lists) {
            if (l != null) {
                klists.add(l);
            }
        }

        ListNode first = new ListNode(-1);
        ListNode prev = first;
        while (!klists.isEmpty()) {
            // 1.Find min list node: TOO SLOW!!!
            ListNode min = null;
            for (Iterator<ListNode> it = klists.iterator(); it.hasNext(); ) {
                ListNode l = it.next();
                if (min == null || min.val > l.val) {
                    min = l;
                }
            }
            // 2.Merge to new list
            prev.next = min;
            prev = prev.next;
            if (min.next == null) {
                klists.remove(min);
            } else {
                klists.set(klists.indexOf(min), min.next);
            }
        }
        return first.next;
    }

}
