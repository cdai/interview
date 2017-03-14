package advanced.scan.sequence.lc496_nextgreaterelement;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 */
public class Solution {

    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer,Integer> nge = new HashMap<>();
        Stack<Integer> s = new Stack<>();
        for (int num : nums) {
            while (!s.isEmpty() && s.peek() < num) {
                nge.put(s.pop(), num);
            }
            s.push(num);
        }

        int[] ret = new int[findNums.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = nge.getOrDefault(findNums[i], -1);
        }
        return ret;
    }

}
