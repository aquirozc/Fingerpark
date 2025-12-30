package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.AddNewVehicleController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.ExploreOwnersAllVehiclesController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.ShowOrEditVehicleController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.AddNewUserController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.ShowOrEditExitingUserController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;

public class FingerlessController extends FingerprintController {

	public FingerlessController(AdminApplication app) {
		super(app);
	}

	@Override
	public void andThenCreateNewUser() {

		int huella_id = getAvailableSlot();

		if(huella_id == -1) {
			return;
		}

		app.updateSceneScene(FINGERPRINT_IO);

		new Alert(AlertType.INFORMATION).showAndWait();

		((AddNewUserController)app.getController(ADD_NEW_USER)).begin(huella_id);

	}

	@Override
	public void andThenEditExistingUser() {

		app.updateSceneScene(FINGERPRINT_IO);

		new Alert(AlertType.INFORMATION).showAndWait();

		VisitantePensionado visitante = dao.getVisitor(102);
		((ShowOrEditExitingUserController)app.getController(SHOW_OR_EDIT_EXISTING_USER)).begin(visitante, USER_MANGMENT);

	}

	@Override
	public void andThenCreateNewCar() {

		app.updateSceneScene(FINGERPRINT_IO);

		new Alert(AlertType.INFORMATION).showAndWait();

		((AddNewVehicleController)app.getController(ADD_NEW_VEHICLE)).begin(24);

	}

	@Override
	public void andThenUpdateCarPlates() {

		app.updateSceneScene(FINGERPRINT_IO);

		new Alert(AlertType.INFORMATION).showAndWait();


		((ShowOrEditVehicleController)app.getController(SHOW_OR_EDIT_EXISTING_VEHICLE)).begin(102);
		app.updateSceneScene(ControllerDirectory.SHOW_OR_EDIT_EXISTING_VEHICLE);
	}

	@Override
	public void andThenDeleteExistingCar() {

		app.updateSceneScene(FINGERPRINT_IO);

		new Alert(AlertType.INFORMATION).showAndWait();


		((ExploreOwnersAllVehiclesController)app.getController(DELETE_EXISITING_OWNER_VEHICLE)).begin(102);
		app.updateSceneScene(ControllerDirectory.DELETE_EXISITING_OWNER_VEHICLE);

	}

}
