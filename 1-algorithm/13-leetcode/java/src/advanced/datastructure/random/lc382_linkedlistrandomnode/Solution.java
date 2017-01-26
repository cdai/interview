package advanced.datastructure.random.lc382_linkedlistrandomnode;

import fundamentals.list.ListNode;

import java.util.Random;

/**
 * Use Reservoir Sampling to get K random number from unknown length of number sequence.
 * Suppose for k+i-1 iteration, the probability that it is in the reservoir is k/(k+i-1)
 * Each number for k+i iteration is picked with probability:
 *    P(X was in the reservoir last time) * P(X is not replaced this time)
 *    = P(X was in the reservoir last time) * (1- P(X is replaced this time))
 *    = k/(k+i-1) * (1 - k/(k+i) * 1/k)
 *    = k/(k+i)
 * When k+i reaches n, the probability of each number staying in the reservoir is k/n
 */
public class Solution {

    private ListNode head;

    private Random rand;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        this.rand = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode node = head, target = head;
        for (int i = 1; node != null; i++) {
            if (rand.nextInt(i) == 0) target = node;
            node = node.next;
        }
        return target.val;
    }

}
