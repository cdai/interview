package advanced.scan.sequence.lc316_removeduplicateletters;

import java.util.Stack;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * Example:
 *  Given "bcabc", Return "abc"
 *  Given "cbacdcbc", Return "acdb"
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicateLetters("cbbba"));
    }

    // Intuitive idea: when you found a smaller char, try to remove previous char if they're duplicates
    // Given "bcabc", when you see a 'b', keep it and continue with the search, then keep the following 'c',
    // then we see an 'a'. Now we get a chance to get a smaller lexi order, you can check if after 'a', there is still 'b' and 'c' or not.
    // O(MN) time: M is size of alphabet, like O(26*N) for this problem.
    // pre: stack holding previous char
    // post: how many char left after cur position
    // dup: if char is already on stack (in result)
    public String removeDuplicateLetters(String s) {
        int[] post = new int[256];  // All lowercase, but for clarity
        for (char c : s.toCharArray()) post[c]++;

        boolean[] dup = new boolean[256];
        Stack<Character> pre = new Stack<>();
        for (char c : s.toCharArray()) {
            post[c]--;
            if (dup[c]) continue;
            while (!pre.isEmpty() && c < pre.peek() && post[pre.peek()] > 0)
                dup[pre.pop()] = false;
            pre.push(c);
            dup[c] = true;
        }

        StringBuilder ret = new StringBuilder();
        for (char c : pre) ret.append(c); // Nice! Java's Stack iterates from the bottom of the stack
        return ret.toString();
    }

}
