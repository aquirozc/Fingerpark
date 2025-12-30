package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.VEHICLE_MANAGMENT;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.CarDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;

public class UpdateLicensePlateController implements IControllerFX {
	
	private AdminApplication app;
	private CarDAO dao;
	
	public UpdateLicensePlateController(AdminApplication app) {
		this.app = app;
		this.dao = new CarDAO(app);
	}
	
	public void begin(Vehículo vehículo) {
		
		String vieja = vehículo.matricula;
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText("Ingrese la nueva placa");
		
		String nueva = dialog.showAndWait().get();
		vehículo.matricula = nueva;
		
		String exitCode = dao.editPlates(vieja, nueva);
		
		if (exitCode != "OK") {
			new Alert(AlertType.ERROR, exitCode).showAndWait();
			return;
		}
		
		new Alert(AlertType.INFORMATION,"Vehiculo añadido a la base de datos").showAndWait();
		
	}
	

	@Override
	public Parent getParent() {
		return null;
	}

}
