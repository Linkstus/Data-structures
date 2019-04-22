public class State {
    String name;
    Person n = new Person(" ", 0);
    genericrbt<Person> grbt = new genericrbt<>(n);

    public State(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isEmpty() {
        return grbt.isEmpty();
    }

    public void makeEmpty() {
        grbt.makeEmpty();
    }

    public void insert(Person p) {
        grbt.insert(P);
    }

    public int countNodes() {
        return grbt.countNodes();
    }

    public boolean search(Person p) {
        return grbt.search(p);
    }

    public void inorder() {
        return grbt.inorder();
    }

    public void preorder() {
        return grbt.preorder();
    }

    public void postorder() {
        return grbt.postorder();
    }

}