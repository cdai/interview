package buildingblock.sorting.merge.lc023_mergeksortedlists;

import fundamentals.list.ListNode;

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
