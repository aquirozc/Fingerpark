package mx.uaemex.fi.linc26.fingerpark.client.controller;

import static mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory.*;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle.AddNewVehicleController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle.DeleteVehicleController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle.DeleteVehicleFromOwnerController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle.ExploreAllVehiclesController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle.FindOwnerController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle.ShowOrEditVehicleController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.vehicle.VehicleManagmentController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.visitor.AddNewUserController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.visitor.DeleteUserController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.visitor.ExploreLogsController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.visitor.ExploreUsersController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.visitor.PaymentController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.visitor.ShowOrEditExitingUserController;
import mx.uaemex.fi.linc26.fingerpark.client.controller.visitor.UserManagmentController;

public class MainController {

    private Map<ControllerDirectory, IControllerFXML> map = new HashMap<>();
    private Stage stage;

    public IControllerFXML getController(ControllerDirectory k){
        return map.get(k);
    }

    public MainController(Stage stage){
        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.show();

        map.put(ADMIN_LOGIN, new LoginController(this));
		map.put(ADMIN_CENTER, new AdminCenterController(this));
		map.put(USER_MANGMENT, new UserManagmentController(this));
		map.put(FINGERPRINT_IO, new FingerlessController(this));
		map.put(ADD_NEW_USER, new AddNewUserController(this));
		map.put(SHOW_ALL_USERS, new ExploreUsersController(this));
		map.put(SHOW_OR_EDIT_EXISTING_USER, new ShowOrEditExitingUserController(this));
		map.put(DELETE_EXISTING_USER, new DeleteUserController(this));
		map.put(VEHICLE_MANAGMENT, new VehicleManagmentController(this));
		map.put(ADD_NEW_VEHICLE, new AddNewVehicleController(this));
		map.put(SHOW_OR_EDIT_EXISTING_VEHICLE, new ShowOrEditVehicleController(this));
		map.put(DELETE_EXISITING_OWNER_VEHICLE, new DeleteVehicleFromOwnerController(this));
		map.put(DELETE_EXISTING_VEHICLE, new DeleteVehicleController(this));
		map.put(SHOW_ALL_VEHICLES, new ExploreAllVehiclesController(this));
		map.put(FIND_VEHICLE_OWNER, new FindOwnerController(this));
		map.put(PAY_PERIODIC_FEE, new PaymentController(this));
		map.put(EXPLORE_LOGS, new ExploreLogsController(this));


        this.stage.setScene(new Scene(map.get(ControllerDirectory.ADMIN_LOGIN).getParent(), 1110, 680));
    }

    public void updateScene(ControllerDirectory k){
        Group rootGroup = new Group();
        Parent oldroot = stage.getScene().getRoot();

        rootGroup.getChildren().add(oldroot);

        map.get(k).getParent().setTranslateX(stage.getWidth());
        rootGroup.getChildren().add(map.get(k).getParent());

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(500));
        transition.setFromX(stage.getWidth());
        transition.setToX(0);

        transition.setNode(map.get(k).getParent());
        transition.play();

        stage.getScene().setRoot(rootGroup);

        PauseTransition	pause = new PauseTransition(Duration.millis(650));
        pause.setOnFinished(e -> rootGroup.getChildren().remove(oldroot));
        pause.play();
    }

    public void updateSceneBasic(ControllerDirectory k) {
		stage.getScene().setRoot(map.get(k).getParent());
	}

}
