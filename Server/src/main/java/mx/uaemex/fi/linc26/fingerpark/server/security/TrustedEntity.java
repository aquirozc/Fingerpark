package mx.uaemex.fi.linc26.fingerpark.server.security;

public enum TrustedEntity{
    EMPLEADO_ADMINISTRADOR("Empleado con privilegios administrativos"),
	PUESTO_DE_ACCESO("Módulo de ingreso a las instalaciones");

    public final String description;

    TrustedEntity(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
