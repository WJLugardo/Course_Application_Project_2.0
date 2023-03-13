package controller;

import interfaces.IControllerInterface;
import view.ChangePetInfoView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//NOTE FOR FUTURE ADD A TRY CATCH FOR AGE BEING A NUMBER

/**
 * The Following class is used to operate the ChangePetInfo GUI that is enabled when the "Change a Pets Information"
 * button is pressed and used in the VetPetAppController.
 * <p>
 * Basic information is below and methods describe all actions and interactions with the interface and utility model
 *
 * @param <E>
 */


public class ChangePetInfoController<E> extends JFrame {

    //Initialize basic needs for methods and class

    private final VetPetAppController firstController;
    private final ChangePetInfoView view;
    private final IControllerInterface model;
    private List<String> oldPetInfo = new ArrayList<>();

    public ChangePetInfoController(VetPetAppController vetPetAppController, ChangePetInfoView view1, IControllerInterface model) {
        this.firstController = vetPetAppController;
        this.view = view1;
        this.model = model;

        //Setup for Info to be Flowing
        nameComboBoxSetup();
        view.form.getSubmitChangeRequestButton().setEnabled(false);


        //All active Listeners respective to name

        //Select Pet Button
        view.form.getSelectPetButton().addActionListener(e -> {

            //Every if statement in a listener is to avoid possible errors
            if (view.form.getComboBox2() != null) {
                JOptionPane.showMessageDialog(view, "Error: This Owner has no pets to modify");
                return;
            }

            view.form.getSelectPetButton().setEnabled(false);
            view.form.getComboBox2().setEnabled(false);
            view.form.getComboBox1().setEnabled(false);
            view.form.getSelectOwnerButton().setEnabled(false);

            //Grabs pet information to be represented after button is pushed
            grabPetInformation();

            view.form.getSubmitChangeRequestButton().setEnabled(true);

        });


        //Select Owner button
        view.form.getSelectOwnerButton().addActionListener(e -> {
            if (view.form.getSubmitChangeRequestButton().isEnabled()) {
                view.form.getSubmitChangeRequestButton().setEnabled(false);
            }
            petComboBoxSetup(view.form.getComboBox1().getSelectedItem().toString());
        });


        //Submit Change Request Button
        view.form.getSubmitChangeRequestButton().addActionListener(e -> {
            updatePetInformation();
            firstController.setVisible(true);
            view.form.getComboBox1().removeAllItems();
            view.form.getComboBox2().removeAllItems();
            view.dispose();
        });

        //Cancel Button
        view.form.getCancelButton().addActionListener(e -> {
            firstController.setVisible(true);
            view.dispose();
        });
    }


    /*
    This method call will take information from new input and use stored information from last input to update petOwner
    and pet information with calls to the IControllerInterface that uses AUtilList in model to perform all the actions
     */
    private void updatePetInformation() {

        List<String> petInfoNew = new ArrayList<>();

        //grab current information

        petInfoNew.add(view.form.getNameChangeTextField().getText());
        petInfoNew.add(getNewPetTypeButtomSelected());
        petInfoNew.add(view.form.getAgeChangeTextField().getText());
        petInfoNew.add(view.form.getBreedChangeTextField().getText());
        petInfoNew.add(getNewDoctorButton());

        //send call to delete old info which is stored ONLY DELETING single pet

        model.deleteOldPetInfo(view.form.getComboBox1().getSelectedItem().toString(), oldPetInfo);

        //send call to replace with new info

        model.addPetToOwner(view.form.getComboBox1().getSelectedItem().toString(), petInfoNew.get(0), petInfoNew.get(1), Integer.parseInt(petInfoNew.get(2)), petInfoNew.get(4), petInfoNew.get(3));
    }

    //Check to get the right String value for each button
    private String getNewDoctorButton() {
        if (view.form.getDrRobertsRadioButton().isSelected()) {
            return "roberts";
        }
        if (view.form.getDrHoldrenRadioButton().isSelected()) {
            return "holdren";
        }
        if (view.form.getDrAbduhlRadioButton().isSelected()) {
            return "abduhl";
        }

        return null;
    }

    //Check to get thr right String value for each button
    private String getNewPetTypeButtomSelected() {
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

    //Method gets all current information for selected pet to be displayed in the GUI
    private void grabPetInformation() {

        if (!oldPetInfo.isEmpty()) {
            oldPetInfo.clear();
        }

        List<String> petInfo = model.allThePetInfo(view.form.getComboBox1().getSelectedItem().toString(), view.form.getComboBox2().getSelectedItem().toString());

        view.form.getNameChangeTextField().setText(petInfo.get(0));
        matchingButtonPetType(petInfo.get(1));
        view.form.getAgeChangeTextField().setText(petInfo.get(2));
        view.form.getBreedChangeTextField().setText(petInfo.get(3));
        matchingButtonDoctor(petInfo.get(4));

        oldPetInfo = petInfo;
    }

    //Takes input from Object in AUtiList that was converted to string to get the right button/info
    private void matchingButtonDoctor(String s) {
        switch (s.toLowerCase()) {
            case "dr. roberts":
                view.form.getDrRobertsRadioButton().setSelected(true);
                break;
            case "dr. holdren":
                view.form.getDrHoldrenRadioButton().setSelected(true);
                break;
            case "dr. abduhl":
                view.form.getDrAbduhlRadioButton().setSelected(true);
                break;
        }
    }

    //Takes input from Object in AUtiList that was converted to string to get the right button/info
    private void matchingButtonPetType(String s) {
        switch (s.toLowerCase()) {
            case "amphibian":
                view.form.getAmphibianRadioButton().setSelected(true);
                break;
            case "bird":
                view.form.getBirdRadioButton().setSelected(true);
                break;
            case "cat":
                view.form.getCatRadioButton().setSelected(true);
                break;
            case "dog":
                view.form.getDogRadioButton().setSelected(true);
                break;
            case "horse":
                view.form.getHorseRadioButton().setSelected(true);
                break;
            case "reptile":
                view.form.getReptileRadioButton().setSelected(true);
        }
    }

    //Takes input from Object in AUtilList that was converted to string to get the right combo box lines
    private void petComboBoxSetup(String name) {
        List<String> petNames = model.getPetNames(name);

        for (String names : petNames) {
            view.form.getComboBox2().addItem(names);
        }

    }

    //Takes input from Object in AUtilList that was converted to string to get the right combo box lines

    private void nameComboBoxSetup() {
        view.form.getComboBox1().removeAllItems();

        List<String> names = model.getNamesForPetOwnerBox();

        Collections.sort(names);

        for (String toUpdate : names) {
            view.form.getComboBox1().addItem(toUpdate);
        }
    }

}
