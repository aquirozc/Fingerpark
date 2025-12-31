package mx.uaemex.fi.linc26.fingerpark.client.controller.visitor;

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
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.VisitorRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;
import mx.uaemex.fi.linc26.fingerpark.client.view.VisitorsTable;

public class ExploreUsersController implements IControllerFXML {

	private Parent parent = getActivity("ExplorarUsuarios.fxml");
	private BorderPane pane = (BorderPane) parent.lookup("#target");
	private Label label = new Label("No existen usuarios en la base de datos");

	private AlertExt alert = new AlertExt(AlertType.ERROR);
	private VisitorRepository repo = new VisitorRepository();
	private MainController controller;
	private VisitorsTable table;

	public ExploreUsersController(MainController controller) {
		this.controller = controller;
		label.getStyleClass().add("texto");
		parent.lookup("#close_btn").setOnMouseClicked(e -> controller.updateScene(USER_MANGMENT));
	}

	@Override
	public void begin() {
	    table.refresh();
		controller.updateScene(SHOW_ALL_USERS);
	}

	public void showUserList() {
		try{
    		List<Visitor> list = repo.getPageOfVisitors(0);

    		if (list.size() == 0) {
    			table = null;
    			pane.setCenter(label);
    		}else {
    			table = new VisitorsTable(this);
    			table.setStyle("-fx-font-size: 12");
    			table.getItems().addAll(FXCollections.observableArrayList(list));
    			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    			pane.setCenter(table);
    		}
		} catch(IOException | InterruptedException exception){
            alert.showErrorMessageAndWait("No se pudo conectar con el servidor");
		} catch(Exception exception){
            alert.showErrorMessageAndWait("No se pudo recuperar la lista de usuarios");
		}

		controller.updateScene(SHOW_ALL_USERS);
	}

	@Override
	public Parent getParent() {
		return parent;
	}

	public void delete(Visitor visitor) {
		if(((DeleteUserController)controller.getController(DELETE_EXISTING_USER)).deleteUser((int) visitor.getId())) {
			table.getItems().remove(visitor);
		}
	}

	public void update(Visitor visitor) {
        controller.getController(SHOW_OR_EDIT_EXISTING_USER).begin(visitor, SHOW_ALL_USERS);
	}
}
