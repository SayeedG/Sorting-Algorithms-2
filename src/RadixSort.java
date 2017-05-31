//Author:Sayeed Gulmahamad
import java.util.*;

class RadixSort implements Sortable{

	private long num = 0;

	//Count Sort. The digit represented by exp.
	 void countSort(int A[], int n, int exp)
	 
	{
		 
		 
		int output[] = new int[n]; // output array
		int i;
		int count[] = new int[10];
		for (int j=0; j < count.length; j++) {
			count[j] = 0;
		}

		// Store count of occurrences in count[]
		for (i = 0; i < n; i++)
			count[ (A[i]/exp)%10 ]++;

		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];

		// Build the output array
		for (i = n - 1; i >= 0; i--)
		{
			output[count[ (A[i]/exp)%10 ] - 1] = A[i];
			count[ (A[i]/exp)%10 ]--;
			num++;
		}

		for (i = 0; i < n; i++)
			A[i] = output[i];
	}

	// Radix Sort
	public int[] sort(int A[])
	{
		this.num = 0;
		// Find the maximum number to know number of digits
		int m = getMax(A, A.length);

		for (int exp = 1; m/exp > 0; exp *= 10)
			countSort(A, A.length, exp);
		
		return A;
	}

	
	static void print(int A[], int n)
	{
		for (int i=0; i<n; i++)
			System.out.print(A[i]+" ");
	}


	public static void main (String[] args)
	{
		int A[] = {170, 45, 75, 90, 802, 24, 2, 66};
		int n = A.length;
		RadixSort radixSort = new RadixSort();
		radixSort.sort(A);
		print(A, n);
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
		return num;
	}
}
