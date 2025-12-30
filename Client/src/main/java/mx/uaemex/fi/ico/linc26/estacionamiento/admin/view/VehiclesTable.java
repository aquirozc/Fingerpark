package mx.uaemex.fi.ico.linc26.estacionamiento.admin.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles.ExploreAllVehiclesController;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;


public class VehiclesTable extends TableView<Vehículo> {
	
	@SuppressWarnings("unchecked")
	public VehiclesTable(ExploreAllVehiclesController controller) {

        TableColumn<Vehículo, Integer> idColumn = new TableColumn<>("Matricula");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("matricula"));

        TableColumn<Vehículo, String> nombreColumn = new TableColumn<>("Marca");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        
        TableColumn<Vehículo, String> apellidosColumn = new TableColumn<>("Modelo");
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn<Vehículo, String> emailColumn = new TableColumn<>("Año");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("año"));
        
        TableColumn<Vehículo, Vehículo> deleteColumn = new TableColumn<>("Borrar");
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteColumn.setCellFactory(p -> new deleteRow2(controller));
        deleteColumn.setStyle ("-fx-alignment: center;");
                
        TableColumn<Vehículo, Vehículo> updateColumn = new TableColumn<>("Editar");
        updateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateColumn.setCellFactory(p -> new updateRow2(controller));
        updateColumn.setStyle ("-fx-alignment: center;");

        this.getColumns().addAll(idColumn, nombreColumn, apellidosColumn, emailColumn, deleteColumn,updateColumn);
        
	}
	

}

	class deleteRow2 extends TableCell<Vehículo , Vehículo>{
		
		private final Button deleteBtn = new Button("Borrar");
		private final ExploreAllVehiclesController controller;
		
		public deleteRow2(ExploreAllVehiclesController controller) {
			this.controller = controller;
		}
		
		@Override
		protected void updateItem(Vehículo vehículo, boolean empty) {
			
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
	
	class updateRow2 extends TableCell<Vehículo , Vehículo>{
		
		private final Button updateBtn = new Button("Editar");
		private final ExploreAllVehiclesController controller;
		
		public updateRow2 (ExploreAllVehiclesController controller) {
			this.controller = controller;
		}
		
		@Override
		protected void updateItem(Vehículo vehículo, boolean empty) {
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
