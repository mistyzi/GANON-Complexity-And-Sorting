package appDomain;

import products.Product;
import utilities.OriginalPriceComparator;
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
            
            // comparator selection mapping
            switch (Character.toLowerCase(config.getCompareType())) {
                case 'o':
                    // map to OriginalPriceComparator
                    OriginalPriceComparator originalComp = new OriginalPriceComparator();
                    break;
                case 'u':
                    // map to UpdatedPriceComparator
                    UpdatedPriceComparator updatedComp = new UpdatedPriceComparator();
                    break;
                case 'd':
                    // map to Comparable (compareTo)
                    break;
                default:
                    throw new IllegalArgumentException("Invalid compare type. Must be o, u, or d.");
            }

            // pass inventory
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println("Correct Format: java -jar Sort.jar -f<file_name> -t<compare_type> -s<sort_alg>");
        }
	}

}
