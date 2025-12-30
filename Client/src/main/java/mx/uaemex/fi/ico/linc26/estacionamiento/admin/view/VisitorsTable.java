package mx.uaemex.fi.ico.linc26.estacionamiento.admin.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors.ExploreUsersController;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;


public class VisitorsTable extends TableView<VisitantePensionado> {
	
	@SuppressWarnings("unchecked")
	public VisitorsTable(ExploreUsersController controller) {

        TableColumn<VisitantePensionado, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("huella_id"));

        TableColumn<VisitantePensionado, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        TableColumn<VisitantePensionado, String> apellidosColumn = new TableColumn<>("Apellidos");
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        TableColumn<VisitantePensionado, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        TableColumn<VisitantePensionado, String> feeColumn = new TableColumn<>("Tarifa");
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        TableColumn<VisitantePensionado, VisitantePensionado> deleteColumn = new TableColumn<>("Borrar");
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteColumn.setCellFactory(p -> new deleteRow(controller));
        deleteColumn.setStyle ("-fx-alignment: center;");
                
        TableColumn<VisitantePensionado, VisitantePensionado> updateColumn = new TableColumn<>("Editar");
        updateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateColumn.setCellFactory(p -> new updateRow(controller));
        updateColumn.setStyle ("-fx-alignment: center;");

        this.getColumns().addAll(idColumn, nombreColumn, apellidosColumn, emailColumn, feeColumn, deleteColumn,updateColumn);
        
	}
	

}

	class deleteRow extends TableCell<VisitantePensionado , VisitantePensionado>{
		
		private final Button deleteBtn = new Button("Borrar");
		private final ExploreUsersController controller;
		
		public deleteRow(ExploreUsersController controller) {
			this.controller = controller;
		}
		
		@Override
		protected void updateItem(VisitantePensionado visitante, boolean empty) {
	        super.updateItem(visitante, empty);
	
	        if (visitante == null) {
	            setGraphic(null);
	            return;
	        }
	
	        setGraphic(deleteBtn);
	        deleteBtn.setOnAction(
	            event -> controller.delete(visitante)
	        );
		}

}
	
	class updateRow extends TableCell<VisitantePensionado , VisitantePensionado>{
		
		private final Button updateBtn = new Button("Editar");
		private final ExploreUsersController controller;
		
		public updateRow(ExploreUsersController controller) {
			this.controller = controller;
		}
		
		@Override
		protected void updateItem(VisitantePensionado visitante, boolean empty) {
	        super.updateItem(visitante, empty);

	        if (visitante == null) {
	            setGraphic(null);
	            return;
	        }

	        setGraphic(updateBtn);
	        updateBtn.setOnAction(
	            event -> controller.update(visitante)
	        );
		}
	
	}
