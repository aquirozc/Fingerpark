package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import javafx.scene.Node;
import javafx.scene.Parent;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.FingerprintController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.CarDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;

public class VehicleManagmentController implements IControllerFX {
	
	private Parent parent = getActivity("AdministrarVehiculo.fxml");	
	private Node add_new_btn = parent.lookup("#launch_add_new");
	private Node show_all_btn = parent.lookup("#launch_show_all");
	private Node show_existing_btn = parent.lookup("#launch_show_existing");
	private Node edit_existing_btn = parent.lookup("#launch_update_plates");
	private Node delete_existing_btn = parent.lookup("#launch_remove_existing");
	private Node find_owner_btn = parent.lookup("#launch_find_owner");
	private Node return_hub_btn = parent.lookup("#return_hub_btn");
	
	public VehicleManagmentController(AdminApplication app) {
		
		add_new_btn.setOnMouseClicked(e ->{
			((FingerprintController)app.getController(FINGERPRINT_IO)).andThenCreateNewCar();
		});
		
		show_existing_btn.setOnMouseClicked(e -> {
			((FingerprintController)app.getController(FINGERPRINT_IO)).andThenUpdateCarPlates();
		});
		
		edit_existing_btn.setOnMouseClicked(e -> {
			((FingerprintController)app.getController(FINGERPRINT_IO)).andThenUpdateCarPlates();
		});
		
		delete_existing_btn.setOnMouseClicked(e -> {
			((FingerprintController)app.getController(FINGERPRINT_IO)).andThenDeleteExistingCar();
		});
		
		show_all_btn.setOnMouseClicked( e -> {
			((ExploreAllVehiclesController)app.getController(SHOW_ALL_VEHICLES)).showUserList();
		});
		
		find_owner_btn.setOnMouseClicked(e -> {
			((FindOwnerController)app.getController(FIND_VEHICLE_OWNER)).begin();
		});
		
		return_hub_btn.setOnMouseClicked(e -> app.updateSceneScene(ADMIN_CENTER));
		
	}
	
	@Override
	public Parent getParent() {
		return parent;
	}
}
