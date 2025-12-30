package mx.uaemex.fi.ico.linc26.estacionamiento.main;

import java.util.List;

import javafx.application.Application;
import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;

public class App {
    

    public static void main(String[] args) {
    	
    	Class<? extends Application> target = List.of(args).indexOf("--boot-into-admin") == -1 ? AdminApplication.class : null;   
    	Application.launch(target,args);
    	
    }
}
