package Negocio;

import Modelo.Producto;
import Presentacion.VistaAdministrador;
import Presentacion.VistaAgregarProducto;
import Presentacion.VistaAlmacen;
import Presentacion.VistaConsultaProducto;
import Presentacion.VistaConsultaProductoRealizada;
import Presentacion.VistaEliminarProducto;
import Presentacion.VistaMostrarProductos;

public class ControlAlmacen {

	private VistaAlmacen vistaalmacen;
	private VistaAdministrador vistaadmin;
	private VistaAgregarProducto vistaaddproducto;
	private VistaEliminarProducto vistadelproducto;
	private VistaConsultaProducto vistaconsultaproducto;
	private VistaConsultaProductoRealizada vistaconsultaproductorealizada;
	private VistaMostrarProductos vistamostrarproductos;
	private ServicioAlmacen servicioalmacen;

	public void setVistaAlmacen(VistaAlmacen vistaalmacen) {
		this.vistaalmacen = vistaalmacen;
	}

	public void setVistaAdministrador(VistaAdministrador vistaadmin) {
		this.vistaadmin = vistaadmin;
	}

	public void setServicioAlmacen(ServicioAlmacen servicioalmacen) {
		this.servicioalmacen = servicioalmacen;
	}

	public void setVistaAgregarProducto(VistaAgregarProducto vistaaddproducto) {
		this.vistaaddproducto = vistaaddproducto;
	}

	public void setVistaEliminarProducto(VistaEliminarProducto vistadelproducto) {
		this.vistadelproducto = vistadelproducto;
	}

	public void setVistaCosultaProducto(VistaConsultaProducto vistaconsultaproducto) {
		this.vistaconsultaproducto = vistaconsultaproducto;
	}

	public void setVistaConsultaProductoRealizada(VistaConsultaProductoRealizada vistaconsultaproductorealizada) {
		this.vistaconsultaproductorealizada = vistaconsultaproductorealizada;
	}

	public void setVistaMostrarProductos(VistaMostrarProductos vistamostrarproductos) {
		this.vistamostrarproductos = vistamostrarproductos;
	}

	public void muestraVistaAlmacen() {
		this.vistaalmacen.setVisible(true);
	}

	public void muestraVistaAdministrador() {
		this.vistaadmin.setVisible(true);
	}

	public void muestraVistaAgregarProducto() {
		this.vistaaddproducto.setVisible(true);
	}

	public void muestraVistaEliminarProducto() {
		this.vistadelproducto.setVisible(true);
	}

	public void muestraVistaConsultaProducto() {
		this.vistaconsultaproducto.setVisible(true);
	}

	public void muestraVistaConsultaProductoRealizada(Producto producto) {
		this.vistaconsultaproductorealizada.obtenerDatosProducto(producto);
		this.vistaconsultaproductorealizada.setVisible(true);
	}

	public void muestraVistaMostrarProductos() {
		this.vistamostrarproductos.setVisible(true);
	}

	public void limpiarDatos(String limpiar) {
		switch (limpiar) {
		case "Agregar":
			vistaaddproducto.limpiarDatosAgregarProducto();
			break;
		case "Eliminar":
			vistadelproducto.limpiarDatosEliminarProducto();
			break;
		case "Consultar":
			vistaconsultaproducto.limpiarDatosConsultarProducto();
			vistaconsultaproductorealizada.limpiarDatosConsultaProducto();
			break;
		case "Mostrar":
			vistamostrarproductos.limpiarDatosMostrarProductos();
			break;
		default:
			throw new IllegalArgumentException("Error, No se Puede Limpiar");
		}
	}

	public boolean existeProducto(String modelo, String tipo) {
		if (servicioalmacen.buscaProducto(modelo, tipo) != null)
			return true;
		return false;
	}

	public boolean existeVendedor(int codigo) {
		if (servicioalmacen.buscaProducto(codigo) != null)
			return true;
		return false;
	}

	public Producto buscaProducto(String modelo, String tipo) {
		return servicioalmacen.buscaProducto(modelo, tipo);
	}

	public Producto buscaProducto(int codigo) {
		return servicioalmacen.buscaProducto(codigo);
	}

	public boolean eliminarProducto(Producto producto) {
		return servicioalmacen.eliminarProducto(producto);
	}

	public void cargarDatosProductos() {
		String[] nuevo = new String[vistamostrarproductos.getTablaModelo().getColumnCount()];
		for (Producto producto : servicioalmacen.dameProductos()) {
			nuevo[0] = String.valueOf(producto.dameCodigo());
			nuevo[1] = producto.dameModelo();
			nuevo[2] = producto.dameTipo();
			nuevo[3] = producto.dameColor();
			nuevo[4] = String.format("%.2f", producto.dameCosto());
			nuevo[5] = String.valueOf(producto.dameTalla());
			nuevo[6] = String.valueOf(producto.dameCantidad());
			vistamostrarproductos.getTablaModelo().addRow(nuevo);
		}
	}

	public boolean esNumero(String num) {
		if (num.isEmpty())
			return false;
		for (Character c : num.toCharArray())
			if (!Character.isDigit(c))
				return false;
		return true;
	}

}
