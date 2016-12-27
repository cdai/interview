package fundamentals.string.convert.lc271_encodeanddecodestrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Design an algorithm to encode a list of strings to a string.
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 * Machine 1 does: string encoded_string = encode(strs);
 * and Machine 2 does: vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * Implement the encode and decode methods.
 */
public class Codec {

    public static void main(String[] args) {
        Codec sol = new Codec();
        String enc = sol.encode(Arrays.asList("abc", "dddddaaaaa"));
        System.out.printf("Encode %s\n", enc);
        System.out.printf("Decode %s\n", sol.decode(enc));
    }

    public String encode(List<String> strs) {
        StringBuilder enc = new StringBuilder();
        for (String s : strs)
            enc.append(s.length()).append(":").append(s);
        return enc.toString();
    }

    public List<String> decode(String str) {
        List<String> ret = new ArrayList<>();
        for (int i = 0, sep, len; i < str.length(); i = sep + 1 + len) {
            sep = str.indexOf(":", i);
            len = Integer.parseInt(str.substring(i, sep));
            ret.add(str.substring(sep + 1, sep + 1 + len));
        }
        return ret;
    }

}
