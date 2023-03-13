package petTypes;

import model.Pet;
import model.Vet;

import java.util.Objects;

public class Bird extends Pet {
    // subclass of Pet for birds
    private String breed;
    // constructor, getters and setters
    public Bird(String name, int age, Vet vet, String breed) {
        super(name, age, vet);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setSpecies(String species) {
        this.breed = species;
    }

    public void displayInfo() {
        System.out.println("Name: " + getName() + ", Age: " + getAge() + ", Breed: " + getBreed() + ", Vet: " + getVet().getName());
    }

    @Override
    public void makeSound() {
        if (Objects.equals(breed, "crow") || Objects.equals(breed, "Crow")) {
            System.out.println("Kaw! Kaw!");
        } else {
            System.out.println("Tweet! Tweet!");
        }
    }

    @Override
    public String toString() {
        return "Bird{" + "breed='" + breed + '\'' + '}';
    }
}