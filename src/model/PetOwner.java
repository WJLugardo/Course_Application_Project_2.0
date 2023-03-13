package model;

import interfaces.IDiaryDisplayable;
import interfaces.IUserType;
import testClasses.DiaryEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetOwner implements IUserType, IDiaryDisplayable {
    // class for pet owners
    private String name;
    private List<Pet> pets = new ArrayList<>();

    // constructor and getter
    public PetOwner(){}
    public PetOwner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // methods for managing pets
    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    // method for displaying all pets owned by the pet owner
    public void displayPets() {
        System.out.println("Pets owned by " + name + ":");
        for (Pet pet : pets) {
            pet.displayInfo();
        }
    }

    @Override
    public void displayDiaryEntries(Pet pet) {
        System.out.println("Diary entries for " + pet.getName() + ":");
        List<DiaryEntry> entries = pet.getDiary().getEntries();
        for (DiaryEntry entry : entries) {
            System.out.println(entry.getText());
        }
    }
    @Override
    public void displayDiaryEntries(Pet pet, LocalDate date) {
        System.out.println("Diary entries for " + pet.getName() + " on " + date.toString() + ":");
        List<DiaryEntry> entries = pet.getDiary().getEntriesByDate(date);
        for (DiaryEntry entry : entries) {
            System.out.println(entry.getText());
        }
    }

    // implementation of UserType interface method
    @Override
    public String getUserType() {
        return "Pet Owner";
    }

    public List<Diary> getDiaries() {
        List<Diary> diaries = new ArrayList<>();
        for (Pet pet : pets) {
            diaries.add(pet.getDiary());
        }
        return diaries;
    }

    @Override
    public String toString() {
        return "PetOwner{" + "name='" + name + '\'' + ", pets=" + pets + '}';
    }
}