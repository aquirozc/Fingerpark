package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.main.App;

public class AdminCenterController implements IControllerFX {
	
	private Parent parent = getActivity("AdminCenter.fxml");
	private Node launch_user_managment = parent.lookup("#launch_user_managment");
	private Node launch_car_managment = parent.lookup("#launch_car_managment");
	
	private AdminApplication app;
	
	public AdminCenterController(AdminApplication app) {
		this.app = app;
		launch_user_managment.setOnMouseClicked(e -> app.updateSceneScene(USER_MANGMENT) );
		launch_car_managment.setOnMouseClicked(e -> app.updateSceneScene(VEHICLE_MANAGMENT));
		
	}
	
	@Override
	public Parent getParent() {
		return parent;
	}
}
