package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Merge sort that arranges products from LARGEST to SMALLEST.
 * No timing or printing happens in here - benchmarking is done by the caller.
 */
public class MergeSort
{
	/** Sort using the natural ordering (Product.compareTo, i.e. calcRealDiff). */
	public static void sort(Product[] arr)
	{
		sort(arr, (a, b) -> a.compareTo(b));
	}

	/** Sort using the supplied comparator. */
	public static void sort(Product[] arr, Comparator<Product> comp)
	{
		if (arr == null || arr.length < 2)
		{
			return;
		}
		Product[] temp = new Product[arr.length];
		mergeSort(arr, temp, 0, arr.length - 1, comp);
	}

	// Recursively split arr[lo..hi], sort each half, then merge the halves
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

	// Merge the two sorted (descending) runs arr[lo..mid] and arr[mid+1..hi]
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
