package mx.uaemex.fi.linc26.fingerpark.server.data.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mx.uaemex.fi.linc26.fingerpark.server.data.record.Visitor;

@ApplicationScoped
public class VisitorRepository implements PanacheRepository<Visitor> {

    public Long findAvailableSlot(){
        return getEntityManager()
                .createQuery("select max(v.id) from Visitor v", Long.class)
                .getSingleResult();
    }

}
