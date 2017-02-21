package others.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class BeautifulNum {

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(2, 3, 4, 6, 8, 9, 12),
                beautifulNum(11, 2, 3));
    }

    public List<Integer> beautifulNum(int n, int a, int b) {
        List<Integer> ret = new ArrayList<>();
        int ia = 0, ib = 0, ic = 0, c = a & b;
        int na = a, nb = b, nc = c;
        while (ret.isEmpty() || ret.get(ret.size() - 1) < n) {
            ret.add(Math.min(na, Math.min(nb, nc)));
            if (ret.get(ret.size() - 1) == na) na = ret.get(ia++) * a;
            if (ret.get(ret.size() - 1) == nb) nb = ret.get(ib++) * b;
            if (ret.get(ret.size() - 1) == nc) nc = ret.get(ic++) * c;
        }
        return ret;
    }

}
