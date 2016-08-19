package miscellaneous.math.pattern.lc292_nimgame;

/**
 * You are playing the following Nim Game with your friend:
 * There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones.
 * The one who removes the last stone will be the winner. You will take the first turn to remove the stones.
 * Both of you are very clever and have optimal strategies for the game.
 * Write a function to determine whether you can win the game given the number of stones in the heap.
 * For example, if there are 4 stones in the heap, then you will never win the game:
 * no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.
 * Hint: If there are 5 stones in the heap, could you figure out a way to remove the stones such that you will always be the winner?
 */
public class Solution {

    // At 4/8/12 you will lose, otherwise you can make opponent there to lose
    // 1,2,3,4,5,6,7,8,9,10,11,12,13...
    // w,w,w,l,w,w,w,l,w, w, w, l, w...
    public boolean canWinNim(int n) {
        if (n <= 0) {
            return false;
        }
        return (n % 4 != 0);
    }

}
