package sorts;

import java.util.Comparator;

public class InsertionSort
{
	public static void main(String args[])
	{
		int[] x = new int[5];
		x[0] = 5;
		x[1] = 4;
		x[2] = 34;
		x[3] = 2;
		x[4] = 1;
		for(int i : x)
			System.out.print(i + " ");
		System.out.println("");
		sort(x);
		for(int i : x)
			System.out.print(i + " ");
	}
	
//	public static <T> void sort(T[] x, Comparator<T> c)
//	{
//		for(int i = 1; i < x.length; i++)
//		{
//			T temp = x[i];
//			for(int j = i - 1; j >= 0; j--)
//			{
//				if(c.compare(temp, x[j]) < 0)
//					x[j] = temp;
//			}
//		}
//	}
	
	
	public static void sort(int[] x)
	{
		for(int i = 1; i < x.length; i++)
		{
			int temp = x[i];
			int j = i - 1;
			while ((j > -1) && (x[i] > temp))
			{
				x[j + 1] = x[j];
				j--;
			}
		}
	}

}
