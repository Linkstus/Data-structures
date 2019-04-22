package Jackson2;

import java.util.Arrays;

public class Jackson2{


    public static int[] fillRandomArray(int array[])
    {
        for(int i = 0; i < array.length; i++){
            array[i] =  1 + (int)(Math.random() * 1000000);
        }

        return array;
    }

    public static int linearSearch(int array[], int key, int pos){
        if(pos > array.length - 1)
        {
            return -1;
        }
        if(array[pos] == key)
        {
        	System.out.printf("found: %d\n", array[pos]);
            return pos;
        }
        return linearSearch(array, key, pos+1);
    }

    public static void main(String[] args)
    {
        int array[] = new int[1048576];
        int searchNum = 1 + (int)(Math.random() * 1000000);

        Arrays.sort(array);

        int pos = linearSearch(array, searchNum, 0);
        System.out.printf("The number %d was found at %d pos\n", searchNum, pos);
    }
}