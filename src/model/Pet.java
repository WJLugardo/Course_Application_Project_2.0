package model;

import interfaces.IUserType;
import testClasses.DiaryEntry;

import java.time.LocalDate;
import java.util.List;

public abstract class Pet implements IUserType {
    private String name;
    private int age;
    private Vet vet;
    private Diary diary;

    public Pet(String name, int age, Vet vet) {
        this.name = name;
        this.age = age;
        this.vet = vet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }

    public void addDiaryEntry(DiaryEntry entry) {
        diary.addEntry(entry);
    }

    public void displayDiaryEntries(LocalDate date) {
        System.out.println("Diary entries for " + getName() + " on " + date.toString() + ":");
        List<DiaryEntry> entries = diary.getEntriesByDate(date);
        for (DiaryEntry entry : entries) {
            System.out.println(entry.getText());
        }
    }

    public abstract void displayInfo();

    public abstract void makeSound();

    @Override
    public String toString() {
        return "Pet{" + "name='" + name + '\'' + ", age=" + age + ", vet=" + vet + ", diary=" + diary + '}';
    }
}