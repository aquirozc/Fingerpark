package mx.uaemex.fi.linc26.fingerpark.client.controller.visitor;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.TariffCatalog;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.VisitorRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

@SuppressWarnings("unchecked")
public class ShowOrEditExitingUserController implements IControllerFXML {

	private Parent parent = getActivity("CrearUsuario.fxml");
	private Button add_new_btn = (Button) parent.lookup("#add_new_btn");
	private Button rollback_btn = (Button) parent.lookup("#rollback_btn");
	private TextField nombre_tf = ((TextField)parent.lookup("#nombre_tf"));
	private TextField apellido_tf = ((TextField)parent.lookup("#apellido_tf"));
	private TextField email_tf = ((TextField)parent.lookup("#email_tf"));
	private ComboBox<String> tarifa_cb = ((ComboBox<String>) parent.lookup("#tarifa_cb"));

	private AlertExt alert = new AlertExt(AlertType.INFORMATION);
	private VisitorRepository repo = new VisitorRepository();
	private MainController controller;


	public ShowOrEditExitingUserController(MainController controller) {
	    this.controller = controller;
		tarifa_cb.setItems(FXCollections.observableArrayList(TariffCatalog.options));
		add_new_btn.textProperty().set("Guardar");
	}

	public void saveChanges(Visitor visitor) {
		visitor.setFirstName(nombre_tf.getText());
		visitor.setLastName(apellido_tf.getText());
		visitor.setEmail(email_tf.getText());
		visitor.setType(tarifa_cb.getValue());

		try{
            repo.editVisitor(visitor);
		} catch(IOException | InterruptedException exception){
            alert.showErrorMessageAndWait("No se pudo conectar con el servidor");
		} catch(Exception exception){
            alert.showErrorMessageAndWait("No se pudo actualizar la información del usuario");
		}

		alert.showMessageAndWait("Información actualizada existosamente");
		rollback_btn.fire();
	}

	@Override
	public void begin(Object key, ControllerDirectory context) {
	    Visitor visitor = (Visitor)key;

		nombre_tf.textProperty().set(visitor.getFirstName());
		apellido_tf.textProperty().set(visitor.getLastName());
		email_tf.textProperty().set(visitor.getEmail());
		tarifa_cb.setValue(visitor.getType());

		add_new_btn.setOnMouseClicked(e -> saveChanges(visitor));
		rollback_btn.setOnAction(e -> controller.getController(context).begin());

		controller.updateScene(SHOW_OR_EDIT_EXISTING_USER);
	}

	@Override
	public Parent getParent() {
		return parent;
	}
}
