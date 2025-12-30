package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.ClientDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Tarifas;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;

public class ShowOrEditExitingUserController implements IControllerFX {

	private ControllerDirectory context;

	private Parent parent = getActivity("CrearUsuario.fxml");
	private Button add_new_btn = (Button) parent.lookup("#add_new_btn");
	private Button rollback_btn = (Button) parent.lookup("#rollback_btn");
	private TextField nombre_tf = ((TextField)parent.lookup("#nombre_tf"));
	private TextField apellido_tf = ((TextField)parent.lookup("#apellido_tf"));
	private TextField email_tf = ((TextField)parent.lookup("#email_tf"));
	private ComboBox<String> tarifa_cb = ((ComboBox<String>) parent.lookup("#tarifa_cb"));

	boolean done;

	private AdminApplication app;
	private VisitantePensionado visitante;
	private ClientDAO dao;

	public ShowOrEditExitingUserController(AdminApplication app) {
		this.app = app;
		dao = new ClientDAO(app);

		tarifa_cb.setItems(FXCollections.observableArrayList(Tarifas.options));

		add_new_btn.setOnMouseClicked(e -> saveChanges());
		add_new_btn.textProperty().set("Guardar");

		rollback_btn.setOnMouseClicked(e -> {
			app.updateSceneScene(context);
			done = true;
		});
	}

	public void saveChanges() {

		visitante.nombre = nombre_tf.getText();
		visitante.apellido = apellido_tf.getText();
		visitante.email = email_tf.getText();
		visitante.tipo = tarifa_cb.getValue();

		String exitCode = dao.editVisitor(visitante);
		done = true;
		System.out.println(done);
		if (exitCode != "OK") {
			new Alert(AlertType.ERROR, exitCode).showAndWait();
			return;
		}

		new Alert(AlertType.INFORMATION,"Información actualizada existosamente").showAndWait();
		app.updateSceneScene(context);

	}

	public void begin(VisitantePensionado visitante, ControllerDirectory context) {

		this.visitante = visitante;
		this.context = context;

		nombre_tf.textProperty().set(visitante.nombre);
		apellido_tf.textProperty().set(visitante.apellido);
		email_tf.textProperty().set(visitante.email);
		tarifa_cb.setValue(visitante.tipo);

		done = false;
		app.updateSceneScene(SHOW_OR_EDIT_EXISTING_USER);
	}

	@Override
	public Parent getParent() {
		return parent;
	}
}
