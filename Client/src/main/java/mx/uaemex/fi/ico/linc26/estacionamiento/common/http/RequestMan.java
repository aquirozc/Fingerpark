package mx.uaemex.fi.ico.linc26.estacionamiento.common.http;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

public class RequestMan {
	
	HttpRequest.Builder builder;
	
	public RequestMan open(String target) {
		builder = HttpRequest.newBuilder(URI.create(target));
		builder.header("Content-Type", "application/json");
		return this;
	}
	
	public RequestMan enforced(String token) {
		builder.header("Authorization", "Bearer " +  token);
		return this;
	}
	
	public RequestMan GET() {
		builder.GET();
		return this;
	}
	
	public RequestMan DELETE() {
		builder.DELETE();
		return this;
	}
	
	public RequestMan PUT(String body) {
		builder.PUT(BodyPublishers.ofString(body));
		return this;
	}
	
	public RequestMan POST(String body) {
		builder.POST(BodyPublishers.ofString(body));
		return this;
	}
	
	public HttpRequest build() {
		return builder.build();
	}
	
	
}