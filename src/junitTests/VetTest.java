package junitTests;

import model.Vet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VetTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        Vet testVet = new Vet();
        assertNull(testVet.getName());
    }

    @Test
    void setName() {
        Vet testVet = new Vet();
        testVet.setName("test");

        assertEquals("test",testVet.getName());
    }

    @Test
    void getAddress() {
        Vet testVet = new Vet();

    }

    @Test
    void setAddress() {
        Vet testVet = new Vet();

    }

    @Test
    void getPhoneNumber() {
        Vet testVet = new Vet();

    }

    @Test
    void setPhoneNumber() {
        Vet testVet = new Vet();

    }

    @Test
    void getLocations() {
        Vet testVet = new Vet();

    }

    @Test
    void setLocations() {
        Vet testVet = new Vet();

    }

    @Test
    void addLocation() {
        Vet testVet = new Vet();

    }

    @Test
    void viewPetOwnerDiaries() {
        Vet testVet = new Vet();

    }

    @Test
    void displayDiaryEntries() {
        Vet testVet = new Vet();

    }

    @Test
    void testDisplayDiaryEntries() {
        Vet testVet = new Vet();

    }

    @Test
    void getUserType() {
        Vet testVet = new Vet();

    }

    @Test
    void testToString() {
        Vet testVet = new Vet();

    }
}