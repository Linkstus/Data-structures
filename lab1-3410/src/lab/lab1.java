package lab;

public class lab1 {

	public static void main(String[] args)
	{
		int [][] md_array = new int[5][10];
		
		int num = 0;
		
		for(int i = 0; i < md_array.length; i++)
		{
			for(int j = 0; j < md_array[i].length; j++)
			{
				md_array[i][j] = num;
				System.out.printf("%d\t", md_array[i][j]);
				num++;
			}
			System.out.println();
		}
	}
}
