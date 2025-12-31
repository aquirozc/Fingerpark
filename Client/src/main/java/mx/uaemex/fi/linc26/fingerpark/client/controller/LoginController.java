package mx.uaemex.fi.linc26.fingerpark.client.controller;

import static mx.uaemex.fi.linc26.fingerpark.client.prefs.PreferenceKey.APP_CONN_TOKEN;
import static mx.uaemex.fi.linc26.fingerpark.client.prefs.PreferenceKey.SERVER_CONN_HOST;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.Gson;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mx.uaemex.fi.linc26.fingerpark.client.http.RequestMan;
import mx.uaemex.fi.linc26.fingerpark.client.prefs.SharedPreferences;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.User;
import mx.uaemex.fi.linc26.fingerpark.client.view.AlertExt;

public class LoginController implements IControllerFXML {

    private Parent parent = getActivity("IniciarSesion.fxml");
    private Node next_btn = parent.lookup("#login_btn");
	private Node close_btn = parent.lookup("#close_btn");
	private TextField user_tf =((TextField)parent.lookup("#user_tf"));
	private TextField pass_tf = ((TextField)parent.lookup("#pass_tf"));

	private MainController controller;
	private AlertExt alert = new AlertExt(AlertType.ERROR);
	private HttpClient client = HttpClient.newHttpClient();
	private Gson gson = new Gson();

	public LoginController(MainController controller){
        this.controller = controller;
        this.next_btn.setOnMouseClicked(e -> attemptLogin());
        this.close_btn.setOnMouseClicked(e -> System.exit(0));
	}

	public void attemptLogin(){
        HttpRequest request = new RequestMan().open(SharedPreferences.getPref(SERVER_CONN_HOST) + "/api/v1/admin/users/login")
                                .POST(gson.toJson(new User(user_tf.getText(), pass_tf.getText())))
                                .build();

        try{
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            switch(response.statusCode()){
                case 400 -> alert.showMessage("Por favor rellene los campos");
                case 404 -> alert.showMessage("El usuario y/o la contraseña son incorrectos");
                case 200 ->{
                    SharedPreferences.setPref(APP_CONN_TOKEN, response.body());
                    controller.getController(ControllerDirectory.ADMIN_CENTER).begin();
                }
            }
        }catch(Exception e){
            alert.showMessage("No se pudo establecer una conexión con el servidor");
        }
	}

	@Override
	public void begin() {
        controller.updateScene(ControllerDirectory.ADMIN_LOGIN);
    }

    @Override
    public Parent getParent() {
        return parent;
    }

}
