package br.com.maratona.dev.resource;
//REST: nessa classe estão os nossos endpoints.
//precisamos da dependencia do jersey (REST).
//e precisamos configurar o jersey no WEB-INF (file: web.xml).

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(value = "/inscricao")
public class InscricaoMaratonaResource {
	
	@GET
	public String matricula() {
		return "Matricula efetuada com sucesso!";
	}

}
