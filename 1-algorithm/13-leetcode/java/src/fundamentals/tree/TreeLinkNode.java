package fundamentals.tree;

public class TreeLinkNode {
    public TreeLinkNode left, right, next;
    public int val;

    public TreeLinkNode() {
    }

    public TreeLinkNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "TreeLinkNode{" +
                "val=" + val +
                '}';
    }
}
