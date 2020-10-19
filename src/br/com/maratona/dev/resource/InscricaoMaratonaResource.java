package br.com.maratona.dev.resource;
//REST: nessa classe estão os nossos endpoints.
//precisamos da dependencia do jersey (REST).
//e precisamos configurar o jersey no WEB-INF (file: web.xml).

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path(value = "/inscricao")
public class InscricaoMaratonaResource {
	
	List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	public void init() {
		pessoas.add(new Pessoa("Titico", 1));
		pessoas.add(new Pessoa("Maria", 2));
		pessoas.add(new Pessoa("Heloisa", 3));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) // aqui nos serve a dependência genson, por causa do json.
	@Path("/listar/inscritos")
	public List<Pessoa> matricula() {
		init();
		return pessoas;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar/inscrito/{id}")
	public Response buscarPorId(@PathParam("id") String id) {
		init();
		for (Pessoa indice : pessoas) {
			if (indice.getMatricula().equals(new Integer(id))) {
				return Response.status(Status.OK).entity(indice).build();
			}
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cadastrar/inscrito/")
	public Response cadastrar(Pessoa pessoa) {
		init();
		pessoa.setMatricula(pessoas.size() + 1);
		pessoas.add(pessoa);
		//return Response.status(Status.CREATED).entity("Inscrito com sucesso!").build();
		return Response.status(Status.OK).entity(pessoas).build();
		
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/remover/inscrito/{id}")
	public Response removerPorId(@PathParam("id") String id) {
		init();
		Pessoa removePessoa = null;
		for (Pessoa indice : pessoas) {
			if (indice.getMatricula().equals(new Integer(id))) {
				removePessoa = indice;
			}
		}
		if ( pessoas.remove(removePessoa) ) {
			//return Response.status(Status.OK).entity("Removido com sucesso!").build();
			return Response.status(Status.OK).entity(pessoas).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/alterar/inscrito/")
	public Response alterar(Pessoa pessoa) {
		init();
		Pessoa pessoaEdit = null;
		for (Pessoa indice : pessoas) {
			if (indice.getMatricula().equals(pessoa.getMatricula())) {
				pessoaEdit = indice; //nota-se que pessoaEdit e indice apontam para a mesma referência
			}
		}
		if ( !pessoaEdit.equals(null) ) {
			pessoaEdit.setNome(pessoa.getNome()); 
			//return Response.status(Status.OK).entity("Atualizado com sucesso!").build();
			return Response.status(Status.OK).entity(pessoas).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}


}
