package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.ClientDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.view.VisitorsTable;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;

public class ExploreUsersController implements IControllerFX {

	private Parent parent = getActivity("ExplorarUsuarios.fxml");
	private BorderPane pane = (BorderPane) parent.lookup("#target");
	private Label label = new Label("No existen usuarios en la base de datos");
	private VisitorsTable table;
	private ClientDAO dao;
	private AdminApplication app;
	private int page;

	public ExploreUsersController(AdminApplication app) {
		this.app = app;
		dao = new ClientDAO(app);
		label.getStyleClass().add("texto");

		parent.lookup("#close_btn").setOnMouseClicked(e -> app.updateSceneScene(USER_MANGMENT));
	}

	public void showUserList() {

		page = 0;

		List<VisitantePensionado> list = dao.getPageOfVisitors(page);

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

		app.updateScene(SHOW_ALL_USERS);

	}

	@Override
	public Parent getParent() {
		return parent;
	}

	public void delete(VisitantePensionado visitante) {
		if(((DeleteUserController)app.getController(DELETE_EXISTING_USER)).deleteUser(visitante.huella_id)) {
			table.getItems().remove(visitante);
		}
	}

	public void update(VisitantePensionado visitante) {
		ShowOrEditExitingUserController controller = ((ShowOrEditExitingUserController)app.getController(SHOW_OR_EDIT_EXISTING_USER));
		controller.begin(visitante, SHOW_ALL_USERS);

		Thread.ofVirtual().start(() -> {while(!controller.done)System.out.println("Hola"); table.refresh();});
	}
}
