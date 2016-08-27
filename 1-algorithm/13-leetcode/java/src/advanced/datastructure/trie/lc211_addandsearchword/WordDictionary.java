package advanced.datastructure.trie.lc211_addandsearchword;

/**
 */
public class WordDictionary {

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println(dict.search("pad"));
        System.out.println(dict.search("bad"));
        System.out.println(dict.search(".ad"));
        System.out.println(dict.search("b.."));
    }

    private TrieNode root = new TrieNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (cur.next[idx] == null)
                cur.next[idx] = new TrieNode();
            cur = cur.next[idx];
        }
        cur.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return doSearch(root, word.toCharArray(), 0);
    }

    private boolean doSearch(TrieNode cur, char[] word, int pos) {
        if (cur == null) return false;
        if (pos == word.length) return cur.isWord; // error: return true is wrong!

        if (word[pos] == '.') {
            for (TrieNode next : cur.next)
                if (doSearch(next, word, pos + 1))
                    return true;
            return false;
        }
        return doSearch(cur.next[word[pos] - 'a'], word, pos + 1);
    }

    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWord;
    }

}
