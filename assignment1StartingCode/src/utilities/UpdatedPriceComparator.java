package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Orders products by their updated price (ascending by this comparator's
 * natural result; the sort classes reverse it to get a descending order).
 */
public class UpdatedPriceComparator implements Comparator<Product>
{
    /**
     * Compares two products by updated price.
     *
     * @param p1 the first product
     * @param p2 the second product
     * @return a negative number, zero or a positive number if the first product's
     *         updated price is less than, equal to or greater than the second's
     */
    @Override
    public int compare(Product p1, Product p2)
    {
        return Double.compare(
                p1.getUpdatedPrice(),
                p2.getUpdatedPrice());
    }
}
