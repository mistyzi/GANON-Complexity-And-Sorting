package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Provides insertion sort, which arranges an array of products in descending
 * order using either compareTo() or a Comparator.
 *
 * @author Ahmed Yassine Messaoudi
 */

public class InsertionSort {

    /**
     * Prevents creating objects of this class, all methods are static.
     */

    private InsertionSort() {

    }

    /**
     * Sorts the array in descending order using insertion sort and the natural
     * ordering of the elements {@code compareTo()}.
     * 
     * @param arr the array to sort, it is rearranged in place
     */

    public static void insertionSort(Product[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Product key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Sorts the array in descending order using insertion sort and the given
     * comparator {@code compare()}.
     * 
     * @param arr the array to sort, it is rearranged in place
     * @param comp the comparator that defines the ordering of the elements
     */

    public static void insertionSort(Product[] arr, Comparator<Product> comp) {
        for (int i = 1; i < arr.length; i++) {
            Product key = arr[i];
            int j = i - 1;
            while (j >= 0 && comp.compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

}
