package com.ejemplo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ejemplo.modelo.Persona;

public class PersonaDaoJDBCImpl extends AbstractJDBCDao implements PersonaDao {
	
	public void guardar(Persona persona) {
		Connection cn = null;
		try {
			cn=dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement("insert into personas (id,nombre,edad,altura,sexo) values (mi_sequence.nextval,?,?,?,?)");
			pst.setString(1, persona.getNombre());
			pst.setInt(2, persona.getEdad());
			pst.setFloat(3, persona.getAltura());
			pst.setString(4, Character.toString(persona.getSexo()));
			pst.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
		}finally{
			try {
				cn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
	}

	public void borrar(Persona persona) {
		Connection cn = null;
		try {
			cn=dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement("delete from personas where id=?");
			pst.setLong(1, persona.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
		}finally{
			try {
				cn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
	}

	public List<Persona> listar() {
		Connection cn = null;
		List<Persona>personas=new ArrayList<Persona>();
		try {
			cn=dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement("select * from personas");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				personas.add(fila2Persona(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				cn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return personas;

	}

	public Persona buscar(Long id) {
		Connection cn = null;
		Persona persona=null;
		try {
			cn=dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement("select * from personas where id=?");
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				persona=fila2Persona(rs);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		}finally{
			try {
				cn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return persona;

	}

	private Persona fila2Persona(ResultSet rs) throws SQLException {
		return new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getInt("edad"),
				rs.getFloat("altura"), rs.getString("sexo").trim().charAt(0));
	}

}
