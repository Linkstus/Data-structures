import java.awt.Color;
import java.util.Comparator;

import javax.lang.model.util.ElementScanner6;

public class redblacktree extends BinarySearchTree {
    public redblacktree() {
        this(null);
    }

    public redblacktree(comparator c) {
        super(c);
    }

    class Node extends LinkedBinaryTreeNode {
        Color color = Color.black;

        public Node(Object data) {
            super(data);
        }
    }

    public void add(Object data) {
        if (root == null)
            root = new Node(data);
        BinaryTreeNode n = root;

        while (true) {
            int comparisonResult = compare(data, n.getData());
            if (comparisonResult == 0) {
                n.setData(data);
                return;
            } else if (comparisonResult < 0) {
                if (n.getLeft() == null) {
                    n.setLeft(new Node(data));
                    adjustAfterInsertion((Node) n.getLeft());
                    break;
                }
                n = n.getLeft();
            } else {
                if (n.getRight() == null) {
                    n.setRight(new Node(data));
                    adjustAfterInsertion((Node) n.getRight());
                    break;
                }
                n = n.getRight();
            }
        }
    }

    public void remove(Object data) {
        Node node = (Node) nodeContaining(data);
        if (node == null)
            return;
        else if (node.getLeft() != null && node.getRight() != null) {
            BinaryTreeNode predecessor = predecessor(node);
            node.setData(predecessor.getData());
            node = (Node) predecessor;
        }

        Node pullUp = leftOf(node) == null ? rightOf(node) : leftOf(node);
        if (pullUP != null) {
            if (node == root)
                setRoot(pullUp);
            else if (node.getParent().getLeft() == node)
                node.getParent().setLeft(pullUp);
            else
                node.getParent().setRight(pullUp);
        } else if (node == root)
            setRoot(null);
        else {
            if (isBlack(node)) {
                adjustAfterRemoval(node);
            }
            node.removeFromParent();
        }
    }

    private void adjustAfterInsertion(Node n) {
        setColor(n, Color.red);

        if (n != null && n != root && isRed(parentOf(n))) {
            if (isRed(siblingOf(parentOf(n)))) {
                setColor(parentOf(n), Color.black);
                setColor(siblingOf(parentOf(n)), Color.black);
                setColor(grandparentOf(n), Color.red);
                adjustAfterInsertion(gradParentOf(n));
            } else if (parentOf(n) == leftOf(grandparentOf(n))) {
                if (n == rightOf(parentOf(n))) {
                    rotateLeft(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateRight(greandparentOf(n));
            }

            else if (parentOf(n) == rightOf(grandparentOf(n))) {
                if (n == leftOf(parentOf(n))) {
                    rotateRight(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateLeft(grandparentOf(n));
            }
        }
        setColor((Node) root, Color.black);
    }

    private void adjustAfterRemoval(Node n) {
        while (n != root && isBlack(n)) {
            if (n == leftOf(parentOf(n))) {
                Node sibling = rightOf(parentOf(n));
                if (isRed(sibling)) {
                    setColor(sibling, Color.black);
                    setColor(parentOf(n), Color.red);
                    rotateLeft(parentOf(n));
                    sibling = rightOf(parentOf(n));
                }
                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
                    setColor(sibling, Color.red);
                    n = parentOf(n);
                } else {
                    if (isBlack(rightOf(sibling))) {
                        setColor(leftOf(sibling), Color.black);
                        setColor(sibling, Color.red);
                        rotateRight(sibling);
                        sibling = rightOf(parentOf(n));
                    }
                    setColor(sibling, colorOf(parentOf(n)));
                    setColor(parentOf(n), Color.black);
                    setColor(rightOf(sibling), Color.black);
                    rotateLeft(parentOf(n));
                    n = (Node) root;
                }
            } else {
                Node sibling = leftOf(parentOf(n));
                if (isRed(sibling)) {
                    setColor(sibling, Color.black);
                    setColor(parentOf(n), Color.red);
                    rotateRight(parentOf(n));
                    sibling = leftOf(parentOf(n));
                }
                if (isBlack(leftOf(sibling)) && isBlack(rightOf(sibling))) {
                    setColor(sibling, Color.red);
                    n = parentOf(n);
                } else {
                    if (isBlack(leftOf(sibling))) {
                        setColor(rightOf(sibling), Color.black);
                        setColor(sibling, Color.red);
                        rotateLeft(sibling);
                        sibling = leftOf(parentOf(n));
                    }
                    setColor(sibling, colorOf(parentOf(n)));
                    setColor(parentOf(n), Color.black);
                    setColor(leftOf(sibling), Color.black);
                    rotateRight(parentOf(n));
                    n = (Node) root;
                }
            }
        }
        setColor(n, Color.black);
    }

    private Color colorOf(Node n) {
        return n == null ? Color.black : n.color;
    }

    private boolean isRed(Node n) {
        return n != null && colorOf(n) == Color.red;
    }

    private boolean isBlack(Node n) {
        return n == null || colorOf(n) == Color.black;
    }

    private void setColor(Node n, Color c) {
        if (n != null)
            n.color = c;
    }

    private Node parentOf(Node n) {
        return n == null ? null : (Node) n.getParent();
    }

    private Node grandparentOf(Node n) {
        return (n == null || n.getParent() == null) ? null : (Node) n.getParent().getParent();
    }

    private Node siblingOf(Node n) {
        return (n == null || n.getParent() == null) ? null
                : (n == n.getParent().getLeft()) ? (Node) n.getParent().getRight() : (Node) n.getParent().getLeft();
    }

    private Node leftOf(Node n) {
        return n == null ? null : (Node) n.getLeft();
    }

    private Node rightOf(Node n) {
        return n == null ? null : (Node) n.getRight();
    }

    public static void main(String[] args) {
        redblacktree rbt = new redblacktree();

        rbt.add(58);
    }
}