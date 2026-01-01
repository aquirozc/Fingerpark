package mx.uaemex.fi.linc26.fingerpark.server.web;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import mx.uaemex.fi.linc26.fingerpark.server.data.record.Tariff;

@Path("/api/v1/payment")
public class PaymentEndpoint {

    @GET
    @Path("/fees/latest")
    public Response getLatestFees(){
        return Response.ok(new Tariff[]{
            new Tariff("Empleado", 0),
            new Tariff("Visitante", 12)
        }).build();
    }

    @GET
    @Path("/fee/{type}")
    public Response getFee(@PathParam("type") String type){
        return Response.ok(240).build();
    }

    @GET
    @Path("/ticket/reference")
    public Response getTicketReference(){
        return Response.status(200).entity("101010").build();
    }

    @POST
    @Path("/ticket")
    public Response saveTicket(){
        return Response.status(201).build();
    }


}
