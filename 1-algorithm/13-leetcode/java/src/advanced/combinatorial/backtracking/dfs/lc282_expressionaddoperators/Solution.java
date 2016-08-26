package advanced.combinatorial.backtracking.dfs.lc282_expressionaddoperators;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or * between the digits
 * so they evaluate to the target value.
 * Examples:
 *  "123", 6 -> ["1+2+3", "1*2*3"]
 *  "232", 8 -> ["2*3+2", "2+3*2"]
 *  "105", 5 -> ["1*0+5","10-5"]
 *  "00", 0 -> ["0+0", "0-0", "0*0"]
 *  "3456237490", 9191 -> []
 */
public class Solution {

    // My 2AC: T(N) = 3*T(N-1)
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        doAddOperators(result, "", num, target, 0, 0, 0);
        return result;
    }

    // Don't add op for first num, try it after the first round in front of second,third... number
    private void doAddOperators(List<String> result, String path,
                                String str, int target,
                                int start, long eval, long mul) {
        if (start == str.length()) {
            if (eval == target) result.add(path);
            return;
        }
        for (int i = start; i < str.length(); i++) {
            if (i > start && str.charAt(start) == '0') break;       // error2: prevent "105" -> "1" and "05"
            long num = Long.valueOf(str.substring(start, i + 1));
            if (start == 0) {                                       // error1: start=0 means first num, not i=1
                doAddOperators(result, String.valueOf(num), str, target, i + 1, num, num);
            } else {
                doAddOperators(result, path + "+" + num, str, target, i + 1, eval + num, num);
                doAddOperators(result, path + "-" + num, str, target, i + 1, eval - num, -num);                 // Recover eval
                doAddOperators(result, path + "*" + num, str, target, i + 1, eval - mul + mul * num, mul * num); // Very smart about the mul handle
            }
        }
    }

}
