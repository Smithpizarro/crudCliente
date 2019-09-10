package com.invpersona.cliente.integration.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.invpersona.cliente.core.negocio.bean.Cliente;
import com.invpersona.cliente.core.negocio.bean.ClienteCollection;
import com.invpersona.cliente.core.negocio.bean.Persona;
import com.invpersona.cliente.core.negocio.bean.PersonaCollection;
import com.invpersona.cliente.integration.dto.ClienteCollectionDTO;
import com.invpersona.cliente.integration.dto.ClienteDTO;
import com.invpersona.cliente.integration.dto.PersonaCollectionDTO;
import com.invpersona.cliente.integration.dto.PersonaDTO;
import com.invpersona.cliente.integration.dto.SearchPlanetDTO;

public class Util {

	public static PersonaCollectionDTO mapAtoB(PersonaCollection personaCollection) {

		PersonaCollectionDTO personaCollectiondto = new PersonaCollectionDTO();

		List<PersonaDTO> listapersonaDTO = new ArrayList<PersonaDTO>();
		List<Persona> listapersona = null;
		if (personaCollection != null) {
			listapersona = personaCollection.getListaPersona();
			for (Persona persona : listapersona) {
				PersonaDTO personadto = new PersonaDTO();
				personadto.setPersonaId(persona.getPersonaId());
				personadto.setName(persona.getName());
				personadto.setHeight(persona.getHeight());
				personadto.setMass(persona.getMass());
				personadto.setHairColor(persona.getHairColor());
				personadto.setGender(persona.getGender());
				personadto.setPlanet(persona.getPlanet());

				listapersonaDTO.add(personadto);
				personadto = null;
			}
		}
		personaCollectiondto.setListaPersona(listapersonaDTO);
		return personaCollectiondto;
	}
   
	public static ClienteCollectionDTO mapAtoB(ClienteCollection clienteCollection) {

		ClienteCollectionDTO personaCollectiondto = new ClienteCollectionDTO();

		List<ClienteDTO> listapersonaDTO = new ArrayList<ClienteDTO>();
		List<Cliente> listapersona = null;
		if (clienteCollection != null) {
			listapersona = clienteCollection.getListaCliente();
			for (Cliente persona : listapersona) {
				ClienteDTO personadto = new ClienteDTO();
				personadto.setClienteId(persona.getClienteId());
				personadto.setNombre(persona.getNombre());
				personadto.setApellido(persona.getApellido());
				personadto.setEdad(persona.getEdad());
				personadto.setFecha_nacimiento(persona.getFecha_nacimiento());
				personadto.setEdad_muerte(persona.getEdad_muerte());

				listapersonaDTO.add(personadto);
				personadto = null;
			}
		}
		personaCollectiondto.setListaCliente(listapersonaDTO);
		return personaCollectiondto;
	}
	public static PersonaCollection mapAtoB(PersonaCollectionDTO personaCollectiondto) {

		PersonaCollection personaCollection = new PersonaCollection();

		List<Persona> listapersona = new ArrayList<Persona>();
		List<PersonaDTO> listapersonaDTO = null;

		if (personaCollectiondto != null) {
			listapersonaDTO = personaCollectiondto.getListaPersona();
			for (PersonaDTO personadto : listapersonaDTO) {
				Persona persona = new Persona();
				persona.setPersonaId(personadto.getPersonaId());
				persona.setName(personadto.getName());
				persona.setHeight(personadto.getHeight());
				persona.setMass(personadto.getMass());
				persona.setHairColor(personadto.getHairColor());
				persona.setGender(personadto.getGender());
				persona.setPlanet(personadto.getPlanet());

				listapersona.add(persona);
				persona = null;
			}
		}
		personaCollection.setListaPersona(listapersona);
		return personaCollection;
	}

	public static List<PersonaDTO> mapAtoBB(List<Persona> listapersona) {

		List<PersonaDTO> listapersonaDTO = new ArrayList<PersonaDTO>();

		for (Persona persona : listapersona) {

			PersonaDTO personadto = new PersonaDTO();
			personadto.setPersonaId(persona.getPersonaId());
			personadto.setName(persona.getName());
			personadto.setHeight(persona.getHeight());
			personadto.setMass(persona.getMass());
			personadto.setHairColor(persona.getHairColor());
			personadto.setGender(persona.getGender());
			personadto.setPlanet(persona.getPlanet());

			listapersonaDTO.add(personadto);
			personadto = null;
		}

		return listapersonaDTO;
	}

	public static List<Persona> mapAtoBA(List<PersonaDTO> listapersonadto) {

		List<Persona> listapersona = new ArrayList<Persona>();

		for (PersonaDTO personadto : listapersonadto) {

			Persona persona = new Persona();
			persona.setPersonaId(personadto.getPersonaId());
			persona.setName(personadto.getName());
			persona.setHeight(personadto.getHeight());
			persona.setMass(personadto.getMass());
			persona.setHairColor(personadto.getHairColor());
			persona.setGender(personadto.getGender());
			persona.setPlanet(personadto.getPlanet());

			listapersona.add(persona);
			persona = null;
		}

		return listapersona;
	}

	public static Persona mapAtoB(PersonaDTO personadto) {

		Persona persona = new Persona();
		persona.setPersonaId(personadto.getPersonaId());
		persona.setName(personadto.getName());
		persona.setHeight(personadto.getHeight());
		persona.setMass(personadto.getMass());
		persona.setHairColor(personadto.getHairColor());
		persona.setGender(personadto.getGender());
		persona.setPlanet(personadto.getPlanet());

		return persona;
	}
   

	  
		public static Cliente mapAtoB(ClienteDTO clientedto) {

			Cliente persona = new Cliente();
			persona.setClienteId(clientedto.getClienteId());
			persona.setNombre(clientedto.getNombre());
			persona.setApellido(clientedto.getApellido());
			persona.setEdad(clientedto.getEdad());
			persona.setFecha_nacimiento(clientedto.getFecha_nacimiento());
			persona.setEdad_muerte(clientedto.getEdad_muerte());
			
			return persona;
		}
		
		
		public static ClienteDTO mapAtoBBB(Cliente cliente) {

			ClienteDTO persona = new ClienteDTO();
			persona.setClienteId(cliente.getClienteId());
			persona.setNombre(cliente.getNombre());
			persona.setApellido(cliente.getApellido());
			persona.setEdad(cliente.getEdad());
			persona.setFecha_nacimiento(cliente.getFecha_nacimiento());
			persona.setEdad_muerte(cliente.getEdad_muerte());
			
			return persona;
		}		
		
	public static PersonaDTO mapAtoB(Persona persona) {

		PersonaDTO personadto = new PersonaDTO();
		personadto.setPersonaId(persona.getPersonaId());
		personadto.setName(persona.getName());
		personadto.setHeight(persona.getHeight());
		personadto.setMass(persona.getMass());
		personadto.setHairColor(persona.getHairColor());
		personadto.setGender(persona.getGender());
		personadto.setPlanet(persona.getPlanet());

		return personadto;
	}
  
	
	public static int validacionPlaneta(String planeta) {
		 
		SearchPlanetDTO search = new SearchPlanetDTO();
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("searchpla", planeta);
	   
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
        
		ResponseEntity<SearchPlanetDTO> responseEntity = new RestTemplate().exchange("https://swapi.co/api/planets/?search={searchpla}", HttpMethod.GET, entity,
				new ParameterizedTypeReference<SearchPlanetDTO>() {
				},uriVariables);
		if (responseEntity != null && responseEntity.hasBody()) {
			search = responseEntity.getBody();
		}
	
		
		return  search.getCount();
	}
}
