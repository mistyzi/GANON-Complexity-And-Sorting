package products;

	//Base class for all product types
	public abstract class Product implements Comparable<Product>
	{
		// Product prices
	    protected double originalPrice;
	    protected double updatedPrice;

	    // Constructor
	    public Product(double originalPrice, double updatedPrice)
	    {
	        this.originalPrice = originalPrice;
	        this.updatedPrice = updatedPrice;
	    }

	    // Returns original price
	    public double getOriginalPrice()
	    {
	        return originalPrice;
	    }

	    // Returns updated price
	    public double getUpdatedPrice()
	    {
	        return updatedPrice;
	    }

	    // Calculate adjusted price difference
	    public abstract double calcRealDiff();

	    // Compare products using calculated difference
	    @Override
	    public int compareTo(Product other)
	    {
	        return Double.compare(
	                this.calcRealDiff(),
	                other.calcRealDiff());
	    }
	}