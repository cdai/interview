package advanced.design.lc211_addandsearchword;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a data structure that supports the following two operations:
 *  void addWord(word)
 *  bool search(word)
 *  search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * For example:
 *  addWord("bad")
 *  addWord("dad")
 *  addWord("mad")
 *  search("pad") -> false
 *  search("bad") -> true
 *  search(".ad") -> true
 *  search("b..") -> true
 * Note: You may assume that all words are consist of lowercase letters a-z.
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
        TrieNode par = root;
        for (char c : word.toCharArray()) {
            TrieNode cur = par.children[c - 'a'];
            if (cur == null) {
                cur = new TrieNode(c);
                par.children[c - 'a'] = cur;
            }
            par = cur;
        }
        par.isWord = true;
    }

    // DFS would be much faster!
    public boolean search(String word) {
        return doDfs(root, word);
    }

    private boolean doDfs(TrieNode node, String word) {
        if (word.isEmpty()) {
            return node.isWord;
        }

        for (TrieNode child : node.children) {
            if (child == null) {
                continue;
            }
            char c = word.charAt(0);
            if ((c == '.' || c == child.val) && doDfs(child, word.substring(1))) {
                return true;
            }
        }
        return false;
    }

    // Essentially, this is BFS, which is not fast...
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search2(String word) {
        List<TrieNode> matched = new ArrayList<>();
        matched.add(root);              // error: use root.children to match word

        for (char c : word.toCharArray()) {
            List<TrieNode> matchedMore = new ArrayList<>();
            for (TrieNode parent : matched) {
                for (TrieNode child : parent.children) {
                    if (child != null && (c == '.' || c == child.val)) {
                        matchedMore.add(child);
                    }
                }
            }
            if (matchedMore.isEmpty()) {
                return false;
            }
            matched = matchedMore;
        }

        for (TrieNode node : matched) {
            if (node.isWord) {
                return true;
            }
        }
        return false;
    }

}
