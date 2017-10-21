package ppi.locadora.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ppi.locadora.dao.CarrosDao;
import ppi.locadora.model.Carro;
import ppi.locadora.model.CarrosList;

@Path("/list-carros")
public class ListCarrosXJ {

	// Obtem o carro pelo renavan em xml
	@GET
	@Path("/{renavan}/renavanxml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getXml(@PathParam("renavan") String renavan) {

		Carro c = obterPorRenavan(Long.parseLong(renavan));

		if (c.getAnoFabricacao() != null)
			return Response.ok(c).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).build();

		return Response.ok("Carro não encontrado").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();
	}

	// Obtem o carro pelo renavan em Json
	@GET
	@Path("/{renavan}/renavanjson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJson(@PathParam("renavan") String renavan) {

		Carro c = obterPorRenavan(Long.parseLong(renavan));

		if (c.getAnoFabricacao() != null)
			return Response.ok(c).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();

		return Response.ok("Carro não encontrado").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();

	}

	// Obtem a lista de carro disponivel em xml
	@GET
	@Path("/list/disponivelxml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getListDispXml() {

		// return obterListaCarrosDisponivel();
		CarrosList listCarros = new CarrosList();
		listCarros.setCarros(obterListaCarrosDisponivel());

		if (listCarros.getCarros().size() > 0)
			return Response.ok(listCarros).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).build();

		return Response.ok("Não existe carro disponível").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
				.build();

	}

	// Obtem a lista de carro disponivel em json
	@GET
	@Path("/list/disponiveljson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListDispJson() {

		CarrosList listCarros = new CarrosList();
		listCarros.setCarros(obterListaCarrosDisponivel());

		if (listCarros.getCarros().size() > 0)
			return Response.ok(listCarros).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();

		return Response.ok("Não existe carro disponível").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
				.build();

	}

	// Obtem a lista de carro por tipo em xml
	@GET
	@Path("/{tipo}/tipoxml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getListTipoXml(@PathParam("tipo") String tipo) {

		// return obterListaCarrosTipo(tipo);
		CarrosList listCarros = new CarrosList();
		listCarros.setCarros(obterListaCarrosTipo(tipo));

		if (listCarros.getCarros().size() > 0)
			return Response.ok(listCarros).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).build();

		return Response.ok("Categoria não encontrada!").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();

	}

	// Obtem a lista de carro por tipo em json
	@GET
	@Path("/{tipo}/tipojson")
	@Produces(MediaType.APPLICATION_JSON)
	public /* List<Carro> */ Response getListTipoJson(@PathParam("tipo") String tipo) {

		// return obterListaCarrosTipo(tipo);
		CarrosList listCarros = new CarrosList();
		listCarros.setCarros(obterListaCarrosTipo(tipo));

		if (listCarros.getCarros().size() > 0)
			return Response.ok(listCarros).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();

		return Response.ok("Categoria não encontrada!").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();

		// return obterListaCarrosTipo(tipo);

	}

	// Obtem um carro em xml e edita
	@PUT
	@Path("/editcarro/xml")
	@Consumes(MediaType.APPLICATION_XML)
	public String editCarroXml(Carro carro) {

		boolean ok = editaCarro(carro);

		if (ok)
			return "Carro alterado.";
		else
			return "Algum erro ocorreu";
	}

	// Obtem um carro em json e edita
	@PUT
	@Path("/editcarro/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public String editCarroJson(Carro carro) {

		boolean ok = editaCarro(carro);

		if (ok)
			return "Carro alterado.";
		else
			return "Algum erro ocorreu";
	}

	// Deleta o carro por id em xml
	@DELETE
	@Path("/{renavan}/deletcarroxml")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteCarroXml(@PathParam("renavan") long renavan) {

		Carro carro = deletaCarro(renavan);

		System.out.println(carro.getAnoFabricacao());

		if (carro.getAnoFabricacao() != null)
			return Response.ok(carro).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).build();

		return Response.ok("Carro não encontrado").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();

	}

	// Deleta o carro por id em em json
	@DELETE
	@Path("/{renavan}/deletcarrojson")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteCarroJson(@PathParam("renavan") long renavan) {

		Carro carro = deletaCarro(renavan);

		System.out.println(carro.getAnoFabricacao());

		if (carro.getAnoFabricacao() != null)
			return Response.ok(carro).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();

		return Response.ok("Carro não encontrado").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();

	}

	// Acesso ao Banco

	// Obter o carro por renavan
	public Carro obterPorRenavan(long renavan) {

		CarrosDao carroDao = new CarrosDao();

		Carro carro = carroDao.obterCarro(renavan);

		return carro;

	}

	// Obter a lista Disponivel
	public List<Carro> obterListaCarrosDisponivel() {

		CarrosDao carroDao = new CarrosDao();

		return carroDao.getCarrosDisponiveis();

	}

	// Obter a lista por tipo
	public List<Carro> obterListaCarrosTipo(String tipo) {

		CarrosDao carroDao = new CarrosDao();

		return carroDao.getCarrosTipo(tipo);

	}

	// Edita um carro
	public boolean editaCarro(Carro carro) {

		CarrosDao carroDao = new CarrosDao();

		return carroDao.atualizarCarro(carro);

	}

	// Deletar um carro
	public Carro deletaCarro(long renavan) {

		CarrosDao carroDao = new CarrosDao();
		Carro retorno = carroDao.obterCarro(renavan);

		carroDao.removerCarro(renavan);

		return retorno;
	}

}
