package misc.math.arithmetic.mod.lc142_linkedlistcycle2;

import fundamentals.list.ListNode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Note: Do not modify the linked list.
 * Follow up: Can you solve it without using extra space?
 */
public class Solution {

    // Suppose linked list is like: a->b->...(x)->c->d->...(y)->c
    //  t is encountering time, k is encountering point, then when meeting:
    //      k = (t - x) % y, k = (2t - x) % y
    //  =>  ny + k = t - x, my + k = 2t - x
    //  =>  my + k = 2(ny + k + x) - x  (note: cannot cancel out k or x, only possibly find relation between k and x)
    //  =>  k + x = (m - 2n)y
    //  =>  (k + x) % y = 0
    // That means when meeting, moving forward x steps will back to starting point of cycle!
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                ListNode slow2 = head;
                while (slow != slow2) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }

}
