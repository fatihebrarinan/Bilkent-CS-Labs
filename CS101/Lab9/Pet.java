package Lab9;

public class Pet
{
    String petName;
    String petType;
    int health;
    int happiness;
    String mood;
    User owner;
    int age;
    boolean old;

    public Pet(String petName, String petType, User owner)
    {
        this.petName = petName;
        this.petType = petType;
        this.happiness = 50;
        this.health = 50;
        this.owner = owner;
        this.mood = "Sad";
        this.age = 0;
    }

    public void feedPet()
    {
        if (happiness >= 100)
        {
            System.out.println("Happiness is already max.");
        } else if (happiness >= 95)
        {
            happiness = 100;
            System.out.println("Happiness is maxed.");
        } else
        {
            happiness += 5;
        }
        mood = setMood(happiness);
        if (health >= 100)
        {
            System.out.println("Health already max.");
        } else
        {
            health += 10;
        }
        agePet();

    }

    public void playWithPet()
    {
        if (happiness >= 100)
        {
            System.out.println("Happiness is already max.");
        } else if (happiness >= 85)
        {
            happiness = 100;
            System.out.println("Happiness is maxed.");
        } else
        {
            happiness += 15;
        }
        mood = setMood(happiness);
        agePet();
    }

    public void groomPet()
    {
        if (health >= 100)
        {
            System.out.println("Health is already max.");
        } else if (health >= 85)
        {
            health = 100;
            System.out.println("Health is maxed.");
        } else
        {
            health += 15;
        }
        agePet();
    }

    public String setMood(int happiness)
    {
        if (happiness > 70)
            return "Happy";
        else if (happiness > 50)
            return "Playful";
        else
            return "Sad";
    }

    public String getPetStatus()
    {
        return "Name: " + petName + ", Type: " + petType + ", Health: " + health + ", Happiness: " + happiness
                + ", Mood: " + mood;
    }

    public void agePet()
    {
        this.age += 0.25;
    }

    public String getPetName()
    {
        return this.petName;
    }

    public String getPetType()
    {
        return this.petType;
    }

    public int getHealth()
    {
        return this.health;
    }

    public int getHappiness()
    {
        return this.happiness;
    }

    public String toString()
    {
        return "Name: " + petName + ", Type: " + petType + ", Health: " + health + ", Happiness: " + happiness
                + ", Mood: " + mood;
    }

    public boolean equals(Pet pet)
    {
        if (this.petName.equals(pet.getPetName()) && this.petType.equals(pet.getPetType()))
            return true;
        else
            return false;
    }
}
