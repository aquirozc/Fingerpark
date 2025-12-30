package mx.uaemex.fi.linc26.fingerpark.server.web;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import mx.uaemex.fi.linc26.fingerpark.server.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.server.data.repo.VisitorRepository;
import mx.uaemex.fi.linc26.fingerpark.server.data.service.VisitorService;
import mx.uaemex.fi.linc26.fingerpark.server.data.transfer.VisitorGenericExchageObject;

@Path("/api/v1/admin/visitors/trusted")
@Transactional
public class VisitorEndpoint {

    @Inject VisitorRepository repo;

    @Inject VisitorService service;

    @GET
    @Path("/id")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getNextAvailableId(){
        Long result = repo.findAvailableSlot();
        return Response.ok(result == null ? 0 : result.longValue()+1).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewTrustedVisitor(VisitorGenericExchageObject request, @Context UriInfo info){
        repo.persist(service.createNewVisitorFromCreationRequest(request));
        return Response.created(info.getAbsolutePathBuilder().path("{id}").build(request.getId())).entity(request).build();
    }

    @GET
	@Path("/{huella_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrustedVisitor(@PathParam("huella_id") long id) {
        Visitor visitor = repo.findById(id);
        return visitor == null ? Response.status(404).build() : Response.ok(service.createGenericExchangeObjectFromEntity(visitor)).build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTrustedVisitors() {
        return Response.ok(repo.findAll().stream().map(service::createGenericExchangeObjectFromEntity).toArray()).build();
	}

	@DELETE
	@Path("/{huella_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTrustedVisitor(@PathParam("huella_id") long id) {
        return repo.deleteById(id) ? Response.noContent().build() : Response.status(404).build() ;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTrustedVisitor(VisitorGenericExchageObject request) {
        Visitor visitor = repo.findById(request.getId());

        if(visitor == null){
            return Response.status(404).build();
        }

        visitor.setFirstName(request.getFirstName());
        visitor.setLastName(request.getLastName());
        visitor.setEmail(request.getEmail());

        return Response.ok(service.createGenericExchangeObjectFromEntity(visitor)).build();
	}

}
