package mx.uaemex.fi.linc26.fingerpark.client.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle.ExploreAllVehiclesController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Vehicle;


public class VehiclesTable extends TableView<Vehicle> {

	@SuppressWarnings("unchecked")
	public VehiclesTable(ExploreAllVehiclesController controller) {

        TableColumn<Vehicle, Integer> idColumn = new TableColumn<>("Matricula");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));

        TableColumn<Vehicle, String> nombreColumn = new TableColumn<>("Marca");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        TableColumn<Vehicle, String> apellidosColumn = new TableColumn<>("Modelo");
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Vehicle, String> emailColumn = new TableColumn<>("Año");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Vehicle, Vehicle> deleteColumn = new TableColumn<>("Borrar");
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteColumn.setCellFactory(p -> new DeleteRow(controller));
        deleteColumn.setStyle ("-fx-alignment: center;");

        TableColumn<Vehicle, Vehicle> updateColumn = new TableColumn<>("Editar");
        updateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateColumn.setCellFactory(p -> new UpdateRow(controller));
        updateColumn.setStyle ("-fx-alignment: center;");

        this.getColumns().addAll(idColumn, nombreColumn, apellidosColumn, emailColumn, deleteColumn,updateColumn);

	}

	static class UpdateRow extends TableCell<Vehicle , Vehicle>{

		private final Button updateBtn = new Button("Editar");
		private final ExploreAllVehiclesController controller;

		public UpdateRow (ExploreAllVehiclesController controller) {
			this.controller = controller;
		}

		@Override
		protected void updateItem(Vehicle vehículo, boolean empty) {
	        super.updateItem(vehículo, empty);

	        if (vehículo == null) {
	            setGraphic(null);
	            return;
	        }

	        setGraphic(updateBtn);
	        updateBtn.setOnAction(
	            event -> controller.update(vehículo)
	        );
		}

	}

	class DeleteRow extends TableCell<Vehicle , Vehicle>{

		private final Button deleteBtn = new Button("Borrar");
		private final ExploreAllVehiclesController controller;

		public DeleteRow(ExploreAllVehiclesController controller) {
			this.controller = controller;
		}

		@Override
		protected void updateItem(Vehicle vehículo, boolean empty) {

	        super.updateItem(vehículo,empty);

	        if (vehículo == null) {
	            setGraphic(null);
	            return;
	        }

	        setGraphic(deleteBtn);
	        deleteBtn.setOnAction(
	            event -> controller.delete(vehículo)
	        );
		}

}

}
