package fundamentals.graph.bfs.toposort.lc269_aliendictionary;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you.
 * You receive a list of words from the dictionary, where words are sorted lexicographically
 * by the rules of this new language. Derive the order of letters in this language.
 * For example, Given the following words in dictionary,
 * [
 *  "wrt",
 *  "wrf",
 *  "er",
 *  "ett",
 *  "rftt"
 * ] The correct order is: "wertf".
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
    }

    public String alienOrder(String[] words) {
        Set<Character>[] adj = new Set[256];
        int[] indegree = new int[256];
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < Math.min(words[i].length(), words[i + 1].length()); j++) {
                char from = words[i].charAt(j), to = words[i + 1].charAt(j);
                if (from != to) {
                    if (adj[from] == null) adj[from] = new HashSet<>();
                    adj[from].add(to);
                    indegree[to]++;
                    break;  // error: only first different char is useful, so break!
                }
            }
        }

        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < adj.length; i++) {
            if (adj[i] != null && indegree[i] == 0) {
                q.offer((char) i);
                break;
            }
        }

        StringBuilder lexorder = new StringBuilder();
        while (!q.isEmpty()) {
            char from = q.poll();
            lexorder.append(from);
            if (adj[from] == null) continue;
            for (char to : adj[from])
                if (--indegree[to] == 0)
                    q.offer(to);
        }
        return lexorder.toString();
    }

}
