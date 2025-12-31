package mx.uaemex.fi.linc26.fingerpark.client.controller;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.DELETE_EXISITING_OWNER_VEHICLE;
import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.SHOW_OR_EDIT_EXISTING_VEHICLE;
import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.USER_MANGMENT;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.VisitorRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

public class FingerlessController extends FingerprintController{

    private TextInputDialog dialog = new TextInputDialog();
    private VisitorRepository repo = new VisitorRepository();
    private AlertExt alert = new AlertExt(AlertType.INFORMATION);

    public FingerlessController(MainController controller){
        super(controller);
    }

    public int getFingerID(){
        dialog.setTitle("Ingresar ID");
        dialog.setHeaderText("Ingresa el Finger ID");
        dialog.setContentText("ID (número entero):");

        return Integer.parseInt(dialog.showAndWait().get());
    }

    @Override
    public void andThenCreateNewUser() {
        try {
            begin();
            alert.showMessageAndWait("Leyendo Huella");
            controller.getController(ControllerDirectory.ADD_NEW_USER).begin(getAvailableSlot());
        } catch (Exception e) {
            alert.showMessage("No se puede crear un usuario en este momento");
        }
    }

    @Override
    public void andThenEditExistingUser() {
        try {
            begin();
            controller.getController(ControllerDirectory.SHOW_OR_EDIT_EXISTING_USER).begin(repo.getVisitor(getFingerID()),USER_MANGMENT);
        } catch (Exception e) {
            alert.showMessage("No se pudo recuperar el usuario en este momento");
        }
    }

    @Override
    public void andThenDeleteExistingUser() {
        try {
            begin();
            controller.getController(ControllerDirectory.DELETE_EXISTING_USER).begin(getFingerID(),USER_MANGMENT);
        } catch (Exception e) {
            alert.showMessage("No se pudo recuperar el usuario en este momento");
        }
    }

    @Override
    public void andThenCreateNewCar() {
        try {
            begin();
            controller.getController(ControllerDirectory.ADD_NEW_VEHICLE).begin(getFingerID());
        } catch (Exception e) {
            alert.showMessage("No se pudo recuperar el usuario en este momento");
        }
    }

    @Override
    public void andThenUpdateCarPlates() {
        try {
            begin();
            controller.getController(ControllerDirectory.SHOW_OR_EDIT_EXISTING_VEHICLE).begin(getFingerID(),SHOW_OR_EDIT_EXISTING_VEHICLE);
        } catch (Exception e) {
            alert.showMessage("No se pudo recuperar el usuario en este momento");
        }
    }

        @Override
        public void andThenDeleteExistingCar() {
            try {
                begin();
                controller.getController(ControllerDirectory.DELETE_EXISITING_OWNER_VEHICLE).begin(getFingerID(),DELETE_EXISITING_OWNER_VEHICLE);
            } catch (Exception e) {
                alert.showMessage("No se pudo recuperar el usuario en este momento");
            }
        }

}
