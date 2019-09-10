package com.invpersona.cliente.core.accesodato.interfaces;

import java.util.List;

import com.invpersona.cliente.core.negocio.bean.Persona;
import com.invpersona.cliente.core.negocio.bean.Cliente;
import com.invpersona.cliente.core.util.AppException;


public interface PersonaDaoInterface {

	
	public Integer listaTablaByParametrosTotalCount(Integer pagina, Integer paginado) throws AppException;
	public List<Cliente> listaTablaByParametros( Integer pagina, Integer paginado) throws AppException;
   
	public  List<Persona>  getAllPersona() throws AppException;
	
	public  Persona getPersona(String idPersona) throws AppException;

	public  Cliente createPersona(Cliente persona) throws AppException;
    public  Persona updatePersona(Persona persona) throws AppException;
  
    public  String deletePersna(String idPersona) throws AppException;
}
