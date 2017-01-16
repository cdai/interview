package advanced.datastructure.trie.lc208_implementtrie;

/**
 */
public class Trie {

    private TrieNode root;

    public Trie() { root = new TrieNode(); }

    public void insert(String word) {
        TrieNode node = search(word, true);
        node.isWord = true; /* node cannot be null */
    }

    public boolean search(String word) {
        TrieNode node = search(word, false);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = search(prefix, false);
        return node != null; // no need to do dfs search here
    }

    private TrieNode search(String word, boolean isCreate) {
        TrieNode node = root; /* invariant: node correspond to word[i-1] => entire word when terminate */
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) {
                if (isCreate) node.children[idx] = new TrieNode();
                else return null;
            }
            node = node.children[idx];
        }
        return node;
    }

    private boolean dfs(TrieNode node) {
        if (node == null) return false;
        if (node.isWord) return true;
        for (TrieNode n : node.children)
            if (dfs(n)) return true;
        return false;
    }

}
