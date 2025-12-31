package mx.uaemex.fi.linc26.fingerpark.client.controller.visitor;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.TariffCatalog;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Visitor;

@SuppressWarnings("unchecked")
public class AddNewUserController implements IControllerFXML {

	private Parent parent = getActivity("CrearUsuario.fxml");
	private Node add_new_btn = parent.lookup("#add_new_btn");
	private Node rollback_btn = parent.lookup("#rollback_btn");
	private TextField nombre_tf = ((TextField)parent.lookup("#nombre_tf"));
	private TextField apellido_tf = ((TextField)parent.lookup("#apellido_tf"));
	private TextField email_tf = ((TextField)parent.lookup("#email_tf"));
	private ComboBox<String> tarifa_cb = ((ComboBox<String>) parent.lookup("#tarifa_cb"));

	private MainController controller;
	private int id;

	public AddNewUserController(MainController controller) {
		this.controller = controller;

		tarifa_cb.setItems(FXCollections.observableArrayList(TariffCatalog.options));
		tarifa_cb.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-text-fill: darkblue; -fx-background-color: lightgray;");

		add_new_btn.setOnMouseClicked(e -> addNew());
		rollback_btn.setOnMouseClicked(e -> controller.getController(USER_MANGMENT).begin());
	}

	public void addNew() {
		Visitor visitante = new Visitor(
            id,
            this.nombre_tf.getText(),
            this.apellido_tf.getText(),
            this.email_tf.getText(),
            this.tarifa_cb.getSelectionModel().getSelectedItem()
		);

		controller.getController(PAY_PERIODIC_FEE).begin(visitante);
	}

	@Override
    public void begin() {
        controller.updateScene(ADD_NEW_USER);
    }

	@Override
	public void begin(Object key) {
		this.id = ((Integer)key).intValue();

		nombre_tf.textProperty().set("");
		apellido_tf.textProperty().set("");
		email_tf.textProperty().set("");
		tarifa_cb.setValue(TariffCatalog.VISITANTE_PENSIONADO.desc);

		controller.updateScene(ADD_NEW_USER);
	}

	@Override
	public Parent getParent() {
		return parent;
	}

}
