package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;

public class DeleteVehicleFromOwnerController extends ExploreOwnersAllVehiclesController implements IControllerFX{

	public DeleteVehicleFromOwnerController(AdminApplication app) {
		super(app);
		((Label)parent.lookup("#header-0")).setText("Eliminar");	
	}

	@Override
	public void action(Button b, Vehículo vehículo) {
		
		if (((DeleteVehicleController)app.getController(DELETE_EXISTING_VEHICLE)).deleteVehicle(vehículo.matricula)){
			row.getChildren().remove(b);
		}
		
	}
	

}
