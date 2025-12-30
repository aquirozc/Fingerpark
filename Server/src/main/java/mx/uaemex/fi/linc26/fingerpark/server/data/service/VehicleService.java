package mx.uaemex.fi.linc26.fingerpark.server.data.service;

import jakarta.enterprise.context.ApplicationScoped;
import mx.uaemex.fi.linc26.fingerpark.server.data.record.Vehicle;
import mx.uaemex.fi.linc26.fingerpark.server.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.server.data.transfer.VehicleGenericExchangeObject;

@ApplicationScoped
public class VehicleService {

    public VehicleGenericExchangeObject createGenericExchangeObjectFromEntity(Vehicle entity){
        return new VehicleGenericExchangeObject(
            entity.getPlate(),
            entity.getManufacturer(),
            entity.getModel(),
            entity.getYear(),
            entity.getOwner().getId()
        );
    }

    public Vehicle createNewVehicleFromCreationRequest(VehicleGenericExchangeObject request, Visitor owner){
        return new Vehicle(
            null,
            request.getPlate(),
            request.getManufacturer(),
            request.getModel(),
            request.getYear(),
            owner
        );
    }

}
