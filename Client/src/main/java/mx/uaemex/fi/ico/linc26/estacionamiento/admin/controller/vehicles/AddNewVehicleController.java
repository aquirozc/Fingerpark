package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.CarDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;

public class AddNewVehicleController implements IControllerFX {
	
	private int propietario_id;
	
	private Parent parent = getActivity("CrearVehículo.fxml");
	private Node add_new_btn = parent.lookup("#add_new_btn");
	private Node rollback_btn = parent.lookup("#rollback_btn");
	private TextField placa_tf = ((TextField)parent.lookup("#placa_tf"));
	private TextField marca_tf = ((TextField)parent.lookup("#marca_tf"));
	private TextField modelo_tf = ((TextField)parent.lookup("#modelo_tf"));
	private TextField año_tf = ((TextField)parent.lookup("#año_tf"));
	
	private AdminApplication app;
	private CarDAO dao;
	
	public AddNewVehicleController(AdminApplication app) {
		this.app = app;
		dao = new CarDAO(app);
		
		add_new_btn.setOnMouseClicked(e -> addNew());
		rollback_btn.setOnMouseClicked(e -> app.updateSceneScene(VEHICLE_MANAGMENT));
	}
	
	public void addNew() {
		
		Vehículo vehículo = new Vehículo();
		
		vehículo.propietario_id = propietario_id;
		vehículo.matricula = placa_tf.getText();
		vehículo.marca = marca_tf.getText();
		vehículo.modelo = modelo_tf.getText();
		vehículo.año = Integer.parseInt(año_tf.getText());
		
		String exitCode = dao.addVehicle(vehículo);
		
		if (exitCode != "OK") {
			new Alert(AlertType.ERROR, exitCode).showAndWait();
			return;
		}
		
		new Alert(AlertType.INFORMATION,"Vehiculo añadido a la base de datos").showAndWait();
		app.updateSceneScene(VEHICLE_MANAGMENT);
		
	}
	
	public void begin(int propietario_id) {
		
		this.propietario_id = propietario_id;
		
		placa_tf.textProperty().set("");
		marca_tf.textProperty().set("");
		modelo_tf.textProperty().set("");	
		año_tf.textProperty().set("");
		
		app.updateSceneScene(ADD_NEW_VEHICLE);
	}

	@Override
	public Parent getParent() {
		return parent;
	}
}
