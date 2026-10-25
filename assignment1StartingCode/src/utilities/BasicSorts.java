package utilities;

import java.util.Comparator;

/**
 * @author Ahmed Yassine Messaoudi 
 */

/**
 * Provides basic O(n^2) sorting algorithms: bubble sort, selection sort and 
 * insertion sort.
 * <p>
 * Every sort arranges the array in descending order.
 * The order is produced by using the results of {@code compareTo()} or
 * {@code compare()} directly, the array is never sorted ascending and then 
 * reversed
 * </p>
 * <p>
 * Each algorithm comes in two versions: one that uses the natural ordering of
 * the elements {@code Comparable} and one that uses a {@code Comparator}.
 * No timing or printing is done in this class.
 * </p>
 */

public class BasicSorts {

    /**
     * Prevents creating objects of this class, all methods are static.
     */

    private BasicSorts() {

    }

    /**
     * Sorts the array in descending order using bubble sort and the natural
     * ordering of the elements {@code compareTo()}.
     * 
     * @param <T> the type of the elements, it must implement {@code Comparable}
     * @param arr the array to sort, it is rearranged in place
     */

    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
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
     * @param <T> the type of the elements
     * @param arr the array to sort, it is rearranged in place
     * @param comp the comparator that defines the ordering of the elements
     */

    public static <T> void bubbleSort(T[] arr, Comparator<? super T> comp) {
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
     * Sorts the array in descending order using selection sort and the natural
     * ordering of the elements {@code compareTo()}.
     * 
     * @param <T> the type of the elements, it must implement {@code Comparable}
     * @param arr the array to sort, it is rearranged in place
     */

    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
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
     * @param <T> the type of the elements
     * @param arr the array to sort, it is rearranged in place
     * @param comp the comparator that defines the ordering of the elements
     */

    public static <T> void selectionSort(T[] arr, Comparator<? super T> comp) {
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
     * Sorts the array in descending order using insertion sort and the natural
     * ordering of the elements {@code compareTo()}.
     * 
     * @param <T> the type of the elements, it must implement {@code Comparable}
     * @param arr the array to sort, it is rearranged in place
     */

    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
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

    public static <T> void insertionSort(T[] arr, Comparator<? super T> comp) {
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

    /**
     *Swaps the elements at two positions in an array. 
     * @param arr the array containing the elements
     * @param a the index of the first element
     * @param b the index of the second element
     */

    private static void swap(Object[] arr, int a, int b) {
        Object temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

