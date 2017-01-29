package fundamentals.string.convert.lc065_validnumber;

/**
 */
public class Solution {

    public boolean isNumber(String s) {
        s = s.trim();
        boolean num = false, dot = false, e = false, nume = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-') {         // Only see + and - in the beginning and after an e
                if (i != 0 && s.charAt(i - 1) != 'e') return false;
            } else if (Character.isDigit(c)) {  // See [0-9] and reset the number flags
                num = nume = true;
            } else if (c == '.') {              // Only see . if we didn't see e or .
                if (dot || e) return false;
                dot = true;
            } else if (c == 'e') {              // Only see e if we didn't see e but see a number. Reset nume to require num.
                if (e || !num) return false;
                e = true;
                nume = false;
            } else return false;
        }
        return num && nume;
    }

    // Format: +/-    345  .14    e+/-10
    //        [sign] [num] [frac] [sci]
    // Note: -.1 or 1., at least one part is not empty
    public boolean isNumber1(String s) {
        char[] c = s.trim().toCharArray();
        int i = 0, n = c.length;

        // 1.Optinal +/- sign
        if (i < n && (c[i] == '+' || c[i] == '-')) i++;

        // 2.Optinal integral part
        int j = i;
        i = readNum(c, i, false);

        // 3.Optional fractional part
        if (i < n && c[i] == '.') { // if no integral part (j==i), then must have one digit at least
            if ((i = readNum(c, i + 1, j == i)) == -1) return false;
        }

        // 4.Optional scientific notation
        if (j < i && i < n && c[i] == 'e') { // must have leading digit, eg. e10
            i++;
            if (i < n && (c[i] == '+' || c[i] == '-')) i++;
            if ((i = readNum(c, i, true)) == -1) return false;
        }
        return j < i && i == n; // at least one digit, eg. +, -
    }

    private int readNum(char[] c, int i, boolean isRequired) {
        int j = i;
        while (i < c.length && Character.isDigit(c[i])) i++;
        return (isRequired && i == j) ? -1 : i;
    }

}
