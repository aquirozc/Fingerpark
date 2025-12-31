package mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Vehicle;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.CarRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

public class DeleteVehicleController implements IControllerFXML {

    private AlertExt alert = new AlertExt(AlertType.ERROR);
    private CarRepository repo = new CarRepository();
	private MainController controller;


	public DeleteVehicleController(MainController controller) {
        this.controller = controller;
	}

	public boolean deleteVehicle(String plate) {
    	try{
    	    Vehicle vehicle = repo.getVehicle(plate);

    		if(alert.showMessageAndWait("¿Desea eliminar el vehículo?" + vehicle.toString()).get().equals(ButtonType.OK)){
                    repo.deleteVehicle(vehicle);
                    return true;
    		}
    	} catch(IOException | InterruptedException exception){
                alert.showErrorMessageAndWait("No se pudo conectar con el servidor");
    	} catch(Exception exception){
                alert.showErrorMessageAndWait("No se pudo eliminar el vehículo");
    	}

    	return false;
	}

	public boolean deleteVehicle(String matricula, ControllerDirectory context) {
		controller.updateScene(context);
		return deleteVehicle(matricula);
	}

	@Override
	public Parent getParent() {
		return null;
	}

}
