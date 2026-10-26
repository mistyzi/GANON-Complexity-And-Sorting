package utilities;

import java.util.Comparator;

/**
 * @author Ahmed Yassine Messaoudi
 */

/**
 * Provides the insertion sort algorithm in descending order.
 * <p>
 * The order is produced by using the results of {@code compareTo()} or
 * {@code compare()} directly, the array is never sorted ascending and then
 * reversed.
 * </p>
 * <p>
 * The algorithm comes in two versions: one that uses the natural ordering of
 * the elements {@code Comparable} and one that uses a {@code Comparator}.
 * No timing or printing is done in this class.
 * </p>
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
     * @param <T> the type of the elements, it must implement {@code Comparable}
     * @param arr the array to sort, it is rearranged in place
     */

    public static <T extends Comparable<? super T>> void sort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
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
     * @param <T> the type of the elements
     * @param arr the array to sort, it is rearranged in place
     * @param comp the comparator that defines the ordering of the elements
     */

    public static <T> void sort(T[] arr, Comparator<? super T> comp) {
        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && comp.compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
