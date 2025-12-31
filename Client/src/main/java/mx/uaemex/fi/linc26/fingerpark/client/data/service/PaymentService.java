package mx.uaemex.fi.linc26.fingerpark.client.data.service;

import static mx.uaemex.fi.linc26.fingerpark.client.prefs.PreferenceKey.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import mx.uaemex.fi.linc26.fingerpark.client.data.record.Ticket;
import mx.uaemex.fi.linc26.fingerpark.client.http.RequestMan;
import mx.uaemex.fi.linc26.fingerpark.client.prefs.SharedPreferences;

public class PaymentService {

    private Gson gson = new Gson();
	private HttpClient client = HttpClient.newHttpClient();
	private RequestMan helper = new RequestMan();

	private final String SERVER_HOST = SharedPreferences.getPref(SERVER_CONN_HOST);
	private final String APP_TOKEN = SharedPreferences.getPref(APP_CONN_TOKEN);

    public String getTicketReference() throws IOException, InterruptedException, IllegalStateException{
        HttpRequest request = helper.open(SERVER_HOST + "/api/v1/payment/ticket/reference")
                   				.enforced(APP_TOKEN)
                   				.GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		if(response.statusCode() != 200) throw new IllegalStateException();

		return response.body();
	}

	public void saveTicket(Ticket ticket) throws IOException, InterruptedException, IllegalStateException{
        HttpRequest request = helper.open(SERVER_HOST + "/api/v1/payment/ticket")
                   				.enforced(APP_TOKEN)
                   				.POST(gson.toJson(ticket))
                                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        if(response.statusCode() != 201) throw new IllegalStateException();
    }


}
