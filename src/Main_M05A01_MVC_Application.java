import controller.VetPetAppController;
import interfaces.IControllerInterface;
import model.AUtilList;
import view.VetPetAppView;

//Main class calls the controller which enables to application gui to start and the magic to begin

public class Main_M05A01_MVC_Application {
    public static void main(String[] args) {
        //Set Variables to be passed to controller for use of Test

        IControllerInterface model = new AUtilList();

        VetPetAppView view = new VetPetAppView();


        VetPetAppController controller =
                new VetPetAppController(model, view);


        //NEVER FORGET TO DO THIS
        view.setVisible(true);



    }
}
