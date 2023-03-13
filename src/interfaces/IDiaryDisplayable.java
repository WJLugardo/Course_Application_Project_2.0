package interfaces;

import model.Pet;

import java.time.LocalDate;

//Not used for current assignment was used as testing interfaces


public interface IDiaryDisplayable {
    void displayDiaryEntries(Pet pet);

    void displayDiaryEntries(Pet pet, LocalDate date);
}