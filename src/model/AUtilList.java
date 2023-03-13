package model;

import interfaces.IControllerInterface;
import petTypes.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The AUtilList Class is the powerhouse of the cell in regard to this application.
 * <p>
 * Every @Override is a method call from controllers through the Interface to here.
 * <p>
 * Everyone other Method is used to support local math that is done here as the central Object based hub.
 * <p>
 * Each section will be explained briefly.
 *
 * @param <E>
 */
public class AUtilList<E> implements IControllerInterface<E> {

    //Initialize Object based variables that will be used A LOT
    private final List<Vet> vetList = new ArrayList<>();
    private final List<PetOwner> petOwners = new ArrayList<>();

    //Standard class constructor
    public AUtilList() {
    }

    //Adds pets that were already declared
    @Override
    public void addVets(Vet toAdd1, Vet toAdd2, Vet toAdd3) {
        vetList.add(toAdd1);
        vetList.add(toAdd2);
        vetList.add(toAdd3);
    }

    //Adds new pet owners to list here
    @Override
    public void addPetOwner(String name) {
        petOwners.add(new PetOwner(name));
    }

    //Checks if the name is the same because we can only handle unique IDs for easy mode
    @Override
    public boolean checkNameifSame(String name) {
        for (PetOwner names : petOwners) {
            if (names.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    //grabs names of pet owners
    @Override
    public List<String> getNamesForPetOwnerBox() {
        List<String> toReturn = new ArrayList<>();

        for (PetOwner names : petOwners) {
            toReturn.add(names.getName());
        }

        return toReturn;
    }

    //Removes pet owner from list
    @Override
    public List<String> removeNameFromPetOwnerBox(String nameToRemove) {
        List<String> toReturn = new ArrayList<>();

        for (PetOwner names : petOwners) {
            if (!names.getName().equals(nameToRemove)) {
                toReturn.add(names.getName());
            }
        }

        Iterator<PetOwner> iterator = petOwners.iterator();
        while (iterator.hasNext()) {
            PetOwner petOwner = iterator.next();
            if (petOwner.getName().equals(nameToRemove)) {
                iterator.remove();
            }
        }

        return toReturn;
    }

    //Checks if petOwner list is empty
    @Override
    public boolean isPetOwnerEmpty() {
        return petOwners.isEmpty();
    }

    //Creates a new pet class of subtype pet type to add as a pet to an active owner in the petowner list
    @Override
    public void addPetToOwner(String petOwnerName, String petName, String petType, int numAge, String vet, String petBreed) {

        Vet tempVet = new Vet();

        switch (vet) {
            case "roberts":
                tempVet = vetList.get(0);
                break;
            case "holdren":
                tempVet = vetList.get(1);
                break;
            default:
                tempVet = vetList.get(2);
                break;
        }

        Iterator<PetOwner> iterator = petOwners.iterator();
        while (iterator.hasNext()) {
            PetOwner petOwner = iterator.next();
            if (petOwner.getName().equals(petOwnerName)) {
                switch (petType) {
                    case "amphibian":
                        Amphibian amphibian = new Amphibian(petName, numAge, tempVet, petBreed);
                        petOwner.addPet(amphibian);
                        break;
                    case "bird":
                        Bird bird = new Bird(petName, numAge, tempVet, petBreed);
                        petOwner.addPet(bird);
                        break;
                    case "cat":
                        Cat cat = new Cat(petName, numAge, tempVet, petBreed);
                        petOwner.addPet(cat);
                        break;
                    case "dog":
                        Dog dog = new Dog(petName, numAge, tempVet, petBreed);
                        petOwner.addPet(dog);
                        break;
                    case "horse":
                        Horse horse = new Horse(petName, numAge, tempVet, petBreed);
                        petOwner.addPet(horse);
                        break;
                    case "reptile":
                        Reptile reptile = new Reptile(petName, numAge, tempVet, petBreed);
                        petOwner.addPet(reptile);
                        break;
                }
            }
        }
    }

    //grabs pet names per pet owner names given
    @Override
    public List<String> getPetNames(String name) {
        PetOwner temp = getNameAndInfo(name);

        List<Pet> tempsPets = temp.getPets();

        List<String> tempPetNames = new ArrayList<>();

        for (Pet names : tempsPets) {
            tempPetNames.add(names.getName());
        }

        return tempPetNames;
    }

    //grabs all the info of a pet given the pet owner name and pet name
    @Override
    public List<String> allThePetInfo(String ownerName, String petName) {

        PetOwner temp = getNameAndInfo(ownerName);

        List<Pet> tempPets = temp.getPets();

        List<String> infoToGet = new ArrayList<>();


        for (Pet names : tempPets) {
            if (names.getName().equals(petName)) {
                infoToGet.add(names.getName());
                infoToGet.add(petTypeFinder(names.toString()));
                infoToGet.add(String.valueOf(names.getAge()));
                infoToGet.add(petBreedFinder(names.toString()));
                infoToGet.add(names.getVet().getName());
            }
        }

        return infoToGet;
    }

    //removes pet from petOwner
    @Override
    public void deleteOldPetInfo(String name, List<String> oldPetInfo) {
        //Find which owner we copy
        PetOwner temp = getNameAndInfo(name);

        //remove original from archive

        if (petOwners.size() == 1) {
            petOwners.clear();
        } else {
            for (PetOwner names : petOwners) {
                if (petOwners.contains(temp)) {
                    petOwners.remove(names);
                }
            }
        }

        //Have List of pets to use
        List<Pet> tempsPets = temp.getPets();

        //remove the single pet we are changing
        if (tempsPets.size() == 1) {
            tempsPets.clear();
        } else {
            for (Pet pet : tempsPets) {
                if (pet.getName().equals(oldPetInfo.get(0)) && pet.getAge() == Integer.parseInt(oldPetInfo.get(2))) {
                    tempsPets.remove(pet);
                }
            }
        }

        //add list back with pet removed
        temp.setPets(tempsPets);

        //Add pet owner info back into list
        petOwners.add(temp);
    }

    //Uses a toString of the pet type from pet owner pet list
    private String petBreedFinder(String s) {
        String regex = "'([^']*)'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        String match = null;
        while (matcher.find()) {
            match = s.substring(matcher.start(1), matcher.end(1));
        }

        return match;
    }

    //Uses a toString of the pet type from pet owner pet list
    private String petTypeFinder(String s) {
        String regex = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        String match = null;
        if (matcher.find()) {
            match = s.substring(matcher.start(), matcher.end());
        }

        return match;
    }

    //able to find the petOwner object given string value of petOwner name
    private PetOwner getNameAndInfo(String name) {
        PetOwner temp = new PetOwner();

        Iterator<PetOwner> iterator = petOwners.iterator();
        while (iterator.hasNext()) {
            PetOwner petOwner = iterator.next();
            if (petOwner.getName().equals(name)) {
                temp = petOwner;
            }
        }

        return temp;
    }
}
