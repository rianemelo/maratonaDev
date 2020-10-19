package br.com.maratona.dev.resource;
//REST: nessa classe estão os nossos endpoints.
//precisamos da dependencia do jersey (REST).
//e precisamos configurar o jersey no WEB-INF (file: web.xml).

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



@Path(value = "/inscricao")
public class InscricaoMaratonaResource {
	
	InscricaoHelper helper = new InscricaoHelper();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) // aqui nos serve a dependência genson, por causa do json.
	@Path("/listar/inscritos")
	public Response matricula() {
		helper.init();
		return Response.status(Status.OK).entity(helper.pessoas).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar/inscrito/{id}")
	public Response buscarPorId(@PathParam("id") String id) {
		helper.init();
		Pessoa pessoaBusca = helper.findById(helper.pessoas, id);
		if ( !pessoaBusca.equals(null) ) {
			return Response.status(Status.OK).entity(pessoaBusca).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cadastrar/inscrito/")
	public Response cadastrar(Pessoa pessoa) {
		helper.init();
		pessoa.setMatricula(helper.pessoas.size() + 1);
		helper.pessoas.add(pessoa);
		return Response.status(Status.CREATED).entity("Inscrito com sucesso!").build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/remover/inscrito/{id}")
	public Response removerPorId(@PathParam("id") String id) {
		helper.init();
		Pessoa pessoaRemove = helper.findById(helper.pessoas, id);
		
		if ( !pessoaRemove.equals(null) ) {
			helper.pessoas.remove((int)pessoaRemove.getMatricula());
			return Response.status(Status.OK).entity("Removido com sucesso!").build();
			//return Response.status(Status.OK).entity(helper.pessoas).build();
		}
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/alterar/inscrito/")
	public Response alterar(Pessoa pessoa) {
		helper.init();
		Pessoa pessoaEdit = helper.findById(helper.pessoas, pessoa.getMatricula().toString());
		if ( !pessoaEdit.equals(null) ) {
			pessoaEdit.setNome(pessoa.getNome()); 
			return Response.status(Status.OK).entity("Alterado com sucesso!").build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}


}
