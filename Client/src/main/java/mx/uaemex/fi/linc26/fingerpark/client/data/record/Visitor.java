package mx.uaemex.fi.linc26.fingerpark.client.data.record;

import com.google.gson.annotations.SerializedName;

public class Visitor{

    @SerializedName("huella_id")
    private long id;

    @SerializedName("nombre")
    private String firstName;

    @SerializedName("apellido")
    private String lastName;

    @SerializedName("correo")
    private String email;

    @SerializedName("tipo")
    private String type;

    public Visitor(long id, String firstName, String lastName, String email, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
       	return "\nID: " + id
         			+ "\nNombre: " + firstName
         			+ "\nApellidos: " + lastName
         			+ "\nCorreo electrónico: " + email + "\n";
    }

}
