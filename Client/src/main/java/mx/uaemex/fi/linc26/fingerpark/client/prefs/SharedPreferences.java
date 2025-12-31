package mx.uaemex.fi.linc26.fingerpark.client.prefs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class SharedPreferences {

    private static final Properties preferences = SharedPreferences.loadPreferences();

    private static Properties loadPreferences(){
        Properties props = new Properties();
        props.put(PreferenceKey.SERVER_CONN_HOST.name(),"http://localhost:8080");

        try {
			props.loadFromXML(new FileInputStream("./SharedPreferences.xml"));
		} catch (Exception e) {
			System.out.println("No se pudo cargar el archivo de configuración");
		}

		try {
			props.storeToXML(new FileOutputStream("./SharedPreferences.xml"), "");
		} catch (Exception e) {
			System.out.println("No se pudo guardar el archivo de configuración");
		}

        return props;
    }

    public static String getPref(PreferenceKey key) {
		return preferences.getProperty(key.toString());
	}

	public static void setPref(PreferenceKey key, String value) {
        preferences.setProperty(key.name(), value);
    }

}
