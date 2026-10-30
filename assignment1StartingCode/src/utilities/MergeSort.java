package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Merge sort that arranges products from LARGEST to SMALLEST.
 * No timing or printing happens in here - benchmarking is done by the caller.
 * <p>
 * Time complexity: O(n log n) in every case. Space complexity: O(n) for the
 * temporary array. The sort is stable.
 * </p>
 */
public class MergeSort
{
	/**
	 * Sorts the array in descending order using the natural ordering
	 * ({@link Product#compareTo(Product)}, i.e. the calculated difference).
	 *
	 * @param arr the array to sort, it is rearranged in place
	 */
	public static void sort(Product[] arr)
	{
		sort(arr, (a, b) -> a.compareTo(b));
	}

	/**
	 * Sorts the array in descending order using the supplied comparator.
	 * Does nothing if the array is {@code null} or has fewer than two elements.
	 *
	 * @param arr the array to sort, it is rearranged in place
	 * @param comp the comparator that defines the ordering of the elements
	 */
	public static void sort(Product[] arr, Comparator<Product> comp)
	{
		if (arr == null || arr.length < 2)
		{
			return;
		}
		Product[] temp = new Product[arr.length];
		mergeSort(arr, temp, 0, arr.length - 1, comp);
	}

	/**
	 * Recursively splits arr[lo..hi] in half, sorts each half, then merges them.
	 *
	 * @param arr the array being sorted
	 * @param temp scratch array of the same length, used while merging
	 * @param lo the first index of the range
	 * @param hi the last index of the range
	 * @param comp the comparator that defines the ordering of the elements
	 */
	private static void mergeSort(Product[] arr, Product[] temp, int lo, int hi, Comparator<Product> comp)
	{
		if (lo >= hi)
		{
			return;
		}
		int mid = lo + (hi - lo) / 2;
		mergeSort(arr, temp, lo, mid, comp);
		mergeSort(arr, temp, mid + 1, hi, comp);
		merge(arr, temp, lo, mid, hi, comp);
	}

	/**
	 * Merges the two sorted (descending) runs arr[lo..mid] and arr[mid+1..hi]
	 * into one sorted run. Equal elements keep their original order, which
	 * keeps the sort stable.
	 *
	 * @param arr the array being sorted
	 * @param temp scratch array of the same length, used while merging
	 * @param lo the first index of the left run
	 * @param mid the last index of the left run
	 * @param hi the last index of the right run
	 * @param comp the comparator that defines the ordering of the elements
	 */
	private static void merge(Product[] arr, Product[] temp, int lo, int mid, int hi, Comparator<Product> comp)
	{
		for (int k = lo; k <= hi; k++)
		{
			temp[k] = arr[k];
		}

		int left = lo;
		int right = mid + 1;
		for (int k = lo; k <= hi; k++)
		{
			if (left > mid)
			{
				arr[k] = temp[right++];
			}
			else if (right > hi)
			{
				arr[k] = temp[left++];
			}
			else if (comp.compare(temp[left], temp[right]) >= 0)
			{
				// left is larger or equal -> comes first (>= keeps the sort stable)
				arr[k] = temp[left++];
			}
			else
			{
				arr[k] = temp[right++];
			}
		}
	}
}
