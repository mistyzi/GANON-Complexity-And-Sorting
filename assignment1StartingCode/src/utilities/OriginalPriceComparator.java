package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Orders products by their original price (ascending by this comparator's
 * natural result; the sort classes reverse it to get a descending order).
 */
public class OriginalPriceComparator implements Comparator<Product>
{
	/**
	 * Compares two products by original price.
	 *
	 * @param p1 the first product
	 * @param p2 the second product
	 * @return a negative number, zero or a positive number if the first product's
	 *         original price is less than, equal to or greater than the second's
	 */
	@Override
 	public int compare(Product p1, Product p2)
 	{
		return Double.compare(
				p1.getOriginalPrice(),
				p2.getOriginalPrice());
 	}
}
