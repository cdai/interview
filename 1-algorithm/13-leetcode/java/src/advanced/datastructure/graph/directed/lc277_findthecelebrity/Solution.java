package advanced.datastructure.graph.directed.lc277_findthecelebrity;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findCelebrity(new int[]{1, 2, 3, 4}));
    }

    public int findCelebrity(int n) {
        int celebrity = 0;
        for (int i = 1; i < n; i++)
            if (knows(celebrity, i))
                celebrity = i;

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
