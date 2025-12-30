package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.ShowOrEditExitingUserController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.CarDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.ClientDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;

public class FindOwnerController implements IControllerFX {
	
	private CarDAO carDAO;
	private ClientDAO clientDAO;
	private ShowOrEditExitingUserController controller;
	
	public FindOwnerController (AdminApplication app) {
		this.carDAO = new CarDAO(app);
		this.clientDAO = new ClientDAO(app);
		controller = (ShowOrEditExitingUserController)app.getController(SHOW_OR_EDIT_EXISTING_USER);
	}
	
	public void begin() {
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Ingrese la matricula del vehículo");
		
		Vehículo vehiculo = carDAO.getVehicle(dialog.showAndWait().get());
		
		if(vehiculo == null) {
			new Alert(AlertType.ERROR, "No se encontro el vehículo").showAndWait();
			return;
		}
		
		controller.begin(clientDAO.getVisitor(vehiculo.propietario_id), VEHICLE_MANAGMENT);
	}
	
	@Override
	public Parent getParent() {
		// TODO Auto-generated method stub
		return null;
	}

}
