package appDomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import products.*; 

public class ProductLoader {

    public static Product[] loadProducts(String filePath) throws FileNotFoundException, IllegalArgumentException {
        File file = new File(filePath);
        
        // Ensure the file actually exists before attempting to read it
        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("The specified file could not be found: " + filePath);
        }
        
        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("The data file is empty.");
            }
            
            int size;
            try {
                // Parse the first line to determine the array size
                size = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("The first line of the file must be a valid integer representing the array size.");
            }
            
            Product[] products = new Product[size];
            
            int index = 0;
            while (scanner.hasNextLine() && index < size) { 
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Malformed line at entry " + (index + 1) + ". Expected 3 elements, found " + parts.length + ": " + line);
                }
                
                String type = parts[0];
                double originalPrice;
                double updatedPrice;
                
                try {
                    originalPrice = Double.parseDouble(parts[1]);
                    updatedPrice = Double.parseDouble(parts[2]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid price format at entry " + (index + 1) + ". Prices must be decimal numbers: " + line);
                }
                
                products[index] = createProduct(type, originalPrice, updatedPrice);
                index++;
            }
            
            if (index < size) {
                throw new IllegalArgumentException("File contained fewer entries (" + index + ") than the declared size (" + size + ").");
            }
            
            return products;
        }
    }
    
    private static Product createProduct(String type, double originalPrice, double updatedPrice) {
        return switch (type.toLowerCase()) {
            case "jeans" -> new Jeans(originalPrice, updatedPrice);
            case "dresses" -> new Dresses(originalPrice, updatedPrice);
            case "shorts" -> new Shorts(originalPrice, updatedPrice);
            case "jackets" -> new Jackets(originalPrice, updatedPrice);
            case "tshirts" -> new TShirts(originalPrice, updatedPrice);
            case "skirts" -> new Skirts(originalPrice, updatedPrice);
            case "shoes" -> new Shoes(originalPrice, updatedPrice);
            default -> throw new IllegalArgumentException("Unknown product type: " + type);
        };
    }
}
