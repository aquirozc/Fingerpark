package mx.uaemex.fi.linc26.fingerpark.client.controller.visitor;

import javafx.scene.paint.Color;

import static mx.uaemex.fi.linc26.fingerpark.client.prefs.PreferenceKey.SERVER_CONN_HOST;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import mx.uaemex.fi.linc26.fingerpark.client.controller.ControllerDirectory;
import mx.uaemex.fi.linc26.fingerpark.client.controller.IControllerFXML;
import mx.uaemex.fi.linc26.fingerpark.client.controller.MainController;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Acceso;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.client.http.RequestMan;
import mx.uaemex.fi.linc26.fingerpark.client.prefs.SharedPreferences;
import mx.uaemex.fi.linc26.fingerpark.client.view.LogTable;

public class ExploreLogsController implements IControllerFXML {

	private Parent parent = getActivity("ExplorarEventos.fxml");
	private DatePicker fromDate = (DatePicker) parent.lookup("#from_date");
	private DatePicker toDate = (DatePicker) parent.lookup("#to_date");
	private Label ganancias_lb = (Label) parent.lookup("#ganancias_lb");

	private VBox pt = (VBox) parent.lookup("#plot_target");

	private TableView<Acceso>  table = new LogTable();
	final BarChart<String, Number> barChart = new BarChart<>(createStringAxis(), createNumberAxis());

	private Gson gson = new Gson();
	private HttpClient client = HttpClient.newHttpClient();
	private RequestMan requestMan = new RequestMan();

	private MainController app;

	public ExploreLogsController(MainController app) {
		this.app = app;

		toDate.setValue(LocalDate.now());
		fromDate.setValue(LocalDate.now().minusWeeks(2));

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

		parent.lookup("#update_btn").setOnMouseClicked(e -> update());
		parent.lookup("#close_btn").setOnMouseClicked(e -> app.updateScene(ControllerDirectory.USER_MANGMENT));

		((BorderPane)parent.lookup("#table_target")).setCenter(table);

		pt.getChildren().add(barChart);

	}

	public void begin() {
		app.updateScene(ControllerDirectory.EXPLORE_LOGS);
		update();
	}

	private void update() {

		table.getItems().clear();


		List<Acceso> list = obtenerLista();

		table.getItems().addAll(FXCollections.observableArrayList(list));

		ganancias_lb.setText("$" + list.stream().mapToDouble(a -> a.getCosto()).sum());

		Map<Date, Double> map = new TreeMap<Date, Double>();

		for (Acceso a : list) {
			double sum = map.getOrDefault(a.horaDeLLegada, 0d);
			map.put(a.horaDeLLegada, sum + a.costo);
		}



        // Configurar el título del gráfico
        barChart.setTitle("Gráfico de Barras");

        // Crear la serie de datos
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ganancias totales");

        // Llenar la serie con los datos del mapa
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
        for (Map.Entry<Date, Double> entry : map.entrySet()) {
            String formattedDate = dateFormat.format(entry.getKey());
            series.getData().add(new XYChart.Data<>(formattedDate, entry.getValue()));

        }

        barChart.lookup(".chart-title").setStyle("-fx-text-fill: white;");

     // Cambiar el color de las etiquetas del eje X
     barChart.getXAxis().lookup(".axis-label").setStyle("-fx-text-fill: white;");

     // Cambiar el color de las etiquetas del eje Y
     barChart.getYAxis().lookup(".axis-label").setStyle("-fx-text-fill: white;");


     	barChart.getData().clear();       // Agregar la serie al gráfico

        barChart.getData().add(series);




	}

	private List<Acceso> obtenerLista(){

		List<Acceso> list = new ArrayList<Acceso>();

		long from = Date.valueOf(fromDate.getValue()).getTime();
		long to = Date.valueOf(toDate.getValue()).getTime();

		HttpRequest request = requestMan.open(SharedPreferences.getPref(SERVER_CONN_HOST) + "/api/admin/log/" + from + "/" + to)
				.GET()
				.build();

		try{

				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

				System.out.println(response.body());

				if(response.statusCode() != 200) {
					throw new Exception();
					}

				list = Arrays.asList(gson.fromJson(response.body().toString(), Acceso[].class));

		}catch(Exception e) {}

		return list;

	}

	@Override
	public Parent getParent() {
		return parent;
	}

	private BarChart<String, Number> createBarChart() {
        return new BarChart<>(createStringAxis(), createNumberAxis());
    }

    private NumberAxis createNumberAxis() {
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Valor");
        yAxis.setTickLabelFill(Color.WHITE);
        return yAxis;
    }

    private CategoryAxis createStringAxis() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Fecha");
        xAxis.setTickLabelFill(Color.WHITE);
        return xAxis;
    }

}
