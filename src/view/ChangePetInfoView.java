package view;

import javax.swing.*;
import java.awt.*;

public class ChangePetInfoView extends JFrame {
    public final ChangePetInfo form;

    public ChangePetInfoView(){
        this.form = new ChangePetInfo();
        JPanel content = form.getChangeInfoJPanel();
        this.setContentPane(content);
        this.setPreferredSize(new Dimension(500,500));
        this.pack();

        this.setTitle("Change Pet Info Test 1");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public ChangePetInfo form() {return form;}
}
