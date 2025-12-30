package mx.uaemex.fi.linc26.fingerpark.server.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VehicleGenericExchangeObject {

    @NotBlank
    @JsonProperty("matricula")
    private String plate;

    @NotBlank
    @JsonProperty("fabricante")
    private String manufacturer;

    @NotBlank
    @JsonProperty("modelo")
    private String model;

    @NotBlank
    @JsonProperty("año")
    private String year;

    @NotBlank
    @JsonProperty("huella_id")
    private long ownerId;

}
