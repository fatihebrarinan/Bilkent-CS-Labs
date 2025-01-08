package Lab9REV;

import java.util.ArrayList;
import java.util.Scanner;

public class VPAManagerREV
{
    private static ArrayList<UserREV> users = new ArrayList<>();
    private static ArrayList<PetREV> unownedPets = new ArrayList<>();
    private static UserREV currentUser;
    private static int step;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit)
        {
            if (currentUser == null)
            {
                System.out.println("\nWelcome to the Virtual Pet Adoption System!");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume new line
                switch (choice)
                {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
                }
            } else
            {
                showUserMenu(scanner);
            }
        }

        scanner.close();
    }

    private static void registerUser(Scanner scanner)
    {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        // Check if username already exists
        for (UserREV user : users)
        {
            if (user.getName().equalsIgnoreCase(username))
            {
                System.out.println("Username already exists. Please choose a different username.");
                return;
            }
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        // Create a new User and add it to the list
        UserREV newUser = new UserREV(username, password); // Pass both username and password
        users.add(newUser);
        System.out.println("Registration successful! You can now log in.");
    }

    private static void loginUser(Scanner scanner)
    {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine(); // Prompt for the password

        // Check if the user exists and verify the password
        for (UserREV user : users)
        {
            if (user.getName().equalsIgnoreCase(username) && user.verifyPassword(password))
            {
                currentUser = user; // Set the logged-in user
                System.out.println("Login successful! Welcome, " + username + "!");
                return;
            }
        }

        // If no match is found
        System.out.println("Invalid username or password. Please try again.");
    }

    private static void showUserMenu(Scanner scanner)
    {
        boolean exit = false;

        while (!exit)
        {
            System.out.println("\nMenu:");
            System.out.println("1. Adopt a new pet");
            System.out.println("2. Adopt an unowned pet");
            System.out.println("3. Release a pet");
            System.out.println("4. Feed a pet");
            System.out.println("5. Play with a pet");
            System.out.println("6. Groom a pet");
            System.out.println("7. Display your pets");
            System.out.println("8. Search pets by happiness");
            System.out.println("9. Search pets by health");
            System.out.println("10. Display average health and happiness");
            System.out.println("11. Logout");
            System.out.println("12. Switch User");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice)
            {
            case 1:
                adoptNewPet(scanner);
                break;
            case 2:
                adoptUnownedPet(scanner);
                break;
            case 3:
                releasePet(scanner);
                break;
            case 4:
                feedPet(scanner);

                break;
            case 5:
                playWithPet(scanner);
                step--;
                break;
            case 6:
                groomPet(scanner);
                step--;
                break;
            case 7:
                currentUser.displayPets();
                break;
            case 8:
                searchPetsByHappiness(scanner);
                break;
            case 9:
                searchPetsByHealth(scanner);
                break;
            case 10:
                currentUser.displayAverageHealthAndHappiness();
                break;
            case 11:
                logoutUser();
                exit = true;
                break;
            case 12:
                switchUser(scanner);
                exit = true;
                break;
            default:
                System.out.println("Invalid option.");
            }

            step++;

            if (step == 3)
            {
                for (PetREV petREV : currentUser.pets)
                {
                    if (petREV.getHealth() < 30)
                    {
                        petREV.setHappiness(petREV.getHappiness() - 10);
                        petREV.setHealth(petREV.getHealth() - 15);
                    } else
                    {
                        petREV.setHappiness(petREV.getHappiness() - 10);
                        petREV.setHealth(petREV.getHealth() - 10);
                    }
                }
                step = 0;
            }
            if (currentUser != null)
            {
                for (PetREV petREV : currentUser.pets)
                {
                    if (petREV.getHealth() <= 0)
                    {
                        currentUser.pets.remove(petREV);
                        unownedPets.add(petREV);
                        System.out.printf("The pet %s is now moved to unowned pets!", petREV.getPetName());
                    }
                }
            }

        }
    }

    private static void adoptNewPet(Scanner scanner)
    {
        System.out.print("Enter pet name: ");
        String petName = scanner.nextLine();
        System.out.print("Enter pet type (Dog/Cat/Rabbit): ");
        String petType = scanner.nextLine();
        currentUser.adoptPet(petName, petType);
    }

    private static void adoptUnownedPet(Scanner scanner)
    {
        if (unownedPets.isEmpty())
        {
            System.out.println("No unowned pets available.");
            return;
        }
        System.out.println("Available unowned pets:");
        for (int i = 0; i < unownedPets.size(); i++)
        {
            System.out.println(i + ". " + unownedPets.get(i).getPetStatus());
        }
        System.out.print("Enter the index of the pet you want to adopt: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (index >= 0 && index < unownedPets.size())
        {
            PetREV pet = unownedPets.remove(index);
            currentUser.adoptPet(pet);
        } else
        {
            System.out.println("Invalid index.");
        }
    }

    private static void releasePet(Scanner scanner)
    {
        System.out.print("Enter the name of the pet to release: ");
        String petName = scanner.nextLine();
        PetREV pet = currentUser.getPetByName(petName);
        if (pet != null)
        {
            PetREV releasedPet = currentUser.releasePet(pet);
            if (releasedPet != null)
            {
                unownedPets.add(releasedPet);
            }
        } else
        {
            System.out.println("Pet not found.");
        }
    }

    private static void feedPet(Scanner scanner)
    {
        System.out.print("Enter the name of the pet to feed: ");
        String petName = scanner.nextLine();
        PetREV pet = currentUser.getPetByName(petName);
        if (pet != null)
        {
            pet.feedPet();
        } else
        {
            System.out.println("Pet not found.");
        }
    }

    private static void playWithPet(Scanner scanner)
    {
        System.out.print("Enter the name of the pet to play with: ");
        String petName = scanner.nextLine();
        PetREV pet = currentUser.getPetByName(petName);
        if (pet != null)
        {
            pet.playWithPet();
        } else
        {
            System.out.println("Pet not found.");
        }
    }

    private static void groomPet(Scanner scanner)
    {
        System.out.print("Enter the name of the pet to groom: ");
        String petName = scanner.nextLine();
        PetREV pet = currentUser.getPetByName(petName);
        if (pet != null)
        {
            pet.groomPet();
        } else
        {
            System.out.println("Pet not found.");
        }
    }

    private static void searchPetsByHappiness(Scanner scanner)
    {
        System.out.print("Enter minimum happiness level: ");
        int minHappiness = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        ArrayList<PetREV> pets = currentUser.searchPetsByHappiness(minHappiness);
        if (pets.isEmpty())
        {
            System.out.println("No pets found with happiness >= " + minHappiness);
        } else
        {
            System.out.println("Pets with happiness >= " + minHappiness + ":");
            for (PetREV pet : pets)
            {
                System.out.println(pet.getPetStatus());
            }
        }
    }

    private static void searchPetsByHealth(Scanner scanner)
    {
        System.out.print("Enter minimum health level: ");
        int minHealth = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        ArrayList<PetREV> pets = currentUser.searchPetsByHealth(minHealth);
        if (pets.isEmpty())
        {
            System.out.println("No pets found with health >= " + minHealth);
        } else
        {
            System.out.println("Pets with health >= " + minHealth + ":");
            for (PetREV pet : pets)
            {
                System.out.println(pet.getPetStatus());
            }
        }
    }

    private static void logoutUser()
    {
        System.out.println("You have been logged out.");
        currentUser = null;
    }

    private static void switchUser(Scanner scanner)
    {
        logoutUser();
        loginUser(scanner);
    }
}