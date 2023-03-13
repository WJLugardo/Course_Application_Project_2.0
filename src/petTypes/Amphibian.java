package petTypes;

import model.Pet;
import model.Vet;

public class Amphibian extends Pet {
    private String breed;

    public Amphibian(String name, int age, Vet vet, String breed) {
        super(name, age, vet);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void displayInfo() {
        System.out.println("Name: " + getName() + ", Age: " + getAge() + ", Breed: " + getBreed() + ", Vet: " + getVet().getName());
    }

    @Override
    public void makeSound() {
        System.out.println("click!");
    }

    @Override
    public String toString() {
        return "Amphibian{" + "breed='" + breed + '\'' + '}';
    }
}
