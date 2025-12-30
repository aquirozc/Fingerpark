package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors;

import static mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory.*;

import javafx.scene.Node;
import javafx.scene.Parent;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.FingerprintController;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;

public class UserManagmentController implements IControllerFX {
	
	private Parent parent = getActivity("AdministrarUsuario.fxml");	
	private Node add_new_btn = parent.lookup("#launch_add_new");
	private Node show_all_btn = parent.lookup("#launch_show_all");
	private Node show_existing_btn = parent.lookup("#launch_show_existing");
	private Node show_log_btn = parent.lookup("#launch_show_track");
	private Node delete_existing_btn = parent.lookup("#launch_remove_existing");
	private Node return_hub_btn = parent.lookup("#return_hub_btn");
	
	public UserManagmentController(AdminApplication app) {
		
		add_new_btn.setOnMouseClicked(e ->{
			((FingerprintController)app.getController(FINGERPRINT_IO)).andThenCreateNewUser();
		});
		
		show_existing_btn.setOnMouseClicked(e ->{
			((FingerprintController)app.getController(FINGERPRINT_IO)).andThenEditExistingUser();
		});
		
		show_all_btn.setOnMouseClicked(e -> ((ExploreUsersController)app.getController(SHOW_ALL_USERS)).showUserList());
		
		delete_existing_btn.setOnMouseClicked(e -> {
			((FingerprintController)app.getController(FINGERPRINT_IO)).andThenDeleteExisitingUser();
		});
		
		show_log_btn.setOnMouseClicked(e -> {
			((ExploreLogsController)app.getController(EXPLORE_LOGS)).begin();
		});
		
		return_hub_btn.setOnMouseClicked(e -> app.updateSceneScene(ADMIN_CENTER));
		
	}
	
	@Override
	public Parent getParent() {
		return parent;
	}
}
