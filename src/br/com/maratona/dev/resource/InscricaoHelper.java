package br.com.maratona.dev.resource;

import java.util.ArrayList;
import java.util.List;

public class InscricaoHelper {
	
	List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void init() {
		pessoas.add(new Pessoa("Titico", 1));
		pessoas.add(new Pessoa("Maria", 2));
		pessoas.add(new Pessoa("Heloisa", 3));
	}
	
	public Pessoa findPessoa(Integer id) {
		init();
		for (Pessoa indice : pessoas) {
			if(indice.getMatricula().equals(id)) {
				return indice;
			}
		}
		return null;
	}

}
