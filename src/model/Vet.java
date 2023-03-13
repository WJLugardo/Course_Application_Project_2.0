package model;

import interfaces.IDiaryDisplayable;
import interfaces.IUserType;
import testClasses.DiaryEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vet implements IUserType, IDiaryDisplayable {
    private String name;
    private String address;
    private String phoneNumber;
    private List<String> locations;

    public Vet() {}

    public Vet(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.locations = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public void addLocation(String location) {
        locations.add(location);
    }

    public void viewPetOwnerDiaries(PetOwner petOwner) {
        List<Diary> diaries = petOwner.getDiaries();
        for (Diary diary : diaries) {
            // do something with the diary
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

    @Override
    public String getUserType() {
        return "Vet";
    }


    @Override
    public String toString() {
        return "Vet{" + "name='" + name + '\'' + ", address='" + address + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", locations=" + locations + '}';
    }
}