package hackerrank.algorithm.search.trie;

/**
 * Huffman decoding
 */
public class Huffman {

    void decode(String S ,Node root) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < S.length(); ) {
            Node p = root;
            while (p.data == '\0') {
                if (S.charAt(i) == '0')
                    p = p.left;
                else p = p.right;
                i++;
            }
            /* Each var in loop needs to hold some invariant
                i is next char to handle; p.data != '\0' here */
            text.append(p.data);
        }

        System.out.println(text.toString());
    }

    class Node {
        int frequency;
        char data;
        Node left, right;
    }

}
