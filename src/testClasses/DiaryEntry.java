package testClasses;

import java.time.LocalDate;

public class DiaryEntry {
    // class for individual diary entries
    private LocalDate date;
    private String text;

    // constructor and getters
    public DiaryEntry(LocalDate date, String text) {
        this.date = date;
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "DiaryEntry{" + "date=" + date + ", text='" + text + '\'' + '}';
    }
}