package mx.uaemex.fi.linc26.fingerpark.server.security;

import java.util.Set;
import org.eclipse.microprofile.jwt.Claims;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JWTIssuer {

	public String generateToken(String... roles) {
		return Jwt.issuer("mx.uaemex.fi")
				.groups(Set.of(roles))
				.subject("token")
				.claim(Claims.exp.name(), 2114477512l)
				.sign();
	}

}
