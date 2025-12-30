package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.CarDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.view.VehiclesTable;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;

public class ExploreAllVehiclesController implements IControllerFX{

	private Parent parent = getActivity("ExplorarAutos.fxml");
	private BorderPane pane = (BorderPane) parent.lookup("#target");
	private Label label = new Label("No existen usuarios en la base de datos");
	private VehiclesTable table;
	private CarDAO dao;
	private AdminApplication app;
	private int page;
	
	public ExploreAllVehiclesController (AdminApplication app) {
		this.app = app;
		dao = new CarDAO(app);
		label.getStyleClass().add("texto");
		
		parent.lookup("#close_btn").setOnMouseClicked(e -> app.updateSceneScene(VEHICLE_MANAGMENT));
	}
	
	public void showUserList() {

		page = 0;
		
		List<Vehículo> list = dao.getPageOfVehicles(page);
		
		if (list.size() == 0) {
			table = null;
			pane.setCenter(label);
		}else {
			table = new VehiclesTable(this);
			table.getItems().addAll(FXCollections.observableArrayList(list));
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
			pane.setCenter(table);
		}
		
		app.updateScene(SHOW_ALL_VEHICLES);
			
	}
	
	@Override
	public Parent getParent() {
		return parent;
	}
	
	public void delete(Vehículo vehículo) {
		if(((DeleteVehicleController)app.getController(DELETE_EXISTING_VEHICLE)).deleteVehicle(vehículo.matricula)){
			table.getItems().remove(vehículo);
		}
	}
	
	public void update(Vehículo vehículo) {
		
		new UpdateLicensePlateController(app).begin(vehículo);
		table.refresh();		
	}

}
