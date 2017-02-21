package others.algorithm;

import org.junit.Test;

/**
 */
public class DeleteSubTree {

    @Test
    public void testDelete() {
        int[] tree = {1, -1, 1, 0, 2, 4, 3, 4, 4};
        deleteSubtree(tree, 2);
    }

    public void deleteSubtree(int[] tree, int node) {
        int n = tree.length;
        Boolean[] memo = new Boolean[n];
        memo[node] = true;
        for (int i = 0; i < n; i++) {
            dfs(memo, tree, i);
        }
        print(memo, tree);
    }

    private boolean dfs(Boolean[] memo, int[] tree, int node) {
        if (node == -1) return false;
        if (memo[node] != null) return memo[node];

        memo[node] = dfs(memo, tree, tree[node]);
        return memo[node];
    }

    private void print(Boolean[] memo, int[] tree) {
        for (int i = 0; i < tree.length; i++) {
            if (!memo[i]) {
                System.out.println(tree[i] + " <-" + i);
            }
        }
    }

}
