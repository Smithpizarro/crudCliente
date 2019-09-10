package com.invpersona.cliente.integration.dto;


import java.io.Serializable;
import java.util.List;



public class ClienteCollectionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<ClienteDTO> listaCliente;
	private PaginacionModel paginacion;
	
	public List<ClienteDTO> getListaCliente() {
		return listaCliente;
	}
	public void setListaCliente(List<ClienteDTO> listaCliente) {
		this.listaCliente = listaCliente;
	}
	public PaginacionModel getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(PaginacionModel paginacion) {
		this.paginacion = paginacion;
	}

	
	


}
