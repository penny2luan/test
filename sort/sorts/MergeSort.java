package sorts;

public class MergeSort
{
	public static void sort(int[] a)
	{
		
	}
	
	private static void sort(int[] a, int low, int high)
	{
	    // check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
	      // Get the index of the element which is in the middle
	      int middle = low + (high - low) / 2;
	      // Sort the left side of the array
	      sort(a, low, middle);
	      // Sort the right side of the array
	      sort(a, middle + 1, high);
	      // Combine them both
	      merge(a, low, middle, high);
	    }
	}

	private static void merge(int[] a, int low, int mid, int high)
	{
		  int[] b = new int[mid + 1 - low];
		  System.arraycopy(a, low, b, 0, b.length);

		  int aLowerIndex = low, bIndex = 0, aHigherIndex = mid + 1;
		  while (aLowerIndex < aHigherIndex && aHigherIndex <= high)
		  {
		    if (a[aHigherIndex] < b[bIndex])
		      a[aLowerIndex++] = a[aHigherIndex++];
		    else
		      a[aLowerIndex++] = b[bIndex++];
		  }

		  while (aLowerIndex < aHigherIndex)
		    a[aLowerIndex++] = b[bIndex++];
		}
}