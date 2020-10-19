package br.com.maratona.dev.resource;

import java.util.ArrayList;
import java.util.List;

public class InscricaoHelper {
	
	List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	public void init() {
		pessoas.add(new Pessoa("Titico", 1));
		pessoas.add(new Pessoa("Maria", 2));
		pessoas.add(new Pessoa("Heloisa", 3));
	}
	
	public Pessoa findById(List<Pessoa> pessoas, String id) {
		for (Pessoa indice : pessoas) {
			if ( indice.getMatricula().equals(new Integer(id)) ) {
				return indice;
			}
		}
		return null;
	}
	
	
	
}
