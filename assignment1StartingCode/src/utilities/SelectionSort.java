package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Provides selection sort, which arranges an array of products in descending
 * order using either compareTo() or a Comparator.
 *
 * @author Ahmed Yassine Messaoudi
 */

public class SelectionSort {
    
    /**
     * Prevents creating objects of this class, all methods are static.
     */

    private SelectionSort() {

    }

    /**
     * Sorts the array in descending order using selection sort and the natural
     * ordering of the elements {@code compareTo()}.
     * 
     * @param arr the array to sort, it is rearranged in place
     */

    public static void selectionSort(Product[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                swap(arr, i, maxIndex);
            }
        }
    }

    /**
     * Sorts the array in descending order using selection sort and the given
     * comparator {@code compare()}.
     * 
     * @param arr the array to sort, it is rearranged in place
     * @param comp the comparator that defines the ordering of the elements
     */

    public static void selectionSort(Product[] arr, Comparator<Product> comp) {
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comp.compare(arr[j], arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                swap(arr, i, maxIndex);
            }
        }
    }

    /**
     * Swaps the elements at two positions in an array. 
     * @param arr the array containing the elements
     * @param a the index of the first element
     * @param b the index of the second element
     */

    private static void swap(Product[] arr, int a, int b) {
        Product temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}