package mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Vehicle;

public class DeleteVehicleFromOwnerController extends ExploreOwnersAllVehiclesController{

	public DeleteVehicleFromOwnerController(MainController app) {
		super(app);
		((Label)parent.lookup("#header-0")).setText("Eliminar");
	}

	@Override
	public void action(Button b, Vehicle vehículo) {

		if (((DeleteVehicleController)controller.getController(DELETE_EXISTING_VEHICLE)).deleteVehicle(vehículo.getPlate())){
			row.getChildren().remove(b);
		}

	}


}
