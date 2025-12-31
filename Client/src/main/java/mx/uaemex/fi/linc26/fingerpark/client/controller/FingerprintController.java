package mx.uaemex.fi.linc26.fingerpark.client.controller;

import static mx.uaemex.fi.linc26.fingerpark.client.prefs.PreferenceKey.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import javafx.scene.Parent;
import mx.uaemex.fi.linc26.fingerpark.client.http.RequestMan;
import mx.uaemex.fi.linc26.fingerpark.client.prefs.SharedPreferences;

public class FingerprintController implements IControllerFXML {

    protected Parent parent = getActivity("RegistrarHuellaDigital.fxml");
    protected MainController controller;

    public FingerprintController(MainController controller){
        this.controller = controller;
    }

    public void andThenCreateNewUser(){

    }

    public void andThenEditExistingUser(){

    }

    public void andThenDeleteExistingUser(){

    }

    public void andThenCreateNewCar() {
    }

    public void andThenUpdateCarPlates() {

    }

    public void andThenDeleteExistingCar() {

    }


    protected int getAvailableSlot() throws Exception{
        HttpRequest request = new RequestMan().open(SharedPreferences.getPref(SERVER_CONN_HOST) + "/api/v1/admin/visitors/trusted/id")
                                    .GET().enforced(SharedPreferences.getPref(APP_CONN_TOKEN)).build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());
        if(response.statusCode() != 200) throw new Exception();
        return Integer.parseInt(response.body());
    }

    @Override
    public void begin() {
        controller.updateScene(ControllerDirectory.FINGERPRINT_IO);
    }

    @Override
    public Parent getParent() {
        return parent;
    }

}
