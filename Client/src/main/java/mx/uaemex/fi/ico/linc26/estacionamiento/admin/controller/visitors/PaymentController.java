package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.ClientDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Ticket;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.http.RequestMan;

public class PaymentController implements IControllerFX {

	private Parent parent = getActivity("RealizarPago.fxml");
	private Node pay_btn = parent.lookup("#pay_btn");
	private Node return_btn = parent.lookup("#return_btn");
	private Label monto_lb = (Label) parent.lookup("#total_lb");
	private TextField dinero1_tf = (TextField) parent.lookup("#dinero1_tf");
	private TextField dinero2_tf = (TextField) parent.lookup("#dinero2_tf");

	private Gson gson;
	private ClientDAO dao;
	private HttpClient client = HttpClient.newHttpClient();
	private AdminApplication app;
	private VisitantePensionado visitante;

	private double tarifa;

	public PaymentController (AdminApplication app) {

		this.app = app;
		this.dao = new ClientDAO(app);

		dinero1_tf.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue.matches("\\d*(\\.\\d*)?")) {
		        dinero2_tf.setText(String.valueOf(Math.max(0,Double.parseDouble(newValue)- tarifa)));
		        return;
		    }
		    dinero1_tf.setText("");
		    dinero2_tf.setText("");
		   });

		dinero2_tf.setEditable(false);

		pay_btn.setOnMouseClicked(e -> pagar());
		return_btn.setOnMouseClicked(e -> app.updateSceneScene(ADD_NEW_USER));

	}

	public void begin(VisitantePensionado visitante) {

		this.visitante = visitante;
		tarifa = obtenerTarifa(visitante.tipo);

		if(tarifa == -1) {
			new Alert(AlertType.ERROR, "Ocurrio un error desconocido").showAndWait();
			app.updateSceneScene(USER_MANGMENT);
			return;
		}

		monto_lb.setText("Monto a pagar: $" + tarifa);

		app.updateSceneScene(PAY_PERIODIC_FEE);

	}

	public void pagar() {

		String exitCode = dao.addVisitor(visitante);

		if (exitCode != "OK") {
			new Alert(AlertType.ERROR, exitCode).showAndWait();
			return;
		}

		int folio = 2;

		if(folio == -1) {
			new Alert(AlertType.ERROR, "Ocurrio un error desconocido").showAndWait();
			app.updateSceneScene(USER_MANGMENT);
		}

		Ticket ticket = new Ticket(folio);
		ticket.generarTicketusuario(tarifa, Double.parseDouble(dinero1_tf.getText()));
		ticket.generarTicketTXT();

		if (exitCode != "OK") {
			new Alert(AlertType.ERROR, exitCode).showAndWait();
			return;
		}

		new Alert(AlertType.INFORMATION,"Usuario añadido a la base de datos").showAndWait();
		app.updateSceneScene(USER_MANGMENT);

	}

	public String guardarTicket(Ticket ticket) {

		String status = "OK";

		HttpRequest request = new RequestMan().open(SERVER_HOST + "/api/admin/payment/ticket")
									.POST(gson.toJson(ticket))
									.enforced(app.getToken())
									.build();

		HttpResponse <String> response;

		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (Exception e) {return e.getMessage();}

		if(response.statusCode() != 200) {
			return response.body();
		}

		return status;

	}

	public int obtenerFolio() {

		int folio = -1;

		HttpRequest request = new RequestMan().open(SERVER_HOST + "/api/admin/payment/folio")
									.GET()
									.enforced(app.getToken())
									.build();

		HttpResponse <String> response;

		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (Exception e) {return folio;}

		if(response.statusCode() == 200) {
			folio= Integer.parseInt(response.body());
		}

		return folio;

	}

	public double obtenerTarifa(String usuario) {

		double tarifa = 100;

		HttpRequest request = new RequestMan().open(SERVER_HOST + "/api/admin/payment/fee/" + usuario)
									.GET()
									.enforced(app.getToken())
									.build();

		HttpResponse <String> response;

		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (Exception e) {return tarifa;}

		if(response.statusCode() == 200) {
			tarifa= Double.parseDouble(response.body().toString());
		}

		return tarifa;

	}

	@Override
	public Parent getParent() {
		return parent;
	}

}
