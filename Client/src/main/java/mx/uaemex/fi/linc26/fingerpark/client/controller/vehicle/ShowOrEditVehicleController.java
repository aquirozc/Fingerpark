package mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Vehicle;

public class ShowOrEditVehicleController extends ExploreOwnersAllVehiclesController {

	public ShowOrEditVehicleController(MainController controller) {
		super(controller);
	}

	@Override
	public void action(Button b, Vehicle vehículo) {
		new UpdateLicensePlateController(controller).begin(vehículo);
		((Label) b.lookup("Label")).setText(vehículo.toString());
	}

}
