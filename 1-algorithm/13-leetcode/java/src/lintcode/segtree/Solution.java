package lintcode.segtree;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        print(sol.build(0, 3));
    }

    // Segment Tree Build I
    public SegmentTreeNode build(int start, int end) {
        if (start > end) return null; // [10,4]

        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start < end) {
            root.left = build(start, (start + end) / 2);
            root.right = build((start + end) / 2 + 1, end);
        }
        return root;
    }

    // Segment Tree Query I
    public int query(SegmentTreeNode root, int start, int end) {
        if (root.start == root.end) return root.max; // no child
        int mid = root.start + (root.end - root.start) / 2;
        if (end <= mid) return query(root.left, start, end);
        if (mid < start) return query(root.right, start, end);
        return Math.max(query(root.left, start, end),
                query(root.right, start, end));
    }

    private static void print(SegmentTreeNode root) {
        if (root == null) return;
        System.out.printf("[%d, %d]", root.start, root.end);
        print(root.left);
        print(root.right);
    }

}
