package ppi.locadora.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class ClientesList {
	
	
	private List<Cliente> clientesLista;

	
	public List<Cliente> getClientesLista() {
		return clientesLista;
	}

	public void setClientesLista(List<Cliente> clientesLista) {
		this.clientesLista = clientesLista;
	}
	
	
	

}
