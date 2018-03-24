package Modelo;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Componentes {
	
	// Constructor de los Componentes
	public Componentes() {
		// vacio
	}

	// Método Para Crear las Propiedades del boton
		public JButton creaBoton(String nombre, int posx, int posy, int ancho, int alto) {
			JButton boton = new JButton(nombre);
			boton.setBounds(posx, posy, ancho, alto);
			boton.setFont(new Font("Serif", Font.ITALIC, 14));
			return boton;
		}

		// Método Para Crear las Propiedades de las Etiquetas
		public JLabel creaEtiqueta(String nombre, int posx, int posy, int ancho, int alto, int tamaño) {
			JLabel etiqueta = new JLabel(nombre);
			etiqueta.setBounds(posx, posy, ancho, alto);
			etiqueta.setFont(new Font("Serif", Font.ITALIC, tamaño));
			return etiqueta;
		}

		// Método Para Crear las Propiedades de los Cuadros de Texto
		public JTextField creaCuadroTexto(int posx, int posy, int ancho, int alto, int tamaño) {
			JTextField texto = new JTextField();
			texto.setBounds(posx, posy, ancho, alto);
			texto.setFont(new Font("Serif", Font.ITALIC, tamaño));
			texto.setText("");
			return texto;
		}
}
