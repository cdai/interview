package advanced.datastructure.trie.lc208_implementtrie;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * Note: You may assume that all inputs are consist of lowercase letters a-z.
 */
public class Trie1 {

    public static void main(String[] args) {
        Trie1 trie = new Trie1();
        trie.insert("hellotrie");
        System.out.println(trie.search("trie"));
        System.out.println(trie.search("hello"));
        System.out.println(trie.search("hellotrie"));
        System.out.println(trie.startsWith("hell"));
    }

    private TrieNode1 root;

    public Trie1() {
        root = new TrieNode1();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode1 node = root;
        for (char c : word.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                TrieNode1 newNode = new TrieNode1(c);
                node.children.put(c, newNode);
                node = newNode;
            }
        }
        node.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode1 node = root;
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
        TrieNode1 node = root;
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
