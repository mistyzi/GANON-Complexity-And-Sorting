package appDomain;

import java.util.Comparator;
import products.Product;
import utilities.BubbleSort;
import utilities.HeapSort;
import utilities.InsertionSort;
import utilities.MergeSort;
import utilities.OriginalPriceComparator;
import utilities.QuickSort;
import utilities.SelectionSort;
import utilities.UpdatedPriceComparator;

/**
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Complexity and Sorting assignment that will be developed in the CPRG304
 * class at SAIT. The implementors of this applications will be required
 * to add all the correct functionality.
 * </p>
 */
public class AppDriver
{
	/**
	 *  The main method is the entry point of the application.
	 *
	 *  @param args The input to control the execution of the application.
	 */
	public static void main( String[] args )
	{
        try {
            AppConfig config = CommandLineParser.parseArgs(args);
            Product[] inventory = ProductLoader.loadProducts(config.getFileName());

            System.out.println("Successfully loaded " + inventory.length + " products.");

            // comparator selection
            char compareType = Character.toLowerCase(config.getCompareType());
            Comparator<Product> comp;
            switch (compareType) {
                case 'o':
                    comp = new OriginalPriceComparator();
                    break;
                case 'u':
                    comp = new UpdatedPriceComparator();
                    break;
                case 'd':
                    comp = null;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid compare type. Must be o, u, or d.");
            }
            
            // sort + benchmark (only the sort call itself is timed)
            char alg = Character.toLowerCase(config.getSortAlgorithm());
            String algName;
            long start;
            long end; 

            // misty use these variables ^^ for your formatting on output. they are uppdated accordingly below in the switch case.

            switch (alg) {
                case 'b':
                    algName = "Bubble Sort";
                    start = System.nanoTime();
                    if (comp == null) BubbleSort.bubbleSort(inventory); else BubbleSort.bubbleSort(inventory, comp);
                    end = System.nanoTime();
                    break;
                case 's':
                    algName = "Selection Sort";
                    start = System.nanoTime();
                    if (comp == null) SelectionSort.selectionSort(inventory); else SelectionSort.selectionSort(inventory, comp);
                    end = System.nanoTime();
                    break;
                case 'i':
                    algName = "Insertion Sort";
                    start = System.nanoTime();
                    if (comp == null) InsertionSort.insertionSort(inventory); else InsertionSort.insertionSort(inventory, comp);
                    end = System.nanoTime();
                    break;
                case 'm':
                    algName = "Merge Sort";
                    start = System.nanoTime();
                    if (comp == null) MergeSort.sort(inventory); else MergeSort.sort(inventory, comp);
                    end = System.nanoTime();
                    break;
                case 'q':
                    algName = "Quick Sort";
                    start = System.nanoTime();
                    if (comp == null) QuickSort.sort(inventory); else QuickSort.sort(inventory, comp);
                    end = System.nanoTime();
                    break;
                case 'z':
                    algName = "Heap Sort";
                    start = System.nanoTime();
                    if (comp == null) HeapSort.sort(inventory); else HeapSort.sort(inventory, comp);
                    end = System.nanoTime();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid sort algorithm. Must be b, s, i, m, q or z.");
            }
            
            long elapsedTime = end - start;
            double elapsedMs = elapsedTime / 1_000_000.0;
          
            System.out.println("\nSorted Results:");
            printResults(inventory);

            System.out.println();
            System.out.println("Algorithm: " + algName);
            System.out.println("Time: " + elapsedMs + " ms");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println("Correct Format: java -jar Sort.jar -f<file_name> -t<compare_type> -s<sort_alg>");
            System.out.println("  -t : o (original price), u (updated price), d (calculated difference)");
            System.out.println("  -s : b (bubble), s (selection), i (insertion), m (merge), q (quick), z (heap)");
        }
	}
	 /**
     * Prints results according to assignment requirements.
     *
     * @param inventory sorted product array
     */
    private static void printResults(Product[] inventory)
    {
        int size = inventory.length;

        if (size <= 10)
        {
            for (int i = 0; i < size; i++)
            {
                System.out.println((i + 1) + ". " + inventory[i]);
            }
        }
        else if (size <= 1000)
        {
            System.out.println("First: " + inventory[0]);

            for (int i = 100; i < size - 1; i += 100)
            {
                System.out.println(i + ": " + inventory[i]);
            }

            System.out.println("Last: "
                    + inventory[size - 1]);
        }
        else
        {
            System.out.println("First: " + inventory[0]);

            for (int i = 1000; i < size - 1; i += 1000)
            {
                System.out.println(i + ": " + inventory[i]);
            }

            System.out.println("Last: "
                    + inventory[size - 1]);
        }
    }
}
