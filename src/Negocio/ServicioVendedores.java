package Negocio;

import Modelo.Usuario;
import Persistencia.DAOLogin;

public class ServicioVendedores {

	@SuppressWarnings("unused")
	private ControlVendedores control;
	private DAOLogin dao = new DAOLogin();

	public void setControl(ControlVendedores control) {
		this.control = control;
	}

	public Usuario[] dameVendedores() {
		Usuario vendedores[] = dao.dameVendedores();
		if (vendedores != null)
			return vendedores;
		return null;
	}

	public boolean agregaVendedor(Usuario vendedor) {
		return dao.agregaVendedor(vendedor);
	}

	public int cuantosVendedores() {
		int cantidadvendedores = dao.cuantosVendedores();
		System.out.println("cantidad: "+cantidadvendedores);
		if (cantidadvendedores != 0)
			return cantidadvendedores;
		return 0;
	}

	public boolean existeVendedor(String dato) {
		if (dao.buscarUsuario(dato) != null)
			return true;
		else if (dao.buscarVendedor(dato) != null)
			return true;
		return false;
	}

	public Usuario buscaVendedorPorNombre(String nombre) {
		return dao.buscarUsuario(nombre);
	}

	public Usuario buscaVendedorPorId(String id) {
		return dao.buscarVendedor(id);
	}

	public boolean eliminarVendedor(Usuario vendedor) {
		return dao.quitaVendedor(vendedor);
	}

}
