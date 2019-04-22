package tamkil;
import java.util.*;

import application.Person;

import java.lang.Long;
import java.lang.String;
import java.io.*;


import java.io.*;
import java.lang.Long;
import java.lang.String;

public class AVLtree2 {
	public long getBalance(Node n) {
		if (n != null) {
			return (getHeight(n.left) - getHeight(n.right));
		}
		return 0;
	}

	public long getHeight(Node n) {
		if (n != null) {
			return n.height;
		}
		return 0;
	}

	public Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		// Rotation
		x.right = y;
		y.left = T2;

		// update their heights
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

		return x;
	}

	public Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		// Rotation
		y.left = x;
		x.right = T2;

		// update their heights
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

		return y;
	}

	public Node insert(Node node, String num) {
      //num.split(" ");
      String data = (num.substring(0,10));
      
		if (node == null) 
            return (new Node(num)); 
      if (num.compareTo(node.data) < 0) 
            node.left = insert(node.left, num);
      if (num.compareTo(node.data) > 0) 
            node.right = insert(node.right, num); 
        // Update heights
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right)); 
  
        // Check if tree is unbalanced:
        // Left-Left case
        if (getBalance(node) > 1 && data.compareTo(node.left.data) < 0) 
            return rightRotate(node); 
  
        // Right-Right Case 
        if (getBalance(node) < -1 && data.compareTo(node.right.data) > 0) 
            return leftRotate(node); 
  
        // Left-Right Case 
        if (getBalance(node) > 1 && data.compareTo(node.left.data) > 0) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right-Left Case 
        if (getBalance(node) < -1 && data.compareTo(node.right.data) < 0) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        }

		return node;
	}

	public void inorder(Node root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(" " + root.data);
			inorder(root.right);
		}
	}

	public static void main(String args[])throws Exception {
		Node root = null;
		AVLtree2 i = new AVLtree2();
      File file = new File("src\\tamkil\\phone.txt");
      File file1 = new File("src\\tamkil\\developer's log");
      BufferedReader br = new BufferedReader(new FileReader(file));
      PrintWriter writer = new PrintWriter(file1);
      long T1, T2;
      long time1, time2;
      String number;
     
      String st;
      T1 = System.nanoTime();
      writer.println("" + java.time.LocalDate.now() + "\n");
      
      while ((st = br.readLine()) != null) { 
    	  	
    	  	time1 = System.currentTimeMillis();
			
    	  	root = i.insert(root, st);
		
			time2 = System.currentTimeMillis();
			
			String start = String.valueOf(time1);
			String end = String.valueOf(time2);
			String total = String.valueOf(time2 - time1);
			
			writer.print("\nInsert People: ");
			writer.print(" Starting time: " + start);
			writer.print(" End time: " + end);
			writer.print(" Time taken: " + total + "\n");
			
         
      }
      T2 = System.nanoTime(); 
     /* number = "9123397654";
		root = i.insert(root, number);
      number = "9122295438";
		root = i.insert(root, number);
      number = "8163478235";
		root = i.insert(root, number);
      number = "8163757766";
		root = i.insert(root, number);
      number = "8162290487";
		root = i.insert(root, number);
      number = "9025559936";
		root = i.insert(root, number);
      number = "9025557029";
		root = i.insert(root, number);
      number = "9123758549";
		root = i.insert(root, number);
      number = "870399394";
		root = i.insert(root, number);
      number = "8709642319";
		root = i.insert(root, number); */
		System.out.print("Inorder Traversal of Constructed AVL Tree :");
		time1 = System.currentTimeMillis();
		
		i.inorder(root);
	
		time2 = System.currentTimeMillis();
		
		String start = String.valueOf(time1);
		String end = String.valueOf(time2);
		String total = String.valueOf(time2 - time1);
		
		writer.print("\nInsert inorder: ");
		writer.print(" Starting time: " + start);
		writer.print(" End time: " + end);
		writer.print(" Time taken: " + total + "\n");
		
		System.out.println("\n New Root of AVL Tree is : " + root.data);
      System.out.println("Time taken in nanoseconds is: " + (T2-T1) + " nanoseconds." );
      
      
      
      writer.close();
	}
}

class Node {
	String data;
	Node left;
	Node right;
	long height;

	public Node(String data) {
		this.data = data;
		height = 1;
	}
}