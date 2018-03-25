package Negocio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Modelo.Ticket;
import Modelo.Usuario;
import Persistencia.DAOTicket;

public class ServicioTicket {

	@SuppressWarnings("unused")
	private ControlVenta control;
	private DAOTicket daoticket = new DAOTicket();

	// Instanciamos el control de nuestro servicio.
	public void setControl(ControlVenta control) {
		this.control = control;
	}

	public boolean agregaTicket(Ticket ticket) {
		return daoticket.agregaTicket(ticket);
	}

	public Ticket dameTicket(int folio) {
		return daoticket.buscaTicket(folio);
	}

	public boolean modificaTicket(int folio, String fecha, String idvendedor, int codigo, double total, double iva) {
		return daoticket.modificaTicket(folio, fecha, idvendedor, codigo, total, iva);
	}

	public Ticket[] obtenTodosTickets() {
		return daoticket.dameTickets();
	}

	public int dameCantidadTickets() {
		return daoticket.cantidadTickets();
	}

	public int dameFolio() {
		return daoticket.cantidadTickets() + 1;
	}

	public double obtenerComicionVendedor(Usuario user) {
		double comicion = 0;
		for (Ticket t : daoticket.dameTickets())
			if (user.getId().equals(t.getIdvendedor()))
				comicion += t.getTotal() * 0.05;
		return comicion;
	}
	
	public int obtenerCantidadVentasVendedor(Usuario user) {
		int vendidos = 0;
		for (Ticket t : daoticket.dameTickets())
			if (user.getId().equals(t.getIdvendedor()))
				vendidos += t.getVendidos();
		return vendidos;
	}
	
	public double obtenerGananciasVendedor(Usuario user) {
		double ganancias = 0;
		for (Ticket t : daoticket.dameTickets())
			if (user.getId().equals(t.getIdvendedor()))
				ganancias += t.getTotal();
		return ganancias;
	}
	
	// Este método nos permite obtener la fecha, para pasarla al ticket impreso.
	public String getFechaActual() {
		Date ahora = new Date();
		DateFormat formatoHora = new SimpleDateFormat("HH:mm");
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		return formateador.format(ahora) + ", " + formatoHora.format(ahora);
	}
}
