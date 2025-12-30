package mx.uaemex.fi.linc26.fingerpark.server.data.service;

import jakarta.enterprise.context.ApplicationScoped;
import mx.uaemex.fi.linc26.fingerpark.server.data.record.Visitor;
import mx.uaemex.fi.linc26.fingerpark.server.data.transfer.VisitorGenericExchageObject;

@ApplicationScoped
public class VisitorService {

    public Visitor createNewVisitorFromCreationRequest(VisitorGenericExchageObject request){
        return new Visitor(
            request.getId(),
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            null
        );
    }

    public VisitorGenericExchageObject createGenericExchangeObjectFromEntity(Visitor entity){
        return new VisitorGenericExchageObject(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getEmail()
        );
    }

}
