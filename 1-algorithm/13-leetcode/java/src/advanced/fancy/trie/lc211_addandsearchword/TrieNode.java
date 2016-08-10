package advanced.fancy.trie.lc211_addandsearchword;

class TrieNode {

    final TrieNode[] children;

    boolean isWord;

    char val;

    // Initialize your data structure here.
    public TrieNode() {
        this.children = new TrieNode[26];
        this.isWord = false;
    }

    public TrieNode(char c) {
        this();
        this.val = c;
    }

}
