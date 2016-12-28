package lintcode.segtree;

/**
 */
public class SegmentTreeNode {

    public int start, end, max;

    public SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end) {
        this(start, end, 0);
    }

    public SegmentTreeNode(int start, int end, int max) {
        this.start = start;
        this.end = end;
        this.left = this.right = null;
    }

}
