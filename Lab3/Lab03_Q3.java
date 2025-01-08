package Lab3;

import java.util.Scanner;
import java.util.Random;

public class Lab03_Q3
{
    public static void main(String[] args)
    {
        String password;
        String username;
        String customers = "PrimeTech, Peak, EcoGoods, ";
        String items = "Item104:Laptop Item125:Monitor ";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        username = scanner.nextLine();

        if (username.equals("manager"))
        {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();

            if (password.equals("inventory"))
            {
                System.out.print(
                        "1- Add customer \n2- Delete customer \n3- Add item \n4- Delete item \n5- Logout \nSelect an operation: ");
                String operation = scanner.nextLine();

                // 1. operation
                if (operation.equals("1"))
                {
                    System.out.print("-- Add Item --\nEnter customer name: ");
                    String newCustomerName = scanner.nextLine();

                    if (customers.contains(newCustomerName))
                    {
                        System.out.println("This customer is already in your list!");
                    } else
                    {
                        System.out.printf("New customer %s is added!", newCustomerName);
                        customers += newCustomerName + ", ";
                    }
                    System.out.printf("Your customers: (%s)", customers);
                    // 2. Operation
                } else if (operation.equals("2"))
                {
                    System.out.print("-- Delete Customer --\nEnter customer name which you want to delete: ");
                    String deletedCustomerName = scanner.nextLine();

                    if (customers.contains(deletedCustomerName))
                    {
                        System.out.printf("%s is deleted successfully from customers!\n", deletedCustomerName);
                        customers = customers.replace(deletedCustomerName + ", ", "");
                    } else
                    {
                        System.out.printf("You don't have any customer whose name is %s!\n", deletedCustomerName);

                    }
                    System.out.printf("Your customers: (%s)", customers);
                    // 3. Operation
                } else if (operation.equals("3"))
                {

                    System.out.print("-- Add Item --\nEnter item name: ");
                    String newItemName = scanner.nextLine();

                    Random random = new Random();
                    String randomNumber = Integer.toString(random.nextInt(900) + 100);

                    if (items.contains(randomNumber))
                    {
                        System.out.printf(
                                "There is an item with the id %s, you cannot add a new item with the same id!\n",
                                randomNumber);
                    } else
                    {
                        System.out.printf("New item with id %s is added!\n", randomNumber);
                        items += "Item" + randomNumber + ":" + newItemName + " ";
                    }
                    System.out.printf("Your items: %s", items);

                    // 4. Operation
                } else if (operation.equals("4"))
                {
                    System.out.print("-- Delete Item --\nEnter itemId which you want to delete: ");
                    String deletedItemID = scanner.nextLine();
                    if (items.contains(deletedItemID))
                    {
                        System.out.printf("The item with the id %s is deleted successfully!\n", deletedItemID);

                        int index1 = items.indexOf("Item" + deletedItemID);
                        int index2 = items.indexOf(" ", index1);
                        items = items.replace(items.substring(index1, index2 + 1), "");

                    } else
                    {
                        System.out.printf("You don't have any item with the id %s!\n", deletedItemID);
                    }
                    System.out.printf("Your items: %s", items);
                    // 5. Operation
                } else if (operation.equals("5"))
                {

                }

            } else
            {
                System.out.println("Incorrect password! Goodbye!");
            }
        } else
        {
            System.out.println("Username not found! Goodbye!");
        }
        scanner.close();
    }
}
