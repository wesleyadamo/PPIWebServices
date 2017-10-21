package ppi.locadora.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ppi.locadora.dao.AlugarDao;
import ppi.locadora.dao.CarrosDao;
import ppi.locadora.dao.ClientesDao;
import ppi.locadora.model.Carro;
import ppi.locadora.model.Cliente;

public class AlugarCarro implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String[] dados = new String[5];

		// recupera dos dados da reserva
		dados[0] = req.getParameter("dataretirada");
		dados[1] = req.getParameter("horaretirada");
		dados[2] = req.getParameter("datadevolucao");
		dados[3] = req.getParameter("horadevolucao");
		dados[4] = req.getParameter("combo");

		String carroSelect = req.getParameter("carro");

		Cliente cl2 = (Cliente) req.getSession(false).getAttribute("cliente");

		// adiciona o cliente e armazena seu id
		ClientesDao cliente = new ClientesDao();

		System.out.println(cl2.getNome());

		int idCliente = cliente.adicionaCliente(cl2);
		cl2.setId(idCliente);

		// obtem as informações do carro selecionado para reserva
		Carro carro = new CarrosDao().obterCarro(Integer.parseInt(carroSelect));

		// reserva o carro e armazena o codigo da reserva
		AlugarDao alugar = new AlugarDao();
		String[] resultAluguel = alugar.alugaCarro(Integer.parseInt(carroSelect), idCliente, dados[0], dados[2],
				carro.getTarifaDia());

		req.setAttribute("result", resultAluguel);

		return "confirmacaoReserva.jsp";
	}

}
