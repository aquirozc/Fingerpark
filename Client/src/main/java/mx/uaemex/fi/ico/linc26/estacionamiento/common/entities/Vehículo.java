package mx.uaemex.fi.ico.linc26.estacionamiento.common.entities;

import com.google.gson.annotations.SerializedName;

public class Vehículo {
	public String matricula;
	@SerializedName("fabricante")
	public String marca;
	public String modelo;
	public int año;
	@SerializedName("huella_id")
	public int propietario_id;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public int getPropietario_id() {
		return propietario_id;
	}

	public void setPropietario_id(int propietario_id) {
		this.propietario_id = propietario_id;
	}

	@Override
    public String toString() {

    	return "ID del propietario: " + propietario_id
    			+ "\nMatricula: " + matricula
    			+ "\nMarca: " + marca
    			+ "\nModelo: " + modelo
    			+ "\nAño: " + año;
    }
}
