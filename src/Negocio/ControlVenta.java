package Negocio;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JOptionPane;
import Modelo.Producto;
import Modelo.Ticket;
import Modelo.Usuario;
import Presentacion.VistaAdministarVendedores;
import Presentacion.VistaCambioCalzado;
import Presentacion.VistaLogin;
import Presentacion.VistaTicket;
import Presentacion.VistaVendedor;
import Presentacion.VistaVentaCalzado;

public class ControlVenta implements Printable {
	// Instanciamos nuestros atributos
	private VistaCambioCalzado vistacambio;
	private VistaVentaCalzado vistaventacalzado;
	private VistaTicket vistaticket;
	private VistaLogin vistalogin;
	private VistaVendedor vistavendedor;
	private VistaAdministarVendedores vistaadminvendedores;
	private ServicioVenta servicioventa;
	private ServicioTicket servicioticket;
	private ServicioAlmacen servicioalmacen;
	private ServicioLogin serviciologin;
	private Usuario user;

	// Agregamos la Vista Cambio de Calzado
	public void setVistaCambioCalzado(VistaCambioCalzado vistacambio) {
		this.vistacambio = vistacambio;
	}

	// Agregamos la Vista Venta de Calzado
	public void setVistaVentaCalzado(VistaVentaCalzado vistaventacalzado) {
		this.vistaventacalzado = vistaventacalzado;
	}

	// Agregamos el Servicio de Venta
	public void setServicioVenta(ServicioVenta servicioventa) {
		this.servicioventa = servicioventa;
	}

	//
	public void setServicioTicket(ServicioTicket servicioticket) {
		this.servicioticket = servicioticket;
	}

	public void setServicioAlmacen(ServicioAlmacen servicioalmacen) {
		this.servicioalmacen = servicioalmacen;
	}

	public void setServicioLogin(ServicioLogin serviciologin) {
		this.serviciologin = serviciologin;
	}

	//
	public void setVistaLogin(VistaLogin vistalogin) {
		this.vistalogin = vistalogin;
	}

	//
	public void setVistaVendedor(VistaVendedor vistavendedor) {
		this.vistavendedor = vistavendedor;
	}

	public void setVistaAdministarVendedores(VistaAdministarVendedores vistaadminvendedores) {
		this.vistaadminvendedores = vistaadminvendedores;
	}

	// Mostramos Ventana de Venta de Calzado
	public void muestraVistaAdministarVendedores() {
		vistaadminvendedores.setVisible(true);
	}

	public void muestraVentadeCalzado() {
		vistaventacalzado.setVisible(true);
	}

	// Mostramos Ventana de Cambio de Calzado
	public void muestraCambioCalzado() {
		vistacambio.setVisible(true);
	}

	//
	public void muestraVistaLogin() {
		vistalogin.setVisible(true);
	}

	//
	public void muestraVistaVendedor() {
		vistavendedor.setVisible(true);
	}

	// Agregamos Vista ticket
	public void setVistaTicket(VistaTicket vistaticket) {
		this.vistaticket = vistaticket;
	}

	// Este método nos permite agregar los datos que nos regresa el DAO a la tabla
	// de consulta.
	public void buscaProducto(int id) {
		Object[] fila = new Object[vistaventacalzado.getTablaModelo().getColumnCount()];
		Producto producto = servicioventa.buscaProducto(id);
		if (producto != null) {
			fila[0] = producto.dameModelo();
			fila[1] = producto.dameTipo();
			fila[2] = producto.dameColor();
			fila[3] = producto.dameTalla();
			fila[4] = producto.dameCosto();
			fila[5] = producto.dameCantidad();
			vistaventacalzado.getTablaModelo().addRow(fila);
		} else
			JOptionPane.showMessageDialog(null, "No se encontraron productos");
	}

	// Este método nos permite agregar los datos que nos regresa el DAO a la tabla
	// de consulta.
	public void buscaTicket(int folio) {
		double total = 0;
		Object[] filaventa = new Object[vistacambio.getTablaModeloVenta().getColumnCount()];
		Object[] filacambio = new Object[vistacambio.getTablaModeloCambio().getColumnCount()];
		if (servicioticket.dameCantidadTickets() != 0) {
			Ticket ticket = servicioticket.dameTicket(folio);
			if (ticket != null && ticket.getCodigoproducto() != 0) {
				Producto producto = servicioalmacen.buscaProducto(ticket.getCodigoproducto());
				filaventa[0] = producto.dameModelo();
				filaventa[1] = producto.dameTipo();
				filaventa[2] = producto.dameColor();
				filaventa[3] = producto.dameTalla();
				filaventa[4] = producto.dameCosto();
				filaventa[5] = producto.dameCosto() * 1.16;
				vistacambio.getTablaModeloVenta().addRow(filaventa);
				for (Producto p : servicioalmacen.dameProductos()) {
					total = p.dameCosto() * 1.16;
					if (total >= (double) filaventa[5] && p.dameCantidad() > 0) {
						filacambio[0] = p.dameModelo();
						filacambio[1] = p.dameTipo();
						filacambio[2] = p.dameColor();
						filacambio[3] = p.dameTalla();
						filacambio[4] = p.dameCosto();
						filacambio[5] = total;
						filacambio[6] = p.dameCantidad();
						filacambio[7] = false;
						vistacambio.getTablaModeloCambio().addRow(filacambio);
					}
				}
			} else
				JOptionPane.showMessageDialog(null, "No se Encontraron los Datos del Folio");
		} else
			JOptionPane.showMessageDialog(null, "No Existen Tickets Actualmente");
	}

	// Este método calcula el total sobre las unidades que se van a vender y su
	// precio unitario.
	public void calculaTotal() {
		if (vistaventacalzado.getTablaModelo().getValueAt(0, 6) == null)
			JOptionPane.showMessageDialog(null, "Agregue pares a vender y presione enter");
		else {
			double costo = (double) vistaventacalzado.getTablaModelo().getValueAt(0, 4);
			int cantidadproductos = (int) (vistaventacalzado.getTablaModelo().getValueAt(0, 5));
			int cantidadventa = Integer.parseInt((String) vistaventacalzado.getTablaModelo().getValueAt(0, 6));
			if (cantidadventa > cantidadproductos)
				JOptionPane.showMessageDialog(null, "Cantidad insuficiente de productos en almacén");
			else {
				double iva = (costo * 0.16);
				double montototal = (costo * cantidadventa) + iva;
				vistaventacalzado.setMontounitario(String.valueOf(costo));
				vistaventacalzado.setIva(String.format("%.2f", iva));
				vistaventacalzado.setMontototal(String.format("%.2f", montototal));
			}
		}
	}

	public void limpiarDatos(String tipo) {
		if (vistaventacalzado.getTablaModelo().getRowCount() != 0 && tipo.equals("Venta")) {
			vistaventacalzado.setIdProducto("");
			vistaventacalzado.setMontounitario("       -");
			vistaventacalzado.setIva("       -");
			vistaventacalzado.setMontototal("       -");
			vistaventacalzado.getTablaModelo().removeRow(0);
		} else if (vistacambio.getTablaModeloVenta().getRowCount() != 0
				|| vistacambio.getTablaModeloCambio().getRowCount() != 0 && tipo.equals("Cambio")) {
			vistacambio.getTablaModeloVenta().removeRow(0);
			vistacambio.setFolioventa("");
			for (int i = vistacambio.getTablaModeloCambio().getRowCount() - 1; i >= 0; i--)
				vistacambio.getTablaModeloCambio().removeRow(i);
		}
	}

	// Creamos el ticket de venta
	public void creaTicketVenta() {
		vistaticket.setFolio(String.valueOf(servicioticket.dameFolio()));
		vistaticket.setFecha(servicioticket.getFechaActual());
		vistaticket.setModelo((String) vistaventacalzado.getTablaModelo().getValueAt(0, 0));
		vistaticket.setTipo((String) vistaventacalzado.getTablaModelo().getValueAt(0, 1));
		vistaticket.setColor((String) vistaventacalzado.getTablaModelo().getValueAt(0, 2));
		vistaticket.setTalla(String.valueOf(vistaventacalzado.getTablaModelo().getValueAt(0, 3)));
		vistaticket.setCantidad((String) vistaventacalzado.getTablaModelo().getValueAt(0, 6));
		vistaticket.setIva(vistaventacalzado.getIva());
		vistaticket.setPrecioUnitario(String.valueOf(vistaventacalzado.getTablaModelo().getValueAt(0, 4)));
		vistaticket.setTotal(vistaventacalzado.getTotal());
		vistaticket.setVisible(true);
	}

	// Creamos el ticket de Cambio
	public void creaTicketCambio(int folio, Producto producto, double iva, double total, double diferencia,
			double tventa) {
		vistaticket.setFolio(String.valueOf(folio));
		vistaticket.setFecha(servicioticket.getFechaActual());
		vistaticket.setModelo(producto.dameModelo());
		vistaticket.setTipo(producto.dameTipo());
		vistaticket.setColor(producto.dameColor());
		vistaticket.setTalla(String.valueOf(producto.dameTalla()));
		vistaticket.setCantidad(String.valueOf(1));
		vistaticket.setIva(String.format("%.2f", iva));
		vistaticket.setPrecioUnitario(String.format("%.2f", producto.dameCosto()));
		vistaticket.setTotal(String.format("%.2f", diferencia));
		vistaticket.setTotalAnterior(String.format("%.2f", tventa));
		vistaticket.setVisible(true);
	}

	public boolean cambioProducto(int folio) {
		int fila = -1;
		String modelocambio, tipocambio;
		double total = 0, iva = 0, diferencia = 0, tventa = 0;
		boolean seleccion;
		for (int i = 0; i < vistacambio.getTablaModeloCambio().getRowCount(); i++) {
			seleccion = (boolean) vistacambio.getTablaModeloCambio().getValueAt(i, 7);
			if (seleccion)
				fila = i;
		}
		if (fila >= 0) {
			modelocambio = (String) vistacambio.getTablaModeloCambio().getValueAt(fila, 0);
			tipocambio = (String) vistacambio.getTablaModeloCambio().getValueAt(fila, 1);
			Producto productocambio = servicioalmacen.buscaProducto(modelocambio, tipocambio);
			Producto productoventa = servicioalmacen.buscaProducto(folio);
			Usuario user = getVendedor();
			total = (double) vistacambio.getTablaModeloCambio().getValueAt(fila, 5);
			iva = productocambio.dameCosto() * 0.16;

			if (JOptionPane.showConfirmDialog(null, "¿Realmente Desea Realizar el Cambio?", "¿Cambio?",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				productocambio.setCantidad(productocambio.dameCantidad() - 1);
				if (servicioticket.modificaTicket(folio, servicioticket.getFechaActual(), user.getId(),
						productocambio.dameCodigo(), total, iva)) {
					productoventa.setCantidad(productoventa.dameCantidad() + 1);
					tventa = (double) vistacambio.getTablaModeloVenta().getValueAt(0, 5);
					diferencia = (productocambio.dameCosto() * 1.16) - tventa;
					creaTicketCambio(folio, productocambio, iva, total, diferencia, tventa);
					return true;
				} else {
					productocambio.setCantidad(productocambio.dameCantidad() + 1);
					JOptionPane.showMessageDialog(null, "No Se Pudo Realizar el Cambio");
				}
			}
		} else
			JOptionPane.showMessageDialog(null, "Seleccione un Producto para Realizar Cambio");
		return false;
	}

	public void almacenarDatosTicket(String[] datosTicket) {
		Producto producto = servicioalmacen.buscaProducto(datosTicket[2], datosTicket[3]);
		Usuario user = getVendedor();
		String fecha;
		double iva, total;
		int folio, vendidos, cantidad;
		folio = Integer.valueOf(datosTicket[0]);
		fecha = datosTicket[1];
		iva = Double.valueOf(datosTicket[4]);
		total = Double.valueOf(datosTicket[5]);
		vendidos = Integer.valueOf(datosTicket[6]);
		cantidad = producto.dameCantidad();
		if (cantidad != 0) {
			servicioalmacen.eliminarProducto(producto);
			producto.setCantidad(cantidad - 1);
			if (producto.dameCantidad() > 0)
				servicioalmacen.agregarProducto(producto);
			Ticket ticket = new Ticket(folio, fecha, user.getId(), producto.dameCodigo(), iva, total, vendidos);
			servicioticket.agregaTicket(ticket);
		} else
			JOptionPane.showMessageDialog(null, "No hay Productos Disponibles");
	}

	// Este método imprime el ticket.
	public void imprimeTicket() {
		try {
			PrinterJob gap = PrinterJob.getPrinterJob();
			gap.setPrintable(this);
			boolean top = gap.printDialog();
			if (top)
				gap.print();
		} catch (PrinterException e) {
			JOptionPane.showMessageDialog(null, "Error al imprimir");
		}
	}

	public boolean realizaCambio() {
		return vistacambio.realizaCambio();
	}

	public Usuario getVendedor() {
		return user;
	}

	public Usuario buscaVendedor(String nombre, String tipo) {
		return serviciologin.dameVendedor(nombre, tipo);
	}

	public void setVendedor(Usuario user) {
		this.user = user;
	}

	public boolean esNumero(String num) {
		if (num.isEmpty())
			return false;
		for (Character c : num.toCharArray())
			if (!Character.isDigit(c))
				return false;
		return true;
	}

	// Valida y le da formato de impresión al ticket.
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0)
			return NO_SUCH_PAGE;
		Graphics2D hub = (Graphics2D) graphics;
		hub.translate(pageFormat.getImageableX() + 20, pageFormat.getImageableY() + 20);
		hub.scale(1, 1);
		vistaticket.getTicket().printAll(graphics);
		return PAGE_EXISTS;
	}
}
