package buildingblock.searching.lc374_guessnumberhigherorlower;

/**
 * We are playing the Guess Game. The game is as follows: I pick a number from 1 to n.
 * You have to guess which number I picked. Every time you guess wrong,
 * I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *  -1 : My number is lower
 *  1 : My number is higher
 *  0 : Congrats! You got it!
 * Example: n = 10, I pick 6. Return 6.
 */
public class Solution extends GuessGame {

    public int guessNumber(int n) {
        int l = 1, r = n;
        while (l < r) {
            int m = l + (r - l) / 2;
            int ret = guess(m);
            if (ret == 0) return m;
            if (ret == 1) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    // My 2nd: iterative solution
    public int guessNumber2(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int result = guess(mid);
            if (result == 1) {
                low = mid + 1;
            } else if (result == -1) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    // My 1st: recursive solution
    public int guessNumber1(int n) {
        return doGuess(1, n);
    }

    private int doGuess(int low, int high) {
        //int mid = (low + high) / 2;
        int mid = low + ((high - low) / 2); // error: avoid overflow!!! [2126753390,1702766719]
        int guess = guess(mid);
        if (guess < 0) {
            return doGuess(low, mid - 1);
        } else if (guess > 0) {
            return doGuess(mid + 1, high);
        } else {
            return mid;
        }
    }

}
