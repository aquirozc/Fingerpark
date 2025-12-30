package mx.uaemex.fi.linc26.fingerpark.server.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VisitorGenericExchageObject {

    @NotNull
    @JsonProperty("huella_id")
    private long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @JsonProperty("nombre")
    private String firstName;

    @NotBlank(message = "El apellido no puede estar vacio")
    @JsonProperty("apellido")
    private String lastName;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Email(message = "El correo debe ser válido")
    @JsonProperty("correo")
    private String email;

}
