import model.PetOwner;
import model.Vet;
import petTypes.Bird;
import petTypes.Cat;
import petTypes.Dog;
import testClasses.DiaryEntry;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        // create some vets
        Vet vet1 = new Vet("Dr. Smith", "123 Main St", "123-456-7890");
        Vet vet2 = new Vet("Dr. Doe", "456 Elm St", "555-555-1212");

        // create some pets
        Dog dog1 = new Dog("Fido", 12, vet1, "Golden Retriever");
        Cat cat1 = new Cat("Fluffy", 8, vet1, "Siamese");
        Bird bird1 = new Bird("Polly", 2, vet2, "Parrot");

        // add vets to locations
        vet1.addLocation("Animal Hospital");
        vet2.addLocation("Pet Clinic");
        vet2.addLocation("Animal Hospital");

        // create some diary entries
        DiaryEntry entry1 = new DiaryEntry(LocalDate.now(), "Fido seemed to be in good health during his checkup.");
        DiaryEntry entry2 = new DiaryEntry(LocalDate.now(), "Fluffy needs to lose some weight.");
        DiaryEntry entry3 = new DiaryEntry(LocalDate.now(), "Polly's beak looks healthy and she is eating well.");

        // add diary entries to pets
        dog1.getDiary().addEntry(entry1);
        cat1.getDiary().addEntry(entry2);
        bird1.getDiary().addEntry(entry3);

        // create some pet owners and add pets to them
        PetOwner owner1 = new PetOwner("Alice");
        PetOwner owner2 = new PetOwner("Bob");
        owner1.addPet(dog1);
        owner1.addPet(cat1);
        owner2.addPet(bird1);

        // display pets owned by each pet owner
        owner1.displayPets();
        owner2.displayPets();

        // display diary entries for a specific pet on a specific date
        owner1.displayDiaryEntries(dog1, LocalDate.now());

        // assign a vet to each pet
        dog1.setVet(vet1);
        cat1.setVet(vet2);
        bird1.setVet(vet2);

        // display vet information for each pet
        System.out.println(dog1.getName() + " is assigned to " + dog1.getVet().getName() + " at " + dog1.getVet().getLocations().get(0));
        System.out.println(cat1.getName() + " is assigned to " + cat1.getVet().getName() + " at " + cat1.getVet().getLocations().get(0));
        System.out.println(bird1.getName() + " is assigned to " + bird1.getVet().getName() + " at " + bird1.getVet().getLocations().get(0));
    }
}