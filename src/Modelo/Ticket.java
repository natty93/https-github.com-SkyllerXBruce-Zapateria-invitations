package Modelo;

public class Ticket {
	// declaramos atributos de ticket
	private int folio = 0, codigoproducto = 0, vendidos=0;
	private String idvendedor, fecha;
	private double iva = 0, total = 0;

	// Constructor para asignar valores al ticket.
	public Ticket(int folio, String fecha, String idvendedor, int codigoproducto, double iva, double total, int vendidos) {
		this.folio = folio;
		this.fecha = fecha;
		this.idvendedor = idvendedor;
		this.codigoproducto = codigoproducto;
		this.iva = iva;
		this.total = total;
		this.vendidos=vendidos;
	}

	public int getVendidos() {
		return vendidos;
	}

	public void setVendidos(int vendidos) {
		this.vendidos = vendidos;
	}

	public int getFolio() {
		return folio;
	}

	public int getCodigoproducto() {
		return codigoproducto;
	}

	public String getIdvendedor() {
		return idvendedor;
	}

	public String getFecha() {
		return fecha;
	}

	public double getIva() {
		return iva;
	}

	public double getTotal() {
		return total;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public void setCodigoproducto(int codigoproducto) {
		this.codigoproducto = codigoproducto;
	}

	public void setIdvendedor(String idvendedor) {
		this.idvendedor = idvendedor;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}