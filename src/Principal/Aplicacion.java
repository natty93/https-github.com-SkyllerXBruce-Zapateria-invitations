package Principal;

import Negocio.ControlAlmacen;
import Negocio.ControlLogin;
import Negocio.ControlVendedores;
import Negocio.ControlVenta;
import Negocio.ServicioAlmacen;
import Negocio.ServicioLogin;
import Negocio.ServicioTicket;
import Negocio.ServicioVendedores;
import Negocio.ServicioVenta;
import Presentacion.VistaAdministarVendedores;
import Presentacion.VistaAdministrador;
import Presentacion.VistaAgregarProducto;
import Presentacion.VistaAgregarUsuario;
import Presentacion.VistaAgregarVendedor;
import Presentacion.VistaAlmacen;
import Presentacion.VistaCambioCalzado;
import Presentacion.VistaConsultaProducto;
import Presentacion.VistaConsultaProductoRealizada;
import Presentacion.VistaConsultaRealizada;
import Presentacion.VistaConsultarVendedor;
import Presentacion.VistaEliminarProducto;
import Presentacion.VistaEliminarVendedor;
import Presentacion.VistaLogin;
import Presentacion.VistaMostrarProductos;
import Presentacion.VistaMostrarVendedores;
import Presentacion.VistaTicket;
import Presentacion.VistaVendedor;
import Presentacion.VistaVentaCalzado;

public class Aplicacion {

	// Este metodo crea el objeto incial
	public static void main(String[] args) {
		// Crea la instancia de la aplicacion
		Aplicacion app = new Aplicacion();
		app.inicia(); // La inicia
	}

	// Constructor de la Clase Aplicacion
	public Aplicacion() {
		// No hay nada que inicializar
	}

	// Inicia la Aplicacion
	public void inicia() {
		// Declaramos las clases e instanciamos las clases(crear objetos de clases)
		VistaAdministarVendedores vistaadminvendedores = new VistaAdministarVendedores();
		VistaAdministrador vistaadmin = new VistaAdministrador();
		VistaAgregarProducto vistaaddproducto = new VistaAgregarProducto();
		VistaAgregarUsuario vistaadduser = new VistaAgregarUsuario();
		VistaAgregarVendedor vistaaddvendedor = new VistaAgregarVendedor();
		VistaAlmacen vistaalmacen = new VistaAlmacen();
		VistaCambioCalzado vistacambio = new VistaCambioCalzado();
		VistaConsultaProducto vistaconsultaproducto = new VistaConsultaProducto();
		VistaConsultaProductoRealizada vistaconsultaproductorealizada =new VistaConsultaProductoRealizada();
		VistaConsultaRealizada vistaconsultarealizada = new VistaConsultaRealizada();
		VistaConsultarVendedor vistaconsultavendedor = new VistaConsultarVendedor();
		VistaEliminarProducto vistadelproducto = new VistaEliminarProducto();
		VistaEliminarVendedor vistadelvendedor = new VistaEliminarVendedor();
		VistaLogin vistalogin = new VistaLogin();
		VistaMostrarProductos vistamostrarproductos=new VistaMostrarProductos();
		VistaMostrarVendedores vistamostrarvendedores = new VistaMostrarVendedores();
		VistaTicket vistaticket = new VistaTicket();
		VistaVendedor vistavendedor = new VistaVendedor();
		VistaVentaCalzado vistavendedorcalzado = new VistaVentaCalzado();

		ControlAlmacen controlalmacen = new ControlAlmacen();
		ControlLogin controllogin = new ControlLogin();
		ControlVendedores controlvendedores = new ControlVendedores();
		ControlVenta controlventa = new ControlVenta();

		ServicioAlmacen servicioalmacen = new ServicioAlmacen();
		ServicioLogin serviciologin = new ServicioLogin();
		ServicioTicket servicioticket = new ServicioTicket();
		ServicioVendedores serviciovendedores = new ServicioVendedores();
		ServicioVenta servicioventa = new ServicioVenta();

		// Establecemos las relaciones entre clases
		vistaadmin.setControl(controlvendedores);
		vistaadmin.setControl(controllogin);
		vistaadmin.setControl(controlalmacen);
		vistaadminvendedores.setControl(controlvendedores);
		vistaaddproducto.setControl(controlalmacen);
		vistaadduser.setControl(controlvendedores);
		vistaaddvendedor.setControl(controlvendedores);
		vistaalmacen.setControl(controlalmacen);
		vistacambio.setControl(controlventa);
		vistaconsultaproducto.setControl(controlalmacen);
		vistaconsultaproductorealizada.setControl(controlalmacen);
		vistaconsultarealizada.setControl(controlvendedores);
		vistaconsultavendedor.setControl(controlvendedores);
		vistadelproducto.setControl(controlalmacen);
		vistadelvendedor.setControl(controlvendedores);
		vistalogin.setControl(controllogin);
		vistamostrarproductos.setControl(controlalmacen);
		vistamostrarvendedores.setControl(controlvendedores);
		vistaticket.setControl(controlventa);
		vistavendedor.setControl(controlventa);
		vistavendedorcalzado.setControl(controlventa);

		serviciologin.setControl(controllogin);
		servicioticket.setControl(controlventa);
		serviciovendedores.setControl(controlvendedores);
		servicioventa.setControl(controlventa);

		// Enviamos una instancia de cada clase al control
		controllogin.setServicioLogin(serviciologin);
		controllogin.setVistaAdministrador(vistaadmin);
		controllogin.setVistaLogin(vistalogin);
		controllogin.setVistaVendedor(vistavendedor);

		controlventa.setServicioAlmacen(servicioalmacen);
		controlventa.setServicioLogin(serviciologin);
		controlventa.setServicioTicket(servicioticket);
		controlventa.setServicioVenta(servicioventa);
		controlventa.setVistaCambioCalzado(vistacambio);
		controlventa.setVistaLogin(vistalogin);
		controlventa.setVistaTicket(vistaticket);
		controlventa.setVistaVendedor(vistavendedor);
		controlventa.setVistaVentaCalzado(vistavendedorcalzado);

		controlvendedores.setServicioVendedores(serviciovendedores);
		controlvendedores.setVistaAdministarVendedores(vistaadminvendedores);
		controlvendedores.setVistaAdministrador(vistaadmin);
		controlvendedores.setVistaAgregarUsuario(vistaadduser);
		controlvendedores.setVistaAgregarVendedor(vistaaddvendedor);
		controlvendedores.setVistaConsultaRealizada(vistaconsultarealizada);
		controlvendedores.setVistaConsultarVendedor(vistaconsultavendedor);
		controlvendedores.setVistaEliminarVendedor(vistadelvendedor);
		controlvendedores.setVistaMostrarVendedores(vistamostrarvendedores);

		controlalmacen.setServicioAlmacen(servicioalmacen);
		controlalmacen.setVistaAdministrador(vistaadmin);
		controlalmacen.setVistaAgregarProducto(vistaaddproducto);
		controlalmacen.setVistaAlmacen(vistaalmacen);
		controlalmacen.setVistaCosultaProducto(vistaconsultaproducto);
		controlalmacen.setVistaConsultaProductoRealizada(vistaconsultaproductorealizada);
		controlalmacen.setVistaEliminarProducto(vistadelproducto);
		controlalmacen.setVistaMostrarProductos(vistamostrarproductos);

		// Hacemos Visible la Clase Vistalogin
		vistalogin.setVisible(true);
	}

}
