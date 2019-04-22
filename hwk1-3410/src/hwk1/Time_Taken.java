package hwk1;

public class Time_Taken 
{

	final static int TEN = 10;
	final static int HUNDRED = 100;
	final static int THOUSAND = 1000;
	final static int TENTHOUSAND = 10000;
	final static int HUNDREDTHOUSAND = 100000;
	
	static void linearSearch(int[] array, int num)
	{	
		int pos = -1;
		
		long t1 = System.nanoTime();
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == num)
			{
				pos = i;
				break;
			}
		}
		long t2 = System.nanoTime();
		
		if(pos != -1) 
		{
			System.out.printf("Time:%d		Number looking for:%d		Position found at: %d\n\n", t2 - t1, num, pos);
		}	
		
		else
		{
			System.out.printf("Time:%d		Number looking for:%d		Position found at: %d\n\n", t2 - t1, num, pos);
		}
	}

	static void binarySearch(int[] array, int num)
	{	
		int firstPos = 0;
		int lastPos = array.length - 1;
		int midPos = (lastPos - firstPos) / 2;
		int pos = -1;
		
		long t1 = System.nanoTime();
		while(firstPos < lastPos)
		{
			if(num > array[midPos])
			{
				firstPos = midPos + 1;
				midPos = (lastPos + firstPos) / 2;
			}
			else if(num < array[midPos])
			{
				lastPos = midPos;
				midPos = (lastPos + firstPos) / 2;
			}
			else
			{
				pos = midPos;
				break;
			}
		}
		long t2 = System.nanoTime();
		
		if(pos != - 1)
		{
			System.out.printf("Time:%d		Number looking for:%d		Position found at: %d\n\n", t2 - t1, num, pos);
		}
		else
		{
			System.out.printf("Time:%d		Number looking for:%d		Position found at: %d\n\n", t2 - t1, num, pos);
		}
		//System.out.println("Hello");
	}
	
	static void sortArray(int[] array)
	{
		for(int i = 0; i < array.length - 1; i++)
		{
			int currentMin = array[i];
			int currentMinIndex = i;
			
			for(int j = i + 1; j < array.length; j++)
			{
				if(currentMin > array[j])
				{
					currentMin = array[j];
					currentMinIndex = j;
				}
			}
			
			if(currentMinIndex != i)
			{
				array[currentMinIndex] = array[i];
				array[i] = currentMin;
			}
		}
	}
	
	static int[] arrayRandomFill(int[] arr)
	{
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = (int)(Math.random() * 100000) + 1;
		}
		
		return arr;
	}
	public static void main(String[] args) 
	{
		int[] array1 = new int[TEN];
		
		arrayRandomFill(array1);
		
		int rand = (int)(Math.random() * 100000) + 1;

		sortArray(array1);
		
		System.out.println("Linear search for Ten elements");
		linearSearch(array1, rand);
		System.out.println("Binary search for Ten elements");
		binarySearch(array1, rand);
		
		array1 = new int[HUNDRED];
		
		arrayRandomFill(array1);
		
		rand = (int)(Math.random() * 100000) + 1;

		sortArray(array1);
		
		System.out.println("Linear search for hundred elements");
		linearSearch(array1, rand);
		System.out.println("Binary search for hundred elements");
		binarySearch(array1, rand);
		
		array1 = new int[THOUSAND];
		
		arrayRandomFill(array1);
		
		rand = (int)(Math.random() * 100000) + 1;

		sortArray(array1);
		
		System.out.println("Linear search for thousand elements");
		linearSearch(array1, rand);
		System.out.println("Binary search for thousand elements");
		binarySearch(array1, rand);
		
		array1 = new int[TENTHOUSAND];
		
		arrayRandomFill(array1);
		
		rand = (int)(Math.random() * 100000) + 1;

		sortArray(array1);
		
		System.out.println("Linear search for ten thousand elements");
		linearSearch(array1, rand);
		System.out.println("Binary search for ten thousand elements");
		binarySearch(array1, rand);
		
		array1 = new int[HUNDREDTHOUSAND];
		
		arrayRandomFill(array1);
		
		rand = (int)(Math.random() * 100000) + 1;

		sortArray(array1);
		
		System.out.println("Linear search for hundred thousand elements");
		linearSearch(array1, rand);
		System.out.println("Binary search for hundred thousand elements");
		binarySearch(array1, rand);
		//System.out.println("Done");
	}

}
