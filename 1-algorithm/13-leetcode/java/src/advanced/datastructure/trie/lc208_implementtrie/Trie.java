package advanced.datastructure.trie.lc208_implementtrie;

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
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) { // error1: create only if null
                cur.children[c - 'a'] = new TrieNode(c);
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            cur = cur.children[c - 'a'];
            if (cur == null) {
                return false;
            }
        }
        return cur.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            cur = cur.children[c - 'a'];
            if (cur == null) {
                return false;
            }
        }
        return true; // error: no need to find one non-null child
    }

}
