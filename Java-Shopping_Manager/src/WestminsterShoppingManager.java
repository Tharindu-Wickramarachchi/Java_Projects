import javax.swing.*;
import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    Scanner scanner = new Scanner(System.in);
    ShoppingCart Cart = new ShoppingCart(); //Creating a new ShoppingCart object

    @Override
    public void displayMenu() {

        // Restoring the product list
        restoringData();


        for (; ; ) {

            System.out.println("\n===== Shopping Manager Menu ====="); //Console menu options
            System.out.println("1. Add a new product");
            System.out.println("2. Remove a product");
            System.out.println("3. Print the product list");
            System.out.println("4. Save products list");
            System.out.println("5. Open graphical user interface");
            System.out.println("0. Exit\n");


            int option = -1;
            while (option <= -1 || option >= 6) {   //only tack 0-5 as input
                System.out.print("Enter option : ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter integers as inputs.\n");
                    System.out.print("Enter option : ");
                    scanner.nextLine();
                }
                option = scanner.nextInt();
                scanner.nextLine();
                if (option < 0 || option > 5) {
                    System.out.println("Invalid input.Please enter options from 0 to 6.\n");
                }
            }

            switch (option) {
                case 0:
                    exit();
                    break;
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    printProductList();
                    break;
                case 4:
                    saveProductsList();
                    break;
                case 5:
                    openGUI();
                    break;

            }
        }
    }

    boolean systemUsed;

    @Override // Method use for add products
    public void addProduct() {
        systemUsed=true;

        if (Cart.productLists.size() >= 50) { //Set productLists limit to 50 products
            System.out.println("\nYou can't add more products. The products limit is reached!");
            return; //if true exits the method
        }


        System.out.println("=== Choose product type: ===\n");
        System.out.println("\t 1. Electronics");
        System.out.println("\t 2. Clothing");

        int chooseProduct;
        while (true) {
            System.out.print("\nEnter your choice: ");
            if (scanner.hasNextInt()) {
                chooseProduct = scanner.nextInt();
                scanner.nextLine(); // Consume the invalid input
                if (chooseProduct == 1 || chooseProduct == 2) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 1 or 2 as inputs.");
                }
            } else {
                scanner.nextLine(); // Consume the invalid input
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }


        String inputId = null; // Initialize variables for user input
        String productId; // Initialize variables for product ID

        while (inputId == null) {
            System.out.print("Enter product ID: ");
            inputId = scanner.nextLine().trim();
            if (inputId.isEmpty()) {
                System.out.println("Please enter the product ID of the product.");
            }
            // Check if the entered product ID already exists in the cart
            for (Product element : Cart.productLists) {
                if (element.getProductID().equals(inputId)) {
                    System.out.println("Product Id all ready exits please input a different one.");
                    inputId = null;
                }
            }
        }
        // Store the validated product ID
        productId = inputId;


        String productName; // Initialize variables for product name
        do {
            System.out.print("Enter product name: ");
            productName = scanner.nextLine();
            if (productName.isEmpty()) {
                System.out.println("Please enter the product name of the product.");
            }
        } while (productName.isEmpty());

        int numAvailableItems; // Initialize variables for available number of items
        while (true) {
            System.out.print("Enter available items: ");
            if (scanner.hasNextInt()) {
                numAvailableItems = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                scanner.nextLine(); //consume the invalid input
                System.out.println("Invalid input. Please enter an integer.");
            }
        }

        double price; // Initialize variables for product price
        while (true) {
            System.out.print("Enter price: ");
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                scanner.nextLine();
                break;
            } else {
                scanner.nextLine(); //consume the invalid input
                System.out.println("Invalid input. Please enter an integer.");
            }
        }


        if (chooseProduct == 1) {

            String brand;
            do {
                System.out.print("Enter product brand: ");
                brand = scanner.nextLine();
                if (brand.isEmpty()) {
                    System.out.println("Please enter the product brand of the product.");
                }
            } while (brand.isEmpty());

            String warrantyPeriod;
            do {
                System.out.print("Enter product warranty period: ");
                warrantyPeriod = scanner.nextLine();
                if (warrantyPeriod.isEmpty()) {
                    System.out.println("Please enter the product warranty period of the product.");
                }
            } while (warrantyPeriod.isEmpty());


            // Create an instance of the Electronics class with the provided information
            Electronics ElectronicsInfo = new Electronics("Electronics", productId, productName, numAvailableItems, price, brand, warrantyPeriod);
            // Add the Electronics instance to the Carts product list
            Cart.addElectronics(ElectronicsInfo);


        } else if (chooseProduct == 2) {
            String color; // Initialize variables for product color
            do {
                System.out.print("Enter product color: ");
                color = scanner.nextLine();

                if (color.isEmpty()) {
                    System.out.println("Please enter the color of the product.");
                } else if (!(color.matches("^[a-zA-Z\\s]+$"))) {
                    System.out.println("Invalid input. Please enter a valid string.");
                }
            } while (color.isEmpty() || !(color.matches("^[a-zA-Z\\s]+$")));


            String size; // Initialize variables for product size
            do {
                System.out.print("Enter product size: ");
                size = scanner.nextLine();
                if (size.isEmpty()) {
                    System.out.println("Please enter the product size of the product.");
                }
            } while (size.isEmpty());


            // Create an instance of the Clothing class with the provided information
            Clothing ClothingInfo = new Clothing("Clothing", productId, productName, numAvailableItems, price, color, size);
            // Add the Clothing instance to the Carts product list
            Cart.addClothing(ClothingInfo);

        }


        String addAnotherPro;
        do {
            System.out.print("\nDo you want to add a another product? (Y/N) : ");
            addAnotherPro = scanner.nextLine();
            if (addAnotherPro.equals("y") || addAnotherPro.equals("Y")) {
                addProduct();
            } else if (addAnotherPro.equals("n") || addAnotherPro.equals("N")) {
                return;
            } else {
                addAnotherPro = "";
                System.out.println("Please enter \"y\" or \"N\" as your options.");
            }
        } while (addAnotherPro.isEmpty());

    }

    @Override // Method use for remove products
    public void removeProduct() {
        systemUsed=true;
        String removeId;

        do {
            System.out.print("Enter product ID: ");
            removeId = scanner.nextLine().trim();

            if (!removeId.isEmpty()) {
                boolean idExists = false;

                for (Product element : Cart.productLists) {
                    if (element.getProductID().equals(removeId)) {
                        idExists = true;
                        Cart.remove(element);
                        System.out.println("======= Product information =======\n");
                        System.out.println(element.toString());
                        System.out.println("\n... Product successfully removed ...\n");

                        System.out.println("Number of products left in the system : " + Cart.productLists.size());
                        break; // Stop continuing if the ID is found
                    }
                }

                if (!idExists) {
                    System.out.println("\nInvalid product Id. Product Id doesn't exist.");
                }

            } else {
                System.out.println("\nInvalid input.");
            }
        } while (removeId.isEmpty());


        String removeAnotherPro;
        do {
            System.out.print("\nDo you want to remove a another product? (Y/N) : ");
            removeAnotherPro = scanner.nextLine();
            if (removeAnotherPro.equals("y") || removeAnotherPro.equals("Y")) {
                removeProduct();
            } else if (removeAnotherPro.equals("n") || removeAnotherPro.equals("N")) {
                return;
            } else {
                removeAnotherPro = "";
                System.out.println("Please enter \"y\" or \"N\" as your options.");
            }
        } while (removeAnotherPro.isEmpty());

    }


    @Override //Method for sort the product list according to alphabetic order
    public void printProductList() {
        ArrayList<Product> sortProductList = new ArrayList<>(Cart.productLists);

        // Create a comparator based on the ProductID
        Comparator<Product> idComparator = Comparator.comparing(Product::getProductID);

        // Sort the list using the custom comparator
        Collections.sort(sortProductList, idComparator);

        // Print the sorted list
        System.out.println("\nSorted Product List by ProductID:\n");
        for (Product product : sortProductList) {
            System.out.println("\n" + product.toString());

        }
    }

    @Override //Method use for saving product list
    public void saveProductsList() {
        systemUsed=false;

        // save product list to Data.txt file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Data.txt"))) {
            for (Product element : Cart.productLists) {
                writer.write(element.getProductType() + "," + element.getProductID() + "," + element.getProductName() + "," + element.getNumAvailableItems() + "," + element.getPrice() + ",");
                if (element instanceof Electronics electronics) {
                    writer.write(electronics.getBrand() + ",");
                    writer.write(electronics.getWarrantyPeriod() + "\n");
                } else if (element instanceof Clothing clothing) {
                    writer.write(clothing.getColor() + ",");
                    writer.write(clothing.getSize() + "\n");
                }
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n Saving process complete");

    }

    @Override // Method use for restore data
    public void restoringData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Data.txt"))) {


            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                // Common fields : ProductType, ProductID, ProductName, NumAvailableItems, Price
                String productType = parts[0].trim();
                String productId = parts[1].trim();
                String productName = parts[2].trim();
                int numAvailableItems = Integer.parseInt(parts[3].trim());
                double price = Double.parseDouble(parts[4].trim());

                if ("Electronics".equals(productType)) {
                    // Additional fields for Electronics
                    String brand = parts[5].trim();
                    String warrantyPeriod = parts[6].trim();

                    // Create an instance of the Electronics class
                    Electronics ElectronicsInfo = new Electronics("Electronics", productId, productName, numAvailableItems, price, brand, warrantyPeriod);
                    // Add the Electronics instance to the Carts product list
                    Cart.addElectronics(ElectronicsInfo);

                } else if ("Clothing".equals(productType)) {
                    // Additional fields for Electronics
                    String color = parts[5].trim();
                    String size = parts[6].trim();

                    // Create an instance of the Clothing class
                    Clothing ClothingInfo = new Clothing("Clothing", productId, productName, numAvailableItems, price, color, size);
                    // Add the Clothing instance to the Carts product list
                    Cart.addClothing(ClothingInfo);
                }
            }


        } catch (Exception ignored) {
        }

    }


    @Override // Open Graphical User Interface
    public void openGUI(){

        if (systemUsed){
            System.out.println("Would you like to save the changes you've made?");
            System.out.println("Enter \"y\" if Yes or \"N\" if No");

            String saveOption;
            do {
                System.out.print("\nPlease enter your option (Y/N) : ");
                saveOption = scanner.nextLine();
                if (saveOption.equals("y") || saveOption.equals("Y")) {
                    saveProductsList();
                    System.out.println("\nChanges successfully saved.");
                    SwingUtilities.invokeLater(() -> {
                        GUI gui = new GUI(Cart.productLists);
                        gui.frame();
                    });
                } else if (saveOption.equals("n") || saveOption.equals("N")) {
                    System.out.println("\nNo changes were saved.");
                    SwingUtilities.invokeLater(() -> {
                        GUI gui = new GUI(Cart.productLists);
                        gui.frame();
                    });
                } else {
                    saveOption = "";
                    System.out.println("Please enter \"y\" or \"N\" as your options.");
                }
            } while (saveOption.isEmpty());

        }else {
            SwingUtilities.invokeLater(() -> {
                GUI gui = new GUI(Cart.productLists);
                gui.frame();

            });
        }

    }


    @Override
    public void exit() {
        if (systemUsed){
            System.out.println("Would you like to save the changes you've made?");
            System.out.println("Enter \"y\" if Yes or \"N\" if No");

            String saveOption;
            do {
                System.out.print("\nPlease enter your option (Y/N) : ");
                saveOption = scanner.nextLine();
                if (saveOption.equals("y") || saveOption.equals("Y")) {
                    saveProductsList();
                    System.out.println("\nChanges successfully saved.");
                    System.out.println("Exiting......");
                    System.exit(0);
                } else if (saveOption.equals("n") || saveOption.equals("N")) {
                    System.out.println("\nNo changes were saved.");
                    System.out.println("Exiting......");
                    System.exit(0);
                } else {
                    saveOption = "";
                    System.out.println("Please enter \"y\" or \"N\" as your options.");
                }
            } while (saveOption.isEmpty());

        }else {
            System.out.println("Exiting......");
            System.exit(0);
        }
    }

}




