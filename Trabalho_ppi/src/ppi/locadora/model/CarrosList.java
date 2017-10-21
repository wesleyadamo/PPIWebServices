package ppi.locadora.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CarrosList {
	
	private List<Carro> carros;

	
	
	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> list) {
		this.carros = list;
	}
	
	
	
	

}
