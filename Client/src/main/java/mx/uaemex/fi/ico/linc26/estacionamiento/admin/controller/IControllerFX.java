package mx.uaemex.fi.ico.linc26.estacionamiento.admin.controller;

import static mx.uaemex.fi.ico.linc26.estacionamiento.common.preferences.PreferencesKeys.SERVER_CONN_HOST;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.preferences.SharedPreferences;
import mx.uaemex.fi.ico.linc26.estacionamiento.main.App;

public interface IControllerFX {
	
	final String SERVER_HOST = SharedPreferences.getPref(SERVER_CONN_HOST);
	
	default Parent getActivity(String n) {
		
		Parent parent = null;
		
		try {
			parent = FXMLLoader.load(App.class.getClassLoader().getResource(n));
			parent.getStylesheets().add(App.class.getClassLoader().getResource("EstilosComunes.css").toString());
		} catch (Exception e) {
			System.out.println("No se pudo cargar la escena " + n);
			e.printStackTrace();
		}
		
		return parent;
	}
	
	public Parent getParent();
	
}
