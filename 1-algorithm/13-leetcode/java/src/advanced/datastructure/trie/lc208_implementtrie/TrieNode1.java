package advanced.datastructure.trie.lc208_implementtrie;

import java.util.HashMap;
import java.util.Map;

class TrieNode1 {

    boolean isWord;

    char val;

    Map<Character, TrieNode1> children;

    // Initialize your data structure here.
    public TrieNode1() {
        this.children = new HashMap<>();
        this.isWord = false;
    }

    public TrieNode1(char c) {
        this();
        this.val = c;
    }

}
