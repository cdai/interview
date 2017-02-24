package advanced.design.lc348_designtictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.

 You may assume the following rules:

 A move is guaranteed to be valid and is placed on an empty block.
 Once a winning condition is reached, no more moves is allowed.
 A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 Example:
 Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

 TicTacToe toe = new TicTacToe(3);

 toe.move(0, 0, 1); -> Returns 0 (no one wins)
 |X| | |
 | | | | // Player 1 makes a move at (0, 0).
 | | | |

 toe.move(0, 2, 2); -> Returns 0 (no one wins)
 |X| |O|
 | | | | // Player 2 makes a move at (0, 2).
 | | | |

 toe.move(2, 2, 1); -> Returns 0 (no one wins)
 |X| |O|
 | | | | // Player 1 makes a move at (2, 2).
 | | |X|

 toe.move(1, 1, 2); -> Returns 0 (no one wins)
 |X| |O|
 | |O| | // Player 2 makes a move at (1, 1).
 | | |X|

 toe.move(2, 0, 1); -> Returns 0 (no one wins)
 |X| |O|
 | |O| | // Player 1 makes a move at (2, 0).
 |X| |X|

 toe.move(1, 0, 2); -> Returns 0 (no one wins)
 |X| |O|
 |O|O| | // Player 2 makes a move at (1, 0).
 |X| |X|

 toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 |X| |O|
 |O|O| | // Player 1 makes a move at (2, 1).
 |X|X|X|
 Follow up:
 Could you do better than O(n2) per move() operation?

 Hint:
 Could you trade extra space such that move() operation can be done in O(1)?
 You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.
 */
public class TicTacToe {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3);
        Assertions.assertEquals(0, game.move(0, 0, 1));
        Assertions.assertEquals(0, game.move(0, 2, 2));
        Assertions.assertEquals(0, game.move(2, 2, 1));
        Assertions.assertEquals(0, game.move(1, 1, 2));
        Assertions.assertEquals(0, game.move(2, 0, 1));
        Assertions.assertEquals(0, game.move(1, 0, 2));
        Assertions.assertEquals(1, game.move(2, 1, 1));
    }

    private final int size;
    private final int[] rows;
    private final int[] cols;
    private final int[] diag;

    public TicTacToe(int size) {
        this.size = size;
        this.rows = new int[size];
        this.cols = new int[size];
        this.diag = new int[2];
    }

    public int move(int x, int y, int player) {
        int one = player == 1 ? 1 : -1;
        rows[x] += one;
        cols[y] += one;
        if (x == y) diag[0] += one;
        else if (x + y == size - 1) diag[1] += one;

        if (Math.abs(rows[x]) == size ||
                Math.abs(cols[x]) == size ||
                Math.abs(diag[0]) == size ||
                Math.abs(diag[1]) == size) return player;
        return 0;
    }

}
