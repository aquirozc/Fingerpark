package mx.uaemex.fi.linc26.fingerpark.client.view;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertExt extends Alert {

    public AlertExt(AlertType type){
        super(type);
    }

    public void showMessage(String message){
        this.setContentText(message);
        this.show();
    }

    public Optional<ButtonType> showMessageAndWait(String message){
        this.setContentText(message);
        return this.showAndWait();
    }

    public void showErrorMessage(String message){
        this.setAlertType(AlertType.ERROR);
        showMessage(message);
    }

    public Optional<ButtonType> showErrorMessageAndWait(String message){
        this.setAlertType(AlertType.ERROR);
        return showMessageAndWait(message);
    }

}
