package mx.uaemex.fi.ico.linc26.estacionamiento.common.entities;

import com.google.gson.annotations.SerializedName;

public class VisitantePensionado extends Visitante {
	public String nombre;
	public String apellido;
	@SerializedName("correo")
	public String email;
	public String tipo;

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
    	return tipo;
    }

    @Override
    public String toString() {

    	return "\nID: " + huella_id
    			+ "\nNombre: " + nombre
    			+ "\nApellidos: " + apellido
    			+ "\nCorreo electrónico: " + email + "\n";

    }
}
