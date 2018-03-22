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

}
