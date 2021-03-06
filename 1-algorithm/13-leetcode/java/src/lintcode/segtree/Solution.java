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

    // Segment Tree Build II
    public SegmentTreeNode build(int[] A) {
        if (A == null || A.length == 0) return null;
        return build(A, 0, A.length - 1);
    }

    private SegmentTreeNode build(int[] A, int start, int end) {
        if (start == end) return new SegmentTreeNode(start, end, A[start]);

        int mid = start + (end - start) / 2;
        SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
        root.left = build(A, start, mid);
        root.right = build(A, mid + 1, end);
        root.max = Math.max(root.left.max, root.right.max); // revise from bottom subtree max
        return root;
    }

    private SegmentTreeNode build2(int[] A, int start, int end) {
        if (start == end) return new SegmentTreeNode(start, end, A[start]);

        int max = Integer.MIN_VALUE; // Not necessary to do this!!!
        for (int i = start; i <= end; i++)
            if (A[i] > max) max = A[i];

        SegmentTreeNode root = new SegmentTreeNode(start, end, max);
        int mid = start + (end - start) / 2;
        root.left = build(A, start, mid);
        root.right = build(A, mid + 1, end);
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

    // Segment Tree Query II
    public int query2(SegmentTreeNode root, int start, int end) {
        if (root == null || start > end || root.end < start || end < root.start) return 0;
        if (root.start == root.end) return root.count;

        int mid = root.start + (root.end - root.start) / 2;
        if (end <= mid) return query(root.left, start, end);
        if (mid < start) return query(root.right, start, end);
        return query(root.left, start, end) + query(root.right, start, end);
    }

    // Segment Tree Modify
    public void modify(SegmentTreeNode root, int index, int value) {
        if (root == null || index < root.start || root.end < index) return;
        if (root.start == root.end) {
            root.max = value;
            return;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) modify(root.left, index, value);
        else modify(root.right, index, value);
        root.max = Math.max(root.left.max, root.right.max);
    }

    private static void print(SegmentTreeNode root) {
        if (root == null) return;
        System.out.printf("[%d, %d]", root.start, root.end);
        print(root.left);
        print(root.right);
    }

}
