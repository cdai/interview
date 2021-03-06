package advanced.combinatorial.subset.lc089_graycode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit. Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 *  00 - 0
 *  01 - 1
 *  11 - 3
 *  10 - 2
 * Note: For a given n, a gray code sequence is not uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 */
public class Solution {

    // i=bit position, j=position in ret
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>(Arrays.asList(0));
        for (int i = 0; i < n; i++) {
            for (int j = ret.size() - 1; j >= 0; j--)
                ret.add(ret.get(j) | (1 << i));
        }
        return ret;
    }

    // My 2AC: DP incremental solution O(N) time.
    public List<Integer> grayCode2(int n) {
        LinkedList<Integer> result = new LinkedList<>();
        result.add(0);
        for (int i = 0; i < n; i++) {                           // Note that i means round not result size
            Iterator<Integer> it = result.descendingIterator(); // Descending to keep only 1 bit diff
            List<Integer> tmp = new ArrayList<>();
            while (it.hasNext()) {
                tmp.add(it.next() | (1 << i));
            }
            result.addAll(tmp);
        }
        return result;
    }

    // My 1AC
    public List<Integer> grayCode1(int n) {
        List<Integer> result = new ArrayList<>();
        BitSet path = new BitSet(n);
        grayCode(result, path, n, 0);
        return result;
    }

    private void grayCode(List<Integer> result, BitSet path, int n, int k) {
        if (n == 0) {
            if (path.isEmpty()) {
                result.add(0);
            } else {
                result.add((int) path.toLongArray()[0]);
            }
            return;
        }

        // (n-1)-bit is: 0,1 at first time (k==0)
        // (n-1)-bit is: 1,0 at second time (k==1)
        if (k == 0) {
            grayCode(result, path, n-1, 0);
            path.set(n-1);   // error1: bits starts from 0, but n starts from 1
            grayCode(result, path, n-1, 1);
        } else {
            path.set(n-1);
            grayCode(result, path, n-1, 0);
            path.clear(n-1);
            grayCode(result, path, n-1, 1);
        }

        path.clear(n-1);
    }

}
