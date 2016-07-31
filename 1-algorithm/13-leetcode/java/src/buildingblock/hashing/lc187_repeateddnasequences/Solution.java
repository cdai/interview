package buildingblock.hashing.lc187_repeateddnasequences;

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

    public List<String> findRepeatedDnaSequences(String s) {
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

    public List<String> findRepeatedDnaSequences2(String s) {
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
