package mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Vehicle;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.CarRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;
import mx.uaemex.fi.linc26.fingerpark.client.view.VehiclesTable;

public class ExploreAllVehiclesController implements IControllerFXML{

	private Parent parent = getActivity("ExplorarAutos.fxml");
	private BorderPane pane = (BorderPane) parent.lookup("#target");
	private Label label = new Label("No existen usuarios en la base de datos");

	private AlertExt alert = new AlertExt(AlertType.ERROR);
	private VehiclesTable table;
	private CarRepository repo = new CarRepository();
	private MainController controller;

	public ExploreAllVehiclesController (MainController controller) {
	    this.controller = controller;
		label.getStyleClass().add("texto");
		parent.lookup("#close_btn").setOnMouseClicked(e -> controller.updateScene(VEHICLE_MANAGMENT));
	}

	public void showVehicleList() {
		try{
    		List<Vehicle> list = repo.getPageOfVehicles(0);

    		if (list.size() == 0) {
    			table = null;
    			pane.setCenter(label);
    		}else {
    			table = new VehiclesTable(this);
    			table.getItems().addAll(FXCollections.observableArrayList(list));
    			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    			pane.setCenter(table);
    		}
		} catch(IOException | InterruptedException exception){
            alert.showErrorMessageAndWait("No se pudo conectar con el servidor");
		} catch(Exception exception){
            alert.showErrorMessageAndWait("No se pudo recuperar la lista de vehiculos");
		}

		controller.updateScene(SHOW_ALL_VEHICLES);
	}

	@Override
	public Parent getParent() {
		return parent;
	}

	public void delete(Vehicle vehículo) {
		if(((DeleteVehicleController)controller.getController(DELETE_EXISTING_VEHICLE)).deleteVehicle(vehículo.getPlate())){
			table.getItems().remove(vehículo);
		}
	}

	public void update(Vehicle vehículo) {
		new UpdateLicensePlateController(controller).begin(vehículo);
		table.refresh();
	}

}
