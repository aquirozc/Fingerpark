package mx.uaemex.fi.linc26.fingerpark.server.data.record;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "RECIBO")
public class Receipt{

    @Id
    @Column(name = "FOLIO")
    private String reference;

    @Column(name = "CONTENIDO", nullable = false)
    private String content;

}
