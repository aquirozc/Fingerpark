package mx.uaemex.fi.linc26.fingerpark.server.data.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Tariff {

    private String description;
    private double price;

}
