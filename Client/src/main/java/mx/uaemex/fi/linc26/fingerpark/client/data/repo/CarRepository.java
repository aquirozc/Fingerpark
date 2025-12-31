package mx.uaemex.fi.linc26.fingerpark.client.data.repo;

import static mx.uaemex.fi.linc26.fingerpark.client.prefs.PreferenceKey.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;

import mx.uaemex.fi.linc26.fingerpark.client.data.record.Vehicle;
import mx.uaemex.fi.linc26.fingerpark.client.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.client.http.RequestMan;
import mx.uaemex.fi.linc26.fingerpark.client.prefs.SharedPreferences;

public class CarRepository {

	private Gson gson = new Gson();
	private HttpClient client = HttpClient.newHttpClient();
	private RequestMan helper = new RequestMan();

	private final String SERVER_HOST = SharedPreferences.getPref(SERVER_CONN_HOST);
	private final String APP_TOKEN = SharedPreferences.getPref(APP_CONN_TOKEN);;

	public Vehicle getVehicle(String plate) throws IOException, InterruptedException, IllegalStateException{
		HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/vehicles/" + plate)
                				.enforced(APP_TOKEN)
                				.GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 200) throw new IllegalStateException();

		return gson.fromJson(response.body(), Vehicle.class);
	}

	public void addVehicle(Vehicle vehicle) throws IOException, InterruptedException, IllegalStateException{
		HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/vehicles")
                				.enforced(APP_TOKEN)
                                .POST(gson.toJson(vehicle))
                				.build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 201) throw new IllegalStateException();
	}

	public void editPlates(String current, String next) throws IOException, InterruptedException, IllegalStateException{
		HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/vehicles/" + current + "/" + next)
               					.enforced(APP_TOKEN)
               					.PUT("").build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 200) throw new IllegalStateException();
	}

	public void deleteVehicle(Vehicle vehicle) throws IOException, InterruptedException, IllegalStateException{
        HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/vehicles/" + vehicle.getPlate())
                				.enforced(APP_TOKEN)
                				.DELETE().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 204) throw new IllegalStateException();
	}

	public List<Vehicle> getPageOfVehicles(int page) throws IOException, InterruptedException, IllegalStateException{
	    HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/vehicles/all")
								.enforced(APP_TOKEN)
								.GET().build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 200) throw new IllegalStateException();

		return Arrays.asList(gson.fromJson(response.body().toString(), Vehicle[].class));
	}

	public List<Vehicle> getAllOwnersVehicles(int owner_id) throws IOException, InterruptedException, IllegalStateException{
		HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/vehicles/owner/" + owner_id)
								.enforced(APP_TOKEN)
								.GET().build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 200) throw new IllegalStateException();

		return Arrays.asList(gson.fromJson(response.body().toString(), Vehicle[].class));
	}

	public Visitor getVehicleOwner(String plate)throws IOException, InterruptedException, IllegalStateException{
    	HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/vehicles/" +plate + "/owner/" )
    							.enforced(APP_TOKEN)
    							.GET().build();

    	HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    	if(response.statusCode() != 200) throw new IllegalStateException();

    	return gson.fromJson(response.body(), Visitor.class);
	}

}
