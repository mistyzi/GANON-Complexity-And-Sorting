package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Provides bubble sort, which arranges an array of products in descending
 * order using either compareTo() or a Comparator.
 *
 * @author Ahmed Yassine Messaoudi
 */

public class BubbleSort {

    /**
     * Prevents creating objects of this class, all methods are static.
     */
    
    private BubbleSort() {

    }

    /**
     * Sorts the array in descending order using bubble sort and the natural
     * ordering of the elements {@code compareTo()}.
     * 
     * @param arr the array to sort, it is rearranged in place
     */

    public static void bubbleSort(Product[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) < 0) {
                    swap(arr, j, j + 1);
                    swapped = true;
                } 
            }
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Sorts the array in descending order using bubble sort and the given
     * comparator {@code compare()}.
     * 
     * @param arr the array to sort, it is rearranged in place
     * @param comp the comparator that defines the ordering of the elements
     */

    public static void bubbleSort(Product[] arr, Comparator<Product> comp) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (comp.compare(arr[j], arr[j + 1]) < 0) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
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
