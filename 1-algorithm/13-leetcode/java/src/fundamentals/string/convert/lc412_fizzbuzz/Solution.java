package fundamentals.string.convert.lc412_fizzbuzz;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program that outputs the string representation of numbers from 1 to n.
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 */
public class Solution {

    // Test case: -1, 0, 1, 10, 17...
    public List<String> fizzBuzz(int n) {
        if (n <= 0) return new ArrayList<>();
        List<String> ret = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) ret.add("FizzBuzz");
            else if (i % 3 == 0) ret.add("Fizz");
            else if (i % 5 == 0) ret.add("Buzz");
            else ret.add(String.valueOf(i));
        }
        return ret;
    }

    // Solution without mod operation
    public List<String> fizzBuzz_nomod(int n) {
        if (n <= 0) return new ArrayList<>();
        List<String> ret = new ArrayList<>();
        for (int i = 1, fizz = 3, buzz = 5; i <= n; i++) {
            if (i == fizz && i == buzz) {
                ret.add("FizzBuzz");
                fizz += 3;
                buzz += 5;
            } else if (i == fizz) {
                ret.add("Fizz");
                fizz += 3;
            } else if (i == buzz) {
                ret.add("Buzz");
                buzz += 5;
            } else ret.add(String.valueOf(i));
        }
        return ret;
    }

}
