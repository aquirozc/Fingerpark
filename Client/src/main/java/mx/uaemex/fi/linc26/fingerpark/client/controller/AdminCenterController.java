package mx.uaemex.fi.linc26.fingerpark.client.controller;

import javafx.scene.Node;
import javafx.scene.Parent;

public class AdminCenterController implements IControllerFXML{

    private Parent parent = getActivity("AdminCenter.fxml");
	private Node launch_user_managment = parent.lookup("#launch_user_managment");
	private Node launch_car_managment = parent.lookup("#launch_car_managment");

	private MainController controller;

	public AdminCenterController(MainController controller) {
	    this.controller = controller;
		launch_user_managment.setOnMouseClicked(e -> controller.updateScene(ControllerDirectory.USER_MANGMENT) );
		launch_car_managment.setOnMouseClicked(e -> controller.updateScene(ControllerDirectory.VEHICLE_MANAGMENT));
	}

	@Override
	public void begin() {
	    controller.updateScene(ControllerDirectory.ADMIN_CENTER);
	}

	@Override
	public Parent getParent() {
		return parent;
	}

}
