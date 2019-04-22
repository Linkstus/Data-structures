package application;

public class State {
    String name;
    personrbt prbt = new personrbt(new Person(" ", 0));

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
    
    public void insert(Person p) {
        prbt.insert(p);
    }
    public boolean search(Person p) {
        return prbt.contains(p);
    }
    
    public nod searchNode(Person p) {
    	return prbt.searchN(p);
    }

    public void inorder() {
        prbt.inorder();
    }

    public void preorder() {
        prbt.preorder();
    }

}