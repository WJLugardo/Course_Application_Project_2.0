import model.Pet;
import model.PetOwner;
import model.Vet;
import petTypes.*;
import testClasses.DiaryEntry;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestHarness {
    ArrayList<Pet> pets = new ArrayList<Pet>();
    Vet vet1 = new Vet("Dr. Smith", "123 Main St", "123-456-7890");
    Vet vet2 = new Vet("Dr. Doe", "456 Elm St", "555-555-1212");

    public TestHarness() {
    }

    public void testClassHierarchy() {

        pets.add(new Amphibian("Froggy", 3, vet1, "White Tree Frog"));
        pets.add(new Bird("Tuccy", 19, vet2, "Cockatiel"));
        pets.add(new Cat("Stewie", 11, vet1, "Ragdoll"));
        pets.add(new Dog("Bud", 7, vet2, "Chihuahua"));
        pets.add(new Horse("Morgan", 5, vet1, "Morgan Horse"));
        pets.add(new Reptile("Tammy", 2, vet2, "Leopard Gecko"));

        for (Pet pet : pets) {
            pet.makeSound();
        }
    }

    public void testInterface(){
        Pet testingPet = pets.get(0);
        PetOwner testBob = new PetOwner("Bob");
        testBob.addPet(testingPet);

        testingPet.getDiary().addEntry(new DiaryEntry(LocalDate.of(2023,2,24),"My frog " +
                "jumped into the window and fell down 2 feet on its back."));

        testingPet.getDiary().addEntry(new DiaryEntry(LocalDate.now(),"Froggy was very depressed looking today " +
                "and barely moved."));


        System.out.println("This will test the Diary Displayble interface calls from Pet Owner");
        System.out.println("------------------------------------------------------------------");

        testBob.displayDiaryEntries(testingPet);
        testBob.displayDiaryEntries(testingPet,LocalDate.now());

        System.out.println("\nThis will test the Diary Displayble interface calls from Vet1");
        System.out.println("-------------------------------------------------------------");

        vet1.displayDiaryEntries(testingPet);
        vet1.displayDiaryEntries(testingPet, LocalDate.now());
    }
}
