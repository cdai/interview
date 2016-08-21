package buildingblock.hashing.bitvector.lc187_repeateddnasequences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * For example, given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", Return: ["AAAAACCCCC", "CCCCCAAAAA"].
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(new Solution().findRepeatedDnaSequences("AAAAAAAAAAA"));
    }

    // Inspired by solution from leetcode discuss
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            int dna = 0;
            for (int j = 0; j < 10; j++) {
                char c = s.charAt(i + j);
                int code = (c == 'A') ? 0 : (c == 'C' ? 1 : (c == 'G' ? 2 : 3));
                dna |= code << (j * 2);
            }
            if (!seen.add(dna)) {
                repeated.add(s.substring(i, i + 10));
            }
        }
        return new ArrayList<>(repeated);
    }

    // Stefan solution: concise as usual but not fast
    public List<String> findRepeatedDnaSequences2(String s) {
        Set<String> seen = new HashSet<>(), repeated = new HashSet<>(); // note: repeated must be Set instead of List
        for (int i = 0; i <= s.length() - 10; i++) {
            String dna = s.substring(i, i + 10);
            if (!seen.add(dna)) {
                repeated.add(dna);
            }
        }
        return new ArrayList<>(repeated);
    }

    // My 2AC: O(N^2), second inner loop will cause TLE...
    public List<String> findRepeatedDnaSequences3(String s) {
        Set<String> result = new HashSet<>();       // error3: duplicate eg.[AAAAAAAAAA AA]
        if (s.length() <= 10) {
            return new ArrayList<>(result);
        }

        int[] dna = new int[s.length() - 10 + 1];   // error2
        for (int i = 0; i <= s.length() - 10; i++) {// error2: i <= s.length()-10, j<i => j+10 < s.length()
            for (int j = 0; j < 10; j++) {
                switch (s.charAt(i + j)) {          // error1: j must start from 0 not i
                    case 'A': break;
                    case 'C': dna[i] |= 1 << (j * 2); break;
                    case 'G': dna[i] |= 2 << (j * 2); break;
                    case 'T': dna[i] |= 3 << (j * 2); break;
                }
            }
            for (int j = 0; j < i; j++) {
                if (dna[i] == dna[j]) {
                    result.add(s.substring(j, j + 10));
                }
            }
        }
        return new ArrayList<>(result);
    }

    // My 1AC
    public List<String> findRepeatedDnaSequences1(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }

        Set<String> result = new HashSet<>();
        Set<String> substrs = new HashSet<>();
        char[] chars = s.toCharArray();

        StringBuilder substr = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            substr.append(chars[i]);
        }
        substrs.add(substr.toString());

        for (int i = 10; i < s.length(); i++) {
            String cur = substr.deleteCharAt(0).append(chars[i]).toString();
            if (substrs.contains(cur)) {
                result.add(cur);
            } else {
                substrs.add(cur);
            }
        }
        return new ArrayList<>(result);
    }

    public List<String> findRepeatedDnaSequences1_2(String s) {
        Set<String> result = new HashSet<>();
        char[] chars = s.toCharArray();
        final int seqlen = 10;
        for (int i = 0, k = 0; i <= (s.length() - 2 * seqlen); ) { // error1: i<=slen-2*seqlen -> j=i+seqlen<=slen-seqlen (k<seqlen) -> j+k<slen

            // 1.Compare [i+k,i+9] and [j+k,j+9] (k chars is known as matched in comparsion before)
            for (int j = i + seqlen; (k < seqlen) && (chars[i + k] == chars[j + k]); k++);

            // error2: decided by loop condition above
            if (k == seqlen) {  // 2.1 Condition-1: k=10 broke loop, so[i,i+9] matches [j,j+9]
                result.add(s.substring(i, i + seqlen));
                i++;
                k--;
            } else {            // 2.2 Condition-2: chars[i+k]!=chars[j+k] broke loop
                i += (k + 1);
                k = 0;          // This is wrong! eg. [AAAAACCCCC|AAAAACCCC|CAAAAA]: |A != |C, but it's wrong to skip to |A+1
            }
        }
        return new ArrayList<>(result);
    }

}
