package Negocio;

import Persistencia.DAOAlmacen;
import Modelo.Producto;

public class ServicioAlmacen {

	private DAOAlmacen dao = new DAOAlmacen();

	public Producto buscaProducto(int codigo) {
		return dao.buscaProducto(codigo);
	}

	public Producto buscaProducto(String modelo, String tipo) {
		return dao.buscaProducto(modelo, tipo);
	}

	public Producto buscaProducto(String modelo, String tipo, String color, double talla) {
		return dao.buscaProducto(modelo, tipo, color, talla);
	}

	public boolean existeProducto(String modelo, String tipo) {
		if (dao.buscaProducto(modelo, tipo) != null)
			return true;
		return false;
	}

	public Producto[] dameProductos() {
		return dao.dameProductos();
	}

	// recibe el producto como boolean del resultado al enviar a dao
	public boolean eliminarProducto(Producto producto) {
		return dao.quitaProducto(producto);
	}

	// recibe las caracteristicas del modelo y regresa producto
	public Producto consultaProducto(String modelo, String tipo) {
		return dao.buscaProducto(modelo, tipo);
	}

	public int cantidadProductos() {
		if (dao.cuantosProductos() != 0)
			return dao.cuantosProductos();
		return 0;
	}

	public boolean agregarProducto(Producto producto) {
		return dao.agregaProducto(producto);
	}

}
