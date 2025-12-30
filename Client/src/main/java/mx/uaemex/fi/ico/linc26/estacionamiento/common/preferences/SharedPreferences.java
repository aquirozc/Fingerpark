package mx.uaemex.fi.ico.linc26.estacionamiento.common.preferences;

import static mx.uaemex.fi.ico.linc26.estacionamiento.common.preferences.PreferencesKeys.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class SharedPreferences {
	
	private static final Properties preferencesDB = loadPreferences();
	
	private static Properties loadPreferences(){
		Properties p = new Properties();
		
		p.put(SERVER_CONN_HOST.toString(), "http://localhost:8080");
		
		try {
			p.loadFromXML(new FileInputStream("./SharedPreferences.xml"));
		} catch (Exception e) {
			System.out.println("No se pudo cargar el archivo de configuración");
		}
		
		try {
			p.storeToXML(new FileOutputStream("./SharedPreferences.xml"), "");
		} catch (Exception e) {
			System.out.println("No se pudo guardar el archivo de configuración");
		}
		
		return p;
	}
	
	public static String getPref(PreferencesKeys key) {
		return preferencesDB.getProperty(key.toString());
	}
}
