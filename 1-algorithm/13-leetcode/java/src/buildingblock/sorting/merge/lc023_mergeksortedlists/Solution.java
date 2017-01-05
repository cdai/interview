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

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode n : lists)
            if (n != null) q.offer(n);

        ListNode dmy = new ListNode(0), pre = dmy;
        while (!q.isEmpty()) {
            ListNode n = q.poll();
            pre = pre.next = n;
            if (n.next != null)
                q.offer(n.next);
        }
        return dmy.next;
    }

    // My 3AC
    public ListNode mergeKLists32(ListNode[] lists) {
        return doMergeK(new LinkedList<>(Arrays.asList(lists)));
    }

    private ListNode doMergeK(List<ListNode> lists) {
        if (lists.isEmpty()) return null;
        if (lists.size() == 1) return lists.get(0);
        if (lists.size() == 2) return doMergeTwo(lists.get(0), lists.get(1));
        return doMergeTwo(doMergeK(lists.subList(0, lists.size() / 2)),
                doMergeK(lists.subList(lists.size() / 2, lists.size())));
    }

    private ListNode doMergeTwo(ListNode list1, ListNode list2) {
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

    // Recursive solution, too many recursions causing StackOverflow
    public ListNode mergeKLists_recursive(ListNode[] lists) {
        // Deal with null or single list
        if (lists.length < 2) return lists.length == 0 ? null : lists[0];

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
        for (ListNode list : lists)
            if (list != null)
                heap.offer(list);

        // Compare K min nodes
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            prev.next = node;
            prev = prev.next;
            // Fetch one more from just polled node
            if (node.next != null)
                heap.offer(node.next);
        }
        return dummy.next;
    }

    // My 3AC: O(NlogK) time + O(K) space
    public ListNode mergeKLists3(ListNode[] lists) {
        Queue<ListNode> h = new PriorityQueue<>(
                (n1, n2) -> Integer.compare(n1.val, n2.val));

        // Intialize heap with K nodes
        for (ListNode n : lists)
            if (n != null) h.offer(n);

        // Compare K min nodes
        ListNode dmy = new ListNode(0), prev = dmy;
        while (!h.isEmpty()) {
            ListNode n = h.poll();
            prev = prev.next = n;
            // Fetch one more from just polled node
            if (n.next != null)
                h.offer(n.next);
        }
        return dmy.next;
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
