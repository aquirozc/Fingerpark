package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.User;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.http.RequestMan;

public class LoginController implements IControllerFX {

	private Parent parent = getActivity("IniciarSesion.fxml");
	private Node next_btn = parent.lookup("#login_btn");
	private Node close_btn = parent.lookup("#close_btn");
	private TextField user_tf =((TextField)parent.lookup("#user_tf"));
	private TextField pass_tf = ((TextField)parent.lookup("#pass_tf"));

	private AdminApplication app;
	private HttpClient client = HttpClient.newHttpClient();
	private Gson gson = new Gson();

	public LoginController(AdminApplication app) {
		this.app = app;
		next_btn.setOnMouseClicked(e -> attemptLogin());
		close_btn.setOnMouseClicked(e -> System.exit(0));
	}

	public void attemptLogin() {

		String failure = "Imposible conectar con el servidor";

		User user  = new User("admin","admin");

		HttpRequest request = new RequestMan().open(SERVER_HOST + "/api/v1/admin/users/login")
									.POST(gson.toJson(user))
									.build();

		HttpResponse response = null;

		try {
			response = client.send(request, BodyHandlers.ofString());
			if(response.statusCode() != 200) {
				failure = response.body().toString();
				throw new Exception();
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR, failure).show();
			return;
		}

		app.setToken(response.body().toString());
		app.updateSceneScene(ADMIN_CENTER);

	}



	@Override
	public Parent getParent() {
		return parent;
	}

}
