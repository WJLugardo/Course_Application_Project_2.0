package controller;

import interfaces.IControllerInterface;
import model.Vet;
import view.ChangePetInfoView;
import view.VetPetAppView;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

/**
 * The following class is the first controller class and uses the IControllerInterface to directly use all the methods
 * inside the AUtilList class.
 * <p>
 * When the Change a Pets Information is pressed it will set this current gui to be no longer visible while working on
 * an additional gui until prompted to come back. Information is shared all within the same object and objects working
 * in the AUtilList class.
 * <p>
 * Each button below is stated and each method explains the connection to the interface and AUtilList class
 */
public class VetPetAppController {
    final private VetPetAppView view;
    final private IControllerInterface model;

    public VetPetAppController(IControllerInterface model,
                               VetPetAppView view) {
        this.view = view;
        this.model = model;

        //Initialize Vet Values beforehand
        addVets();

        //Enables some elements to be disabled when launched
        setFalseForStart();


        //button listeners and other stuff to follow


        //Listener for Add to Owner List button
        view.form.getAddToOwnerListButton().addActionListener(e -> {
            //Each if statement within the button is to ensure proper information and no mistakes are occuring

            if (getOwnerNameInput().isBlank()
                    || getOwnerNameInput().startsWith(" ")) {
                JOptionPane.showMessageDialog(view,
                        "Error: No Name Detected to be added or invalid Starting Space in Name");
                view.form.getOwnerNameTextField().setText("");
                return;
            }

            //same name checker
            if (!sameNameChecker(getOwnerNameInput())) {
                JOptionPane.showMessageDialog(view,
                        "Error: Non-Unique name Detected, please enter a different identifier");
                return;
            }

            addPetOwner(getOwnerNameInput());

            setTrueWhenAtLeastOnePetOwner();
            updatePetOwnerComboBox();
            view.form.getOwnerNameTextField().setText("");
            clearInputBoxes();
        });

        //Listener for the Remove from Owner List button
        view.form.getRemoveFromOwnerListButton().addActionListener(e -> {
            removeFromComboBox();
        });

        //Listener for the Submit Pet Button
        view.form.getSubmitPetButton().addActionListener(e -> {
            if (getPetName().isBlank()
                    || getOwnerNameInput().startsWith(" ")
                    || getOwnerNameInput().contains(" ")) {
                JOptionPane.showMessageDialog(view,
                        "Error: No Name Detected to be added" +
                                "\nor invalid Starting Space in Name" +
                                "\nor Space detected");
                return;
            }

            if (!ageIsNotANumber()) {
                JOptionPane.showMessageDialog(view,
                        "Error: Age was not in number format");
                return;
            }

            if (!radioButtonPet()) {
                JOptionPane.showMessageDialog(view,
                        "Error: you did not select a Pet Type");
                return;
            }

            if (!radioButtonDoctor()) {
                JOptionPane.showMessageDialog(view,
                        "Error: you did not select a Pet Type");
                return;
            }


            addPetToOwner(getPetName(), view.form.getPetageinputbox().getText());

            JOptionPane.showMessageDialog(view, "Pet Successfully Added!");
            clearInputBoxes();
        });

        //Listener for the Change A Pets Infromation Button
        view.form.getChangeAPetsInformationButton().addActionListener(e -> {
            openChangePetInfoController();
        });
    }

    //Method to check if a radio button is actually checked
    private boolean radioButtonDoctor() {
        if (view.form.getDrRobertsRadioButton().isSelected()) {
            return true;
        }
        if (view.form.getDrHoldrenRadioButton().isSelected()) {
            return true;
        }
        return view.form.getDrAbduhlRadioButton().isSelected();
    }

    //Method to check if a radio button is actually checked
    private boolean radioButtonPet() {
        if (view.form.getAmphibianRadioButton().isSelected()) {
            return true;
        }
        if (view.form.getBirdRadioButton().isSelected()) {
            return true;
        }
        if (view.form.getCatRadioButton().isSelected()) {
            return true;
        }
        if (view.form.getDogRadioButton().isSelected()) {
            return true;
        }
        if (view.form.getHorseRadioButton().isSelected()) {
            return true;
        }
        return view.form.getReptileRadioButton().isSelected();
    }

    //Method to clear input text boxes
    private void clearInputBoxes() {
        view.form.getPetNameInput().setText("");
        view.form.getPetageinputbox().setText("");
        view.form.getPetbreedText().setText("");
        view.form.getAmphibianRadioButton().setSelected(false);
        view.form.getBirdRadioButton().setSelected(false);
        view.form.getCatRadioButton().setSelected(false);
        view.form.getDogRadioButton().setSelected(false);
        view.form.getHorseRadioButton().setSelected(false);
        view.form.getReptileRadioButton().setSelected(false);
        view.form.getDrHoldrenRadioButton().setSelected(false);
        view.form.getDrAbduhlRadioButton().setSelected(false);
        view.form.getDrRobertsRadioButton().setSelected(false);

    }

    //Opens the second GUI + Controller while hiding this one temporarily
    public void openChangePetInfoController() {
        ChangePetInfoView view1 = new ChangePetInfoView();

        ChangePetInfoController secondController = new ChangePetInfoController(this, view1, model);
        view1.setVisible(true);

        this.setVisible(false);
    }

    //Does a try/catch to verify number input is a number
    private boolean ageIsNotANumber() {
        String testAge = view.form.getPetageinputbox().getText();

        try {
            int num = Integer.parseInt(testAge);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Simple call to get text in the gui
    private String getPetName() {
        return view.form.getPetNameInput().getText();
    }

    //Method to interact with interface to add pet to a petowner we made
    private void addPetToOwner(String petName, String petAge) {
        String petOwnerName = view.form.getComboBox1().getSelectedItem().toString();
        int numAge = Integer.parseInt(petAge);
        String petType = getPetType();
        String vet = vetSelected();
        String petBreed = view.form.getPetbreedText().getText();

        model.addPetToOwner(petOwnerName, petName, petType, numAge, vet, petBreed);
    }

    //method to get proper string for petType class
    private String getPetType() {
        if (view.form.getAmphibianRadioButton().isSelected()) {
            return "amphibian";
        }
        if (view.form.getBirdRadioButton().isSelected()) {
            return "bird";
        }
        if (view.form.getCatRadioButton().isSelected()) {
            return "cat";
        }
        if (view.form.getDogRadioButton().isSelected()) {
            return "dog";
        }
        if (view.form.getHorseRadioButton().isSelected()) {
            return "horse";
        }
        return "reptile";
    }

    //grabs proper string value for vet
    private String vetSelected() {
        if (view.form.getDrHoldrenRadioButton().isSelected()) {
            return "holdren";
        } else if (view.form.getDrRobertsRadioButton().isSelected()) {
            return "roberts";
        }
        return "abduhl";
    }

    //if removing a pet owner it will remove from display and AUtilList class List
    private void removeFromComboBox() {
        String nameToRemove = view.form.getComboBox1().getSelectedItem().toString();

        view.form.getComboBox1().removeAllItems();

        List<String> names = model.removeNameFromPetOwnerBox(nameToRemove);

        Collections.sort(names);

        for (String toUpdate : names) {
            view.form.getComboBox1().addItem(toUpdate);
        }

        if (model.isPetOwnerEmpty()) {
            setFalseForStart();
        }
        clearInputBoxes();
    }

    //Initial Element Values for display
    private void setFalseForStart() {
        view.form.getRemoveFromOwnerListButton().setEnabled(false);
        view.form.getComboBox1().setEnabled(false);
        view.form.getAmphibianRadioButton().setEnabled(false);
        view.form.getBirdRadioButton().setEnabled(false);
        view.form.getCatRadioButton().setEnabled(false);
        view.form.getDogRadioButton().setEnabled(false);
        view.form.getHorseRadioButton().setEnabled(false);
        view.form.getReptileRadioButton().setEnabled(false);
        view.form.getDrRobertsRadioButton().setEnabled(false);
        view.form.getDrHoldrenRadioButton().setEnabled(false);
        view.form.getDrAbduhlRadioButton().setEnabled(false);
        view.form.getSubmitPetButton().setEnabled(false);
        view.form.getChangeAPetsInformationButton().setEnabled(false);
        view.form.getPetNameInput().setEnabled(false);
        view.form.getPetageinputbox().setEnabled(false);
        view.form.getPetbreedText().setEnabled(false);
    }

    //Toggles for GUI depending on how many pet owners input and taken away
    public void setTrueWhenAtLeastOnePetOwner() {
        view.form.getRemoveFromOwnerListButton().setEnabled(true);
        view.form.getComboBox1().setEnabled(true);
        view.form.getAmphibianRadioButton().setEnabled(true);
        view.form.getBirdRadioButton().setEnabled(true);
        view.form.getCatRadioButton().setEnabled(true);
        view.form.getDogRadioButton().setEnabled(true);
        view.form.getHorseRadioButton().setEnabled(true);
        view.form.getReptileRadioButton().setEnabled(true);
        view.form.getDrRobertsRadioButton().setEnabled(true);
        view.form.getDrHoldrenRadioButton().setEnabled(true);
        view.form.getDrAbduhlRadioButton().setEnabled(true);
        view.form.getSubmitPetButton().setEnabled(true);
        view.form.getChangeAPetsInformationButton().setEnabled(true);
        view.form.getPetNameInput().setEnabled(true);
        view.form.getPetageinputbox().setEnabled(true);
        view.form.getPetbreedText().setEnabled(true);
    }

    //updates petOwnerBox
    private void updatePetOwnerComboBox() {
        view.form.getComboBox1().removeAllItems();

        List<String> names = model.getNamesForPetOwnerBox();

        Collections.sort(names);

        for (String toUpdate : names) {
            view.form.getComboBox1().addItem(toUpdate);
        }
    }


    //Initialize Vet Values beforehand
    private void addVets() {
        Vet roberts = new Vet("Dr. Roberts", "123 Test Ave", "215-215-2152");
        Vet holdren = new Vet("Dr. Holdren", "123 Test Ave", "215-215-2152");
        Vet abduhl = new Vet("Dr. Abduhl", "123 Test Ave", "215-215-2152");
        model.addVets(roberts, holdren, abduhl);
    }

    //method call to add pet owner name as an object
    private void addPetOwner(String name) {
        model.addPetOwner(name);
    }

    //gets owner name input textbox
    private String getOwnerNameInput() {
        return view.form.getOwnerNameTextField().getText();
    }

    //checks for same values of names everyone must be a snowflake!
    private boolean sameNameChecker(String name) {
        return model.checkNameifSame(name);
    }

    //call from second controller to enable first when its done.
    public void setVisible(boolean state) {
        view.setVisible(state);
    }
}
