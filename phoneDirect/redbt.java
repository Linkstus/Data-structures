class redbt {
    node current;
    node parent;
    node grandP;
    node greatGP;
    node header;
    static node nullNode;

    static {
        nullNode = new node(" ");
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }

    final int BLACK = 1;
    final int RED = 0;

    public redbt(String negInf) {
        header = new node(negInf);
        header.left = nullNode;
        header.right = nullNode;
    }

    public boolean isEmpty() {
        return header.right == nullNode;
    }

    public void makeEmpty() {
        header.right = nullNode;
    }

    public void insert(String d) {
        current = parent = grandP = header;
        nullNode.data = d;

        while (current.data != d) {
            greatGP = grandP;
            grandP = parent;
            parent = current;
            current = (d.compareTo(current.data) == -1) ? current.left : current.right;

            if (current.left.color == RED && current.right.color == RED)
                rotation(d);

        }
        if (current != nullNode)
            return;
        current = new node(d, nullNode, nullNode);

        if (d.compareTo(parent.data) == -1)
            parent.left = current;
        else
            parent.right = current;
        rotation(d);
    }

    public void rotation(String d) {
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED) {
            grandP.color = RED;
            if ((d.compareTo(grandP.data) == -1) != (d.compareTo(parent.data) == -1))
                parent = rotate(d, grandP);
            current = rotate(d, greatGP);
            current.color = BLACK;
        }
        header.right.color = BLACK;
    }

    public node rotate(String d, node parent) {
        if (d.compareTo(parent.data) == -1)
            return parent.left = d.compareTo(parent.left.data) == -1 ? rotateLeftChild(parent.left)
                    : rotateRightChild(parent.left);
        else
            return parent.right = d.compareTo(parent.right.data) == -1 ? rotateLeftChild(parent.right)
                    : rotateRightChild(parent.right);
    }

    public node rotateLeftChild(node k2) {
        node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    public node rotateRightChild(node k1) {
        node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    public int countNodes() {
        return countNodes(header.right);
    }

    public int countNodes(node r) {
        if (r == nullNode)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }

    public boolean search(String val) {
        return search(header.right, val);
    }

    public boolean search(node r, String val) {
        boolean found = false;

        while ((r != nullNode) && !found) {
            String rval = r.data;
            if (val.compareTo(rval) == -1)
                r = r.left;
            else if (val.compareTo(rval) == 1)
                r = r.right;
            else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }

    public void inorder() {
        inorder(header.right);
    }

    public void inorder(node r) {
        if (r != nullNode) {
            inorder(r.left);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.println(r.data + " " + c + " ");
            inorder(r.right);
        }
    }

    public void preorder() {
        preorder(header.right);
    }

    public void preorder(node r) {
        if (r != nullNode) {
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.println(r.data + " " + c + " ");
            preorder(r.left);
            preorder(r.right);
        }
    }

    public void postorder() {
        postorder(header.right);
    }

    public void postorder(node r) {
        if (r != nullNode) {
            postorder(r.left);
            postorder(r.right);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.println(r.data + " " + c + " ");
        }
    }

    public static void main(String[] args) {
        redbt r = new redbt(" ");

        r.insert("James");
        r.insert("Kim");
        r.insert("Oscar");
        r.insert("Alicia");
        r.insert("David");
        r.insert("Bailey");
        r.insert("Cat");

        r.inorder();

    }
}

class node {
    node left, right;
    String data;
    int color;

    public node(String data) {
        this(data, null, null);
    }

    public node(String data, node left, node right) {
        this.left = left;
        this.right = right;
        this.data = data;
        this.color = 1;
    }
}