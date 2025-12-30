package mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper;

import static mx.uaemex.fi.ico.linc26.estacionamiento.common.preferences.PreferencesKeys.SERVER_CONN_HOST;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Vehículo;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.http.RequestMan;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.preferences.SharedPreferences;

public class CarDAO {

	private Gson gson = new Gson();
	private HttpClient client = HttpClient.newHttpClient();
	private RequestMan requestMan = new RequestMan();
	private String SERVER_HOST = SharedPreferences.getPref(SERVER_CONN_HOST);
	private AdminApplication app;

	public CarDAO (AdminApplication app) {
		this.app = app;
	}

	public Vehículo getVehicle(String matricula) {

		Vehículo vehículo = null;

		HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/vehicles/" + matricula)
				.GET()
				.enforced(app.getToken())
				.build();
		try{

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			System.out.println(response.body());
			if(response.statusCode() != 200) {
				throw new Exception("Visitante no encontrado");
			}



			vehículo = gson.fromJson(response.body(), Vehículo.class);

		}catch(Exception e) {}

		return vehículo;

	}

	public String addVehicle(Vehículo vehículo) {

		String status = "NOT_OK";

		HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/vehicles")
				.POST(gson.toJson(vehículo))
				.enforced(app.getToken())
				.build();

		try {

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			if(response.statusCode() != 201) {
				throw new Exception(gson.toJson(vehículo));
			}

			status = "OK";

		}catch(Exception e) {
			status = e.getMessage();
		}

		return status;

	}

	public String editPlates(String vieja, String nueva){

			String status = "OK";

			HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/vehicles/" + vieja + "/" + nueva)
					.PUT("")
					.enforced(app.getToken())
					.build();

			try{

				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

				if(response.statusCode() != 200) {
					status = response.body();
				}

			}catch(Exception e) {
				status = e.getMessage();
			}


			return status;
	}

	public String deleteVehicle(Vehículo vehículo) {

		String status = "OK";

		HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/vehicles/" + vehículo.matricula)
				.DELETE()
				.enforced(app.getToken())
				.build();

		try{

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			if(response.statusCode() != 204) {
				status = response.body();
			}

		}catch(Exception e) {
			status = e.getMessage();
		}

		return status;
	}

	public List<Vehículo> getPageOfVehicles(int page){

		List<Vehículo> list = new ArrayList<Vehículo>();

		HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/vehicles/all")
								.GET()
								.enforced(app.getToken())
								.build();

		try{

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			System.out.println(response.statusCode());

			if(response.statusCode() != 200) {
				throw new Exception();
			}

			list = Arrays.asList(gson.fromJson(response.body().toString(), Vehículo[].class));

		}catch(Exception e) {}

		return list;

	}

	public List<Vehículo> getAllOwnersVehicles(int propietario_id){

			List<Vehículo> list = new ArrayList<Vehículo>();

			HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/vehicles/owner/" + propietario_id)
									.GET()
									.enforced(app.getToken())
									.build();

			try{

				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

				System.out.println(response.statusCode());
				System.out.println(response.body());

				if(response.statusCode() != 200) {
					throw new Exception();
				}

				list = Arrays.asList(gson.fromJson(response.body().toString(), Vehículo[].class));

			}catch(Exception e) {}

			return list;

		}

	}
