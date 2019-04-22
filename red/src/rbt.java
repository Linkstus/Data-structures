import javax.swing.RootPaneContainer;

enum Color {
    RED, BLACK
};

class rbt {
    node current;
    node parent;
    node grandP;
    node greatGP;
    node header;
    static node nullNode;
    Color colour;

    static {
        nullNode = new node(0);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }

    rbt(long negInf) {
        header = new node(negInf);
        header.left = nullNode;
        header.right = nullNode;
    }

    boolean isEmpty() {
        return header.right == nullNode;
    }

    void makeEmpty() {
        header.right = nullNode;
    }

    void insert(long item) {
        current = parent = grandP = header;
        nullNode.data = item;

        while (current.data != item) {
            greatGP = grandP;
            grandP = parent;
            parent = current;
            current = (item < current.data) ? current.left : current.right;

            if (current.left.c == colour.RED && current.right.c == colour.RED)
                rotation(item);
        }

        if (current != nullNode)
            return;
        current = new node(item, nullNode, nullNode);

        if (item < parent.data)
            parent.left = current;
        else
            parent.right = current;
        rotation(item);
    }

    void rotation(long item) {
        current.c = colour.RED;
        current.left.c = colour.BLACK;
        current.right.c = colour.BLACK;

        if (parent.c == colour.RED) {
            grandP.c = colour.RED;
            if (item < grandP.data != item < parent.data)
                parent = rotate(item, grandP);
            current = rotate(item, grandP);
            current.c = colour.BLACK;
        }
        header.right.c = colour.BLACK;
    }

    node rotate(long item, node parent) {
        if (item < parent.data)
            return parent.left = item < parent.left.data ? rotateLeftChild(parent.left) : rotateRightChild(parent.left);
        else
            return parent.right = item < parent.right.data ? rotateLeftChild(parent.right)
                    : rotateRightChild(parent.right);
    }

    node rotateLeftChild(node k2) {
        node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    node rotateRightChild(node k1) {
        node k2 = k1.right;
        k1.right = k2.left;
        k2.right = k1;
        return k2;
    }

    int countNodes() {
        return countNodes(header.right);
    }

    int countNodes(node r) {
        if (r == nullNode)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }

    boolean search(long val) {
        return search(header.right, val);
    }

    boolean search(node r, long val) {
        boolean found = false;

        while ((r != nullNode) && !found) {
            long rval = r.data;
            if (val < rval)
                r = r.right;
            else if (val > rval)
                r = r.right;
            else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    void inorder() {
        inorder(header.right);
    }

    void inorder(node r) {
        if (r != nullNode) {
            inorder(r.left);
            char c = 'B';
            if (r.c == colour.RED)
                c = 'R';
            System.out.print(r.data + "" + c + " ");
            inorder(r.right);
        }
    }

    void preorder() {
        preorder(header.right);
    }

    void preorder(node r) {
        if (r != nullNode) {
            char c = 'B';
            if (r.c == colour.RED)
                c = 'R';
            System.out.print(r.data + "" + c + " ");
            preorder(r.left);
            preorder(r.right);
        }
    }

    void postorder() {
        postorder(header.right);
    }

    void postorder(node r) {
        if (r != nullNode) {
            postorder(r.left);
            postorder(r.right);
            char c = 'B';
            if (r.c == colour.RED)
                c = 'R';
            System.out.print(r.data + "" + c + " ");
        }
    }

    public static void main(String[] args) {
        rbt r = new rbt(80);

        r.insert(3);
        r.insert(6);
        r.inorder();

    }

}

class node {
    long data;
    Color c;
    node left, right;

    node(long data) {
        this(data, null, null);
    }

    node(long data, node left, node right) {
        this.left = left;
        this.right = right;
        this.data = data;
        this.c = Color.BLACK;
    }
}