package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Quick sort using the "median-of-3" pivot rule. Arranges products from
 * LARGEST to SMALLEST. No timing or printing happens in here.
 */
public class QuickSort
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
		quickSort(arr, 0, arr.length - 1, comp);
	}

	// "a belongs before b" in a descending sort
	private static boolean before(Product a, Product b, Comparator<Product> comp)
	{
		return comp.compare(a, b) > 0;
	}

	private static void swap(Product[] arr, int i, int j)
	{
		Product t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

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

	// Sorts 2 or 3 elements directly (descending)
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

	// Median-of-3 partition. Returns the final index of the pivot.
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
