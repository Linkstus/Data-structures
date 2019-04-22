class rbt {
    node current;
    node parent;
    node grandP;
    node greatGP;
    node header;
    static node nullNode;
    // Color colour;

    static {
        nullNode = new node(0);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }

    final int BLACK = 1;
    final int RED = 0;

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

            if (current.left.color == RED && current.right.color == RED)
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
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED) {
            grandP.color = RED;
            if (item < grandP.data != item < parent.data)
                parent = rotate(item, grandP);
            current = rotate(item, greatGP);
            current.color = BLACK;
        }
        header.right.color = BLACK;
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
        k2.left = k1;
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
                r = r.left;
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
            if (r.color == 0)
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
            if (r.color == 0)
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
            if (r.color == 0)
                c = 'R';
            System.out.print(r.data + "" + c + " ");
        }
    }

    public static void main(String[] args) {
        rbt r = new rbt(Long.MIN_VALUE);

        r.insert(2294151320L);
        r.insert(2298349198L);
        r.insert(2298349197L);
        r.insert(2717674710L);
        r.insert(2993758417L);
        r.insert(2293001735L);
        r.insert(9125923913L);

        System.out.println("Phonenumbers in order");
        r.inorder();

        rbt r1 = new rbt(1);

        r1.insert(5556583751L);
        r1.insert(9926648374L);
        r1.insert(2298435566L);
        r1.insert(3256667394L);
        r1.insert(9828347463L);
        r1.insert(-1111111111L);
        r1.insert(0000000000L);

        System.out.println("\nrandom numbers in order traversal");
        r1.inorder();
        System.out.println("\nrandom numbers pre order traversal");
        r1.preorder();
        System.out.println("\n\n\n");

        rbt r2 = new rbt(2);

        r2.insert(483);
        r2.insert(001);
        r2.insert(1384);
        r2.insert(2);
        r2.insert(49);
        r2.insert(-2);
        r2.insert(10);

        System.out.println("\nrandom numbers in order traversal");
        r2.inorder();
        System.out.println("\nrandom numbers pre order traversal");
        r2.preorder();

    }

}

class node {
    node left, right;
    long data;
    int color;

    public node(long data) {
        this(data, null, null);
    }

    node(long data, node lt, node rt) {
        this.left = lt;
        this.right = rt;
        this.data = data;
        this.color = 1;
    }
}