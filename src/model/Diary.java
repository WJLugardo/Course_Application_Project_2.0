package model;

import testClasses.DiaryEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Diary {
    // class for pet diary
    private List<DiaryEntry> entries;

    // constructor and getter
    public Diary() {
        this.entries = new ArrayList<>();
    }

    public List<DiaryEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<DiaryEntry> entries) {
        this.entries = entries;
    }

    // method for adding a diary entry
    public void addEntry(DiaryEntry entry) {
        entries.add(entry);
    }

    // method for getting all diary entries for a specific date
    public List<DiaryEntry> getEntriesByDate(LocalDate date) {
        List<DiaryEntry> result = new ArrayList<>();
        for (DiaryEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                result.add(entry);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Diary{" + "entries=" + entries + '}';
    }
}