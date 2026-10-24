package appDomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import products.*; 

public class ProductLoader {

    public static Product[] loadProducts(String filePath) throws FileNotFoundException, IllegalArgumentException {
        File file = new File(filePath);
        
        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("File is empty.");
            }
            
            // read first line for array size
            int size = Integer.parseInt(scanner.nextLine().trim());
            Product[] products = new Product[size];
            
            int index = 0;
            while (scanner.hasNextLine() && index < size) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Malformed line in data file: " + line);
                }
                
                String type = parts[0];
                double originalPrice = Double.parseDouble(parts[1]);
                double updatedPrice = Double.parseDouble(parts[2]);
                
                products[index] = createProduct(type, originalPrice, updatedPrice);
                index++;
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