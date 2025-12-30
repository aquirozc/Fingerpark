package mx.uaemex.fi.ico.linc26.estacionamiento.common.entities;

import java.util.stream.Stream;

public enum Tarifas {
	
	VISITANTE_PENSIONADO ("Pensionado"),
	EMPLEADO("Empleado");
	
	public final String desc;
	public static final String[] options = Stream.of(Tarifas.values())
											.map(t -> t.desc)
											.toArray(String[]::new);
	
	Tarifas(String desc) {
		this.desc = desc;
	}

}
