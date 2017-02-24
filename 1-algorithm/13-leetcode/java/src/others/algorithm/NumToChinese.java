package others.algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 */
public class NumToChinese {

    @Test
    void test() {
        Assertions.assertEquals("十五亿零一千零三", numToChinese(1500001003));
        Assertions.assertEquals("十五亿七千八百九十万零四百零三", numToChinese(1578900403));
    }

    // In Chinese, 10,100,1000,10000 are all base cases. 十 is special case.
    private String numToChinese(int num) {
        String[] ones = " 一 二 三 四 五 六 七 八 九".split(" ");
        String[] tens = " 十 二十 三十 四十 五十 六十 七十 八十 九十".split(" ");
        if (num < 10) return ones[num];
        else if (num < 100) return tens[num / 10] + numToChinese(num % 10);
        else if (num < 1000) return ones[num / 100] + "百"
                + (num % 100 < 10 ? "零" : "") + numToChinese(num % 100);
        else if (num < 10000) return ones[num / 1000] + "千"
                + (num % 1000 < 100 ? "零" : "") + numToChinese(num % 1000);
        else if (num < 100000000) return numToChinese(num / 10000) + "万"
                + (num % 10000 < 1000 ? "零" : "") + numToChinese(num % 10000);
        else return numToChinese(num / 100000000) + "亿"
                + (num % 100000000 < 10000 ? "零" : "") + numToChinese(num % 100000000);
    }

}
