package com.invpersona.cliente.core.negocio.bean;


import java.io.Serializable;
import java.util.List;



public class ClienteCollection implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Cliente> listaCliente;
	private PaginacionModel paginacion;
	
	public List<Cliente> getListaCliente() {
		return listaCliente;
	}
	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	public PaginacionModel getPaginacion() {
		return paginacion;
	}
	public void setPaginacion(PaginacionModel paginacion) {
		this.paginacion = paginacion;
	}
	
	

}
