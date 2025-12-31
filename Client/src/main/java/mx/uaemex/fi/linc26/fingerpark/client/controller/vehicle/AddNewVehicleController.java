package mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Vehicle;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.CarRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

public class AddNewVehicleController implements IControllerFXML {

	private Parent parent = getActivity("CrearVehículo.fxml");
	private Node add_new_btn = parent.lookup("#add_new_btn");
	private Node rollback_btn = parent.lookup("#rollback_btn");
	private TextField placa_tf = ((TextField)parent.lookup("#placa_tf"));
	private TextField marca_tf = ((TextField)parent.lookup("#marca_tf"));
	private TextField modelo_tf = ((TextField)parent.lookup("#modelo_tf"));
	private TextField año_tf = ((TextField)parent.lookup("#año_tf"));

	private AlertExt alert = new AlertExt(AlertType.INFORMATION);
	private CarRepository repo = new CarRepository();
	private MainController controller;
	private int id;

	public AddNewVehicleController(MainController controller) {
		this.controller = controller;

		add_new_btn.setOnMouseClicked(e -> addNew());
		rollback_btn.setOnMouseClicked(e -> controller.updateScene(VEHICLE_MANAGMENT));
	}

	public void addNew() {
        try{
            Vehicle vehicle = new Vehicle(
                placa_tf.getText(),
                marca_tf.getText(),
                modelo_tf.getText(),
                Integer.parseInt(año_tf.getText()),
                id
            );

            repo.addVehicle(vehicle);
            alert.showMessageAndWait("Vehiculo añadido a la base de datos");
			controller.updateScene(VEHICLE_MANAGMENT);
    	} catch(IOException | InterruptedException exception){
                alert.showErrorMessageAndWait("No se pudo conectar con el servidor");
    	} catch(Exception exception){
                alert.showErrorMessageAndWait("No se pudo añadir el usuario");
        }
	}

	@Override
	public void begin(Object key) {
		this.id = (Integer) key;

		placa_tf.textProperty().set("");
		marca_tf.textProperty().set("");
		modelo_tf.textProperty().set("");
		año_tf.textProperty().set("");

		controller.updateScene(ADD_NEW_VEHICLE);
	}

	@Override
	public Parent getParent() {
		return parent;
	}
}
