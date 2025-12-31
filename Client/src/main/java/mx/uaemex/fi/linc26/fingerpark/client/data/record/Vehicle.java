package mx.uaemex.fi.linc26.fingerpark.client.data.record;

import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @SerializedName("matricula")
	private String plate;

	@SerializedName("fabricante")
	private String manufacturer;

	@SerializedName("modelo")
	private String model;

	@SerializedName("año")
	private int year;

	@SerializedName("huella_id")
	private int owner_id;

    public Vehicle(String plate, String manufacturer, String model, int year, int owner_id) {
        this.plate = plate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.owner_id = owner_id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

	@Override
    public String toString() {
    	return "\nID del propietario: " + owner_id
    			+ "\nMatricula: " + plate
    			+ "\nMarca: " + manufacturer
    			+ "\nModelo: " + model
    			+ "\nAño: " + year;
    }
}
