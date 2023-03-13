package petTypes;

import model.Pet;
import model.Vet;

public class Dog extends Pet {
    // subclass of Pet for dogs
    private String breed;

    // constructor, getters and setters
    public Dog(String name, int age, Vet vet, String breed) {
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
        System.out.println("Woof!!");
    }

    @Override
    public String toString() {
        return "Dog{" + "breed='" + breed + '\'' + '}';
    }
}