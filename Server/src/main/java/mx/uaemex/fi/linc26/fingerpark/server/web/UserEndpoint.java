package mx.uaemex.fi.linc26.fingerpark.server.web;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import mx.uaemex.fi.linc26.fingerpark.server.data.record.User;
import mx.uaemex.fi.linc26.fingerpark.server.data.repo.UserRepository;
import mx.uaemex.fi.linc26.fingerpark.server.data.transfer.UserAuthenticationRequest;
import mx.uaemex.fi.linc26.fingerpark.server.security.JWTIssuer;
import mx.uaemex.fi.linc26.fingerpark.server.security.TrustedEntity;

@Path("/api/v1/admin/users")
@Transactional
public class UserEndpoint{

    @Inject
    private JWTIssuer issuer;

    @Inject
    private UserRepository repo;

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public Response login(UserAuthenticationRequest request){
        User user = repo.findByUsername(request.getUsername());

        if(user == null || !user.getPassword().equals(request.getPassword())){
            return Response.status(401).build();
        }

        return Response.ok(issuer.generateToken(TrustedEntity.EMPLEADO_ADMINISTRADOR.name())).build();
    }

}
