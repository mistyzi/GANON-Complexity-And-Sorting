package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Quick sort using the "median-of-3" pivot rule. Arranges products from
 * LARGEST to SMALLEST. No timing or printing happens in here.
 * <p>
 * Time complexity: O(n log n) on average, O(n^2) in the worst case (made
 * unlikely by the median-of-3 pivot). Recursing only into the smaller part
 * keeps the stack depth at O(log n). The sort is not stable.
 * </p>
 */
public class QuickSort
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
		quickSort(arr, 0, arr.length - 1, comp);
	}

	/**
	 * Tells whether {@code a} belongs before {@code b} in a descending sort.
	 *
	 * @param a the first product
	 * @param b the second product
	 * @param comp the comparator that defines the ordering of the elements
	 * @return {@code true} if {@code a} is strictly larger than {@code b}
	 */
	private static boolean before(Product a, Product b, Comparator<Product> comp)
	{
		return comp.compare(a, b) > 0;
	}

	/**
	 * Swaps the elements at two positions in an array.
	 *
	 * @param arr the array containing the elements
	 * @param i the index of the first element
	 * @param j the index of the second element
	 */
	private static void swap(Product[] arr, int i, int j)
	{
		Product t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	/**
	 * Sorts arr[lo..hi]. Recurses into the smaller part and loops on the larger
	 * part, which keeps the stack depth at O(log n). Ranges of three or fewer
	 * elements are sorted directly.
	 *
	 * @param arr the array being sorted
	 * @param lo the first index of the range
	 * @param hi the last index of the range
	 * @param comp the comparator that defines the ordering of the elements
	 */
	private static void quickSort(Product[] arr, int lo, int hi, Comparator<Product> comp)
	{
		// Recurse into the smaller part, loop on the larger part: keeps stack depth O(log n)
		while (lo < hi)
		{
			if (hi - lo + 1 <= 3)
			{
				sortSmall(arr, lo, hi, comp);
				return;
			}

			int p = partition(arr, lo, hi, comp);

			if (p - lo < hi - p)
			{
				quickSort(arr, lo, p - 1, comp);
				lo = p + 1;
			}
			else
			{
				quickSort(arr, p + 1, hi, comp);
				hi = p - 1;
			}
		}
	}

	/**
	 * Sorts a range of two or three elements directly, in descending order.
	 *
	 * @param arr the array being sorted
	 * @param lo the first index of the range
	 * @param hi the last index of the range (at most {@code lo + 2})
	 * @param comp the comparator that defines the ordering of the elements
	 */
	private static void sortSmall(Product[] arr, int lo, int hi, Comparator<Product> comp)
	{
		if (before(arr[hi], arr[lo], comp))
		{
			swap(arr, lo, hi);
		}
		if (hi - lo == 2)
		{
			int mid = lo + 1;
			if (before(arr[mid], arr[lo], comp))
			{
				swap(arr, lo, mid);
			}
			if (before(arr[hi], arr[mid], comp))
			{
				swap(arr, mid, hi);
			}
		}
	}

	/**
	 * Partitions arr[lo..hi] around a median-of-3 pivot (the median of the
	 * first, middle and last elements). Larger elements end up before the pivot
	 * and smaller ones after it.
	 *
	 * @param arr the array being sorted
	 * @param lo the first index of the range
	 * @param hi the last index of the range
	 * @param comp the comparator that defines the ordering of the elements
	 * @return the final index of the pivot
	 */
	private static int partition(Product[] arr, int lo, int hi, Comparator<Product> comp)
	{
		int mid = lo + (hi - lo) / 2;

		// Order arr[lo], arr[mid], arr[hi] so that lo >= mid >= hi (descending).
		// The median then sits at mid, and lo/hi act as sentinels for the scans.
		if (before(arr[mid], arr[lo], comp))
		{
			swap(arr, lo, mid);
		}
		if (before(arr[hi], arr[lo], comp))
		{
			swap(arr, lo, hi);
		}
		if (before(arr[hi], arr[mid], comp))
		{
			swap(arr, mid, hi);
		}

		// Hide the pivot (the median) next to the end
		swap(arr, mid, hi - 1);
		Product pivot = arr[hi - 1];

		int i = lo;
		int j = hi - 1;
		while (true)
		{
			while (before(arr[++i], pivot, comp))
			{
				// move right while elements belong before the pivot
			}
			while (before(pivot, arr[--j], comp))
			{
				// move left while elements belong after the pivot
			}
			if (i >= j)
			{
				break;
			}
			swap(arr, i, j);
		}

		// Put the pivot into its final position
		swap(arr, i, hi - 1);
		return i;
	}
}
