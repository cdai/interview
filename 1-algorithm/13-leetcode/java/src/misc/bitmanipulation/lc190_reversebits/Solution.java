package misc.bitmanipulation.lc190_reversebits;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class Solution {

    public static void main(String[] args) {
        //      43261596    (00000010100101000001111010011100)
        // =>   964176192   (00111001011110000010100101000000)
        System.out.println(new Solution().reverseBits(43261596));

        System.out.println(new Solution().reverseBits((int) 2147483648L));

        System.out.println(new Solution().reverseBits((int) 1));
        System.out.println(new Solution().reverseBits((int) -2139062144));
    }

    private Map<Byte,Integer> cache = new HashMap<>();

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i < 4; i++, n >>= 8){
            System.out.println("n: " + n + ", res: " + res);
            res = res << 8 | reverseByte((byte) n);
        }
        return res;
    }

    private int reverseByte(byte n) {
        Integer res = cache.get(n);
        if (res != null) {
            return res;
        }
        res = 0;
        for(int i = 0; i < 8; i++, n >>= 1) {
            res = (res << 1 | (n & 1));
        }
        cache.put(n, res);
        return res;
    }

    public int reverseBits2(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++, n >>= 1){
            res = res << 1 | (n & 1);
        }
        return res;
    }

    // you need treat n as an unsigned value
    public int reverseBits3(int n) {
        int result = 0;
        for (int i = 0; i < 31; i++) {  // error1: process 32 bits but move left 31 times.
            //System.out.println("i=" + i + ": " + (n % 2));
            result += (n % 2);          // error2: could be negative, use n & 1
            result = result << 1;       // or n >>> 1
            n = n >> 1;
            System.out.println(n);
        }
        result += (n % 2);
        //System.out.println(Integer.toUnsignedLong(result));
        return result;
    }

}
