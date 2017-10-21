package ppi.locadora.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class AlugueisList {

	private List<Aluguel> alugueis;

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

}
