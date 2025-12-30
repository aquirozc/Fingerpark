package mx.uaemex.fi.linc26.fingerpark.server.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import mx.uaemex.fi.linc26.fingerpark.server.security.PBKDF2;

@Getter
public class UserAuthenticationRequest {

    @NotBlank(message = "El nombre no puede estar vacío")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacia")
    private String password;

    @JsonProperty("password")
    public void setPassword(@NotBlank String password) {
        if(password == null || password.trim().isBlank()){
            throw new IllegalArgumentException("La contraseña no puede estar vacia");
        }
        this.password = PBKDF2.encodePassword(password);
    }

}
