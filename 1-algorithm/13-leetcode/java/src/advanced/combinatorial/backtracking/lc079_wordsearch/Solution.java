package advanced.combinatorial.backtracking.lc079_wordsearch;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * For example, Given board =
 *  [
 *      ['A','B','C','E'],
 *      ['S','F','C','S'],
 *      ['A','D','E','E']
 *  ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */
public class Solution {

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || word.length() == 0) {
            return false;
        }

        int[][] path = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(path, board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(int[][] path, char[][] board, String word, int i, int j, int k) {
        if (i < 0 || i > board.length-1 || j < 0 || j > board[i].length-1) {
            return false;
        }

        if (board[i][j] != word.charAt(k)) {
            return false;
        }

        if (path[i][j] != 0) {
            return false;
        }

        if (k == word.length()-1) {
            return true;
        }

        path[i][j] = 1;

        boolean isExist = exist(path, board, word, i-1, j, k+1) ||
                exist(path, board, word, i+1, j, k+1) ||
                exist(path, board, word, i, j-1, k+1) ||
                exist(path, board, word, i, j+1, k+1);

        path[i][j] = 0;

        return isExist;
    }

}
