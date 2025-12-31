package mx.uaemex.fi.linc26.fingerpark.client.data.record;

import java.util.stream.Stream;

public enum TariffCatalog {

	VISITANTE_PENSIONADO ("Pensionado"),
	EMPLEADO("Empleado");

	public final String desc;
	public static final String[] options = Stream.of(TariffCatalog.values())
											.map(t -> t.desc)
											.toArray(String[]::new);

	TariffCatalog(String desc) {
		this.desc = desc;
	}

}
