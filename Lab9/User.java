package Lab9;

import java.util.ArrayList;

public class User
{
    String username;
    String password;
    ArrayList<Pet> pets;
    int budget = 100;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.pets = new ArrayList<Pet>();
    }

    public boolean verifyPassword(String password)
    {
        if (this.password.equals(password))
            return true;
        else
            return false;
    }

    public void adoptPet(String name, String type)
    {
        if (type.equals("Cat") && budget >= 30)
        {
            Pet newPet = new Pet(name, type, this);
            pets.add(newPet);
            this.budget -= 30;
        } else if (type.equals("Dog") && budget >= 40)
        {
            Pet newPet = new Pet(name, type, this);
            pets.add(newPet);
            this.budget -= 40;
        } else if (type.equals("Rabbit") && budget >= 20)
        {
            Pet newPet = new Pet(name, type, this);
            pets.add(newPet);
            this.budget -= 20;
        }
        System.out.printf("You have adopted a new %s named %s.\n", type, name);

    }

    public void adoptPet(Pet pet)
    {
        pets.add(pet);
        System.out.printf("You have adopted a new %s named %s.\n", pet.getPetType(), pet.getPetName());

    }

    public Pet releasePet(Pet pet)
    {
        for (Pet p : pets)
        {
            if (p.equals(pet))
            {
                pets.remove(p);
                System.out.printf("You have released %s the %s", p.getPetName(), p.getPetType());
                return p;
            }
        }
        System.out.println("Pet not found!");
        return null;
    }

    public void displayPets()
    {
        System.out.println("Your pets: ");
        for (Pet pet : pets)
        {
            System.out.println(pet.getPetStatus());
            System.out.println();
        }
    }

    public Pet getPetByIndex(int index)
    {
        return pets.get(index);
    }

    public Pet getPetByName(String name)
    {
        for (Pet pet : pets)
        {
            if (pet.getPetName().equals(name))
                return pet;
        }
        return null;
    }

    public ArrayList<Pet> searchPetsByHealth(int health)
    {
        ArrayList<Pet> healthyPets = new ArrayList<Pet>();
        for (Pet pet : pets)
        {
            if (pet.getHealth() >= health)
            {
                healthyPets.add(pet);
            }
        }
        return healthyPets;
    }

    public ArrayList<Pet> searchPetsByHappiness(int happiness)
    {
        ArrayList<Pet> happyPets = new ArrayList<Pet>();
        for (Pet pet : pets)
        {
            if (pet.getHappiness() >= happiness)
            {
                happyPets.add(pet);
            }
        }
        return happyPets;

    }

    public void displayAverageHealthAndHappiness()
    {
        int totalHappiness = 0;
        int totalHealth = 0;
        for (Pet pet : pets)
        {
            totalHappiness += pet.getHappiness();
            totalHealth += pet.getHealth();
        }
        System.out.printf("Average health: %d\nAverage happiness: %d\n", totalHealth / pets.size(),
                totalHappiness / pets.size());
    }

    public String getName()
    {
        return this.username;
    }

    public String toString()
    {
        String petsString = "";
        for (int i = 0; i < pets.size(); i++)
        {
            petsString += Integer.toString(i + 1) + ": " + pets.get(i).toString() + "\n";
        }
        return "Username: " + username + "\nPassword: " + password + "\nBudget: " + budget + "\nPets: " + petsString;
    }

    public boolean equals(User user)
    {
        if (this.username.equals(user.getName()))
            return true;
        else
            return false;
    }

}
