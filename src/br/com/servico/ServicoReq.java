package br.com.servico;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.dao.ContaDAO;
import br.com.entities.Conta;

@Path("/main")
public class ServicoReq {
	@GET
	@Path("lista-contas")
	@Produces("application/json")
	public List<Conta> teste() {
		return ContaDAO.getInstance().findAll();
	}
	
	@POST
	@Path("criar-conta")
	@Produces("application/json")
	public Conta createCount(Conta conta) {
		ContaDAO.getInstance().persist(conta);
		return new Conta();
	}
	
	@PUT
	@Path("editar-conta")
	@Produces("application/json")
	public Conta editCount(Conta conta) {
		ContaDAO.getInstance().merge(conta);
		return new Conta();
	}
	
}
