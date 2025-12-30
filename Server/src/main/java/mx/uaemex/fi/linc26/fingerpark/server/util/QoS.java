package mx.uaemex.fi.linc26.fingerpark.server.util;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import mx.uaemex.fi.linc26.fingerpark.server.security.JWTIssuer;

@RequestScoped
@Path("/api/v1/status")
public class QoS{

    @Inject
    JWTIssuer issuer;

    @GET
    public String getStatus(){
        return "Server is OK";
    }

    @GET
    @Path("/refresh-token")
    public String getSampleToken(){
        return issuer.generateToken("SAMPLE_ROLE");
    }

    @GET
    @Path("/auth-test")
    @RolesAllowed("SAMPLE_ROLE")
    public String getGreeting(){
        return "Acceso Granted";
    }

}
