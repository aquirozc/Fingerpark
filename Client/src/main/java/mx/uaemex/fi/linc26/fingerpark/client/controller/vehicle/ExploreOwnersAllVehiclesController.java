package mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import mx.uaemex.fi.linc26.fingerpark.client.app.Main;
import mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Vehicle;
import mx.uaemex.fi.linc26.fingerpark.client.data.repo.CarRepository;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

public abstract class ExploreOwnersAllVehiclesController implements IControllerFXML{

	protected Parent parent = getActivity("ExplorarAutos.fxml");
	protected BorderPane pane = (BorderPane) parent.lookup("#target");
	protected Label label = new Label("No hay vehículos para mostrar");
	protected ScrollPane table;
	protected VBox row;
	protected HBox column;

	protected AlertExt alert = new AlertExt(AlertType.ERROR);
	protected CarRepository repo = new CarRepository();
	protected Random rn = ThreadLocalRandom.current();
	protected MainController controller;

	public ExploreOwnersAllVehiclesController(MainController controller) {
        this.controller = controller;
		label.getStyleClass().add("texto");
		parent.lookup("#close_btn").setOnMouseClicked(e -> controller.updateScene(ControllerDirectory.VEHICLE_MANAGMENT));
	}

	@Override
	public void begin(Object key, ControllerDirectory context) {

        controller.updateScene(context);

        try{
            List<Vehicle> list = repo.getAllOwnersVehicles((Integer)key);

		if (list.isEmpty()) {
			table = null;
			pane.setCenter(label);
		}else {

			row = new VBox();
			row.setSpacing(20);
			column = new HBox();
			HBox.setHgrow(row, Priority.ALWAYS);
			column.getChildren().add(row);

			table = new ScrollPane(row);
			table.setPadding(new Insets(10));
			table.setStyle("-fx-background: none;");
			table.setFitToWidth(true);

			for(Vehicle vehículo : list){

				char c = (char) rn.nextInt((int) 'A', (int)'D');
				Button boton = new Button();
				HBox box = new HBox();
				box.setSpacing(20);
				box.setAlignment(Pos.CENTER_LEFT);
				Label l = new Label(vehículo.toString());
				l.getStyleClass().add("texto");

				ImageView i = new ImageView(Main.class.getClassLoader().getResource("CarIcon" +  c + ".png").toString());
				i.setFitHeight(88);
				i.setFitWidth(88);

				box.getChildren().add(i);
				box.getChildren().add(l);
				boton.setGraphic(box);
				boton.getStyleClass().addAll("boton");
				boton.setMaxWidth(Double.MAX_VALUE);
				boton.setOnMouseClicked(e -> action(boton, vehículo));
				boton.setOpacity(0);

				row.getChildren().add(boton);

			}

			animateButtons(row);
			pane.setCenter(table);

		}
    	} catch(IOException | InterruptedException exception){
                alert.showErrorMessageAndWait("No se pudo conectar con el servidor");
    	} catch(Exception exception){
            alert.showErrorMessageAndWait("No se pudo recupera la lista de vehiculos");
    	}

	}

	private void animateButtons(VBox vbox) {
		 Duration duration = Duration.seconds(0.5);

		 for (int i = 0; i < vbox.getChildren().size(); i++) {
	            Button button = (Button) vbox.getChildren().get(i);

	            FadeTransition fadeTransition = new FadeTransition(duration, button);
	            fadeTransition.setToValue(1);
	            fadeTransition.setDelay(duration.multiply(i));
	            fadeTransition.play();

	            TranslateTransition transition = new TranslateTransition(duration, button);
	            transition.setFromY(680);
	            transition.setToY(0);
	            transition.setInterpolator(Interpolator.EASE_BOTH);
	            transition.setDelay(duration.multiply(i));
	            transition.play();
	        }
    }

	public abstract void action(Button b, Vehicle vehículo);

	@Override
	public Parent getParent() {
		return parent;
	}
}
