package com.invpersona.cliente.integration.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invpersona.cliente.core.negocio.interfaces.PersonaServiceInterface;
import com.invpersona.cliente.core.util.AppException;
import com.invpersona.cliente.integration.dto.ClienteCollectionDTO;
import com.invpersona.cliente.integration.dto.ClienteDTO;
import com.invpersona.cliente.integration.dto.ErrorModelDTO;
import com.invpersona.cliente.integration.dto.PersonaCollectionDTO;
import com.invpersona.cliente.integration.dto.PersonaDTO;
import com.invpersona.cliente.integration.util.Constantes;
import com.invpersona.cliente.integration.util.Util;

@RestController
@RequestMapping("/cliente")
public class PersonaRestController {
	
	protected final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	public PersonaServiceInterface personaservice;
	
	public PersonaRestController() {
		
	}
	

	
	
	@GetMapping(path = "/listarPersona/{pagina}")
	public ResponseEntity<Object> getAllPersonaPagina(@PathVariable("pagina") String pagina) {
		ClienteCollectionDTO personacollectionDTO = null;
		ErrorModelDTO errorModelDTO = null;

		try {
			personacollectionDTO = getListaPersona(pagina);

			if (personacollectionDTO != null && personacollectionDTO.getListaCliente().size() != 0) {

				return new ResponseEntity<Object>(personacollectionDTO, HttpStatus.OK);
			} else {

				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.DATOS_NO_SE_ENCUENTRAN_REGISTRADOS.getCodigo(),
						Constantes.CodigoError.DATOS_NO_SE_ENCUENTRAN_REGISTRADOS.getMensage());
				return new ResponseEntity<Object>(personacollectionDTO, HttpStatus.OK);
			}
		}

		catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModelDTO = new ErrorModelDTO(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	} 
	

	@GetMapping(path = "/allPersona")
	public ResponseEntity<Object> getAllPersona() {
		List<PersonaDTO> listapersonaDTO = null;
		ErrorModelDTO errorModelDTO = null;

		try {
			listapersonaDTO = personaservice.getAllPersona();

			if (listapersonaDTO != null ) {

				return new ResponseEntity<Object>(listapersonaDTO, HttpStatus.OK);
			} else {

				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.DATOS_NO_SE_ENCUENTRAN_REGISTRADOS.getCodigo(),
						Constantes.CodigoError.DATOS_NO_SE_ENCUENTRAN_REGISTRADOS.getMensage());
				return new ResponseEntity<Object>(errorModelDTO, HttpStatus.NOT_FOUND);
			}
		}

		catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModelDTO = new ErrorModelDTO(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@GetMapping(path = "/getPersona/{id}")
	public  ResponseEntity<Object> getConfiguracion(@PathVariable("id") String idpersona) throws AppException {
		PersonaDTO personaDTO = null;
		ErrorModelDTO errorModelDTO = null;

		try {
			
			
			personaDTO = personaservice.getPersona(idpersona);

			if (personaDTO != null) {

				return new ResponseEntity<Object>(personaDTO, HttpStatus.OK);
			} else {

				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.DATOS_NO_SE_ENCUENTRAN_REGISTRADOS.getCodigo(),
						Constantes.CodigoError.DATOS_NO_SE_ENCUENTRAN_REGISTRADOS.getMensage());
				return new ResponseEntity<Object>(errorModelDTO, HttpStatus.NOT_FOUND);
			}
		}

		catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModelDTO = new ErrorModelDTO(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path = "/createPersona")
	public ResponseEntity<Object> addConfiguracion(@RequestBody ClienteDTO clientedto){
		ClienteDTO personadtotmp;
		ErrorModelDTO errorModelDTO = null;
		try {
			if (clientedto != null) {
				  
				if (   clientedto.getEdad()!=null &&  clientedto.getEdad().getClass().getName().equals("java.lang.Long") &&
				   
						clientedto.getEdad()!=null &&  clientedto.getEdad().getClass().getName().equals("java.lang.Long") &&
						 
								clientedto.getNombre()!=null && clientedto.getNombre().length() <=200 	   
				     ) {

					 
						 personadtotmp = personaservice.createPersona(clientedto);
							return new ResponseEntity<Object>(personadtotmp, HttpStatus.CREATED);
					 
					

				} else {
					errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getCodigo(),
							Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getMensage());
					return new ResponseEntity<Object>(errorModelDTO, HttpStatus.BAD_REQUEST);
				}

			} else {

				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getCodigo(),
						Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getMensage());
				return new ResponseEntity<Object>(errorModelDTO, HttpStatus.BAD_REQUEST);

			}

		} catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModelDTO = new ErrorModelDTO(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PutMapping(path = "/updatePersona")
	public ResponseEntity<Object> updconfiguracion(@RequestBody PersonaDTO personadto) {

		PersonaDTO personadtotmp;
		ErrorModelDTO errorModelDTO = null;
		
		try {
			if (personadto != null) {
				if (personadto.getPersonaId()!= null
						&& personadto.getPersonaId().getClass().getName().equals("java.lang.Long")

				) {
                  
					 if(Util.validacionPlaneta(personadto.getPlanet())==0) {
						
						 errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.PLANETA_NO_EXISTE.getCodigo(),
									Constantes.CodigoError.PLANETA_NO_EXISTE.getMensage());
							return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR); 
						 
					 }else {
						 
						 personadtotmp = personaservice.updatePersona(personadto);
							return new ResponseEntity<Object>(personadtotmp, HttpStatus.OK);
					 }
					

				} else {
					errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getCodigo(),
							Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getMensage());
					return new ResponseEntity<Object>(errorModelDTO, HttpStatus.BAD_REQUEST);
				}

			} else {

				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getCodigo(),
						Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getMensage());
				return new ResponseEntity<Object>(errorModelDTO, HttpStatus.BAD_REQUEST);

			}

		} catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModelDTO = new ErrorModelDTO(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(path = "/deletePersona/{id}")
	public ResponseEntity<Object> deletePersona(@PathVariable("id") String idPersona) {

		String configuracioncampoDTOtmp ="";
		ErrorModelDTO errorModelDTO = null;
		try { 
			
				if ( idPersona != null
					) {

					configuracioncampoDTOtmp = personaservice.deletePersona(idPersona);
					return new ResponseEntity<Object>(configuracioncampoDTOtmp, HttpStatus.CREATED);	
					
				} else {
					errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getCodigo(),
							Constantes.CodigoError.DATOS_ENTRADAS_ERRONEOS.getMensage());
					return new ResponseEntity<Object>(errorModelDTO, HttpStatus.BAD_REQUEST);
				}

		    
		} catch (AppException e) {
			logger.error(e);
			Constantes.CodigoError codigoError = Constantes.CodigoError.getCodigoError(e.getCodigo());
			if (codigoError != null)
				errorModelDTO = new ErrorModelDTO(codigoError.getCodigo(), codigoError.getMensage());
			else
				errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (UncategorizedSQLException e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_TECNICO.getCodigo(), e.getMessage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			logger.error(e);
			errorModelDTO = new ErrorModelDTO(Constantes.CodigoError.ERROR_GENERICO.getCodigo(),
					Constantes.CodigoError.ERROR_GENERICO.getMensage());
			return new ResponseEntity<Object>(errorModelDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private ClienteCollectionDTO getListaPersona(String pagina) throws Exception{
		
		
		ClienteCollectionDTO personaollectiondto = personaservice.findByParametros(Integer.parseInt(pagina) ,8);
				
		return personaollectiondto;
	}
	
	
}
