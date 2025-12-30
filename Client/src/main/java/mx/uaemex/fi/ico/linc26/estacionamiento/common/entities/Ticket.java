package mx.uaemex.fi.ico.linc26.estacionamiento.common.entities;

import java.io.File;
import java.io.FileWriter;
import java.sql.Date;

public class Ticket {

	private int folio = -1;
	private String content = "Elimine este archivo";
	
	public Ticket(int folio) {
		this.folio = folio;
	}
	
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public String getContent() {
		return content;
	}
	
	public void generarTicketusuario(double tarifa, double dineroIngresado) {
		content = 
				"--------------------------------------------------------------------------\n" +
	            "\t\t Plaza HorizonTowers                          \n" +
	            "--------------------------------------------------------------------------\n\n" +
	        	"Folio: " + folio + "\n" +
	    		"Fecha de emisión: " + new Date(System.currentTimeMillis())+ "\n\n" +
	            "--------------------------------------------------------------------------\n\n" +
	            "Tarifa : " + tarifa + "\n" +
	    		"Dinero ingresado: " + dineroIngresado + "\n" +
	    		"Cambio: " + Math.max(0, dineroIngresado-tarifa) + "\n\n" +
	            "--------------------------------------------------------------------------\n\n" +
	            "\t\t          Gracias!\n" +
	            "\t     ¡Vuelva pronto a HorizonTowers!\n" +
	            "--------------------------------------------------------------------------";
	}
	
	public void generarTicketTXT() {
		
        try (FileWriter fw = new FileWriter(new File("Ticket_no" + System.currentTimeMillis() + ".txt"))){
			fw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
	
}