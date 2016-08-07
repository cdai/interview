package miscellaneous.fancy.lc208_implementtrie;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * Note: You may assume that all inputs are consist of lowercase letters a-z.
 */
public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hellotrie");
        System.out.println(trie.search("trie"));
        System.out.println(trie.search("hello"));
        System.out.println(trie.search("hellotrie"));
        System.out.println(trie.startsWith("hell"));
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                TrieNode newNode = new TrieNode(c);
                node.children.put(c, newNode);
                node = newNode;
            }
        }
        node.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                return false;
            }
        }
        return node.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                return false;
            }
        }
        return true;    // error: start with prefix, including prefix itself
    }

}
