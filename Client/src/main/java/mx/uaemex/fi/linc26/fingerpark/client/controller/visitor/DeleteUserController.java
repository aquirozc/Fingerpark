package mx.uaemex.fi.linc26.fingerpark.client.controller.visitor;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.VisitorRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

public class DeleteUserController implements IControllerFXML {

    private AlertExt alert = new AlertExt(AlertType.CONFIRMATION);
    private VisitorRepository repo = new VisitorRepository();
	private MainController controller;

	public DeleteUserController(MainController controller) {
	    this.controller = controller;
	}

	@Override
    public void begin(Object key, ControllerDirectory context) {
        deleteUser(((Integer)key), context);
    }

    public boolean deleteUser(int id, ControllerDirectory context){
        controller.updateScene(context);
        return deleteUser(id);
    }

	public boolean deleteUser(int id) {
		try{
		    Visitor visitor = repo.getVisitor(id);

			if(alert.showMessageAndWait("¿Desea eliminar el usuario?" + visitor.toString()).get().equals(ButtonType.OK)){
                repo.deleteVisitor(visitor);
                return true;
			}
		} catch(IOException | InterruptedException exception){
            alert.showErrorMessageAndWait("No se pudo conectar con el servidor");
		} catch(Exception exception){
            alert.showErrorMessageAndWait("No se pudo eliminar el usuario");
		}

		return false;
	}

	@Override
	public Parent getParent() {
		return null;
	}

}
