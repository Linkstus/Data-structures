import java.util.Arrays;
import java.util.Scanner;

public class jackson2{

    static int sumL;
    static int sumB;

    public static int[] fillRandomArray(int array[])
    {
        for(int i = 0; i < array.length; i++){
            array[i] =  1 + (int)(Math.random() * 1000000);
        }

        return array;
    }

    public static int linearSearch(int array[], int key, int pos){
        long t1 = System.nanoTime();
        long t2;

        if(pos > array.length - 1)
        {
            t2 = System.nanoTime();

            sumL += t2 - t1;
            return -1;
        }
        else if(array[pos] == key)
        {
        	t2 = System.nanoTime();

            sumL += t2 - t1;
            return pos;
        }
        t2 = System.nanoTime();

        sumL += t2 - t1;
        return linearSearch(array, key, pos+1);
    }

    public static int binarySearch(int array[], int leftPos, int rightPos, int midPos, int key)
    {
        long t1 = System.nanoTime();
        long t2;
        if(array[midPos] == key)
        {
            t2 = System.nanoTime();

            sumB += t2 - t1;
            return midPos;
        }
        else if(leftPos >= rightPos){
            t2 = System.nanoTime();

            sumB += t2 - t1;
            return -1;
        }
        else if(array[midPos] < key)
        {
            t2 = System.nanoTime();

            sumB += t2 - t1;
            return binarySearch(array, midPos + 1, rightPos, ((midPos + 1) + rightPos) / 2, key);
        }
        else{
            t2 = System.nanoTime();

            sumB += t2 - t1;
            return binarySearch(array, leftPos, midPos - 1, (leftPos + (midPos - 1)) / 2, key);
        }
    }

    public static void main(String[] args)
    {
        
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the amount of times you want to run this! ");
        int user = scan.nextInt();

        for(int i = 0; i < user; i++){

            int array[] = new int[1048576];
            
            array = fillRandomArray(array);
            
            int searchNum = 1 + (int)(Math.random() * 1000000);

            Arrays.sort(array);
            
            int posL = linearSearch(array, searchNum, 0);
            System.out.printf("Linear search: The number %d was found at pos %d with %d time\n", searchNum, posL, sumL);
            int posB = binarySearch(array, 0, array.length - 1, (array.length / 2), searchNum);
            System.out.printf("Binary search: The number %d was found at pos %d with %d time\n",searchNum, posB, sumB);

            System.out.println();
        }
    }
}