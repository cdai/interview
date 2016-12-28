package lintcode.segtree;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        print(sol.build(0, 3));
    }

    public SegmentTreeNode build(int start, int end) {
        if (start > end) return null; // [10,4]

        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start < end) {
            root.left = build(start, (start + end) / 2);
            root.right = build((start + end) / 2 + 1, end);
        }
        return root;
    }

    private static void print(SegmentTreeNode root) {
        if (root == null) return;
        System.out.printf("[%d, %d]", root.start, root.end);
        print(root.left);
        print(root.right);
    }

}
