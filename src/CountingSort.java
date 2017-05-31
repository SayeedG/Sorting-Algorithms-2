//Author:Sayeed Gulmahamad
import java.util.Arrays;

public class CountingSort implements Sortable{
	private long count = 0;
	public int[] sort(int[] A) {
		this.count = 0;
		int[] B = new int[A.length + 1];
		int[] Count = new int[A.length + 1];

		for (int i = 0; i < Count.length; i++) {
			Count[i] = 0; // put count for every element as 0
		}
		for (int i = 0; i < A.length; i++) {
			Count[A[i]] = Count[A[i]] + 1;
		}
		for (int i = 1; i < Count.length; i++) {
			Count[i] = Count[i] + Count[i - 1];
		}
		
		for (int i = A.length - 1; i >= 0; i--) {
			B[Count[A[i]]] = A[i];
			Count[A[i]] = Count[A[i]] -1;
			count++;
		}
		return B;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int input[] = { 2, 1, 4, 5, 7, 1, 1, 8, 9, 10, 11, 14, 15, 3, 2, 4 };
		System.out.println("Orginal Array " + Arrays.toString(input));
		CountingSort c = new CountingSort();
		int[] B = c.sort(input);
		System.out.println("Sorted Array : " + Arrays.toString(B));

	}

	@Override
	public long getCount() {
		return count;
	}

}