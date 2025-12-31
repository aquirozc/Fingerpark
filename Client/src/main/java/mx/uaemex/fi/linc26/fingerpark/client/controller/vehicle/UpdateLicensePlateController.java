package mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Vehicle;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.CarRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

public class UpdateLicensePlateController implements IControllerFXML {

    private AlertExt alert = new AlertExt(AlertType.INFORMATION);
    private CarRepository repo = new CarRepository();

	public UpdateLicensePlateController(MainController controller) {

	}

	public void begin(Vehicle vehicle) {
		try{
            TextInputDialog dialog = new TextInputDialog();
            dialog.setContentText("Ingrese la nueva placa");
            String next = dialog.showAndWait().get();

            repo.editPlates(vehicle.getPlate(), next);
            vehicle.setPlate(next);
            alert.showMessageAndWait("Información del vehículo actualizada");
		} catch(IOException | InterruptedException exception){
            alert.showErrorMessageAndWait("No se pudo conectar con el servidor");
		} catch(Exception exception){
            alert.showErrorMessageAndWait("No se pudo modificar las placas");
		}
	}

	@Override
	public Parent getParent() {
		return null;
	}

}
