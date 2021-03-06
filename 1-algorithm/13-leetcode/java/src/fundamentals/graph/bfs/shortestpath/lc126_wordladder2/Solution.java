package fundamentals.graph.bfs.shortestpath.lc126_wordladder2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 * from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * Given: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"].
 * Return
 * [
 *  ["hit","hot","dot","dog","cog"],
 *  ["hit","hot","lot","log","cog"]
 * ]
 * Note: All words have the same length. All words contain only lowercase alphabetic characters.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findLadders("hit", "cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log")));
        System.out.println(new Solution().findLadders("red", "tax",
                Arrays.asList("ted","tex","red","tax","tad","den","rex","pee")));
        System.out.println(new Solution().findLadders("hot", "dog",
                Arrays.asList("hot","dog")));
    }

    // Naive solution: almost TLE
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ret = new ArrayList<>();
        Queue<List<String>> q = new LinkedList<>();
        Set<String> dict = new HashSet<>(wordList);
        q.offer(Arrays.asList(beginWord));
        while (!q.isEmpty() && ret.isEmpty()) { // q could be NOT empty when ret has elts if w==endWord not appear first
            Set<String> vis = new HashSet<>();
            for (int i = q.size(); i > 0; i--) {
                List<String> path = q.poll();
                String w = path.get(path.size() - 1);
                if (w.equals(endWord)) ret.add(path); // "steal" path from queue and enter "exit" mode

                for (int j = 0; j < w.length(); j++) {
                    char[] ch = w.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == w.charAt(j)) continue;
                        ch[j] = c;
                        String nb = String.valueOf(ch);
                        if (!dict.contains(nb)) continue;

                        List<String> next = new ArrayList<>(path);
                        next.add(nb);
                        q.offer(next);
                        vis.add(nb);
                    }
                }
            }
            dict.removeAll(vis);// Nice trick: How to avoid cycle meanwhile find all shortest path?
        }                       // Allow for duplicate on same level, but remove to avoid visit repeatedly
        return ret;
    }

    public List<List<String>> findLadders2(String start, String end, Set<String> dict) {
        Queue<List<String>> q = new LinkedList<>();
        if (!start.isEmpty()) q.offer(Arrays.asList(start));
        dict.add(end);

        List<List<String>> ret = new ArrayList<>();
        for (int i = 1; !q.isEmpty(); i++) {
            Set<String> vis = new HashSet<>();
            for (int j = q.size(); j > 0; j--) {
                List<String> path = q.poll();
                String word = path.get(path.size() - 1);
                if (end.equals(word)) ret.add(path);
                if (!ret.isEmpty()) continue;

                char[] ch = word.toCharArray();
                for (int k = 0; k < ch.length; k++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        ch[k] = c;
                        String nw = String.valueOf(ch);
                        if (dict.contains(nw)) {
                            List<String> np = new ArrayList<>(path);
                            np.add(nw);
                            q.offer(np);
                            vis.add(nw);
                        }
                    }
                    ch[k] = word.charAt(k);
                }
            }
            dict.removeAll(vis); // remove at last to avoid missing out solution
        }
        return ret;
    }

    // My 2AC
    public List<List<String>> findLadders21(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordList.add(endWord);

        List<List<String>> result = new ArrayList<>();
        boolean isEnd = false;
        while (!queue.isEmpty() && !isEnd) {
            Set<String> visited = new HashSet<>();
            int size = queue.size();
            while (size-- > 0) {
                String ladder = queue.poll();
                String lastword = ladder.substring(ladder.length() - beginWord.length());
                if (lastword.equals(endWord)) {
                    result.add(Arrays.asList(ladder.split(",")));
                    isEnd = true;
                }
                if (isEnd) continue;

                for (int i = 0; i < lastword.length(); i++) {
                    char[] chars = lastword.toCharArray();
                    for (char c = 'a'; (chars[i] = c) <= 'z'; c++) {
                        //if (c == lastword.charAt(i)) continue;
                        String newword = new String(chars);
                        if (wordList.contains(newword)) {  // error: don't remove now, otherwise missing solution
                            queue.offer(ladder + "," + newword);
                            visited.add(newword);
                        }
                    }
                }
            }
            wordList.removeAll(visited);
        }
        return result;
    }

}
