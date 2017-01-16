package advanced.datastructure.trie.lc212_wordsearch2;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * For example, Given words = ["oath","pea","eat","rain"] and board =
 * [
 *  ['o','a','a','n'],
 *  ['e','t','a','e'],
 *  ['i','h','k','r'],
 *  ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note: You may assume that all inputs are consist of lowercase letters a-z.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findWords(new char[][]{{'a'}}, new String[]{"a"}));
    }

    // O(mns4^w), where m & n are the board dimensions, s is the number of words in dict,
    // and w is the maximum one of words’ length. (4 means try in 4 directions).
    // Using Trie tree, build it in O(sw), search part become O(mnw4^w).
    // When mostly w (max len) << s (#words in dict), using a trie would save lots of time.
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode trie = TrieNode.buildTree(words);
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                dfs(result, trie, board, i, j);
        return result;
    }

    private void dfs(List<String> ret, TrieNode node, char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '#') return;

        char c = board[x][y];
        node = node.next[c - 'a']; // Note! node itself is matched before cur char. eg.[[a]],[a]
        if (node == null) return;

        if (node.word != null) {
            ret.add(node.word);
            node.word = null; // de-duplicate
        }
        board[x][y] = '#';
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : dirs) dfs(ret, node, board, x + d[0], y + d[1]);
        board[x][y] = c;
    }

    private void doFindWords(List<String> result, TrieNode p, char[][] board, int row, int col) {
        char c = board[row][col];
        if (!Character.isLetter(board[row][col]) || p.next[c - 'a'] == null)
            return;

        p = p.next[c - 'a'];                // Optimize-1: don't start from root every time
        if (p.word != null) {
            result.add(p.word);
            p.word = null;                  // Optimize-2: de-duplicate without Set
        }

        board[row][col] = '#';              // Optimize-3: terminate earlier
        if (row > 0) doFindWords(result, p, board, row - 1, col);
        if (col > 0) doFindWords(result, p, board, row, col - 1);
        if (row < board.length - 1) doFindWords(result, p, board, row + 1, col);
        if (col < board[0].length - 1) doFindWords(result, p, board, row, col + 1);
        board[row][col] = c;
    }

    static class TrieNode {                 // Optimize-4: don't store char
        String word;
        TrieNode[] next;
        TrieNode() { this.next = new TrieNode[26]; }

        static TrieNode buildTree(String[] words) {
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode cur = root;
                for (char c : word.toCharArray()) {
                    int idx = c - 'a';
                    if (cur.next[idx] == null)
                        cur.next[idx] = new TrieNode();
                    cur = cur.next[idx];
                }
                cur.word = word;
            }
            return root;
        }
    }

}
