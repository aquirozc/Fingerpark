package mx.uaemex.fi.linc26.fingerpark.client.controller;

import java.lang.UnsupportedOperationException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import mx.uaemex.fi.linc26.fingerpark.client.app.Main;

public interface IControllerFXML {

    default void begin(){
        throw new UnsupportedOperationException();
    }

    default void begin(Object key){
        throw new UnsupportedOperationException();
    }

    default void begin(Object key, ControllerDirectory context){
        throw new UnsupportedOperationException();
    }

    default Parent getActivity(String n) {
		Parent parent = null;
		try {
			parent = FXMLLoader.load(Main.class.getClassLoader().getResource(n));
			parent.getStylesheets().add(Main.class.getClassLoader().getResource("EstilosComunes.css").toString());
		} catch (Exception e) {
			System.out.println("No se pudo cargar la escena " + n);
			e.printStackTrace();
		}

		return parent;
	}

	public Parent getParent();

}
