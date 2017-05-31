//Author: Sayeed Gulmahamad
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Vector;

public class BucketSort implements Sortable{

	private long count = 0;
	public static void main(String[] args) {

		int A[] = {170, 45, 75, 90, 802, 24, 2, 66};
		int n = A.length;
		
		BucketSort bucketSort = new BucketSort();
		int[]sorted_sequence= bucketSort.sort(A);
		System.out.println("Sorted Array : " + Arrays.toString(sorted_sequence));
		System.out.println(bucketSort.getCount());
	}

	public int[] sort(int A[]) {
		this.count = 0;
		 // Bucket Sort
        int[] Bucket = new int[getMax(A, A.length) + 1];
        int[] sorted_sequence = new int[A.length];
 
        for (int i = 0; i < A.length; i++)
            Bucket[A[i]]++;
 
        int outPos = 0;
        for (int i = 0; i < Bucket.length; i++) {
            for (int j = 0; j < Bucket[i]; j++) {
                sorted_sequence[outPos++] = i;
               
            }
            count++;
        }
       
        return sorted_sequence;
	}
	//To get maximum value in A[]
		private	static int getMax(int A[], int n)
			{
				int mx = A[0];
				for (int i = 1; i < n; i++)
					if (A[i] > mx)
						mx = A[i];
				return mx;
			}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	
}
