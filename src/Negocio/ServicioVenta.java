package Negocio;

import Modelo.Producto;
import Persistencia.DAOAlmacen;

public class ServicioVenta {
	// Declaramos atributos de nuestro servicio.

	@SuppressWarnings("unused")
	private ControlVenta control;
	private DAOAlmacen dao = new DAOAlmacen();
	
	// Instanciamos el control de nuestro servicio.
	public void setControl(ControlVenta control) {
		this.control = control;
	}
	
	// Este método consulta al DAOProducto si existe alguno con ese id. y lo
	// retorna.
	public Producto buscaProducto(int id) {
		Producto producto = dao.buscaProducto(id);
		return producto;
	}

}
