package mx.uaemex.fi.ico.linc26.estacionamiento.common.entities;

public class User {

	public String username;
	public String password;

	public User(String usuario, String contraseña) {
		this.username = usuario;
		this.password = contraseña;
	}
}
