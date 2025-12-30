package mx.uaemex.fi.linc26.fingerpark.server.data.record;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "VISITANTE")
public class Visitor{

    @Id
    @Column(name = "HUELLA_ID")
    private long id;

    @Column(name = "NOMBRE", nullable = false)
    private String firstName;

    @Column(name = "APELLIDO", nullable = false)
    private String lastName;

    @Column(name = "CORREO", nullable = false)
    private String email;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles;

}
