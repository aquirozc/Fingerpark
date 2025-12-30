package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.CarDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;

public class DeleteVehicleController implements IControllerFX {
	
	private AdminApplication app;
	private CarDAO dao;
	
	
	public DeleteVehicleController(AdminApplication app) {
		this.app = app;
		this.dao = new CarDAO(app);
	}
	
	public boolean deleteVehicle(String matricula) {
		boolean exit = false;
		
		 Vehículo vehículo = dao.getVehicle(matricula);
		
		ButtonType  op= new Alert(AlertType.CONFIRMATION, "¿Desea eliminar vehículo?\n\n" + vehículo.toString()).showAndWait().get();
		
		if (op.equals(ButtonType.OK)) {
			String status = dao.deleteVehicle(vehículo);
			if(!status.equals("OK")) {
				new Alert(AlertType.ERROR, status).showAndWait();
				return exit;
			}
			exit = true;
		}
		
		return exit;
	}
	
	public boolean deleteVehicle(String matricula, ControllerDirectory context) {
		app.updateSceneScene(context);
		return deleteVehicle(matricula);	
	}

	@Override
	public Parent getParent() {
		return null;
	}

}
