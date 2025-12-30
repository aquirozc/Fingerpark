package mx.uaemex.fi.linc26.fingerpark.server.data.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import mx.uaemex.fi.linc26.fingerpark.server.data.record.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{

    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }

}
