package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modelo.Componentes;
import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaComiciones extends JFrame {

	// Variables Globales
	private JButton regresar;
	private JLabel lblnombre, lblventas, lblcomicion;
	private ControlVendedores control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaComiciones frame = new VistaComiciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAdministrador
	public VistaComiciones() {
		// Propiedades de la Ventana
		setSize(480, 340);
		setLocationRelativeTo(null);
		setResizable(false);
		confirmarSalida();
		iniciarComponentes();
	}

	// Si Da Click en Cerrar (X) Pregunta si Desea Cerrar la Sesion de la Aplicacion
	private void confirmarSalida() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Salir del Balance?",
						"¿Salir del Balance?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaAdministrador();
					dispose();
				}
			}
		});
	}

	// Creamos y Agregamos los Componetes de la Ventana
	private void iniciarComponentes() {
		// creamos el panel y lo agregamos a la ventana
		JPanel panel = new JPanel(null);
		Componentes componente = new Componentes();
		JLabel titulo, nombre, ventas,  comicion;

		setContentPane(panel);

		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaAgregarVendedor.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		regresar = componente.creaBoton("", 40, 240, 50, 50);
		regresar.setIcon(imgIcon);
		regresar.setToolTipText("Cancela la Operacion y Regresa a la Ventana del Administrador");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = componente.creaEtiqueta("Comicion del Vendedor", 60, 40, 360, 35, 30);
		nombre = componente.creaEtiqueta("Vendedor:", 40, 100, 220, 25, 16);
		ventas = componente.creaEtiqueta("Ventas Realizadas:", 40, 140, 220, 25, 16);	
		comicion = componente.creaEtiqueta("Comicion Obtenida:   $", 60, 200, 220, 25, 18);
		lblnombre = componente.creaEtiqueta("Nombre", 240, 100, 140, 25, 16);
		lblventas = componente.creaEtiqueta("Venta", 240, 140, 140, 25, 16);
		lblcomicion = componente.creaEtiqueta("Comicion", 260, 200, 180, 25, 18);
		nombre.setToolTipText("Nombre del Vendedor");
		ventas.setToolTipText("Muestra las Cantidad de Ventas Realizadas");
		comicion.setToolTipText("Muestra la Comicion Obtenida en Total");
		lblnombre.setToolTipText("Nombre del Vendedor");
		lblventas.setToolTipText("Muestra las Cantidad de Ventas Realizadas");
		lblcomicion.setToolTipText("Muestra la Comicion Obtenida en Total");

		// Se Realiza Acciones de los Componentes
		accionesComponentes();

		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(nombre);
		panel.add(ventas);
		panel.add(comicion);
		panel.add(lblnombre);
		panel.add(lblventas);
		panel.add(lblcomicion);
		panel.add(regresar);
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {

		// Accion del boton Regresa
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministarVendedores();
				control.limpiarDatos("AgregarVendedor");
				dispose();
			}
		});
	}

	// Metodo que limpia los TextFields
	public void limpiarDatosComiciones() {
		lblnombre.setText("");
		lblventas.setText("");
		lblcomicion.setText("");
	}

	// Obtenemos la Instancia del Control Vendedor
	public void setControl(ControlVendedores controlvendedores) {
		this.control = controlvendedores;
	}

}
