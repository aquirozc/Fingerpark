package mx.uaemex.fi.ico.linc26.estacionamiento.admin.view;

import java.sql.Date;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Acceso;

public class LogTable extends TableView<Acceso> {
	
	public LogTable() {
		TableColumn<Acceso, Date> columnaHoraLlegada = new TableColumn<>("Llegada");
        TableColumn<Acceso, Date> columnaHoraSalida = new TableColumn<>("Salida");
        TableColumn<Acceso, Double> columnaTarifa = new TableColumn<>("Tarifa");
        TableColumn<Acceso, Integer> columnaVisitanteHuella = new TableColumn<>("ID");
        TableColumn<Acceso, Double> columnaCosto = new TableColumn<>("Costo");

        // Asigna las propiedades a cada columna
        columnaHoraLlegada.setCellValueFactory(new PropertyValueFactory<>("horaDeLLegada"));
        columnaHoraSalida.setCellValueFactory(new PropertyValueFactory<>("horaDeSalida"));
        columnaTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifa"));
        columnaVisitanteHuella.setCellValueFactory(new PropertyValueFactory<>("visitante_huella"));
        columnaCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        // Agrega las columnas a la TableView
        this.getColumns().addAll(columnaHoraLlegada, columnaHoraSalida, columnaTarifa, columnaVisitanteHuella, columnaCosto);
	}
}
