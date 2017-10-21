package ppi.locadora.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ppi.locadora.dao.ClientesDao;
import ppi.locadora.model.Cliente;
import ppi.locadora.model.ClientesList;

@Path("/list-clientes")
public class ListClientesXJ {

	// Obtem todos os clientes em xml
	@GET
	@Path("/allclientesxml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getAllClientesXml() {

		ClientesList clientes = new ClientesList();

		clientes.setClientesLista(obterTodosClientes());

		if (clientes.getClientesLista().size() > 0)
			return Response.ok(clientes).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).build();
		return Response.ok("Nao ha clientes cadastrados").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
				.build();

	}

	// Obtem todos os clientes em Json
	@GET
	@Path("/allclientesjson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllClientesJson() {

		ClientesList clientes = new ClientesList();

		clientes.setClientesLista(obterTodosClientes());

		if (clientes.getClientesLista().size() > 0)
			return Response.ok(clientes).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();
		return Response.ok("Nao ha clientes cadastrados").header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN)
				.build();

	}

	// Obtem cliente por id em xml
	@GET
	@Path("/{id}/clienteidxml")
	@Produces(MediaType.APPLICATION_XML)
	public Response getClienteIdXml(@PathParam("id") int id) {

		Cliente clientes = obterClienteId(id);

		if (clientes.getCpf() != null)
			return Response.ok(clientes).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).build();
		return Response.ok("Nao ha cliente cadastrado com esse ID")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();

	}

	// Obtem cliente por id em Json
	@GET
	@Path("/{id}/clienteidjson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClienteIdJson(@PathParam("id") int id) {

		Cliente clientes = obterClienteId(id);

		if (clientes.getCpf() != null)
			return Response.ok(clientes).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).build();
		return Response.ok("Nao ha cliente cadastrado com esse ID")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN).build();

	}

	// Acesso ao banco

	// Obtem todos os clientes
	public List<Cliente> obterTodosClientes() {

		ClientesDao clienteDao = new ClientesDao();

		return clienteDao.obterListaClientesCompleta();

	}

	// Obtem cliente por id
	public Cliente obterClienteId(int id) {

		ClientesDao clienteDao = new ClientesDao();

		return clienteDao.obterCliente(id);
	}

}
