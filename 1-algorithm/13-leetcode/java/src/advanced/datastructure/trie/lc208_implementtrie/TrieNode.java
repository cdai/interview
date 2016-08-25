package advanced.datastructure.trie.lc208_implementtrie;

public class TrieNode {

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
