/* 
 * Itz ya boiz
 * Fall 2018
 * Data Structures - AVL Tree Word Frequency
 *_____________________________________________________________
 * This program accepts an input text file through command line
 * arguments. The file is then read a picks up words one at a time
 * removing any any non-alphabetical characters.  The words are then
 * put into an avl tree.  The tree is ordered in aplhabetically.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import java.lang.String;

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
class avl { 
    public static void main(String[] args) throws FileNotFoundException { 
        // Make avl tree
        avl tree = new avl(); 
  
        // Get file and print file name so user can make sure input
        // was correct
        Scanner input = new Scanner(new File(args[0]));
        System.out.println("Input file: " + args[0]);

		// Gets word and delets all non-alphabetic characters
		// which causes some string to be "" so they are ignored
        // non-empty strings are added to tree.  Any 
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
        
        // Print out inorder of tree
        System.out.println("Inorder traversal: "); 
        tree.inorder(tree.root); 
    } 

    // Start tree by making root
    Node root; 

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

    // Inorder traversal of the tree
    void inorder(Node node) { 
        if (node != null) {
            inorder(node.left); 
            System.out.print(node.key + " " + node.frequency + ", ");
            inorder(node.right);
        } 
    } 
} 