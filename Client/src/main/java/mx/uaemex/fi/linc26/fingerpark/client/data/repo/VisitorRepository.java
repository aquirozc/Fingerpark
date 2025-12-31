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

import mx.uaemex.fi.linc26.fingerpark.client.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.client.http.RequestMan;
import mx.uaemex.fi.linc26.fingerpark.client.prefs.SharedPreferences;

public class VisitorRepository {

	private Gson gson = new Gson();
	private HttpClient client = HttpClient.newHttpClient();
	private RequestMan helper = new RequestMan();

	private final String SERVER_HOST = SharedPreferences.getPref(SERVER_CONN_HOST);
	private final String APP_TOKEN = SharedPreferences.getPref(APP_CONN_TOKEN);

	public Visitor getVisitor(int id) throws IOException, InterruptedException, IllegalStateException{
		HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/visitors/trusted/" + id)
			                    .enforced(APP_TOKEN)
								.GET().build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 200) throw new IllegalStateException();

		return gson.fromJson(response.body(), Visitor.class);
	}

	public void addVisitor(Visitor visitor) throws IOException, InterruptedException, IllegalStateException{
		HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/visitors/trusted")
                				.enforced(APP_TOKEN)
                                .POST(gson.toJson(visitor))
                				.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 201) throw new IllegalStateException();
	}

	public double getVisitorTariff(Visitor visitor) throws IOException, InterruptedException, IllegalStateException{
        HttpRequest request = helper.open(SERVER_HOST + "/api/v1/payment/fee/" + visitor.getType())
                				.enforced(APP_TOKEN)
                                .GET().build();

       	HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
       	if(response.statusCode() != 200) throw new IllegalStateException();

        return Double.parseDouble(response.body());
	}

	public void editVisitor(Visitor visitor) throws IOException, InterruptedException, IllegalStateException {
		HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/visitors/trusted")
                				.enforced(APP_TOKEN)
                                .PUT(gson.toJson(visitor))
                				.build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 200) throw new IllegalStateException();
	}

	public void deleteVisitor(Visitor visitor) throws IOException, InterruptedException, IllegalStateException{
		HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/visitors/trusted/" + visitor.getId())
                				.enforced(APP_TOKEN)
                				.DELETE().build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 204) throw new IllegalStateException();
	}

	public List<Visitor> getPageOfVisitors(int page) throws IOException, InterruptedException, IllegalStateException{
		HttpRequest request = helper.open(SERVER_HOST + "/api/v1/admin/visitors/trusted/all")
								.enforced(APP_TOKEN)
								.GET().build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 200) throw new IllegalStateException();

		return Arrays.asList(gson.fromJson(response.body().toString(), Visitor[].class));
	}

}
