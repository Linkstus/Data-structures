

import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class testing {

	public int num;
	public int value;
	public char size;
	public int arraySize;
	public char retry;
	
	public static void main(String[] args) {
		
		testing test = new testing();
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		
		System.out.println("A:10 B:100 C:1000 D:10000 E:100000");
		System.out.print("Size of Array:");
		test.size = scan.next().charAt(0);
		
		if(test.size == 'A' || test.size == 'a') {
			test.arraySize = 10;
		}
		else if(test.size == 'B' || test.size == 'b') {
			test.arraySize = 100;
		}
		else if(test.size == 'C' || test.size == 'c') {
			test.arraySize = 1000;
		}
		else if(test.size == 'D' || test.size == 'd') {
			test.arraySize = 10000;
		}
		else if(test.size == 'E' || test.size == 'e') {
			test.arraySize = 100000;
		}
		else {
			System.out.print("\nInvalid choice, try again\n");
			main(args);
		}
		
		test.value = rand.nextInt(100000);
		
		int [] array = new int[test.arraySize];
		
		for(int i = 0; i < test.arraySize; i++) {
			test.num = rand.nextInt(100000);
			array[i] = test.num;
		}
		
		Arrays.sort(array);
		
		long T1 = System.nanoTime();
		test.linearSearch(array, test.value);
		long T2 = System.nanoTime();
		long T3 = System.nanoTime();
		test.binarySearch(array, test.value);
		long T4 = System.nanoTime();
		
		System.out.printf("\nArray size %d:\n", test.arraySize);
		System.out.printf("Linear search time: %d\t", T2 - T1);
		System.out.printf("Binary search time: %d", T4 - T3);
		
		System.out.print("\n\nRun another (Y or N):");
		test.retry = scan2.next().charAt(0);
		System.out.print("\n");
		
		if(test.retry == 'Y' || test.retry == 'y'){
			main(args);
		}
		else {
			scan.close();
			scan2.close();
		}
		
	}
	
	public boolean linearSearch(int [] numbers, int value) {
		
		boolean found = false;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] == value) {
				found = true;
				break;
			}
			else {
				continue;
			}
		}
		return found;
	}
	
	public boolean binarySearch(int [] numbers, int value) {
		
		int first = 0;
		int last = numbers.length - 1;
		int middle = (first + last) / 2;
		boolean found = false;
		
		while(first < last) {
			if(numbers[middle] < value) {
				first = middle + 1;
			}
			else if(numbers[middle] == value) {
				found = true;
				break;
			}
			else {
				last = middle - 1;
			}
			middle = (first + last) / 2;
		}
		return found;	
	}
	
}