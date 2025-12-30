package mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.AdminCenterController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.FingerlessController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.FingerprintController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.LoginController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.AddNewVehicleController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.DeleteVehicleController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.DeleteVehicleFromOwnerController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.ExploreAllVehiclesController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.FindOwnerController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.ShowOrEditVehicleController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.VehicleManagmentController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.AddNewUserController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.DeleteUserController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.ExploreLogsController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.ExploreUsersController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.PaymentController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.ShowOrEditExitingUserController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.UserManagmentController;

public class AdminApplication extends Application{

	private Stage stage;

	private Map<ControllerDirectory, IControllerFX> map = new HashMap<ControllerDirectory,IControllerFX>();
	private String HTTP_AUTH_TOKEN;

	@Override
	public void start(Stage stage) throws Exception {

		map.put(ADMIN_LOGIN, new LoginController(this));
		map.put(ADMIN_CENTER, new AdminCenterController(this));
		map.put(USER_MANGMENT, new UserManagmentController(this));
		map.put(FINGERPRINT_IO, new FingerlessController(this));
		map.put(ADD_NEW_USER, new AddNewUserController(this));
		map.put(SHOW_ALL_USERS, new ExploreUsersController(this));
		map.put(SHOW_OR_EDIT_EXISTING_USER, new ShowOrEditExitingUserController(this));
		map.put(DELETE_EXISTING_USER, new DeleteUserController(this));
		map.put(VEHICLE_MANAGMENT, new VehicleManagmentController(this));
		map.put(ADD_NEW_VEHICLE, new AddNewVehicleController(this));
		map.put(SHOW_OR_EDIT_EXISTING_VEHICLE, new ShowOrEditVehicleController(this));
		map.put(DELETE_EXISITING_OWNER_VEHICLE, new DeleteVehicleFromOwnerController(this));
		map.put(DELETE_EXISTING_VEHICLE, new DeleteVehicleController(this));
		map.put(SHOW_ALL_VEHICLES, new ExploreAllVehiclesController(this));
		map.put(FIND_VEHICLE_OWNER, new FindOwnerController(this));
		map.put(PAY_PERIODIC_FEE, new PaymentController(this));
		map.put(EXPLORE_LOGS, new ExploreLogsController(this));

		this.stage = stage;
		this.stage.setScene(new Scene(map.get(ADMIN_LOGIN).getParent(), 1100,680));

		onCreate();
	}

	public void updateSceneScene(ControllerDirectory k) {
		try {

			// Crear un contenedor que contendrá ambas escenas
			Group rootGroup = new Group();

			Parent oldroot = stage.getScene().getRoot();

			// Añadir la escena antigua al contenedor
			rootGroup.getChildren().add(oldroot);

			// Añadir la nueva escena al contenedor (fuera de la vista)
			map.get(k).getParent().setTranslateX(stage.getWidth());
			rootGroup.getChildren().add(map.get(k).getParent());

			// Crear una nueva transición de deslizamiento desde el lado derecho
			TranslateTransition transition = new TranslateTransition();
			transition.setDuration(Duration.millis(500));
			transition.setFromX(stage.getWidth());
			transition.setToX(0);

			// Configurar un manejador de eventos para manejar el evento de finalización de la transición
			transition.setOnFinished( e -> {
			       try {
			    	   // Actualizar la escena con la nueva root después de que la transición haya terminado
				        stage.getScene().setRoot(map.get(k).getParent());
				} catch (Exception e2) {
					// TODO: handle exception
				}

			});

			// Aplicar la transición al nodo de la nueva escena
			transition.setNode(map.get(k).getParent());
			transition.play();

			// Aplicar el contenedor con ambas escenas a la ventana
			stage.getScene().setRoot(rootGroup);

			PauseTransition	pause = new PauseTransition(Duration.millis(650));
			pause.setOnFinished(e -> rootGroup.getChildren().remove(oldroot));
			pause.play();

			} catch (Exception e) {
		}
	}

	public void updateScene(ControllerDirectory k) {
		stage.getScene().setRoot(map.get(k).getParent());
	}

	private void onCreate() {
		stage.setResizable(false);
		stage.show();
	}

	public IControllerFX getController(ControllerDirectory k) {
		return map.get(k);
	}

	public void setToken(String token) {
		HTTP_AUTH_TOKEN = token;
	}

	public String getToken() {
		return HTTP_AUTH_TOKEN;
	}
}
