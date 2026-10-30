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
 * <p>
 * Time complexity: O(n log n) in every case. Space complexity: O(1). The sort
 * is not stable.
 * </p>
 */
public class HeapSort {
	/**
	 * Sorts the array in descending order using the natural ordering
	 * ({@link Product#compareTo(Product)}, i.e. the calculated difference).
	 *
	 * @param arr the array to sort, it is rearranged in place
	 */
	public static void sort(Product[] arr) {
		sort(arr, (a, b) -> a.compareTo(b));
	}

	/**
	 * Sorts the array in descending order using the supplied comparator.
	 * Does nothing if the array is {@code null} or has fewer than two elements.
	 *
	 * @param arr the array to sort, it is rearranged in place
	 * @param comp the comparator that defines the ordering of the elements
	 */
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

	/**
	 * Pushes the element at index {@code i} down the min-heap until neither of
	 * its children is smaller than it, restoring the heap property.
	 *
	 * @param arr the array holding the heap in arr[0..size-1]
	 * @param i the index of the element to push down
	 * @param size the number of elements currently in the heap
	 * @param comp the comparator that defines the ordering of the elements
	 */
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
