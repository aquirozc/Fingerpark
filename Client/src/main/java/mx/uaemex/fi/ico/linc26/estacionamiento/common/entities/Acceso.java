package mx.uaemex.fi.ico.linc26.estacionamiento.common.entities;

import java.sql.Date;

public class Acceso {
	
	public Date horaDeLLegada;
	public Date horaDeSalida;
	public double tarifa;
	public int visitante_huella;
	public double costo;
	
	
	public Date getHoraDeLLegada() {
		return horaDeLLegada;
	}
	public void setHoraDeLLegada(Date horaDeLLegada) {
		this.horaDeLLegada = horaDeLLegada;
	}
	public Date getHoraDeSalida() {
		return horaDeSalida;
	}
	public void setHoraDeSalida(Date horaDeSalida) {
		this.horaDeSalida = horaDeSalida;
	}
	public double getTarifa() {
		return tarifa;
	}
	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}
	public int getVisitante_huella() {
		return visitante_huella;
	}
	public void setVisitante_huella(int visitante_huella) {
		this.visitante_huella = visitante_huella;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
}
