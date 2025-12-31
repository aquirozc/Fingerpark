package mx.uaemex.fi.linc26.fingerpark.client.controller.visitor;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Ticket;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.VisitorRepository;
import mx.uaemex.fi.linc26.fingerpark.client.data.service.PaymentService;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

public class PaymentController implements IControllerFXML {

	private Parent parent = getActivity("RealizarPago.fxml");
	private Node pay_btn = parent.lookup("#pay_btn");
	private Node return_btn = parent.lookup("#return_btn");
	private Label monto_lb = (Label) parent.lookup("#total_lb");
	private TextField dinero1_tf = (TextField) parent.lookup("#dinero1_tf");
	private TextField dinero2_tf = (TextField) parent.lookup("#dinero2_tf");

	private AlertExt alert = new AlertExt(AlertType.ERROR);
	private VisitorRepository repo = new VisitorRepository();
	private PaymentService service = new PaymentService();
	private MainController controller;
	private Visitor visitor;
	private double fee;

	public PaymentController (MainController controller) {
		dinero1_tf.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue.matches("\\d*(\\.\\d*)?")) {
		        dinero2_tf.setText(String.valueOf(Math.max(0,Double.parseDouble(newValue)-fee)));
		        return;
		    }
		    dinero1_tf.setText("");
		    dinero2_tf.setText("");
		   });

		dinero2_tf.setEditable(false);
		this.controller = controller;

		pay_btn.setOnMouseClicked(e -> processPayment());
		return_btn.setOnMouseClicked(e -> controller.getController(ADD_NEW_USER).begin());
	}

	@Override
    public void begin(Object key) {
        this.visitor = (Visitor) key;

        try {
            this.fee = repo.getVisitorTariff(visitor);
            monto_lb.setText("Monto a pagar: $" + fee);
            controller.updateScene(PAY_PERIODIC_FEE);
        } catch (IOException | InterruptedException e) {
            alert.showMessageAndWait("No se pudo conectar con el servidor");
            controller.updateScene(USER_MANGMENT);
        } catch (IllegalStateException e){
            alert.showMessageAndWait("No se puede realizar el pago en este momento");
            controller.updateScene(USER_MANGMENT);
        }
    }

	public void processPayment() {
	    try {
			if(Double.parseDouble("0" + dinero1_tf.getText()) < fee){
			    alert.showMessageAndWait("Fondos insuficientes, por favor deposite un monto válido");
				return;
			}

			Ticket ticket = new Ticket(service.getTicketReference());
			ticket.updateContent(fee, Double.parseDouble(dinero1_tf.getText()));
			Ticket.saveTicketToFileSystem(ticket);

			repo.addVisitor(visitor);
			alert.setAlertType(AlertType.INFORMATION);
			alert.showMessageAndWait("Usuario añadido a la base de datos");

			controller.updateScene(USER_MANGMENT);
        } catch (IOException | InterruptedException e) {
            alert.showMessageAndWait("No se pudo conectar con el servidor");
            controller.updateScene(USER_MANGMENT);
        } catch (IllegalStateException e){
            alert.showMessageAndWait("No se pudo agregar el usuario a la base de datos");
            controller.updateScene(USER_MANGMENT);
        }
	}

	@Override
	public Parent getParent() {
		return parent;
	}

}
