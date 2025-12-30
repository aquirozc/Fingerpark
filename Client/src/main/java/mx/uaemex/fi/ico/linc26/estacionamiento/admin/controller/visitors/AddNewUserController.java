package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.ClientDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Tarifas;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;

public class AddNewUserController implements IControllerFX {
	
	private int huella_id;
	
	private Parent parent = getActivity("CrearUsuario.fxml");
	private Node add_new_btn = parent.lookup("#add_new_btn");
	private Node rollback_btn = parent.lookup("#rollback_btn");
	private TextField nombre_tf = ((TextField)parent.lookup("#nombre_tf"));
	private TextField apellido_tf = ((TextField)parent.lookup("#apellido_tf"));
	private TextField email_tf = ((TextField)parent.lookup("#email_tf"));
	private ComboBox<String> tarifa_cb = ((ComboBox<String>) parent.lookup("#tarifa_cb"));	
	private AdminApplication app;
	private ClientDAO dao;
	
	public AddNewUserController(AdminApplication app) {
		this.app = app;
		dao = new ClientDAO(app);
		
		tarifa_cb.setItems(FXCollections.observableArrayList(Tarifas.options));
		tarifa_cb.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-text-fill: darkblue; -fx-background-color: lightgray;");
		
		add_new_btn.setOnMouseClicked(e -> addNew());
		rollback_btn.setOnMouseClicked(e -> app.updateSceneScene(USER_MANGMENT));
	}
	
	public void addNew() {
		
		VisitantePensionado visitante = new VisitantePensionado();
		visitante.huella_id = huella_id;
		visitante.nombre = nombre_tf.getText();
		visitante.apellido = apellido_tf.getText();
		visitante.email = email_tf.getText();
		visitante.tipo = tarifa_cb.getValue();
		
		((PaymentController)app.getController(PAY_PERIODIC_FEE)).begin(visitante);
		
	}
	
	public void begin(int huella_id) {
		
		this.huella_id = huella_id;
		
		nombre_tf.textProperty().set("");
		apellido_tf.textProperty().set("");
		email_tf.textProperty().set("");	
		tarifa_cb.setValue(Tarifas.VISITANTE_PENSIONADO.desc);
		
		app.updateSceneScene(ADD_NEW_USER);
	}

	@Override
	public Parent getParent() {
		return parent;
	}
}
