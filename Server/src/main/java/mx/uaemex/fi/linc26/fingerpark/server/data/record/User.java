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
@Table(name = "USUARIO")
public class User{

    @Id
    @Column(name = "IDENTIFICADOR")
    private String username;

    @Column(name = "CONTRASEÑA", nullable = false)
    private String password;

}
