package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.visitors;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.ClientDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;

public class DeleteUserController implements IControllerFX {
	
	AdminApplication app;
	ClientDAO dao;
	
	public DeleteUserController(AdminApplication app) {
		this.app = app;
		this.dao = new ClientDAO(app);
	}
	
	public boolean deleteUser(int huella_id) {
		
		boolean exit = false;
		
		VisitantePensionado visitante = dao.getVisitor(huella_id);
		
		ButtonType  op= new Alert(AlertType.CONFIRMATION, "¿Desea eliminar el usuario?" + visitante.toString()).showAndWait().get();
		
		if (op.equals(ButtonType.OK)) {
			String status = dao.deleteVisitor(visitante);
			if(!status.equals("OK")) {
				new Alert(AlertType.ERROR, status).showAndWait();
				return exit;
			}
			exit = true;
		}
		
		return exit;
	}
	
	public boolean deleteUser(int huella_id, ControllerDirectory context) {
		app.updateSceneScene(context);
		return deleteUser(huella_id);	
	}

	@Override
	public Parent getParent() {
		return null;
	}

}
