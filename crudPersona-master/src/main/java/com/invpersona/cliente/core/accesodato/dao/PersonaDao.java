package com.invpersona.cliente.core.accesodato.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.invpersona.cliente.core.accesodato.interfaces.PersonaDaoInterface;
import com.invpersona.cliente.core.negocio.bean.Persona;
import com.invpersona.cliente.core.negocio.bean.Cliente;
import com.invpersona.cliente.core.util.AppException;
import com.invpersona.cliente.core.util.Conexion;

@Repository
public class PersonaDao implements PersonaDaoInterface {

	
	public PersonaDao() {
				
	}
	
	
	
	public Integer listaTablaByParametrosTotalCount(Integer pagina, Integer paginado) throws AppException{
		
		CallableStatement cstmt = null;
		Long totalNotificaciones = null;
		Conexion con = null;
		String SQL = "{ ? =  CALL sp_count_personas(?,?,?) }";

		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema("crudpersona");
			cstmt = con.getConexion().prepareCall(SQL);

			cstmt.registerOutParameter(1, Types.BIGINT);
			
			cstmt.setLong(2, pagina);
			cstmt.setLong(3, paginado);
			cstmt.setLong(4, 0);
			cstmt.execute();
			totalNotificaciones = (cstmt.getLong(1));

			con.getConexion().commit();

		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		return totalNotificaciones.intValue();
	}
	
	
	public List<Cliente> listaTablaByParametros( Integer pagina, Integer paginado) throws AppException{
	
		List<Cliente> listapersonas = new ArrayList<>();

		CallableStatement cstmt = null;
		ResultSet rs = null;
		Conexion con = null;
		String SQL = "{ ? =  CALL sp_listar_clientes(?,?,?) }";

		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema("clientes");
			cstmt = con.getConexion().prepareCall(SQL);

			cstmt.registerOutParameter(1, Types.OTHER);
		
			cstmt.setLong(2, pagina);
			cstmt.setLong(3, paginado);
			cstmt.setLong(4, 0);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(1);

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setClienteId(rs.getLong("ID_CLIENTE"));
				cliente.setNombre((rs.getString("NOMBRE")));
				cliente.setApellido(rs.getString("APELLIDO"));
				cliente.setEdad(rs.getLong("EDAD"));
				cliente.setFecha_nacimiento(rs.getString("FECHA_NACIMIENTO"));
				cliente.setEdad_muerte(rs.getLong("EDAD_MUERTE"));
				
				listapersonas.add(cliente);
			}
			con.getConexion().commit();

		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		return listapersonas;
	}
   
	public  List<Persona>  getAllPersona() throws AppException{
		
		return null;
	}
	
	public  Persona getPersona(String idPersona) throws AppException{
		
		Persona persona = null;
		List<Persona> listapersonas = new ArrayList<>();
		Conexion con = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		try {
			con = new Conexion();
		} catch (Exception e) {
			throw new AppException(e);
		}

		try {

			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema("crudpersona");
			cs = con.getConexion().prepareCall("{ ? =  CALL sp_listar_personas(?,?,?) }");

			cs.registerOutParameter(1, Types.OTHER);
			cs.setLong(2, 1);
			cs.setLong(3, 1);
			cs.setLong(4, Long.parseLong(idPersona));

			// conn.setAutoCommit(false);
			cs.executeUpdate();
			con.getConexion().commit();

			rs = (ResultSet) cs.getObject(1);
			while (rs.next()) {
				
				persona.setPersonaId(rs.getLong("ID_PERSONA"));
				persona.setName(rs.getString("NAME"));
				persona.setHeight(rs.getLong("HEIGHT"));
				persona.setMass(rs.getDouble("MASS"));
				persona.setHairColor(rs.getString("HAIR_COLOR"));
				persona.setGender(rs.getString("GENDER"));
				persona.setPlanet(rs.getString("PLANET"));
				
				
			}
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), rs, null, cs, null);
			} catch (Exception e) {// TODO Auto-generated catch block e.printStackTrace();}
			}

		}

		return persona;
	}

	public  Cliente createPersona(Cliente persona) throws AppException{
		
		CallableStatement cstmt = null;
		Long resultado;

		Conexion con = null;
		String SQL = "{ ? = CALL sp_agregar_cliente(?,?,?,?,?) }";

		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema("clientes");
			cstmt = con.getConexion().prepareCall(SQL);

			cstmt.registerOutParameter(1, Types.BIGINT);
			cstmt.setString(2,persona.getNombre() == null ? null : persona.getNombre());
			cstmt.setString(3, persona.getApellido() == null ? null :  persona.getApellido());
			cstmt.setLong(4, persona.getEdad() == null ? null : persona.getEdad());
			cstmt.setString(5,persona.getFecha_nacimiento() == null ? null : persona.getFecha_nacimiento());
			cstmt.setLong(6, persona.getEdad_muerte() == null ? null : persona.getEdad_muerte());
			cstmt.execute();
			resultado = cstmt.getLong(1);

			persona.setClienteId(resultado);

			con.getConexion().commit();
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		return persona;
	}
	
    public  Persona updatePersona(Persona persona) throws AppException{
    	
    	CallableStatement cstmt = null;

		Conexion con = null;
		String SQL = "{ CALL SP_ACTUALIZAR_PERSONA(?,?,?,?,?,?,?) }";

		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema("crudpersona");
			cstmt = con.getConexion().prepareCall(SQL);

			cstmt.setLong(1, persona.getPersonaId()==null?0:persona.getPersonaId());

			cstmt.setString(2, persona.getName() == null ? null : persona.getName());
			cstmt.setLong(3, persona.getHeight() == null ? null :  persona.getHeight());
			cstmt.setDouble(4, persona.getMass() == null ? null : persona.getMass());
			cstmt.setString(5, persona.getHairColor() == null ? null : persona.getHairColor());
			cstmt.setString(6, persona.getGender() == null ? null : persona.getGender());
			cstmt.setString(7, persona.getPlanet()== null ? null : persona.getPlanet());

			cstmt.executeUpdate();

			con.getConexion().commit();
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		return persona;
    }
  
    public  String deletePersna(String idPersona) throws AppException{
    	
    	CallableStatement cstmt = null;
		String msjconfiguracion = null;
		Conexion con = null;
		String SQL = "{ CALL sp_delete_persona(?) }";
		
		try {
			con = new Conexion();
			con.getConexion().setAutoCommit(false);
			con.getConexion().setSchema("crudpersona");
			cstmt = con.getConexion().prepareCall(SQL);
			
			
			cstmt.setLong(1,  Long.parseLong(idPersona));
			
			int rows = cstmt.executeUpdate();
			
			con.getConexion().commit();
		    
			if(rows!=-1) {
				
				msjconfiguracion ="La persona con  id: "+idPersona+" se ha eliminado ";
			}
		
		} catch (SQLException sqle) {
			throw new AppException(sqle);
		} catch (Exception e) {
			throw new AppException(e);
		} finally {
			try {
				con.closeResources(con.getConexion(), null, null, cstmt, null);
			} catch (Exception e) {
				throw new AppException(e);
			}
		}
		return msjconfiguracion;
	}
    
    
}
