package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.AddNewVehicleController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.ExploreOwnersAllVehiclesController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.ShowOrEditVehicleController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.AddNewUserController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.DeleteUserController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.ShowOrEditExitingUserController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.ClientDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.http.RequestMan;

public class FingerprintController implements IControllerFX {

	protected Parent parent = getActivity("RegistrarHuellaDigital.fxml");
	protected Label label = (Label) parent.lookup("#msg_label");

	protected HttpClient client = HttpClient.newHttpClient();
	protected AdminApplication app;
	protected ClientDAO dao;

	public FingerprintController(AdminApplication app) {
		this.app = app;
		this.dao = new ClientDAO(app);
	}

	public void andThenCreateNewUser() {

		int huella_id = getAvailableSlot();

		if(huella_id == -1) {
			return;
		}

		app.updateSceneScene(FINGERPRINT_IO);

		Thread.ofVirtual().start(() -> {

			try {
//				arduino.eliminarHuella(huella_id);
			//	arduino.registrarHuella(huella_id);
			}catch (Exception e) {
				Platform.runLater(() -> {
					new Alert(AlertType.ERROR, "No se pudo registrar la huella\n" +  e.getMessage()).showAndWait();
					app.updateSceneScene(USER_MANGMENT);
				});
				return;
			}

			Platform.runLater(() -> {
				((AddNewUserController)app.getController(ADD_NEW_USER)).begin(huella_id);
			});

		});

	}

	public void andThenEditExistingUser() {

		app.updateSceneScene(FINGERPRINT_IO);

		Thread.ofVirtual().start(() -> {

			int huella_id = 0;

			try {
				//huella_id = arduino.buscarHuellas();

				if (huella_id==0) {

					Platform.runLater(() -> {
						new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
						app.updateSceneScene(USER_MANGMENT);
					});

				}

			}catch (Exception e) {
				Platform.runLater(() -> {
					new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
					app.updateSceneScene(USER_MANGMENT);
				});
				return;
			}

			Platform.runLater(() -> {
				VisitantePensionado visitante = dao.getVisitor(huella_id);
				((ShowOrEditExitingUserController)app.getController(SHOW_OR_EDIT_EXISTING_USER)).begin(visitante, USER_MANGMENT);
			});

		});

	}

	public void andThenDeleteExisitingUser() {

		app.updateSceneScene(FINGERPRINT_IO);

		Thread.ofVirtual().start(() -> {

			int huella_id = 0;

			try {
				//huella_id = arduino.buscarHuellas();

				if (huella_id==0) {

					Platform.runLater(() -> {
						new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
						app.updateSceneScene(USER_MANGMENT);
					});

				}else {

				//	arduino.eliminarHuella(huella_id);

				}

			}catch (Exception e) {
				Platform.runLater(() -> {
					new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
					app.updateSceneScene(USER_MANGMENT);
				});
				return;
			}

			Platform.runLater(() -> {
				((DeleteUserController)app.getController(DELETE_EXISTING_USER)).deleteUser(huella_id, USER_MANGMENT);
			});

		});

	}

	public void andThenCreateNewCar() {

		app.updateSceneScene(FINGERPRINT_IO);

		Thread.ofVirtual().start(() -> {

			int huella_id = 0;

			try {
				//huella_id = arduino.buscarHuellas();

				if (huella_id==0) {

					Platform.runLater(() -> {
						new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
						app.updateSceneScene(VEHICLE_MANAGMENT);
					});

				}
			}catch (Exception e) {
				Platform.runLater(() -> {
					new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
					app.updateSceneScene(VEHICLE_MANAGMENT);
				});
				return;
			}

			Platform.runLater(() -> {
				((AddNewVehicleController)app.getController(ADD_NEW_VEHICLE)).begin(huella_id);
			});

		});

	}

	public void andThenUpdateCarPlates() {

		app.updateSceneScene(FINGERPRINT_IO);

		Thread.ofVirtual().start(() -> {

			int huella_id = 0;

			try {
				//huella_id = arduino.buscarHuellas();
				if (huella_id==0) {

					Platform.runLater(() -> {
						new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
						app.updateSceneScene(VEHICLE_MANAGMENT);
					});

				}

			}catch (Exception e) {
				Platform.runLater(() -> {
					new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
					app.updateSceneScene(VEHICLE_MANAGMENT);
				});
				return;
			}

			Platform.runLater(() -> {
				((ShowOrEditVehicleController)app.getController(SHOW_OR_EDIT_EXISTING_VEHICLE)).begin(huella_id);
				app.updateSceneScene(ControllerDirectory.SHOW_OR_EDIT_EXISTING_VEHICLE);
			});

		});

	}

	public void andThenDeleteExistingCar() {

		app.updateSceneScene(FINGERPRINT_IO);

		Thread.ofVirtual().start(() -> {

			int huella_id = 0;

			try {
				//huella_id = arduino.buscarHuellas();
				if (huella_id==0)  {

					Platform.runLater(() -> {
						new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
						app.updateSceneScene(VEHICLE_MANAGMENT);
					});

				}
			}catch (Exception e) {
				Platform.runLater(() -> {
					new Alert(AlertType.ERROR, "Usuario no encontrado").showAndWait();
					app.updateSceneScene(VEHICLE_MANAGMENT);
				});
				return;
			}

			Platform.runLater(() -> {
				((ExploreOwnersAllVehiclesController)app.getController(DELETE_EXISITING_OWNER_VEHICLE)).begin(huella_id);
				app.updateSceneScene(ControllerDirectory.DELETE_EXISITING_OWNER_VEHICLE);
			});

		});

	}

	public int getAvailableSlot() {

		int id = -1;

		HttpRequest request = new RequestMan().open(SERVER_HOST + "/api/v1/admin/visitors/trusted/id")
									.GET()
									.enforced(app.getToken())
									.build();

		HttpResponse <String> response;

		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (Exception e) {return id;}

		if(response.statusCode() == 200) {
			id = Integer.parseInt(response.body().toString());
		}

		return id;
	}

	@Override
	public Parent getParent() {
		return parent;
	}

}
