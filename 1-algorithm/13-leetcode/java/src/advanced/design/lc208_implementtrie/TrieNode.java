package advanced.design.lc208_implementtrie;

import java.util.HashMap;
import java.util.Map;

class TrieNode {

    boolean isWord;

    char val;

    Map<Character, TrieNode> children;

    // Initialize your data structure here.
    public TrieNode() {
        this.children = new HashMap<>();
        this.isWord = false;
    }

    public TrieNode(char c) {
        this();
        this.val = c;
    }

}
