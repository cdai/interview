package advanced.combinatorial.permutation.lc401_binarywatch;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Solution {

    public List<String> readBinaryWatch(int num) {
        List<String> ret = new ArrayList<>();
        dfs(ret, 0, num, 0);
        return ret;
    }

    private void dfs(List<String> ret, int path, int num, int k) {
        int hr = (path & 0xF), min = (path & 0xFF0) >> 4;
        if (hr > 11 || min > 59) return;
        if (num == 0) {
            ret.add(hr + ":" + (min < 10 ? "0": "") + min);
        } else {
            for (int i = k; i < 10; i++) {
                dfs(ret, (path | (1 << i)), num - 1, i + 1);
            }
        }
    }

}
