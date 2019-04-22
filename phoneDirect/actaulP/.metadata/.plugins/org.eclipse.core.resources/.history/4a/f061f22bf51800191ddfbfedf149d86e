package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

enum Colouur{
	RED, BLACK;
};
public class personrbt{
	
	private nod current;
	private nod parent;
	private nod grand;
	private nod great;
	private nod header;
	private static nod nullnod;
	Colouur color;
	static Person blank = new Person(" ", 0);
	Person p[] = new Person[20];
	int index = 0;
	static File file = new File("src\\application\\Developer.txt");
	static //BufferedWriter writer = null;
	PrintWriter writer = null;
	
	static {
		nullnod = new nod(blank);
		nullnod.left = nullnod;
		nullnod.right = nullnod;
	}
	
	public personrbt(Person person) {
		header = new nod(person);
		header.left = nullnod;
		header.right = nullnod;
	}
	
	public void insert(Person person) {
		try {
			
			current = parent = grand = header;
			nullnod.data = person;
			
			while(current.data != person) {
				great = grand;
				grand = parent;
				parent = current;
				
				current = (person.compareTo(current.data) < 0) ? current.left: current.right;
				
				if(current.left.color == color.RED && current.right.color == color.RED)
					rotation(person);
			}
			
			if(current != nullnod)
				return;
			
			current = new nod(person, nullnod, nullnod);
			
			if(person.compareTo(parent.data) < 0)
				parent.left = current;
			else if(person.compareTo(parent.data) > 0)
				parent.right = current;
			else
				return;
			
			rotation(person);
		}catch(Exception e) {
			System.out.println("YOU ARE A BIG HUGE IDIOT");
		}
	}
	
	private void rotation(Person person) {
		current.color = color.RED;
		current.left.color = color.BLACK;
		current.right.color = color.BLACK;
		
		if(parent.color == color.RED) {
			grand.color = color.RED;
			if(person.compareTo(grand.data) < 0 != person.compareTo(parent.data) < 0) {
				parent = rotate(person, grand);
			}
			current = rotate(person, great);
			current.color = color.BLACK;
		}
		
		header.right.color = color.BLACK;
	}
	
	private nod rotate(Person person, nod parent) {
		if(person.compareTo(parent.data) < 0)
			return parent.left = person.compareTo(parent.left.data) < 0 ? rotateLeftChild(parent.left)
					:rotateRightChild(parent.left);
		else
			return parent.right = person.compareTo(parent.right.data) < 0 ? rotateLeftChild(parent.right)
					:rotateRightChild(parent.right);
	}
	
	private nod rotateLeftChild(nod n2) {
		nod n1 = n2.left;
		n2.left = n1.right;
		n1.right = n2;
		return n1;
	}
	
	private nod rotateRightChild(nod n1) {
		nod n2 = n1.right;
		n1.right = n2.left;
		n2.left = n1;
		return n2;
	}
	
	public boolean contains(Person person) {
		return contains(header.right, person);
	}
	
	public boolean contains(nod temp, Person person) {
		boolean found = false;
		try {
			while((temp != nullnod) && !found) {
				Person tempPerson = temp.data;
				
				if(person.compareTo(tempPerson) < 0)
					temp = temp.left;
				else if(person.compareTo(tempPerson) < 0)
					temp = temp.right;
				else {
					found = true;
					break;
				}
				
				found = contains(temp, person);
			}
			
		}catch(Exception e) {
			System.out.println("YOUR ARE AN IDIOT");
		}
		
		return found;
	}
	
	public void search(Person person) {
		nod temp = header.right;
		boolean found = contains(person);
		
		if(found == true) {
			char c = 'B';
			if(temp.color == color.RED)
				c = 'R';
			System.out.printf("Person: %s\t\tColor: %c", person.toString(), c);
		}
		else
			System.out.printf("Person: %s was not found!\n", person.toString());
	}
	
	public nod searchN(Person person) {
		return searchN(header.right, person);
	}
	
	private nod searchN(nod temp, Person person) {
		boolean found = false;
		
		nod searched = nullnod;
		
		while((temp != nullnod) && !found) {
			Person tempPerson = temp.data;
			
			int num = person.compareTo(tempPerson);
			
			if(num < 0)
				temp = temp.left;
			else if(num > 0)
				temp = temp.right;
			else {
				found = true;
				searched = temp;
				break;
			}
			searched = searchN(temp, person);
		}
		return searched;
	}

	
	public void inorder() {
		System.out.print("\nInOrder: \n");
		inorder(header.right);
	}
	
	private void inorder(nod temp) {
		if(temp != nullnod) {
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
	
	private void preorder(nod temp) {
		if(temp != nullnod) {
			char c = 'B';
			if(temp.color == color.RED)
				c = 'R';
			System.out.printf("Word: %s Color: %c", temp.data, c);
		}
	}
	
	public void clear() {
		header.right = nullnod;
		System.out.println("\nTree has been cleared");
	}
	
	private int treeSize(nod temp) {
		if(temp == nullnod)
			return 0;
		else {
			int count = 1;
			count += treeSize(temp.left);
			count += treeSize(temp.right);
			return count;
		}
	}
	
}

class nod{
	nod left, right;
	Person data;
	Colouur color;
	
	public nod(Person data) {
		this(data, null, null);
	}
	
	public nod(Person data, nod left, nod right) {
		this.left = left;
		this.right = right;
		this.data = data;
		this.color = color.BLACK;
	}
}
