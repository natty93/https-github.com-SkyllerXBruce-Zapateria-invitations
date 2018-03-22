package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Usuario;

public class DAOLogin {

	//
	public boolean agregaVendedor(Usuario user) {
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			statement.execute("INSERT INTO Loginpersonal VALUES('" + user.getId() + "','" + user.getUsuario() + "', '"
					+ user.getPass() + "', '" + user.getTipo() + "', '" + user.getNombre() + "', '" + user.getCurp()
					+ "', '" + user.getCorreo() + "', '" + user.getTelefono() + "')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//
	public boolean quitaVendedor(Usuario usuario) {
		int resultado = 0;
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			// Recibe los resutados
			resultado = statement.executeUpdate("DELETE FROM Loginpersonal WHERE Tipo='" + usuario.getTipo()
					+ "'AND Usuario ='" + usuario.getUsuario() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultado == 0)
			return false;
		else
			return true;
	}

	// Este método se encarga de validar si existe un usuario especifico en al base
	// de datos.
	public boolean valida(Usuario usuario) {
		String sql = "SELECT * FROM Loginpersonal WHERE Tipo='" + usuario.getTipo() + "'AND Usuario ='"
				+ usuario.getUsuario() + "' AND Contraseña = '" + usuario.getPass() + "'   ";
		try {
			// crea el statement
			Statement st = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs.next();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Surgio un error verifique sus datos");
		}
		return false;
	}

	public Usuario buscarUsuario(String usuario, String tipo) {
		Usuario user = null;
		String sql = "SELECT * FROM Loginpersonal WHERE Tipo='" + tipo + "'AND Usuario ='" + usuario + "'   ";
		try {
			// crea el statement
			Statement st = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next())
				user = new Usuario(rs.getString("Id"), rs.getString("Usuario"), rs.getString("Contraseña"),
						rs.getString("Tipo"), rs.getString("Nombre"), rs.getString("Curp"), rs.getString("Correo"),
						rs.getString("Telefono"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Surgio un Error, Verifique sus Datos");
		}
		return user;
	}

	public Usuario buscarUsuario(String nombre) {
		Usuario user = null;
		try {
			// crea el statement
			Statement st = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Loginpersonal WHERE Nombre='" + nombre + "' ");
			if (rs.next())
				user = new Usuario(rs.getString("Id"), rs.getString("Usuario"), rs.getString("Contraseña"),
						rs.getString("Tipo"), rs.getString("Nombre"), rs.getString("Curp"), rs.getString("Correo"),
						rs.getString("Telefono"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Surgio un Error, Verifique sus Datos");
		}
		return user;
	}

	public Usuario buscarVendedor(String id) {
		Usuario user = null;
		try {
			// crea el statement
			Statement st = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Loginpersonal WHERE Id='" + id + "' ");
			if (rs.next())
				user = new Usuario(rs.getString("Id"), rs.getString("Usuario"), rs.getString("Contraseña"),
						rs.getString("Tipo"), rs.getString("Nombre"), rs.getString("Curp"), rs.getString("Correo"),
						rs.getString("Telefono"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Surgio un Error, Verifique sus Datos");
		}
		return user;
	}

	public Usuario[] dameVendedores() {
		ArrayList<Usuario> vendedorTemp = new ArrayList<Usuario>();
		Usuario usuarios[] = null;
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT * FROM Loginpersonal");
			while (rs.next()) {
				// Crea una nueva instancia del objeto
				Usuario user = new Usuario(rs.getString("Id"), rs.getString("Usuario"), rs.getString("Contraseña"),
						rs.getString("Tipo"), rs.getString("Nombre"), rs.getString("Curp"), rs.getString("Correo"),
						rs.getString("Telefono"));
				vendedorTemp.add(user);
			}
			usuarios = new Usuario[vendedorTemp.size()];
			vendedorTemp.toArray(usuarios);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	//
	public int cuantosVendedores() {
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Loginpersonal");
			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
