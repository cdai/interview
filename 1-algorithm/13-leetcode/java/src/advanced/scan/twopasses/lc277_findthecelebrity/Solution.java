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
        int celebrity = 0;
        for (int i = 1; i < n; i++)
            if (knows(celebrity, i))
                celebrity = i;

        /* for people < celebrity, they cannot be candidate because they know someone before them
         * for people > celebrity, they cannot be candidate because celebrity knows all of them */
        for (int i = 0; i < n; i++) {
            if (i < celebrity && (knows(celebrity, i) || !knows(i, celebrity)))
                return -1;
            if (i > celebrity && !knows(i, celebrity))
                return -1;
        }
        return celebrity;
    }

    private boolean knows(int a, int b) {
        if (b == 3)
            return true;
        return false;
    }

}
