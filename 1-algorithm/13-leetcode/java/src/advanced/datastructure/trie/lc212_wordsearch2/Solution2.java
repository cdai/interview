package advanced.datastructure.trie.lc212_wordsearch2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public class Solution2 {

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        Trie trie = new Trie();
        for (String word : words)
            trie.insert(word);

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                doFindWords(result, trie, "", board, i, j);
        return new ArrayList<>(result);
    }

    private void doFindWords(Set<String> result, Trie words, String path,
                             char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return;
        if (!Character.isLetter(board[row][col]))
            return;

        path += board[row][col];
        TrieNode node = words.startsWith(path);
        if (node == null)
            return;
        if (node.isWord)
            result.add(path);

        board[row][col] = ' ';
        doFindWords(result, words, path, board, row - 1, col);
        doFindWords(result, words, path, board, row, col + 1);
        doFindWords(result, words, path, board, row + 1, col);
        doFindWords(result, words, path, board, row, col - 1);
        board[row][col] = path.charAt(path.length() - 1);
    }


    static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) { // error1: create only if null
                    cur.children[c - 'a'] = new TrieNode(c);
                }
                cur = cur.children[c - 'a'];
            }
            cur.isWord = true;
        }

        public TrieNode startsWith(String prefix) { // A new method!
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                cur = cur.children[c - 'a'];
                if (cur == null) {
                    return null;
                }
            }
            return cur;
        }
    }

    static class TrieNode {

        boolean isWord;

        char val;

        TrieNode[] children;

        // Initialize your data structure here.
        public TrieNode() {
            this.children = new TrieNode[26];
        }

        public TrieNode(char val) {
            this();
            this.val = val;
        }

    }

}
