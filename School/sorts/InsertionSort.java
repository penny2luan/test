package sorts;

import java.util.Comparator;

public class InsertionSort
{
	public static void main(String args[])
	{
		int[] x = new int[5];
		x[0] = 5;
		x[1] = 3;
		x[2] = 8;
		x[3] = 6;
		x[4] = 1;
		for(int i : x)
			System.out.print(i + " ");
		System.out.println("");
		sort(x);
		for(int j : x)
			System.out.print(j + " ");
	}
	
	public static void sort(int[] x)
	{
		for(int i = 0; i < x.length; i++)
		{
			int temp = x[i];
			int j = i - 1;
			while(j >= 0 && temp < x[j])
			{
				x[j+1] = x[j];
				j--;
			}
			x[j+1] = temp;
		}
	}
	

}
