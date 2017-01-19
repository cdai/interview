package lintcode.hashing;

/**
 */
public class Solution {

    // 128-Hash Function
    public int hashCode(char[] key, int HASH_SIZE) {
        long hash = 0;
        for (char c : key)
            hash = (hash * 33 + (int) c) % HASH_SIZE;
        return (int) hash;
    }

}
