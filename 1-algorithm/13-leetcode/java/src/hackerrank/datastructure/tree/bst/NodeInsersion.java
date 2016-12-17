package hackerrank.datastructure.tree.bst;

/**
 */
public class NodeInsersion {

    public Node insert(Node root,int value) {
        if (root == null) {
            Node node = new Node();
            node.data = value;
            return node;
        }

        if (value < root.data)
            root.left = insert(root.left, value);
        else
            root.right = insert(root.right, value);
        return root;
    }

    // CLRS iterative version
    public Node insertIterative(Node root,int value) {
        Node par = null;
        Node cur = root;
        while (cur != null) {
            par = cur;
            cur = (value < par.data) ? cur.left : cur.right;
        }

        Node ins = new Node();
        ins.data = value;
        if (par == null) /* tree is empty */
            root = ins;
        else if (value < par.data)
            par.left = ins;
        else
            par.right = ins;
        return root;
    }

}
