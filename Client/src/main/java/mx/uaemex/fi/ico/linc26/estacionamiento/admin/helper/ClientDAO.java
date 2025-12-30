package mx.uaemex.fi.ico.linc26.estacionamiento.admin.helper;

import static mx.uaemex.fi.ico.linc26.estacionamiento.common.preferences.PreferencesKeys.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mx.uaemex.fi.ico.linc26.estacionamiento.admin.fxapp.AdminApplication;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.Visitante;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.entities.VisitantePensionado;
import mx.uaemex.fi.ico.linc26.estacionamiento.common.http.RequestMan;

import mx.uaemex.fi.ico.linc26.estacionamiento.common.preferences.SharedPreferences;

public class ClientDAO {

	private Gson gson = new Gson();
	private HttpClient client = HttpClient.newHttpClient();
	private RequestMan requestMan = new RequestMan();
	private String SERVER_HOST = SharedPreferences.getPref(SERVER_CONN_HOST);
	private AdminApplication app;

	public ClientDAO(AdminApplication app) {
		this.app = app;
	}

	public VisitantePensionado getVisitor(int huella_id) {

		VisitantePensionado  visitante = null;

		HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/visitors/trusted/" + huella_id)
				.GET()
				.enforced(app.getToken())
				.build();
		try{

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			if(response.statusCode() != 200) {
				throw new Exception("Visitante no encontrado");
			}

			visitante = gson.fromJson(response.body(), VisitantePensionado.class);

		}catch(Exception e) {}

		return visitante;

	}

	public String addVisitor(VisitantePensionado visitante) {

		String status = "NOT_OK";

		HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/visitors/trusted")
				.POST(gson.toJson(visitante))
				.enforced(app.getToken())
				.build();

		try{

			System.out.println(gson.toJson(visitante));


			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			if(response.statusCode() != 201) {
			System.out.println(response.statusCode());
				throw new Exception(response.body());
			}

			status = "OK";

		}catch(Exception e) {
			status = e.getMessage();
		}

		return status;

	}


	public String editVisitor(VisitantePensionado visitante) {

		String status = "OK";

		HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/visitors/trusted")
				.PUT(gson.toJson(visitante))
				.enforced(app.getToken())
				.build();

		try{

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			if(response.statusCode() != 200) {
   System.out.println(response.statusCode());
				status = response.body();
			}

		}catch(Exception e) {
			status = e.getMessage();
		}


		return status;
	}

	public String deleteVisitor(VisitantePensionado visitante) {

		String status = "OK";

		HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/visitors/trusted/" + visitante.huella_id)
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
			e.printStackTrace();
		}


		return status;

	}

	public List<VisitantePensionado> getPageOfVisitors(int page){

		List<VisitantePensionado> list = new ArrayList<VisitantePensionado>();

		HttpRequest request = requestMan.open(SERVER_HOST + "/api/v1/admin/visitors/trusted/all")
								.GET()
								.enforced(app.getToken())
								.build();

		try{

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			if(response.statusCode() != 200) {
			    System.out.println(response.statusCode() + response.body());
				throw new Exception();
			}

			list = Arrays.asList(gson.fromJson(response.body().toString(), VisitantePensionado[].class));

		}catch(Exception e) {
		    e.printStackTrace();
		}

		return list;

	}

}
