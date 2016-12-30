package advanced.datastructure.game.lc293_flipgame;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.generatePossibleNextMoves("++++"));
    }

    public List<String> generatePossibleNextMoves(String s) {
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < s.length() - 1; i++) {
            if ("++".equals(s.substring(i, i + 2))) {
                char[] ch = s.toCharArray();
                ch[i] = ch[i + 1] = '-';
                ret.add(String.valueOf(ch));
            }
        }
        return ret;
    }

}
