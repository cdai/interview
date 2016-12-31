package advanced.datastructure.game.lc294_flipgame2;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.canWin("++++"));
        System.out.println(sol.canWin("+++"));
        System.out.println(sol.canWin("+++++"));
    }

    // Winning strategy: player 2 loses at every case
    public boolean canWin(String s) {
        //if (!s.contains("++")) return round % 2 == 1; // lose at opponent's round

        boolean win = false;
        for (int i = -1; (i = s.indexOf("++", i + 1)) >= 0; ) {
            char[] ch = s.toCharArray();
            ch[i] = ch[i + 1] = '-';
            win |= !canWin(String.valueOf(ch)); // player-1: exists one choice that opponent definitely loses
            //win |= !play(String.valueOf(ch), round + 1); // player-2: all choices are definitely lose
        }
        return win;
    }

}
