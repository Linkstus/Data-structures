package application;

enum Colour{
	RED, BLACK;
};
public class staterbt {
	
	private nodee current;
	private nodee parent;
	private nodee grand;
	private nodee great;
	private nodee header;
	private static nodee nullnodee;
	Colour color;
	static State blank = new State(" ");
	
	
	static {
		nullnodee = new nodee(blank);
		nullnodee.left = nullnodee;
		nullnodee.right = nullnodee;
	}
	
	public staterbt(State state) {
		header = new nodee(state);
		header.left = nullnodee;
		header.right = nullnodee;
	}
	
	public void insert(State state) {
		current = parent = grand = header;
		nullnodee.data = state;
		
		while(current.data != state) {
			great = grand;
			grand = parent;
			parent = current;
			
			current = (state.getName().compareTo(((State) current.data).getName()) < 0) ? current.left: current.right;
			
			if(current.left.color == color.RED && current.right.color == color.RED)
				rotation(state);
		}
		
		if(current != nullnodee)
			return;
		
		current = new nodee(state, nullnodee, nullnodee);
		
		if(state.getName().compareTo(((State)parent.data).getName()) < 0)
			parent.left = current;
		else 
			parent.right = current;
		
		rotation(state);
	}
	
	private void rotation(State state) {
		current.color = color.RED;
		current.left.color = color.BLACK;
		current.right.color = color.BLACK;
		
		if(parent.color == color.RED) {
			grand.color = color.RED;
			if((state.getName().compareTo(((State)grand.data).getName()) < 0) != state.getName().compareTo(((State)parent.data).getName()) < 0) {
				parent = rotate(state, grand);
			}
			current = rotate(state, great);
			current.color = color.BLACK;
		}
		
		header.right.color = color.BLACK;
	}
	
	private nodee rotate(State state, nodee parent) {
		if(state.getName().compareTo(((State)parent.data).getName()) < 0)
			return parent.left = state.getName().compareTo(((State)parent.left.data).getName()) < 0 ? rotateLeftChild(parent.left)
					:rotateRightChild(parent.left);
		else
			return parent.right = state.getName().compareTo(((State)parent.right.data).getName()) < 0 ? rotateLeftChild(parent.right)
					:rotateRightChild(parent.right);
	}
	
	private nodee rotateLeftChild(nodee n2) {
		nodee n1 = n2.left;
		n2.left = n1.right;
		n1.right = n2;
		return n1;
	}
	
	private nodee rotateRightChild(nodee n1) {
		nodee n2 = n1.right;
		n1.right = n2.left;
		n2.left = n1;
		return n2;
	}
	
	public boolean contains(State state) {
		return contains(header.right, state);
	}
	
	public boolean contains(nodee temp, State state) {
		boolean found = false;
		
		while((temp != nullnodee) && !found) {
			State tempState = temp.data;
			
			if(state.getName().compareTo(tempState.getName()) < 0)
				temp = temp.left;
			else if(state.getName().compareTo(tempState.getName()) < 0)
				temp = temp.right;
			else {
				found = true;
				break;
			}
			
			found = contains(temp, state);
		}
		
		return found;
	}
	
	public nodee searchN(State state) {
		return searchN(header.right, state);
	}
	
	private nodee searchN(nodee temp, State state) {
		boolean found = false;
		
		nodee searched = nullnodee;
		
		while((temp != nullnodee) && !found) {
			State tempState = temp.data;
			int num = state.getName().compareTo(tempState.getName()); 
			if(num < 0)
				temp = temp.left;
			else if(num < 0)
				temp = temp.right;
			else {
				found = true;
				searched = temp;
				break;
			}
			
			searched = searchN(temp, state);
		}
		
		return searched;
	}
	
	public void inorder() {
		System.out.print("\nInorder: \n");
		inorder(header.right);
	}
	
	private void inorder(nodee temp) {
		if(temp != nullnodee) {
			inorder(temp.left);
			char c = 'B';
			if(temp.color == color.RED) {
				c = 'R';
			}
			System.out.printf("Word: %s Color: %c", temp.data, c);
			inorder(temp.right);
		}
	}
	
	public void preorder() {
		System.out.print("\nPreOrder:\n");
		preorder(header.right);
	}
	
	private void preorder(nodee temp) {
		if(temp != nullnodee) {
			char c = 'B';
			if(temp.color == color.RED)
				c = 'R';
			System.out.printf("Word: %s Color: %c", temp.data, c);
		}
	}
	
	public void clear() {
		header.right = nullnodee;
		System.out.println("\nTree has been cleared");
	}
	
	public void countnodees() {
		int count = treeSize(header.right);
		System.out.printf("\nThere are %d nodees in the tree");
	}
	
	private int treeSize(nodee temp) {
		if(temp == nullnodee)
			return 0;
		else {
			int count = 1;
			count += treeSize(temp.left);
			count += treeSize(temp.right);
			return count;
		}
	}

}

class nodee{
	nodee left, right;
	State data;
	Colour color;
	
	public nodee(State data) {
		this(data, null, null);
	}
	
	public nodee(State data, nodee left, nodee right) {
		this.left = left;
		this.right = right;
		this.data = data;
		this.color = color.BLACK;
	}
}
