/* 
 * Itz ya boiz
 * Fall 2018
 * Data Structures - AVL Tree Word Frequency
 *_____________________________________________________________
 * This program accepts an input text file through command line
 * arguments. It then picks up words one at a time removing any 
 * non-alphabetical characters.  The words are then put into an 
 * avl tree.  The tree is ordered aplhabetically.  The tree 
 * allows createTree(), search(),  and preorder(), postorder()
 * inorder() traversals.
 * ____________________________________________________________
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;

// Node class that store key(word) and it's frequency 
class Node {
    String key;
    int frequency;
    int height; 
    Node left;
    Node right; 
  
    Node(String s) { 
        key = s; 
        frequency = 1; 
        height = 1;
        left = null;
        right = null;
    } 
} 

// Class for tree
public class avl { 
    public static void main(String[] args){ 
        avl tree = new avl();
        tree.menu(tree);
    } 

    // Start tree by making root
    Node root;

    // Function to create menu
    public void menu(avl tree){
        // Choice for menu
        int choice = 1;

        // Menu
        System.out.print("\nMenu:\n\n");
        System.out.print("1) Insert Text File\n");
        System.out.print("2) Search for a Word\n");
        System.out.print("3) Preorder Traversal\n");
        System.out.print("4) Postorder Traversal\n");
        System.out.print("5) Inorder Traversal\n");
        System.out.print("0) Exit\n");

        while (choice != 0) {
            Scanner scan = new Scanner(System.in);
            System.out.print("\nEnter your choice: ");
            while (!scan.hasNextInt()) {
                System.out.print("\nInvalid choice, try again!\n");
                System.out.print("Enter another choice: ");
                scan.next();
            }

            choice = scan.nextInt();

            // Switch statment for menu
            switch (choice) {
                case 0:
                    System.out.print("\nGoodbye.\n");
                    return;
                case 1:
                    try{
                        String file;
                        System.out.print("\nEnter name of text file: ");
                        file = scan.next();
                        createTree(tree, file);
                    }
                    catch(FileNotFoundException e){
                        System.out.printf("File not found.\n");
                    }
                    break;
                case 2:
                    try{
                        System.out.print("\nEnter a word to search for: ");
                        String word = scan.next();
                        Node temp =search(root, word);
                        System.out.printf("The key '%s' is in tree. Occurs %d time(s).\n", temp.key, temp.frequency);
                    } 
                    catch(NullPointerException e){
                        System.out.printf("Key was not found.\n");
                    }
                    break;
                case 3:
                    preorder();
                    break;
                case 4:
                    postorder();
                    break;
                case 5:
                    inorder();
                    break;
                
                default:
                    System.out.print("\nInvalid choice, try again!\n");
                    break;
            }
        }
    }

    //root 

    // Function to create avl tree
    public void createTree(avl tree, String string) throws FileNotFoundException{
        
  
        // Get file and print file name so user can make sure input
        // was correct
        File file = new File(string);
        Scanner input = new Scanner(file);
        System.out.println("Input file: " + string);

		// Gets word and deletes all non-alphabetic characters
        // which causes some string to be empty/null so they are 
        // ignored, non-empty strings are added to tree.  Any 
        // NullPointerExceptions are caught and printed.
		try{
        	while (input.hasNext()) {
            	String word = input.next().toLowerCase().replaceAll("[^a-z]", "");
            	if (word.equals("") || word.equals(" ") || word.equals(null)){
					continue;
				}
				else 
					tree.root = tree.insert(tree.root, word);
			}
		}
		catch(NullPointerException e){
			e.printStackTrace();
        }
        input.close();
    }

    // Insert a node into tree
    Node insert(Node node, String key) {
        // Normal BST insertion, but any duplicates will increment frequency
        if (node == null) 
            return (new Node(key)); 
        if (key.compareTo(node.key) < 0) 
            node.left = insert(node.left, key); 
        else if (key.compareTo(node.key) > 0) 
            node.right = insert(node.right, key); 
        else {
            (node.frequency)++;
            return node; 
        }

        // Update heights
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right)); 
  
        // Check if tree is unbalanced:
        // Left-Left case
        if (getBalance(node) > 1 && key.compareTo(node.left.key) < 0) 
            return rightRotate(node); 
  
        // Right-Right Case 
        if (getBalance(node) < -1 && key.compareTo(node.right.key) > 0) 
            return leftRotate(node); 
  
        // Left-Right Case 
        if (getBalance(node) > 1 && key.compareTo(node.left.key) > 0) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right-Left Case 
        if (getBalance(node) < -1 && key.compareTo(node.right.key) < 0) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        return node; 
    } 

    // Get balance of node
    int getBalance(Node n) { 
        if (n != null) 
            return getHeight(n.left) - getHeight(n.right);  
        else
            return 0;
    } 

    // Get height of node
    int getHeight(Node n) { 
        if (n != null) 
            return n.height;
        else 
            return 0; 
    } 
  
    // Function for right rotation 
    Node rightRotate(Node r) { 
        // Create nodes
        Node l = r.left; 
        Node t = l.right; 
  
        // Perform rotation 
        l.right = r; 
        r.left = t; 
  
        // Update heights 
        r.height = Math.max(getHeight(r.left), getHeight(r.right)) + 1; 
        l.height = Math.max(getHeight(l.left), getHeight(l.right)) + 1; 
  
        return l; 
    } 
  
    // Function for left rotation
    Node leftRotate(Node l) { 
        // Create nodes
        Node r = l.right; 
        Node t = r.left; 
  
        // Perform rotation 
        r.left = l; 
        l.right = t; 
  
        //  Update heights 
        l.height = Math.max(getHeight(l.left), getHeight(l.right)) + 1; 
        r.height = Math.max(getHeight(r.left), getHeight(r.right)) + 1; 
 
        return r; 
    } 

    // Search for an key in the tree and give it's occurence
    public Node search(Node node, String key){
        // Check if key is at node
        if (node == null || node.key.equals(key))
            return node;

        // If key is greater check left else go right
        if (node.key.compareTo(key) > 0) 
            return search(node.left, key); 
        else
            return search(node.right, key); 
    } 
    
    // Inorder traversal of the tree
    public void inorder(){
        System.out.printf("Inorder:\n");
        inorder(root);
    }

    private void inorder(Node node) { 
        if (node != null) {
            inorder(node.left); 
            System.out.print(node.key + " " + node.frequency + ", ");
            inorder(node.right);
        } 
        else return;
    } 

    // Preorder traversal of the tree
    public void preorder(){
        System.out.printf("Preorder:\n");
        preorder(root);
    }

    private void preorder(Node node) { 
        if (node != null) {
            System.out.print(node.key + " " + node.frequency + ", ");
            inorder(node.left); 
            inorder(node.right);
        } 
        else return;
    } 
    
    // Postorder traversal of the tree
    public void postorder(){
        System.out.printf("Postorder:\n");
        postorder(root);
    }

    private void postorder(Node node) { 
        if (node != null) {
            inorder(node.left); 
            inorder(node.right);
            System.out.print(node.key + " " + node.frequency + ", ");
        } 
        else return;
    } 
} 