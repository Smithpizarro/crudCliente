package com.invpersona.cliente.core.negocio.interfaces;

import java.util.List;

import com.invpersona.cliente.core.util.AppException;
import com.invpersona.cliente.integration.dto.ClienteCollectionDTO;
import com.invpersona.cliente.integration.dto.ClienteDTO;
import com.invpersona.cliente.integration.dto.PersonaDTO;;


public interface PersonaServiceInterface {

	
	public  List<PersonaDTO>  getAllPersona() throws AppException;
	
	public  ClienteCollectionDTO findByParametros (Integer pagina , Integer paginado)  throws AppException;
	
	public  PersonaDTO getPersona(String idPersona) throws AppException;

	public  ClienteDTO createPersona(ClienteDTO personadto) throws AppException;
    public  PersonaDTO updatePersona(PersonaDTO personadto) throws AppException;
  
    public  String deletePersona(String idPersona) throws AppException;
}
