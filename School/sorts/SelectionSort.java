package sorts;

import java.lang.reflect.Array;

public class SelectionSort
{
    public static void sort(int[] x)
    {
    	sort(x, 0);
    }
    
    public static void sort(int[] x, int index)
    {
    	if(index == x.length - 1)
    		return;
    	
    	int minIndex = index;
    	for(int i = minIndex + 1; i < x.length; i++)
    		if(x[i] < x[minIndex])
    			minIndex = i;
    	swap(x, index, minIndex);
    	sort(x, index + 1);
    }
    
    private static void swap(int[] x, int i, int j)
    {
    	int temp = x[j];
    	x[j] = x[i];
    	x[i] = temp;
    }
    
    public static void main(String args[])
    {
    	int[] arr = new int[6];
    	arr[0] = 5;
    	arr[1] = 4;
    	arr[2] = 8;
    	arr[3] = 2;
    	arr[4] = 1;
    	arr[5] = 0;
    	sort(arr);
    	for(int x : arr)
    		System.out.print(x + " ");
    }
    

}