package advanced.combinatorial.backtracking.dfs.lc079_wordsearch;

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

    // My 2AC: O(mn*mn) time?
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return word.isEmpty();
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (isExist(board, word, i, j))
                    return true;
        return false;
    }

    private boolean isExist(char[][] board, String word, int row, int col) {
        if (word.isEmpty()) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return false;
        if (word.charAt(0) != board[row][col]) return false;

        String substr = word.substring(1);
        board[row][col] = ' ';
        boolean result = (isExist(board, substr, row - 1, col) || isExist(board, substr, row, col + 1) ||
                isExist(board, substr, row + 1, col) || isExist(board, substr, row, col - 1));
        board[row][col] = word.charAt(0);
        return result;
    }

    // My 1AC: use extra space besides call stack
    public boolean exist1(char[][] board, String word) {
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
