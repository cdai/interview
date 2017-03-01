package fundamentals.graph.bfs.shortestpath.lc127_wordladder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
 *  1.Only one letter can be changed at a time
 *  2.Each intermediate word must exist in the word list
 * For example,
 * Given:
 *  beginWord = "hit"
 *  endWord = "cog"
 *  wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 * Note:
 *  Return 0 if there is no such transformation sequence.
 *  All words have the same length.
 *  All words contain only lowercase alphabetic characters.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().ladderLength("hit","hit",
                Arrays.asList("hot")));

        System.out.println(new Solution().ladderLength("hit","cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log")));
    }

    // 5AC.
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();
        if (dict.add(endWord)) return 0; // add end word to dict to make it reachable
        q.offer(beginWord);
        for (int len = 1; !q.isEmpty(); len++) {
            for (int i = q.size(); i > 0; i--) {
                String w = q.poll();
                if (w.equals(endWord)) return len;

                for (int j = 0; j < w.length(); j++) { // suppose dictionary is large
                    char[] ch = w.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        ch[j] = c;
                        String nb = String.valueOf(ch);
                        if (dict.remove(nb)) q.offer(nb);
                    }
                }
            }
        }
        return 0;
    }

    // 1) It's much faster than one-end search indeed as explained in wiki.
    // 2) BFS isn't equivalent to Queue. Sometimes Set is more accurate representation for nodes of level. (also handy since we need to check if we meet from two ends)
    // 3) It's safe to share a visited set for both ends since if they meet same string it terminates early. (vis is useful since we cannot remove word from dict due to bidirectional search)
    // 4) It seems like if(set.add()) is a little slower than if(!contains()) then add().
    public int ladderLength_bidirection(String start, String end, Set<String> dict) {
        Set<String> qs = new HashSet<>(), qe = new HashSet<>(), vis = new HashSet<>(); // safe to share visited set
        if (!start.isEmpty()) qs.add(start);
        if (!end.isEmpty()) qe.add(end);

        for (int i = 2; !qs.isEmpty() && !qe.isEmpty(); i++) {
            Set<String> next = new HashSet<>();
            for (String w : qs) {
                char[] ch = w.toCharArray();
                for (int j = 0; j < ch.length; j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        ch[j] = c;
                        String ns = String.valueOf(ch);
                        if (qe.contains(ns)) return i; // meet from two ends
                        if (dict.contains(ns) && vis.add(ns)) next.add(ns);
                    }
                    ch[j] = w.charAt(j);
                }
            }
            qs = (next.size() < qe.size()) ? next : qe; // switch to small one to traverse from other end
            qe = (qs == qe) ? next : qe;
        }
        return 0;
    }

    // 4AC. Minor change: reuse char array
    public int ladderLength4(String begin, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        dict.add(end);
        for (int i = 1; !q.isEmpty(); i++) {
            for (int j = q.size(); j > 0; j--) {
                String w = q.poll();
                if (end.equals(w)) return i;

                char[] ch = w.toCharArray();
                for (int k = 0; k < ch.length; k++) { // suppose dict is large
                    for (char c = 'a'; c <= 'z'; c++) {
                        ch[k] = c;
                        String nw = String.valueOf(ch);
                        if (dict.remove(nw)) q.offer(nw);
                    }
                    ch[k] = w.charAt(k);
                }
            }
        }
        return 0;
    }

    // My 3AC. O(N) time where N is #words in dict.
    public int ladderLength3(String begin, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<>();
        q.offer(begin);
        dict.add(end);                      // add end word to dict to make it reachable in loop below
        for (int i = 1; !q.isEmpty(); i++) {// start from 1 since we need entire ladder length
            for (int size = q.size(); size > 0; size--) {
                String w = q.poll();
                if (w.equals(end)) return i;
                for (int j = 0; j < w.length(); j++) {
                    char[] c = w.toCharArray();
                    for (char k = 'a'; k <= 'z'; k++) {
                        c[j] = k;
                        String next = new String(c);
                        if (dict.remove(next)) q.offer(next);
                    }
                }
            }
        }
        return 0;
    }

    // Try double-end, but it doesn't work...
    public int ladderLength_twoend(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        queue.offer(endWord);
        for (int i = 2; !queue.isEmpty(); i += 2) {
            int size = queue.size();
            Set<String> meet = new HashSet<>();
            while (size-- > 0) {
                String word = queue.poll();
                for (int j = 0; j < word.length(); j++) {
                    char[] letters = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        letters[j] = c;
                        String next = new String(letters);
                        if (!wordList.contains(next))
                            continue;
                        if (!meet.add(next)) // impossible to meet, since we remove wordList
                            return i;
                        queue.offer(next);
                        wordList.remove(next);
                    }
                }
            }
        }
        return 0;
    }

    // My 2AC: O(N) time (N = sizeof(wordList))
    public int ladderLength_bfs(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        for (int i = 1; !queue.isEmpty(); i++) {
            int size = queue.size();
            while (size-- > 0) {
                String word = queue.poll();
                for (int j = 0; j < word.length(); j++) {
                    char[] letters = word.toCharArray(); // reuse this array in this round
                    for (char c = 'a'; c <= 'z'; c++) {
                        letters[j] = c;                  // Miss a check if c == original letter
                        String next = new String(letters);
                        if (next.equals(endWord)) return i + 1; // error: +1 for endWord
                        if (!wordList.contains(next)) continue;
                        queue.offer(next);
                        wordList.remove(next);
                    }
                }
            }
        }
        return 0;
    }

    // My 1AC
    public int ladderLength1(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        wordList.add(endWord);

        for (int i = 1; !queue.isEmpty(); i++) {

            int size = queue.size();
            while (size-- > 0) {
                String w = queue.poll();
                if (w.equals(endWord)) {
                    return i;
                }

                // Same result but rely on dict size
                /*for (Iterator<String> it = wordList.iterator(); it.hasNext(); ) {
                    String str = it.next();
                    if (isOnlyOneEdit(w, str)) {
                        queue.offer(str);
                        it.remove();
                    }
                }*/

                // Rely on the word size
                for (int j = 0; j < w.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == w.charAt(j)) {
                            continue;
                        }

                        char[] oldstr = w.toCharArray();
                        oldstr[j] = c;
                        String newstr = new String(oldstr);
                        if (wordList.contains(newstr)) {
                            queue.add(newstr);

                            /*
                             * This is the key! Why? Suppose hit->hot->dot,lot.
                             * Does this mean lot can't go to dog, which is removed by dot?
                             * Suppose X: X + 1 = dot and X + 1 = lot. Then X must be ?ot.
                             * But ?ot must be on the same level as dot,lot.
                             */
                            wordList.remove(newstr);
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean isOnlyOneEdit(String w1, String w2) {
        int edit = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                if (++edit > 1) {
                    return false;
                }
            }
        }
        return (edit == 1); // edit may be 0 or 1 here
    }

}
