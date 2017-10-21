package ppi.locadora.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ppi.locadora.dao.AlugarDao;
import ppi.locadora.model.AlugueisList;
import ppi.locadora.model.Aluguel;

@Path("/list-alugueis")
public class ListAlugueisXJ {

	// Obtem a lista de alugueis em xml
	@GET
	@Path("/list/allalugueisxml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getListAluXml() {

		AlugueisList alugueis = new AlugueisList();
		alugueis.setAlugueis(obterTodosAlugueis());
		if (alugueis.getAlugueis().size() > 0) {
			return Response.ok(alugueis).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).build();
		}

		return Response.ok("Não há reserva cadastrada").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();

	}

	// Obtem a lista de alugueis em json
	@GET
	@Path("/list/allalugueisjson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListAluJson() {

		AlugueisList alugueis = new AlugueisList();
		alugueis.setAlugueis(obterTodosAlugueis());
		if (alugueis.getAlugueis().size() > 0) {
			return Response.ok(alugueis).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();
		}

		return Response.ok("Não há reserva cadastrada").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();
	}

	// Obtem o aluguel por id em xml
	@GET
	@Path("/{id}/allalugueisxml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAluguelIdXml(@PathParam("id") int id) { // Verificar se pode receber int ou tem que ser String e
																// transformar em int

		Aluguel aluguel = obterAluguelId(id);

		if (aluguel.getCpfCliente() != null)
			return Response.ok(aluguel).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).build();
		return Response.ok("Nao ha aluguel cadastrado nesse ID").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
				.build();

	}

	// Obtem o aluguel por id em json
	@GET
	@Path("/{id}/allalugueisjson")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response getAluguelIdJson(@PathParam("id") int id) {

		Aluguel aluguel = obterAluguelId(id);

		if (aluguel.getCpfCliente() != null)
			return Response.ok(aluguel).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();
		return Response.ok("Nao ha aluguel cadastrado nesse ID").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
				.build();

	}

	// Acesso ao banco

	// Obtem a lista completa de alugueis
	public List<Aluguel> obterTodosAlugueis() {

		AlugarDao alugarDao = new AlugarDao();

		return alugarDao.obterListaAlugueisCompleta();

	}

	// Obtem o aluguel por id
	public Aluguel obterAluguelId(int id) {

		AlugarDao alugarDao = new AlugarDao();

		return alugarDao.obterAluguel(id);

	}

}
