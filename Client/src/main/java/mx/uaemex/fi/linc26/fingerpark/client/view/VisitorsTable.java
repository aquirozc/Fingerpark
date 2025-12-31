package mx.uaemex.fi.linc26.fingerpark.client.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uaemex.fi.linc26.fingerpark.client.controller.visitor.ExploreUsersController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Visitor;

public class VisitorsTable extends TableView<Visitor> {

	@SuppressWarnings("unchecked")
	public VisitorsTable(ExploreUsersController controller) {
        TableColumn<Visitor, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Visitor, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Visitor, String> apellidosColumn = new TableColumn<>("Apellidos");
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Visitor, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Visitor, String> feeColumn = new TableColumn<>("Tarifa");
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Visitor, Visitor> deleteColumn = new TableColumn<>("Borrar");
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteColumn.setCellFactory(p -> new DeleteRow(controller));
        deleteColumn.setStyle ("-fx-alignment: center;");

        TableColumn<Visitor, Visitor> updateColumn = new TableColumn<>("Editar");
        updateColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateColumn.setCellFactory(p -> new UpdateRow(controller));
        updateColumn.setStyle ("-fx-alignment: center;");

        this.getColumns().addAll(idColumn, nombreColumn, apellidosColumn, emailColumn, feeColumn, deleteColumn,updateColumn);
	}

	static class DeleteRow extends TableCell<Visitor , Visitor>{

		private final Button deleteBtn = new Button("Borrar");
		private final ExploreUsersController controller;

		public DeleteRow(ExploreUsersController controller) {
			this.controller = controller;
		}

		@Override
		protected void updateItem(Visitor visitante, boolean empty) {
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

	static class UpdateRow extends TableCell<Visitor , Visitor>{

		private final Button updateBtn = new Button("Editar");
		private final ExploreUsersController controller;

		public UpdateRow(ExploreUsersController controller) {
			this.controller = controller;
		}

		@Override
		protected void updateItem(Visitor visitante, boolean empty) {
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


}
