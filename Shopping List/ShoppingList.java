import java.io.*;
import java.util.*;

public class ShoppingList {
    private List<Item> items;
    private String fileName;

    public ShoppingList(String fileName) {
        this.fileName = fileName;
        this.items = new ArrayList<>();
        loadItems();
    }

    public void addItem(String name, double price) {
        Item item = new Item(name, price);
        items.add(item);
        saveItems();
    }

    public void removeItem(String name) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(name));
        saveItems();
    }

    public void showItems() {
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    private void loadItems() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                Item item = new Item(name, price);
                items.add(item);
            }
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file: " + e.getMessage());
        }
    }

    private void saveItems() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Item item : items) {
                writer.write(item.getName() + ", " + item.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error occurred while writing to the file: " + e.getMessage());
        }
    }
}