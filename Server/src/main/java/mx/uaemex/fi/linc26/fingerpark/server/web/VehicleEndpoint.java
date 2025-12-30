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

import mx.uaemex.fi.linc26.fingerpark.server.data.record.Vehicle;
import mx.uaemex.fi.linc26.fingerpark.server.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.server.data.repo.VehicleRepository;
import mx.uaemex.fi.linc26.fingerpark.server.data.repo.VisitorRepository;
import mx.uaemex.fi.linc26.fingerpark.server.data.service.VehicleService;
import mx.uaemex.fi.linc26.fingerpark.server.data.transfer.VehicleGenericExchangeObject;

@Path("/api/v1/admin/vehicles")
@Transactional
public class VehicleEndpoint {

    @Inject VisitorRepository catalog;
    @Inject VehicleRepository repo;
    @Inject VehicleService service;

    @GET
	@Path("/owner/{huella_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRegisteredVehiclesFromOwner(@PathParam("huella_id") long id) {
	    Visitor owner = catalog.findById(id);

		if(owner == null){
            return Response.status(404).build();
        }

        return Response.ok(owner.getVehicles().stream().map(service::createGenericExchangeObjectFromEntity).toArray()).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addNewRegisteredVehicle(VehicleGenericExchangeObject request, @Context UriInfo info) {
    	Visitor owner = catalog.findById(request.getOwnerId());

        if(owner == null){
            return Response.status(401).build();
        }

        repo.persist(service.createNewVehicleFromCreationRequest(request, owner));
        return Response.created(info.getAbsolutePathBuilder().path("{id}").build(request.getPlate())).entity(request).build();
	}

	@GET
	@Path("/{matricula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRegisteredVehicle(@PathParam("matricula") String plate) {
        Vehicle vehicle = repo.findByPlate(plate);
        return vehicle == null ? Response.status(404).build() : Response.ok(service.createGenericExchangeObjectFromEntity(vehicle)).build();
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRegisteredVehicles() {
	    return Response.ok(repo.findAll().stream().map(service::createGenericExchangeObjectFromEntity).toArray()).build();
	}

	@DELETE
	@Path("/{matricula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRegisteredVehicle(@PathParam("matricula") String plate) {
        return repo.delete("plate", plate) > 0 ? Response.noContent().build() : Response.status(404).build() ;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{vieja}/{nueva}")
	public Response editVehicle(@PathParam("vieja") String current, @PathParam("nueva") String next) {
        Vehicle vehicle = repo.findByPlate(current);

        if(vehicle == null){
            return Response.status(404).build();
        }

        vehicle.setPlate(next);

         return Response.ok(service.createGenericExchangeObjectFromEntity(vehicle)).build();
	}

}
