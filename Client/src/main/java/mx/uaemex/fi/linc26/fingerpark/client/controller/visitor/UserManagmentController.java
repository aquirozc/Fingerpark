package mx.uaemex.fi.linc26.fingerpark.client.controller.visitor;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import javafx.scene.Node;
import javafx.scene.Parent;
import mx.uaemex.fi.linc26.fingerpark.client.controller.FingerprintController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;

public class UserManagmentController implements IControllerFXML {

	private Parent parent = getActivity("AdministrarUsuario.fxml");
	private Node add_new_btn = parent.lookup("#launch_add_new");
	private Node show_all_btn = parent.lookup("#launch_show_all");
	private Node show_existing_btn = parent.lookup("#launch_show_existing");
	private Node show_log_btn = parent.lookup("#launch_show_track");
	private Node delete_existing_btn = parent.lookup("#launch_remove_existing");
	private Node return_hub_btn = parent.lookup("#return_hub_btn");
	private MainController controller;


	public UserManagmentController(MainController controller) {

		add_new_btn.setOnMouseClicked(e ->{
			((FingerprintController)controller.getController(FINGERPRINT_IO)).andThenCreateNewUser();
		});

		show_existing_btn.setOnMouseClicked(e ->{
			((FingerprintController)controller.getController(FINGERPRINT_IO)).andThenEditExistingUser();
		});

		show_all_btn.setOnMouseClicked(e -> {
		    ((ExploreUsersController)controller.getController(SHOW_ALL_USERS)).showUserList();
		});

		delete_existing_btn.setOnMouseClicked(e -> {
			((FingerprintController)controller.getController(FINGERPRINT_IO)).andThenDeleteExistingUser();
		});

		show_log_btn.setOnMouseClicked(e -> {
			((ExploreLogsController)controller.getController(EXPLORE_LOGS)).begin();
		});

		return_hub_btn.setOnMouseClicked(e -> {
            controller.getController(ADMIN_CENTER).begin();
		});

		this.controller = controller;

	}

	@Override
    public void begin() {
        controller.updateScene(USER_MANGMENT);
    }

	@Override
	public Parent getParent() {
		return parent;
	}
}
