package mx.uaemex.fi.linc26.fingerpark.server.data.record;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "VEHICULO")
public class Vehicle{

    @Id
    @GeneratedValue()
    private Long id;

    @Column(name = "MATRICULA",unique = true, nullable = false)
    private String plate;

    @Column(name = "FABRICANTE", nullable = false)
    private String manufacturer;

    @Column(name = "MODELO", nullable = false)
    private String model;

    @Column(name = "AÑO", nullable = false)
    private String year;

    @ManyToOne
    @JoinColumn(name = "VISITOR_FK", referencedColumnName = "HUELLA_ID")
    private Visitor owner;

}
