package advanced.datastructure.trie.lc208_implementtrie;

/**
 */
public class Trie {
    private TrieNode root;

    public Trie() { root = new TrieNode(); }

    public void insert(String word) {
        TrieNode node = traverse(word, true);
        node.isWord = true;
    }

    public boolean search(String word) {
        TrieNode node = traverse(word, false);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        return traverse(prefix, false) != null;
    }

    private TrieNode traverse(String word, boolean create) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.next[idx] == null) {
                if (!create) return null;
                cur.next[idx] = new TrieNode();
            }
            cur = cur.next[idx];
        }
        return cur;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWord = false;
    }
}
