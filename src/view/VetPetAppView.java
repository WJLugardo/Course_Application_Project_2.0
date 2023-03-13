package view;

import javax.swing.*;
import java.awt.*;

public class VetPetAppView extends JFrame{

    public final VetPetAppForm form;


    public VetPetAppView() {
        this.form = new VetPetAppForm();
        JPanel content = form.getVetPetJPanel();
        this.setContentPane(content);
        this.setPreferredSize(new Dimension(500,500));
        this.pack();

        this.setTitle("VetPet Test 1");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public VetPetAppForm form(){return form;}
}
