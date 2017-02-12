package fundamentals.string.palindrome.lc005_longestpalindromicsubstring;

import java.util.Arrays;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("aaabaaaa"));
        System.out.println(new Solution().longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
    }

    public String longestPalindrome(String s) {
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = extend(s, i, i), s2 = extend(s, i, i + 1);
            if (s1.length() > max.length()) max = s1;
            if (s2.length() > max.length()) max = s2;
        }
        return max;
    }

    private String extend(String s, int i, int j) {
        for (; 0 <= i && j < s.length(); i--, j++) {
            if (s.charAt(i) != s.charAt(j)) break;
        }
        return s.substring(i + 1, j);
    }

    // Much efficient than mine, since we start from small string
    // O(N^2) in the worst case, but on average O(N*len)
    public String longestPalindrome3(String s) {
        int max = 0, idx = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = extend3(s, i, i), len2 = extend3(s, i, i + 1);         // try to extend in odd or even length
            if (max < Math.max(len1, len2)) {
                idx = (len1 > len2) ? (i - len1 / 2) : (i - len2 / 2 + 1);  // get starting idx according to center and length
                max = Math.max(len1, len2);
            }
        }
        return s.substring(idx, idx + max); // error: idx+max not idx...
    }

    private int extend3(String s, int i, int j) {
        for (; i >= 0 && j < s.length(); i--, j++)
            if (s.charAt(i) != s.charAt(j))
                break;
        return j - i - 2 + 1; // 2 means current two unmatched char
    }

    // My 2AC: brute-force, accepted but very slow
    public String longestPalindrome_bruteforce(String s) {
        if (s.isEmpty()) return "";
        int max = 1, idx = 0;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (isPalindrome(s, j, i)) {
                    if (max < (i - j + 1)) {
                        max = (i - j + 1);
                        idx = j;
                    }
                    break;
                }
            }
        }
        return s.substring(idx, idx + max);
    }

    private boolean isPalindrome(String s, int from, int to) {
        for (int i = from, j = to; i < j; i++, j--)
            if (s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }

    // My 1AC
    public String longestPalindrome1(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }

        // start(n): start point of longest palindrome from [0,n-1]
        // longest(n) means length of longest palindrome from [0,n-1]
        int[] start = new int[n + 1];
        int[] longest = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            longest[i] = 1;
        }

        // longest(n) = max(longest(n), 2*j(+1)), if [n-2*j(+1),n-1] is palindrome
        char[] c = s.toCharArray();
        for (int i = 0; i < n; i++) {
            // Odd length palindrome
            for (int j = 0; 0 <= i - j && i + j < n && c[i - j] == c[i + j]; j++) {
                int length = (2 * j + 1);
                int max = Math.max(longest[i - j], length);
                if (longest[i + j + 1] < max) {
                    longest[i + j + 1] = max;
                    start[i + j + 1] = (max == length) ? (i - j) : start[i - j];
                }
            }
            // Even length palindrome
            for (int j = 1; 0 <= i - j + 1 && i + j < n && c[i - j + 1] == c[i + j]; j++) {
                int length = (2 * j);
                int max = Math.max(longest[i - j + 1], length);
                if (longest[i + j + 1] < max) {
                    longest[i + j + 1] = max;
                    start[i + j + 1] = (max == length) ? (i - j + 1) : start[i - j + 1];
                }
            }
        }
        return s.substring(start[n], start[n] + longest[n]);
    }

    // Error: Case-2/3, "bananas" ask f[last], "aaabaaaa" ask last
    public String longestPalindrome2(String s) {
        int[] f = new int[s.length()];
        f[0] = 0;

        int start = 0, longest = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((f[i - 1] == i - 1) && (s.charAt(i - 1) == c)) {    // Case-1: cb,b=[01,1]
                f[i] = i - 1;
            } else {
                int j = i - 1;
                while (j >= f[i - 1] && s.charAt(j) == c) {
                    j--;
                }

                if (j < f[i - 1]) {                                 // Case-2: bbbbb,b=[00000,0]
                    f[i] = f[i - 1];
                } else {                                            // Case-3: bbcb,b=[0021,0]
                    int last = f[i - 1] - 1;
                    f[i] = (last >= 0 && s.charAt(last) == c) ? f[last] : i;
                }
            }

            if (longest < i - f[i] + 1) {
                start = f[i];
                longest = i - f[i] + 1;
            }
        }
        System.out.println(Arrays.toString(f));
        return s.substring(start, start + longest);
    }

}
