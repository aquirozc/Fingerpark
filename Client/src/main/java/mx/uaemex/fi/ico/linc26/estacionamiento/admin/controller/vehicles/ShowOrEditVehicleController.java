package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;

public class ShowOrEditVehicleController extends ExploreOwnersAllVehiclesController implements IControllerFX {

	public ShowOrEditVehicleController(AdminApplication app) {
		super(app);
	}

	@Override
	public void action(Button b, Vehículo vehículo) {
		
		new UpdateLicensePlateController(app).begin(vehículo);
		((Label) b.lookup("Label")).setText(vehículo.toString());
		
	}

	

}
