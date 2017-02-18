package advanced.combinatorial.backtracking.dfs.lc463_islandperimeter;

/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
 * and there is exactly one island (i.e., one or more connected land cells).
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island).
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100.
 * Determine the perimeter of the island.
 */
public class Solution {

    public int islandPerimeter(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int perim = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                if (i == 0 || grid[i - 1][j] == 0) perim++;
                if (j == 0 || grid[i][j - 1] == 0) perim++;
                if (i == m - 1 || grid[i + 1][j] == 0) perim++;
                if (j == n - 1 || grid[i][j + 1] == 0) perim++;
            }
        }
        return perim;
    }

    public int islandPerimeter1(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int perim = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) perim++;
            if (grid[i][n - 1] == 1) perim++;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) continue;
                if (i > 0 && grid[i - 1][j] == 1) perim++;
                if (j > 0 && grid[i][j - 1] == 1) perim++;
                if (i < m - 1 && grid[i + 1][j] == 1) perim++;
                if (j < n - 1 && grid[i][j + 1] == 1) perim++;
            }
        }

        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) perim++;
            if (grid[m - 1][j] == 1) perim++;
        }
        return perim;
    }

}
