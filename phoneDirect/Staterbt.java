import java.util.Comparator;

enum Colour {
    RED, BLACK;
}

public class Staterbt implements Comparable<State> {
    static State s = new State(null);
    Node current;
    Node parent;
    Node grandP;
    Node greatGP;
    Node header;
    static Node nullNode;
    Colour color;

    static {
        nullNode = new Node(s);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }

    Staterbt(State negInf) {
        header = new Node(negInf);
        header.left = nullNode;
        header.right = nullNode;
    }

    public int compareState(State item1, Node item2) {
        if (item1.compareTo(item2.data) < 0)
            return -1;
        else if (item1.compareTo(item2.data) > 0)
            return 1;
        else
            return 0;

    }

    boolean isEmpty() {
        return header.right == nullNode;
    }

    void makeEmpty() {
        header.right = nullNode;
    }

    void insert(State item) {
        current = parent = grandP = header;
        nullNode.data = item;

        while (current.data != item) {
            greatGP = grandP;
            grandP = parent;
            parent = current;
            current = compareState(item, current) == -1 ? current.left : current.right;

            if (current.left.color == color.RED && current.right.color == color.RED)
                rotation(item);
        }

        if (current != nullNode)
            return;
        current = new Node(item, nullNode, nullNode);

        if (compareState(item, parent) == -1)
            parent.left = current;
        else
            parent.right = current;
        rotation(item);
    }

    void rotation(State item) {
        current.color = color.RED;
        current.left.color = color.BLACK;
        current.right.color = color.BLACK;

        if (parent.color == color.RED) {
            grandP.color = color.RED;
            if (compareState(item, grandP) != compareState(item, parent))
                parent = rotate(item, grandP);
            current = rotate(item, greatGP);
            current.color = color.BLACK;
        }
        header.right.color = color.BLACK;
    }

    Node rotate(State item, Node parent) {
        if (compareState(item, parent) == -1)
            return parent.left = compareState(item, parent.left) == -1 ? rotateLeftChild(parent.left)
                    : rotateRightChild(parent.left);
        else
            return parent.right = compareState(item, parent.right) == -1 ? rotateLeftChild(parent.right)
                    : rotateRightChild(parent.right);
    }

    Node rotateLeftChild(Node k2) {
        Node k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    Node rotateRightChild(Node k1) {
        Node k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    int countNodes() {
        return countNodes(header.right);
    }

    int countNodes(Node r) {
        if (r == nullNode)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }

    boolean search(State val) {
        return search(header.right, val);
    }

    boolean search(Node r, State val) {
        boolean found = false;

        while ((r != nullNode) && !found) {
            if (compareState(val, r) == -1)
                r = r.left;
            else if (compareState(val, r) == 1)
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

    void inorder(Node r) {
        if (r != nullNode) {
            inorder(r.left);
            char c = 'B';
            if (r.color == color.RED)
                c = 'R';
            System.out.print(r.data + "" + c + " ");
            inorder(r.right);
        }
    }

    void preorder() {
        preorder(header.right);
    }

    void preorder(Node r) {
        if (r != nullNode) {
            char c = 'B';
            if (r.color == color.RED)
                c = 'R';
            System.out.print(r.data + "" + c + " ");
            preorder(r.left);
            preorder(r.right);
        }
    }

    void postorder() {
        postorder(header.right);
    }

    void postorder(Node r) {
        if (r != nullNode) {
            postorder(r.left);
            postorder(r.right);
            char c = 'B';
            if (r.color == color.RED)
                c = 'R';
            System.out.print(r.data + "" + c + " ");
        }
    }

    public static void main(String[] args) {
        State g = new State("Georgia");
        State a = new State("Alabama");
        State f = new State("Florida");

        Staterbt rbt = new Staterbt(g);
        rbt.insert(g);
        rbt.insert(a);
        rbt.insert(f);

        rbt.inorder();
        rbt.preorder();
    }

}

public class Node {

    Node left, right;
    State data;
    Colour color;

    public Node(State s) {
        this(s, null, null);
    }

    public Node(State s, Node left, Node right) {
        this.data = s;
        this.left = left;
        this.right = right;
        this.color = color.BLACK;
    }
}

public class State {
    String name;
    Numberrbt nrbt;

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}