package advanced.scan.twopasses.lc277_findthecelebrity;

/**
 *
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findCelebrity(4));
    }

    public int findCelebrity(int n) {
        int celeb = 0;
        for (int i = 1; i < n; i++) {
            if (knows(celeb, i)) celeb = i;
        }

        /* for people < celebrity, they cannot be candidate because they know someone before them
         * for people > celebrity, they cannot be candidate because celebrity knows all of them */
        for (int i = 0; i < n; i++) {
            if (i < celeb && (knows(celeb, i) || !knows(i, celeb)))
                return -1;
            if (i > celeb && !knows(i, celeb)) // no need to check knows(celebrity,i) again
                return -1;
        }
        return celeb;
    }

    private boolean knows(int a, int b) {
        if (b == 3)
            return true;
        return false;
    }

}
