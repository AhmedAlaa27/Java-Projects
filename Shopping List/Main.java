import java.util.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "items.txt";
        ShoppingList shoppingList = new ShoppingList(fileName);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Show items");
            System.out.println("4. Calculate total price");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    double price = scanner.nextDouble();
                    shoppingList.addItem(name, price);
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String removeName = scanner.nextLine();
                    shoppingList.removeItem(removeName);
                    break;
                case 3:
                    shoppingList.showItems();
                    break;
                case 4:
                    double totalPrice = shoppingList.calculateTotalPrice();
                    System.out.println("Total price: $" + totalPrice);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}