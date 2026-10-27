package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Heap sort (our algorithm of choice, sort option "z"). Arranges products from
 * LARGEST to SMALLEST. No timing or printing happens in here.
 *
 * A MIN-heap (by the comparator) is used: the root is always the element that
 * belongs LAST in the output. Repeatedly moving the root to the end of the
 * shrinking heap therefore leaves the array in descending order, without ever
 * sorting ascending and reversing.
 */
public class HeapSort {
	/** Sort using the natural ordering (Product.compareTo, i.e. calcRealDiff). */
	public static void sort(Product[] arr) {
		sort(arr, (a, b) -> a.compareTo(b));
	}

	/** Sort using the supplied comparator. */
	public static void sort(Product[] arr, Comparator<Product> comp) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int n = arr.length;

		// Phase 1: build the min-heap, starting from the last parent node
		for (int i = n / 2 - 1; i >= 0; i--) {
			siftDown(arr, i, n, comp);
		}

		// Phase 2: move the root (smallest) to the end, shrink the heap, repair it
		for (int end = n - 1; end > 0; end--) {
			Product t = arr[0];
			arr[0] = arr[end];
			arr[end] = t;
			siftDown(arr, 0, end, comp);
		}
	}

	// Push arr[i] down until neither child is smaller than it (heap is arr[0..size-1])
	private static void siftDown(Product[] arr, int i, int size, Comparator<Product> comp) {
		Product item = arr[i];
		while (true)
		{
			int child = 2 * i + 1;
			if (child >= size) {
				break;
			}
			// pick the smaller of the two children
			if (child + 1 < size && comp.compare(arr[child + 1], arr[child]) < 0) {
				child++;
			}
			// stop if the item is already <= the smaller child
			if (comp.compare(item, arr[child]) <= 0) {
				break;
			}
			arr[i] = arr[child];
			i = child;
		}
		arr[i] = item;
	}
}
