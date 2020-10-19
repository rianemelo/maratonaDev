package br.com.maratona.dev.resource;

import javax.inject.Inject;

// **1 CDI. Precisamos de duas novas dependências:
//weld-servlet-core: CDI
//resteasy-cdi: nosso projeto contem cdi e rest. Essa dep comunica ao rest que o se está usando cdi e vice versa. 
//Precisamos de dois files de configuração, pq estamos usando o TOMCAT:
// META-INF_ context.xml: diz para o TOMCAT criar os nossos objetos.
// WEB-INF_ beans.xml: 

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
	
	@Inject //**1 
	InscricaoHelper helper;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listar/inscritos")
	public Response matricula() {
		helper.init();
		return Response.status(Status.OK).entity(helper.getPessoas()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar/inscrito/{id}")
	public Response buscarPorId(@PathParam("id") String id) {
		System.out.println(helper);
		Pessoa pessoaBusca = helper.findPessoa(new Integer(id));
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
		pessoa.setMatricula(helper.getPessoas().size() + 1);
		helper.getPessoas().add(pessoa);
		return Response.status(Status.CREATED).entity("Inscrito com sucesso!").build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/remover/inscrito/{id}")
	public Response removerPorId(@PathParam("id") String id) {
		Pessoa pessoaRemove = helper.findPessoa(new Integer(id));
		
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
		Pessoa pessoaEdit = helper.findPessoa(pessoa.getMatricula());
		if ( !pessoaEdit.equals(null) ) {
			pessoaEdit.setNome(pessoa.getNome()); 
			return Response.status(Status.OK).entity("Alterado com sucesso!").build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}

}
