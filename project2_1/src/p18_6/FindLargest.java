package p18_6;

public class FindLargest
{
        public static void main(String[] args)
        {
                int[] array = {4,1,7,1,0,9,7};
                System.out.println(findLargest(array));
        }
        
        public static int findLargest(int[] a)
        {
        	if(a.length == 1)
        		return a[0];
        	int[] array = ConstrainedCopy(a, 0, array, 0, a.length-1);
        	if(findLargest(array) > a[a.length-1])
        		return findLargest(a);
        	else
        		return a[a.length-1];
        }
        //not complete
        
        private static int findLargest(int[] a, int start)
        {
                return -1;
        }
}