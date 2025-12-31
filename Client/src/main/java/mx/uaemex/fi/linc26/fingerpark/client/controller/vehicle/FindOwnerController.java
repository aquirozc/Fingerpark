package mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.CarRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

public class FindOwnerController implements IControllerFXML {

    private AlertExt alert = new AlertExt(AlertType.ERROR);
	private CarRepository repo = new CarRepository();
	private MainController controller;

	public FindOwnerController (MainController controller) {
		this.controller = controller;
	}

	public void begin() {
	    try{
			TextInputDialog dialog = new TextInputDialog();
            dialog.setContentText("Ingrese la placa del vehiculo");

			controller.getController(SHOW_OR_EDIT_EXISTING_USER).begin(repo.getVehicleOwner(dialog.showAndWait().get()), VEHICLE_MANAGMENT);
    	} catch(IOException | InterruptedException exception){
                alert.showErrorMessageAndWait("No se pudo conectar con el servidor");
    	} catch(Exception exception){
                alert.showErrorMessageAndWait("No se pudo localizar le usuario");
        }
	}

	@Override
	public Parent getParent() {
		return null;
	}

}
