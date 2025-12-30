package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.vehicles;

import java.util.List;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.ControllerDirectory;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller.IControllerFX;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper.CarDAO;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;
import mx.uaemex.fi.ico.linc26.estacionamiento.main.App;

public abstract class ExploreOwnersAllVehiclesController implements IControllerFX{
	
	protected Parent parent = getActivity("ExplorarAutos.fxml");
	protected BorderPane pane = (BorderPane) parent.lookup("#target");
	protected Label label = new Label("No hay vehículos para mostrar");
	protected ScrollPane table;
	protected VBox row;
	protected HBox column;
	
	protected Random rn = new Random();
	protected CarDAO dao;
	protected AdminApplication app;
	
	public ExploreOwnersAllVehiclesController(AdminApplication app) {
		this.app = app;
		dao = new CarDAO(app);
		label.getStyleClass().add("texto");
		parent.lookup("#close_btn").setOnMouseClicked(e -> app.updateSceneScene(ControllerDirectory.VEHICLE_MANAGMENT));
	}
	
	public void begin(int propietario_id) {
		
		List<Vehículo> list = dao.getAllOwnersVehicles(propietario_id);
		
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
			
			for(Vehículo vehículo : list){
				
				char c = (char) rn.nextInt((int) 'A', (int)'D');				
				Button boton = new Button();
				HBox box = new HBox();
				box.setSpacing(20);
				box.setAlignment(Pos.CENTER_LEFT);
				Label l = new Label(vehículo.toString());
				l.getStyleClass().add("texto");
				
				ImageView i = new ImageView(App.class.getClassLoader().getResource("CarIcon" +  c + ".png").toString());
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
			
			animarBotones(row);
			pane.setCenter(table);
			
		}
		
		
	}
	
	private void animarBotones(VBox vbox) {

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
	
	public abstract void action(Button b, Vehículo vehículo);

	@Override
	public Parent getParent() {
		return parent;
	}
}
