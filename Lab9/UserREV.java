package Lab9REV;

import java.util.ArrayList;

public class UserREV
{
    String username;
    String password;
    ArrayList<PetREV> pets;
    int budget = 100;

    public UserREV(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.pets = new ArrayList<PetREV>();
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
            PetREV newPet = new PetREV(name, type, this);
            pets.add(newPet);
            this.budget -= 30;
        } else if (type.equals("Dog") && budget >= 40)
        {
            PetREV newPet = new PetREV(name, type, this);
            pets.add(newPet);
            this.budget -= 40;
        } else if (type.equals("Rabbit") && budget >= 20)
        {
            PetREV newPet = new PetREV(name, type, this);
            pets.add(newPet);
            this.budget -= 20;
        }
        System.out.printf("You have adopted a new %s named %s.\n", type, name);

    }

    public void adoptPet(PetREV pet)
    {
        pets.add(pet);
        System.out.printf("You have adopted a new %s named %s.\n", pet.getPetType(), pet.getPetName());

    }

    public PetREV releasePet(PetREV pet)
    {
        for (PetREV p : pets)
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
        for (PetREV pet : pets)
        {
            System.out.println(pet.getPetStatus());
            System.out.println();
        }
    }

    public PetREV getPetByIndex(int index)
    {
        return pets.get(index);
    }

    public PetREV getPetByName(String name)
    {
        for (PetREV pet : pets)
        {
            if (pet.getPetName().equals(name))
                return pet;
        }
        return null;
    }

    public ArrayList<PetREV> searchPetsByHealth(int health)
    {
        ArrayList<PetREV> healthyPets = new ArrayList<PetREV>();
        for (PetREV pet : pets)
        {
            if (pet.getHealth() >= health)
            {
                healthyPets.add(pet);
            }
        }
        return healthyPets;
    }

    public ArrayList<PetREV> searchPetsByHappiness(int happiness)
    {
        ArrayList<PetREV> happyPets = new ArrayList<PetREV>();
        for (PetREV pet : pets)
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
        for (PetREV pet : pets)
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

    public boolean equals(UserREV user)
    {
        if (this.username.equals(user.getName()))
            return true;
        else
            return false;
    }

}
