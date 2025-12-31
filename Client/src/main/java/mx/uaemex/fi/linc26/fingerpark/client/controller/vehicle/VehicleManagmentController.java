
package mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import javafx.scene.Node;
import javafx.scene.Parent;
import mx.uaemex.fi.linc26.fingerpark.client.controller.FingerprintController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;

public class VehicleManagmentController implements IControllerFXML {

	private Parent parent = getActivity("AdministrarVehiculo.fxml");
	private Node add_new_btn = parent.lookup("#launch_add_new");
	private Node show_all_btn = parent.lookup("#launch_show_all");
	private Node show_existing_btn = parent.lookup("#launch_show_existing");
	private Node edit_existing_btn = parent.lookup("#launch_update_plates");
	private Node delete_existing_btn = parent.lookup("#launch_remove_existing");
	private Node find_owner_btn = parent.lookup("#launch_find_owner");
	private Node return_hub_btn = parent.lookup("#return_hub_btn");
	private MainController controller;

	public VehicleManagmentController(MainController controller) {

		add_new_btn.setOnMouseClicked(e ->{
			((FingerprintController)controller.getController(FINGERPRINT_IO)).andThenCreateNewCar();
		});

		show_existing_btn.setOnMouseClicked(e -> {
			((FingerprintController)controller.getController(FINGERPRINT_IO)).andThenUpdateCarPlates();
		});

		edit_existing_btn.setOnMouseClicked(e -> {
			((FingerprintController)controller.getController(FINGERPRINT_IO)).andThenUpdateCarPlates();
		});

		delete_existing_btn.setOnMouseClicked(e -> {
			((FingerprintController)controller.getController(FINGERPRINT_IO)).andThenDeleteExistingCar();
		});

		show_all_btn.setOnMouseClicked( e -> {
			((ExploreAllVehiclesController)controller.getController(SHOW_ALL_VEHICLES)).showVehicleList();
		});

		find_owner_btn.setOnMouseClicked(e -> {
			((FindOwnerController)controller.getController(FIND_VEHICLE_OWNER)).begin();
		});

		this.controller = controller;
		return_hub_btn.setOnMouseClicked(e -> controller.updateScene(ADMIN_CENTER));

	}

	@Override
    public void begin() {
        controller.updateScene(VEHICLE_MANAGMENT);
    }

	@Override
	public Parent getParent() {
		return parent;
	}
}
