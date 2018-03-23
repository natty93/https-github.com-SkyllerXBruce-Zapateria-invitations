package Presentacion;

import java.awt.EventQueue;
import java.awt.Font;
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

import Negocio.ControlVendedores;

@SuppressWarnings("serial")
public class VistaAdministarVendedores extends JFrame {

	// Variable Global
	private JButton agregar, eliminar, consultar, listar, regresar;
	private ControlVendedores control;

	// Muestra Solo la Presentacion de la Vista
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAdministarVendedores frame = new VistaAdministarVendedores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Constructor de la Ventana VistaAdministrarVendedores
	public VistaAdministarVendedores() {
		// Propiedades de la Ventana
		setSize(480, 300);
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Cerrar Sesión?", "Cerrar Sesión",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
		setContentPane(panel);
		JLabel titulo;
		
		// Imagen del Boton regresar
		ImageIcon imgIcon = new ImageIcon(VistaAdministarVendedores.class.getResource("return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);

		// Creamos y Agregamos las Propiedades del Método creaBoton para Cada Boton
		agregar = creaBoton("Agregar Vendedor", 40, 100, 180, 30);
		eliminar = creaBoton("Eliminar Vendedor", 40, 150, 180, 30);
		consultar = creaBoton("Consultar Vendedor", 260, 100, 180, 30);
		listar = creaBoton("Listar Vendedores", 260, 150, 180, 30);
		regresar = creaBoton("", 20, 200, 50, 50);
		regresar.setIcon(imgIcon);
		agregar.setToolTipText("Agrega a un Vendedor");
		eliminar.setToolTipText("Elimina a un Vendedor");
		consultar.setToolTipText("Consulta a un Vendedor");
		listar.setToolTipText("Muestra a Todos los Vendedores");
		regresar.setToolTipText("Regresa a la Ventana del Administrador");

		// Se Modifica la Posicion, Tipo de Letra y su Tamaño Tanto de las Etiquetas
		// Como de la Letra
		titulo = creaEtiqueta("Administrar Vendedores", 80, 40, 340, 28, 28);

		// Se Realiza Acciones de los Componentes
		accionesComponentes();
		
		// Agregamos los Componentes al Panel
		panel.add(titulo);
		panel.add(agregar);
		panel.add(eliminar);
		panel.add(consultar);
		panel.add(regresar);
		panel.add(listar);
	}

	// Método Para Crear las Propiedades del boton
	private JButton creaBoton(String nombre, int posx, int posy, int ancho, int alto) {
		JButton boton = new JButton(nombre);
		boton.setBounds(posx, posy, ancho, alto);
		boton.setFont(new Font("Serif", Font.ITALIC, 14));
		return boton;
	}

	// Método Para Crear las Propiedades de las Etiquetas
	private JLabel creaEtiqueta(String nombre, int posx, int posy, int ancho, int alto, int tamaño) {
		JLabel etiqueta = new JLabel(nombre);
		etiqueta.setBounds(posx, posy, ancho, alto);
		etiqueta.setFont(new Font("Serif", Font.ITALIC, tamaño));
		return etiqueta;
	}

	// Método para Crear las Acciones de Los Componentes
	private void accionesComponentes() {
		// Accion del boton Agregar 
		agregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAgregarVendedor();
				dispose();
			}
		});

		// Accion del boton Eliminar
		eliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaEliminarVendedor();
				dispose();
			}
		});

		// Accion del boton Consultar
		consultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaConsultarVendedor();
				dispose();
			}
		});

		// Accion del boton Listar
		listar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.cargarDatosVendedores();
				control.muestraVistaMuestraVendedores();
				dispose();
			}
		});
		
		// Accion de Boton Regresar
		regresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.muestraVistaAdministrador();
				dispose();
			}
		});
	}

	// Obtenemos la Instancia del Control Vendedor
	public void setControl(ControlVendedores control) {
		this.control = control;
	}
}
