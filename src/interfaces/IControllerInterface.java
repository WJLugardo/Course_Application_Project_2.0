package interfaces;

import model.Vet;

import java.util.List;

/**
 * The IControllerInterface is the bridge between the Controllers and the AUtilList!
 * <p>
 * It holds the methods and names that can be called to do incredible actions when a button is pressed.
 * <p>
 * All the information passes through this middleman of a class.
 * <p>
 * Math is done in AUtilList and data is shown publicly through the first and second Controllers when needed
 * <p>
 * The names of each method explains what is does. For more information find the corresponding method in AUtilList class
 *
 * @param <T>
 */


public interface IControllerInterface<T> {
    //All the methods below are calls from the constructors to the AUtilList class.
    void addVets(Vet toAdd1, Vet toAdd2, Vet toAdd3);

    void addPetOwner(String name);

    boolean checkNameifSame(String name);

    List<String> getNamesForPetOwnerBox();

    List<String> removeNameFromPetOwnerBox(String nameToRemove);

    boolean isPetOwnerEmpty();

    void addPetToOwner(String petOwnerName, String petName, String petType, int numAge, String vet, String petBreed);

    List<String> getPetNames(String name);

    List<String> allThePetInfo(String ownerName, String petName);

    void deleteOldPetInfo(String name, List<String> oldPetInfo);
}
