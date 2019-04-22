package prob1;

public class Prob1Driver {

	public static void main(String[] args) {
		Person a = new Person("210 Washington Parkway", "Matthew", 7064241001L);
		Person b = new Person("182 Apple Street", "John", 2145694532L);
		Person c = new Person("591 Khyber Pass", "Kyle", 2513424567L);
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(a.getPhoneNumber());
		tree.insert(b.getPhoneNumber());
		tree.insert(c.getPhoneNumber());
		tree.insert(1958275921);
		tree.insert(2294561024L);
		tree.display(tree.root);
		
	}

}
