package mx.uaemex.fi.linc26.fingerpark.client.data.record;

import java.io.File;
import java.io.FileWriter;
import java.sql.Date;

public class Ticket {

	private String reference;
	private String content;

	public Ticket(String reference) {
		this.reference = reference;
	}

	public String getFolio() {
		return reference;
	}
	public void setFolio(String reference) {
		this.reference = reference;
	}
	public String getContent() {
		return content;
	}

	public void updateContent(double tariff, double money) {
		content =
				"--------------------------------------------------------------------------\n" +
	            "\t\t Plaza HorizonTowers                          \n" +
	            "--------------------------------------------------------------------------\n\n" +
	        	"Folio: " + reference + "\n" +
	    		"Fecha de emisión: " + new Date(System.currentTimeMillis())+ "\n\n" +
	            "--------------------------------------------------------------------------\n\n" +
	            "Tarifa : " + tariff + "\n" +
	    		"Dinero ingresado: " + money+ "\n" +
	    		"Cambio: " + Math.max(0, tariff -money) + "\n\n" +
	            "--------------------------------------------------------------------------\n\n" +
	            "\t\t          Gracias!\n" +
	            "\t     ¡Vuelva pronto a HorizonTowers!\n" +
	            "--------------------------------------------------------------------------";
	}

	public static void saveTicketToFileSystem(Ticket ticket) {

        try (FileWriter fw = new FileWriter(new File("Ticket_no" + System.currentTimeMillis() + ".txt"))){
			fw.write(ticket.content);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

}
