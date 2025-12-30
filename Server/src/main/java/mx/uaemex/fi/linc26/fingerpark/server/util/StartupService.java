package mx.uaemex.fi.linc26.fingerpark.server.util;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import mx.uaemex.fi.linc26.fingerpark.server.data.record.User;
import mx.uaemex.fi.linc26.fingerpark.server.data.repo.UserRepository;
import mx.uaemex.fi.linc26.fingerpark.server.security.PBKDF2;

@ApplicationScoped
@Startup
public class StartupService {

    @Inject
    UserRepository repo;

    @ConfigProperty(name = "fingerpark.admin.password", defaultValue = "admin")
    String password;

    @PostConstruct
    public void init() {
        createAdminIfMissing();
    }

    @Transactional
    public void createAdminIfMissing() {
        User admin = new User("admin", PBKDF2.encodePassword(password));
        repo.persist(admin);
    }
}
